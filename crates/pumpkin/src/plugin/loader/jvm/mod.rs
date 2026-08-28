//! Hosting a JVM so mods written in Java can run against Pumpkin.

pub mod handles;
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
    fn on_load(&self, _context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
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
        Box::pin(async move {
            let vm = vm::boot(&classpath).map_err(|err| err.to_string())?;
            load_mod(vm, &jar)
                .map(|_mod_id| ())
                .map_err(|err| err.to_string())
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
