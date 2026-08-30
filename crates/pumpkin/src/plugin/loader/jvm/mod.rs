//! Hosting a JVM so mods written in Java can run against Pumpkin.

pub mod handles;
pub mod loot;
pub mod natives;
pub mod vm;

use std::{
    any::Any,
    path::{Path, PathBuf},
    sync::Arc,
};

use crate::plugin::{
    Context, Plugin, PluginFuture, PluginMetadata,
    loader::{LoaderError, PluginLoadFuture, PluginLoader, PluginUnloadFuture},
};

use vm::{ModVm, VmError};

/// Calls a `Bootstrap` static method that takes a jar path and returns a `String`,
/// describing and clearing any pending exception before turning it into a [`VmError`].
fn call_bootstrap_string_method(
    vm: &'static ModVm,
    method: &'static str,
    jar: &str,
) -> Result<String, VmError> {
    let jar = jar.to_owned();
    vm.call(move |env| {
        let path = env
            .new_string(&jar)
            .map_err(|err| VmError::Java(err.to_string()))?;

        let returned = env.call_static_method(
            "dev/pumpkin/jvmhost/Bootstrap",
            method,
            "(Ljava/lang/String;)Ljava/lang/String;",
            &[(&path).into()],
        );

        // A Java exception leaves the env in a pending state; describe and clear it, or
        // the next call fails for the wrong reason.
        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java(format!(
                "the mod at {jar} threw during {method}"
            )));
        }

        let object = returned
            .and_then(jni::objects::JValueGen::l)
            .map_err(|err| VmError::Java(err.to_string()))?;

        env.get_string(&jni::objects::JString::from(object))
            .map(Into::into)
            .map_err(|err| VmError::Java(err.to_string()))
    })
}

/// Reads a mod jar's declared id without executing any of its code.
///
/// Backed by `Bootstrap.discoverModId`, which only reads `neoforge.mods.toml` and scans
/// class files for `@Mod` using `Class.forName` with `initialize=false` — enough to name
/// the mod, not enough to run any of it. This is what [`JvmPluginLoader::load`] calls, so
/// that a jar disabled in configuration or denied by a permission check never executes.
///
/// # Errors
/// Returns [`VmError::Java`] if the jar's `neoforge.mods.toml` or `@Mod` class is missing
/// or malformed.
pub fn discover_mod_id(vm: &'static ModVm, jar: &str) -> Result<String, VmError> {
    call_bootstrap_string_method(vm, "discoverModId", jar)
}

/// Brings one mod up inside the VM and returns its declared mod id.
///
/// Backed by `Bootstrap.loadAndRegister`, which constructs the mod's `@Mod` class and
/// fires `RegisterEvent` — this is where the mod's own code actually runs. Called from
/// [`JvmPlugin::on_load`], never from [`JvmPluginLoader::load`].
///
/// # Errors
/// Returns [`VmError::Java`] if discovery, construction or registration threw.
pub fn load_mod(vm: &'static ModVm, jar: &str) -> Result<String, VmError> {
    call_bootstrap_string_method(vm, "loadAndRegister", jar)
}

/// Extracts a mod jar's `data/` tree into a datapack directory.
///
/// Backed by `Bootstrap.extractDatapack`. Returns how many files were extracted; zero
/// means the target was already current (the jar has not changed since last boot).
///
/// # Errors
/// Returns [`VmError::Java`] if the jar cannot be read or a file cannot be written.
pub fn extract_datapack(
    vm: &'static ModVm,
    jar: &str,
    target: &std::path::Path,
) -> Result<i32, VmError> {
    let jar = jar.to_owned();
    let target = target.to_string_lossy().into_owned();
    vm.call(move |env| {
        let jar_string = env
            .new_string(&jar)
            .map_err(|err| VmError::Java(err.to_string()))?;
        let target_string = env
            .new_string(&target)
            .map_err(|err| VmError::Java(err.to_string()))?;

        let returned = env.call_static_method(
            "dev/pumpkin/jvmhost/Bootstrap",
            "extractDatapack",
            "(Ljava/lang/String;Ljava/lang/String;)I",
            &[(&jar_string).into(), (&target_string).into()],
        );

        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java(format!(
                "extracting the datapack from {jar} threw"
            )));
        }

        returned
            .and_then(jni::objects::JValueGen::i)
            .map_err(|err| VmError::Java(err.to_string()))
    })
}

/// Every stubbed shim member reached so far, one per line, sorted.
///
/// The burndown for the next slice: subtract these from the committed manifest and what
/// remains is untouched. A mod stops at its first missing member, so one boot never
/// enumerates everything — but hits accumulate across every mod in the run, and each line is
/// a manifest key, so the result joins mechanically rather than being read by eye.
///
/// # Errors
/// Returns [`VmError::Java`] if the call throws.
pub fn burndown(vm: &'static ModVm) -> Result<String, VmError> {
    vm.call(|env| {
        let returned = env.call_static_method(
            "dev/pumpkin/jvmhost/Bootstrap",
            "burndown",
            "()Ljava/lang/String;",
            &[],
        );

        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java("collecting the burndown threw".to_owned()));
        }

        let object = returned
            .and_then(jni::objects::JValueGen::l)
            .map_err(|err| VmError::Java(err.to_string()))?;

        env.get_string(&jni::objects::JString::from(object))
            .map(Into::into)
            .map_err(|err| VmError::Java(err.to_string()))
    })
}

/// Gives every block Java has registered its drops, read from mod loot tables.
///
/// The registration native records blocks it could not give behaviour to (it runs with
/// no server handle); this drains that list with the server in hand. Each block's loot
/// table is looked up in the extracted `mod_*` datapacks by the vanilla convention --
/// `data/<namespace>/loot_table/blocks/<path>.json` -- parsed by [`loot`], and installed
/// as a [`PluginBlockBehaviour`]. A block without a table simply has no drops, which is
/// also what vanilla means by a missing table.
///
/// [`PluginBlockBehaviour`]: crate::plugin::api::block_behaviour::PluginBlockBehaviour
fn wire_block_drops(server: &Arc<crate::server::Server>) {
    let pending = natives::take_pending_blocks();
    if pending.is_empty() {
        return;
    }

    let datapacks = server.basic_config.get_world_path().join("datapacks");
    let mut wired = 0usize;
    let mut without_table = 0usize;
    let mut skipped_entries = 0usize;

    for block in &pending {
        let Some(table) = find_block_loot_table(&datapacks, &block.name) else {
            without_table += 1;
            continue;
        };
        let Some(parsed) = loot::parse_block_loot_table(&table) else {
            tracing::warn!("{}: its loot table is not valid JSON", block.name);
            without_table += 1;
            continue;
        };
        skipped_entries += parsed.skipped_entries;

        let mut drops = Vec::new();
        for drop in &parsed.drops {
            // Staged lookup, not published: the registries freeze only after every
            // plugin has loaded, and the item this drop names was registered moments
            // ago by the same mod.
            let Some(item_id) =
                pumpkin_data::dynamic::registering_item_id(&drop.item).or_else(|| {
                    pumpkin_data::item::Item::from_registry_key(&drop.item).map(|item| item.id)
                })
            else {
                tracing::warn!(
                    "{}: its loot table drops {}, which is not a registered item",
                    block.name,
                    drop.item
                );
                continue;
            };
            drops.push(crate::plugin::api::block_behaviour::BlockDrop {
                item_id,
                min: drop.min,
                max: drop.max,
                from_state: 0,
                to_state: u32::MAX,
            });
        }

        let behaviour: &'static dyn crate::block::BlockBehaviour = Box::leak(Box::new(
            crate::plugin::api::block_behaviour::PluginBlockBehaviour::new(
                block.first_state,
                drops,
            ),
        ));
        server
            .block_registry
            .set_plugin_block(block.block_id, behaviour);
        wired += 1;
    }

    tracing::info!(
        "wired drops for {wired} mod block(s); {without_table} without a loot table, \
         {skipped_entries} table entr(ies) beyond the drop model (tool conditions, nested \
         tables)"
    );
}

/// A block's loot table JSON, searched across every extracted mod datapack.
fn find_block_loot_table(datapacks: &Path, block_name: &str) -> Option<String> {
    let (namespace, path) = block_name.split_once(':')?;
    let entries = std::fs::read_dir(datapacks).ok()?;
    for entry in entries.flatten() {
        if !entry.file_name().to_string_lossy().starts_with("mod_") {
            continue;
        }
        // Both spellings: 1.21+ uses `loot_table`, older packs `loot_tables`.
        for dir_name in ["loot_table", "loot_tables"] {
            let candidate = entry
                .path()
                .join("data")
                .join(namespace)
                .join(dir_name)
                .join("blocks")
                .join(format!("{path}.json"));
            if let Ok(json) = std::fs::read_to_string(&candidate) {
                return Some(json);
            }
        }
    }
    None
}

/// A loaded Java mod, seen by Pumpkin as an ordinary plugin.
struct JvmPlugin {
    mod_id: String,
    /// Absolute path to the mod's jar, kept so `on_load` can construct it — `load` only
    /// discovered its id.
    jar: String,
    /// The classpath `load` booted the VM with (the shim, FML and host jars), kept so
    /// `on_load` boots with the same classpath rather than an empty one.
    classpath: Vec<PathBuf>,
}

impl Plugin for JvmPlugin {
    fn on_load(&self, context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
        // Construction and registration happen here rather than in `JvmPluginLoader::load`
        // because `load` runs before the operator gets a say: `PluginManager::load_plugins`
        // only checks a plugin's config override (the `enabled == false` skip) and its
        // permissions (`check_permissions_cached`) *after* `load` has already returned
        // (crates/pumpkin/src/plugin/mod.rs, the override skip at line 755 and the
        // permission check at line 829). A jar that is disabled or denied permissions must
        // never have executed by then, so `load` may only discover the mod's id.
        //
        // Deferring to `on_load` still lands before the registries freeze: `on_load` is
        // invoked at crates/pumpkin/src/plugin/mod.rs:643, from inside
        // `PluginManager::load_plugins`, and `pumpkin::init_plugins` only calls
        // `pumpkin_data::dynamic::freeze()` (crates/pumpkin/src/lib.rs:413) after
        // `load_plugins` returns — so there is room to spare.
        let jar = self.jar.clone();
        let classpath = self.classpath.clone();
        let mod_id = self.mod_id.clone();
        Box::pin(async move {
            let vm = vm::boot(&classpath).map_err(|err| err.to_string())?;
            load_mod(vm, &jar).map_err(|err| err.to_string())?;

            // A NeoForge jar carries its recipes and tags as an ordinary datapack under
            // `data/`. Copy that tree out beside the world's other datapacks -- named
            // `mod_<id>`, which the datapack loader treats as implicitly enabled -- and
            // reload, so the mod's craftable recipes exist by the time anyone joins.
            // Reloading per mod is idempotent and boot-time cheap; no player is online
            // to be resent anything yet.
            let target = context
                .server
                .basic_config
                .get_world_path()
                .join("datapacks")
                .join(format!("mod_{mod_id}"));
            let extracted = extract_datapack(vm, &jar, &target).map_err(|err| err.to_string())?;
            if extracted > 0 {
                tracing::info!("{mod_id}: extracted {extracted} datapack files");
            }
            context.server.reload_datapacks(&context.server);

            wire_block_drops(&context.server);
            Ok(())
        })
    }

    fn on_unload(&self, _context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
        let mod_id = self.mod_id.clone();
        Box::pin(async move {
            Err(format!(
                "{mod_id} cannot be unloaded: the JVM outlives the server"
            ))
        })
    }

    fn on_ipc_message(
        &self,
        _sender: &str,
        _message: &[u8],
    ) -> PluginFuture<'_, Result<Vec<u8>, String>> {
        Box::pin(async { Err("Java mods do not accept IPC yet".to_owned()) })
    }
}

/// Loads `.jar` mods by starting a JVM and running them against the shim.
pub struct JvmPluginLoader {
    classpath: Vec<PathBuf>,
}

impl JvmPluginLoader {
    /// A loader that boots its VM with `classpath` — the shim, FML and host jars.
    #[must_use]
    pub const fn new(classpath: Vec<PathBuf>) -> Self {
        Self { classpath }
    }
}

impl PluginLoader for JvmPluginLoader {
    fn load<'a>(&'a self, path: &'a Path) -> PluginLoadFuture<'a> {
        Box::pin(async move {
            let vm = vm::boot(&self.classpath)
                .map_err(|err| LoaderError::InitializationFailed(err.to_string()))?;

            let jar = path.to_string_lossy().into_owned();
            // Discovery only: no code from the jar runs until `JvmPlugin::on_load` decides
            // to construct it. See that method's comment for why the split matters.
            let mod_id = discover_mod_id(vm, &jar)
                .map_err(|err| LoaderError::InitializationFailed(err.to_string()))?;

            let metadata = PluginMetadata {
                name: mod_id.clone(),
                version: "0.0.0".to_owned(),
                authors: Vec::new(),
                description: "A NeoForge mod hosted on the JVM".to_owned(),
                dependencies: Vec::new(),
                permissions: Vec::new(),
            };

            Ok((
                Arc::new(JvmPlugin {
                    mod_id,
                    jar,
                    classpath: self.classpath.clone(),
                }) as Arc<dyn Plugin>,
                metadata,
                Box::new(()) as Box<dyn Any + Send + Sync>,
            ))
        })
    }

    fn can_load(&self, path: &Path) -> bool {
        path.extension()
            .unwrap_or_default()
            .eq_ignore_ascii_case("jar")
    }

    fn unload(&self, _data: Box<dyn Any + Send + Sync>) -> PluginUnloadFuture<'_> {
        Box::pin(async { Ok(()) })
    }

    fn can_unload(&self) -> bool {
        false
    }
}
