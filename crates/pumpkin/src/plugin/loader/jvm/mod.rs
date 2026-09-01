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
        // Every registered block gets a behaviour -- interactions, ticks and random
        // ticks route through it whether or not the block drops anything. The loot
        // table only decides the drops; skipping the whole block for a missing table
        // left crops without their growth hook.
        let table = find_block_loot_table(&datapacks, &block.name);
        let parsed = table
            .as_deref()
            .and_then(loot::parse_block_loot_table)
            .inspect(|_| wired += 1)
            .unwrap_or_else(|| {
                if table.is_some() {
                    tracing::warn!("{}: its loot table is not valid JSON", block.name);
                }
                without_table += 1;
                loot::ParsedTable::default()
            });
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

        let behaviour: &'static dyn crate::block::BlockBehaviour =
            Box::leak(Box::new(JvmBlockBehaviour {
                inner: crate::plugin::api::block_behaviour::PluginBlockBehaviour::new(
                    block.first_state,
                    drops,
                ),
                block_name: block.name.clone(),
                block_id: block.block_id,
            }));
        server
            .block_registry
            .set_plugin_block(block.block_id, behaviour);
    }

    install_jvm_tick_hook();
    install_jvm_extract_hook();

    // Tell the bridge where the extracted mod datapacks live: the recipe manager it
    // serves to mod machines decodes their recipe JSON straight from there.
    if let Some(vm) = vm::current() {
        let dir = server
            .basic_config
            .get_world_path()
            .join("datapacks")
            .to_string_lossy()
            .into_owned();
        let _ = vm.call(move |env| {
            let dir = env
                .new_string(&dir)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.call_static_method(
                "dev/pumpkin/bridge/PumpkinRecipes",
                "setDatapacksDir",
                "(Ljava/lang/String;)V",
                &[(&dir).into()],
            )
            .map(|_| ())
            .map_err(|err| VmError::Java(err.to_string()))
        });
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

/// Block behaviour that routes right-clicks into the mod's own Java.
///
/// Wraps the generic [`PluginBlockBehaviour`] -- placement, drops and ticks stay with it
/// -- and sends `use` through the JVM bridge (`PumpkinInteractions.useBlockOn`), which
/// invokes the block's real `useItemOn`. The bridge's reply is a flat string: the result
/// kind, the hand stack if the mod replaced it, and any drops, each item named by its
/// registered id.
///
/// [`PluginBlockBehaviour`]: crate::plugin::api::block_behaviour::PluginBlockBehaviour
struct JvmBlockBehaviour {
    inner: crate::plugin::api::block_behaviour::PluginBlockBehaviour,
    block_name: String,
    /// The block's id, for resolving its block entity type at interaction time. Wire time
    /// is too early: the behaviour is installed while the registries are still staged,
    /// and the block-to-entity link only becomes readable once they freeze.
    block_id: pumpkin_data::BlockId,
}

impl JvmBlockBehaviour {
    fn bridge(
        &self,
        world: &Arc<crate::world::World>,
        position: &pumpkin_util::math::position::BlockPos,
        held: Option<&mut pumpkin_data::item_stack::ItemStack>,
        player: Option<&Arc<crate::entity::player::Player>>,
    ) -> crate::block::registry::BlockActionResult {
        use crate::block::registry::BlockActionResult;

        let Some(vm) = vm::current() else {
            return BlockActionResult::Pass;
        };
        let block_name = self.block_name.clone();
        let entity_type =
            pumpkin_data::dynamic::block_entity_type_for_block(self.block_id.as_u16())
                .and_then(pumpkin_data::dynamic::block_entity_type_name)
                .unwrap_or("")
                .to_string();
        let (held_id, held_count) = held.as_ref().filter(|stack| stack.item_count > 0).map_or(
            (String::new(), 0),
            |stack| {
                // Vanilla registry keys are bare; recipes and mods speak namespaced ids.
                let key = stack.item.registry_key;
                let id = if key.contains(':') {
                    key.to_string()
                } else {
                    format!("minecraft:{key}")
                };
                (id, i32::from(stack.item_count))
            },
        );
        let held_display = format!("{held_id}:{held_count}");
        // The blob the last interaction (or the last run) saved, handed to the mod's
        // entity when it is rebuilt. Opaque here: only the bridge's ValueIO reads it.
        let saved_data = read_mod_data(world, position);
        let (x, y, z) = (position.0.x, position.0.y, position.0.z);
        let has_signal =
            crate::block::blocks::redstone::block_receives_redstone_power(world, position);
        let player_uuid =
            player.map_or_else(String::new, |player| player.gameprofile.id.to_string());
        let sneaking = player.is_some_and(|player| {
            player
                .living_entity
                .entity
                .sneaking
                .load(std::sync::atomic::Ordering::Relaxed)
        });

        let reply = vm.call(move |env| {
            let block = env
                .new_string(&block_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let entity = env
                .new_string(&entity_type)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let held = env
                .new_string(&held_id)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let saved = env
                .new_string(&saved_data)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let uuid_string = env
                .new_string(&player_uuid)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinInteractions",
                "useBlockOn",
                "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;ILjava/lang/String;ZZLjava/lang/String;)Ljava/lang/String;",
                &[
                    (&block).into(),
                    (&entity).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&held).into(),
                    held_count.into(),
                    (&saved).into(),
                    has_signal.into(),
                    sneaking.into(),
                    (&uuid_string).into(),
                ],
            );
            if env.exception_check().unwrap_or(false) {
                let _ = env.exception_describe();
                let _ = env.exception_clear();
                return Err(VmError::Java("useBlockOn threw".into()));
            }
            let object = returned
                .and_then(jni::objects::JValueGen::l)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.get_string(&jni::objects::JString::from(object))
                .map(Into::into)
                .map_err(|err| VmError::Java(err.to_string()))
        });

        let reply: String = match reply {
            Ok(reply) => reply,
            Err(err) => {
                tracing::warn!("{}: interaction stopped in the mod: {err}", self.block_name);
                return BlockActionResult::Pass;
            }
        };
        if !reply.starts_with("PASS") {
            tracing::info!(
                "{} at {position:?}: hand was {held_display}, mod answered {reply}",
                self.block_name
            );
        }
        apply_interaction_reply(&reply, world, position, held, player)
    }
}

impl JvmBlockBehaviour {
    /// Honours a reply's `SCHEDULE=<ticks>` by scheduling a real block tick.
    #[allow(clippy::unused_self)] // sits beside the other reply appliers, which do use it
    fn apply_schedule(
        &self,
        reply: &str,
        world: &Arc<crate::world::World>,
        position: &pumpkin_util::math::position::BlockPos,
    ) {
        for part in reply.split(';') {
            if let Some(delay) = part.strip_prefix("SCHEDULE=")
                && let Ok(delay) = delay.parse::<u32>()
            {
                // The scheduler's delay is a u8; a mod asking for longer (a growth
                // accelerator's ~200-tick cooldown fits, but a config could not) gets
                // the longest wait the server has rather than a silent wrap.
                #[allow(clippy::cast_possible_truncation)]
                let delay = delay.min(u32::from(u8::MAX)) as u8;
                world.schedule_block_tick(
                    pumpkin_data::Block::from_state_id(world.get_block_state_id(position)),
                    *position,
                    delay,
                    pumpkin_world::tick::TickPriority::Normal,
                );
            }
        }
    }

    /// Applies a reply's `WRITES=x,y,z:prop=v,prop=v&...` -- states the mod set at
    /// arbitrary positions (an accelerator growing the crop above itself).
    fn apply_writes(&self, reply: &str, world: &Arc<crate::world::World>) {
        for part in reply.split(';') {
            let Some(writes) = part.strip_prefix("WRITES=") else {
                continue;
            };
            for write in writes.split('&').filter(|write| !write.is_empty()) {
                let Some((pos, values)) = write.split_once(':') else {
                    continue;
                };
                let mut coords = pos.split(',').filter_map(|c| c.parse::<i32>().ok());
                let (Some(x), Some(y), Some(z)) = (coords.next(), coords.next(), coords.next())
                else {
                    continue;
                };
                let target = pumpkin_util::math::position::BlockPos::new(x, y, z);
                let block = world.get_block(&target);
                let values: Vec<(&str, &str)> = values
                    .split(',')
                    .filter_map(|pair| pair.split_once('='))
                    .collect();
                if let Some(new_state) = pumpkin_data::dynamic::block_state_for(block.id, &values) {
                    tracing::info!(
                        "{}: scheduled tick set {} to {values:?}",
                        self.block_name,
                        block.name
                    );
                    world.set_block_state(
                        &target,
                        new_state,
                        crate::world::BlockFlags::NOTIFY_LISTENERS,
                    );
                } else {
                    tracing::warn!(
                        "{}: a scheduled tick wrote {values:?} to {}, which has no such state",
                        self.block_name,
                        block.name
                    );
                }
            }
        }
    }

    /// One bonemeal question over the bridge: `valid`, `success`, or `perform`.
    ///
    /// Carries the same state-and-neighborhood context as a random tick; the mod's
    /// `BonemealableBlock` methods read the crop's age and the ground under it.
    fn bonemeal_bridge(
        &self,
        world: &Arc<crate::world::World>,
        position: &pumpkin_util::math::position::BlockPos,
        state_id: pumpkin_data::BlockStateId,
        mode: &'static str,
    ) -> Option<String> {
        let vm = vm::current()?;
        let (x, y, z) = (position.0.x, position.0.y, position.0.z);
        let state_spec = pumpkin_data::dynamic::block_state_values(self.block_id, state_id)
            .map(|values| join_state_values(&values))
            .unwrap_or_default();
        let neighborhood = tick_neighborhood(world, position);
        let block_name = self.block_name.clone();
        let reply = vm.call(move |env| {
            let block = env
                .new_string(&block_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let state = env
                .new_string(&state_spec)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let snapshot = env
                .new_string(&neighborhood)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let mode = env
                .new_string(mode)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinBonemeal",
                "apply",
                "(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                &[
                    (&block).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&state).into(),
                    (&snapshot).into(),
                    (&mode).into(),
                ],
            );
            if env.exception_check().unwrap_or(false) {
                let _ = env.exception_describe();
                let _ = env.exception_clear();
                return Err(VmError::Java("bonemeal threw".into()));
            }
            let object = returned
                .and_then(jni::objects::JValueGen::l)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.get_string(&jni::objects::JString::from(object))
                .map(Into::into)
                .map_err(|err| VmError::Java(err.to_string()))
        });
        let reply: Result<String, VmError> = reply;
        match reply {
            Ok(reply) => Some(reply),
            Err(err) => {
                tracing::warn!("{}: bonemeal stopped in the mod: {err}", self.block_name);
                None
            }
        }
    }

    /// Applies a `TICKED;STATE=age=3;SOUNDS=...` reply: resolves the named values to the
    /// block's own state and writes it, so the growth the mod decided lands in the world.
    fn apply_random_tick_reply(
        &self,
        reply: &str,
        world: &Arc<crate::world::World>,
        position: &pumpkin_util::math::position::BlockPos,
    ) {
        for part in reply.split(';') {
            if let Some(spec) = part.strip_prefix("STATE=") {
                if spec == "unchanged" || spec.is_empty() {
                    continue;
                }
                let values: Vec<(&str, &str)> = spec
                    .split(',')
                    .filter_map(|pair| pair.split_once('='))
                    .collect();
                if let Some(new_state) =
                    pumpkin_data::dynamic::block_state_for(self.block_id, &values)
                {
                    world.set_block_state(
                        position,
                        new_state,
                        crate::world::BlockFlags::NOTIFY_LISTENERS,
                    );
                } else {
                    tracing::warn!(
                        "{}: the mod grew into state {spec}, which this block does not have",
                        self.block_name
                    );
                }
            } else if let Some(sounds) = part.strip_prefix("SOUNDS=") {
                for sound in sounds.split(',').filter(|sound| !sound.is_empty()) {
                    play_mod_sound(world, sound);
                }
            }
        }
    }
}

/// `name=value` comma-joined -- the spelling the bridge's state parser reads.
fn join_state_values(values: &[(&str, &str)]) -> String {
    values
        .iter()
        .map(|(name, value)| format!("{name}={value}"))
        .collect::<Vec<_>>()
        .join(",")
}

/// The states a scheduled tick's column scan reads: a 3x3 slab from the ticked block up
/// eighteen -- enough for every growth accelerator range the mod ships, and any read
/// beyond it still fails loudly in the level stand-in.
fn column_neighborhood(
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
) -> String {
    use std::fmt::Write;

    let mut out = String::new();
    for dy in 0..=18 {
        for dx in -1..=1 {
            for dz in -1..=1 {
                if dx == 0 && dy == 0 && dz == 0 {
                    continue;
                }
                let (x, y, z) = (position.0.x + dx, position.0.y + dy, position.0.z + dz);
                let neighbor = pumpkin_util::math::position::BlockPos::new(x, y, z);
                let (block, state) = world.get_block_and_state(&neighbor);
                let props = block
                    .properties(state.id)
                    .map(|p| {
                        p.to_props()
                            .into_iter()
                            .map(|(name, value)| format!("{name}={value}"))
                            .collect::<Vec<_>>()
                            .join(",")
                    })
                    .or_else(|| {
                        pumpkin_data::dynamic::block_state_values(block.id, state.id)
                            .map(|values| join_state_values(&values))
                    })
                    .unwrap_or_default();
                if !out.is_empty() {
                    out.push(';');
                }
                let _ = write!(out, "{x},{y},{z}={}", block.name);
                if !props.is_empty() {
                    let _ = write!(out, "|{props}");
                }
            }
        }
    }
    out
}

/// Light along the scanned column, measured per position -- the crop an accelerator
/// reaches keeps its own daylight gate.
fn column_brightness(
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
) -> String {
    use std::fmt::Write;

    let mut out = String::new();
    for dy in 0..=18 {
        let (x, y, z) = (position.0.x, position.0.y + dy, position.0.z);
        let level = world
            .get_max_local_raw_brightness(&pumpkin_util::math::position::BlockPos::new(x, y, z));
        if !out.is_empty() {
            out.push(';');
        }
        let _ = write!(out, "{x},{y},{z}:{level}");
    }
    out
}

/// The states around a ticked position that growth logic reads: the 3x3 soil square
/// below, the 3x3 same-level ring, and the position two below (a crux, for crops that
/// need one). `x,y,z=id|prop=v,prop=v` semicolon-joined.
fn tick_neighborhood(
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
) -> String {
    use std::fmt::Write;

    let mut out = String::new();
    let mut offsets: Vec<(i32, i32, i32)> = Vec::with_capacity(19);
    for dx in -1..=1 {
        for dz in -1..=1 {
            offsets.push((dx, -1, dz));
            if dx != 0 || dz != 0 {
                offsets.push((dx, 0, dz));
            }
        }
    }
    offsets.push((0, -2, 0));

    for (dx, dy, dz) in offsets {
        let (x, y, z) = (position.0.x + dx, position.0.y + dy, position.0.z + dz);
        let neighbor = pumpkin_util::math::position::BlockPos::new(x, y, z);
        let (block, state) = world.get_block_and_state(&neighbor);
        let props = block
            .properties(state.id)
            .map(|p| {
                p.to_props()
                    .into_iter()
                    .map(|(name, value)| format!("{name}={value}"))
                    .collect::<Vec<_>>()
                    .join(",")
            })
            .or_else(|| {
                pumpkin_data::dynamic::block_state_values(block.id, state.id)
                    .map(|values| join_state_values(&values))
            })
            .unwrap_or_default();
        if !out.is_empty() {
            out.push(';');
        }
        let _ = write!(out, "{x},{y},{z}={}", block.name);
        if !props.is_empty() {
            let _ = write!(out, "|{props}");
        }
    }
    out
}

/// Applies the bridge's reply -- hand stack change and drops -- and maps its result.
fn apply_interaction_reply(
    reply: &str,
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
    held: Option<&mut pumpkin_data::item_stack::ItemStack>,
    player: Option<&Arc<crate::entity::player::Player>>,
) -> crate::block::registry::BlockActionResult {
    use crate::block::registry::BlockActionResult;
    use pumpkin_data::item_stack::ItemStack;

    let mut result = BlockActionResult::Pass;
    let mut held = held;
    for part in reply.split(';') {
        if let Some(spec) = part.strip_prefix("HELD=") {
            if spec == "unchanged" {
                continue;
            }
            if let Some(held) = held.as_deref_mut() {
                *held = parse_stack(spec).unwrap_or(ItemStack::EMPTY.clone());
            }
        } else if let Some(spec) = part.strip_prefix("MENU=") {
            if !spec.is_empty()
                && let Some(player) = player
            {
                open_jvm_menu(spec, player);
            }
        } else if let Some(spec) = part.strip_prefix("SOUNDS=") {
            for sound in spec.split(',').filter(|sound| !sound.is_empty()) {
                play_mod_sound(world, sound);
            }
        } else if let Some(spec) = part.strip_prefix("DATA=") {
            // An empty DATA means "nothing to say" -- no entity, or an unchanged one --
            // never "erase what was stored": a truly emptied machine serialises to a
            // non-empty blob describing empty slots.
            if !spec.is_empty() {
                write_mod_data(world, position, spec);
            }
        } else if let Some(spec) = part.strip_prefix("EJECT=") {
            // The mod pushed items into what it saw as an adjacent container; land them
            // in the real one, first-fit, and drop what genuinely does not fit.
            for entry in spec.split(',').filter(|entry| !entry.is_empty()) {
                let Some((coords, stack_spec)) = entry.split_once('|') else {
                    continue;
                };
                let parts: Vec<i32> = coords.split('/').filter_map(|c| c.parse().ok()).collect();
                let Some(stack) = parse_stack(stack_spec) else {
                    continue;
                };
                if parts.len() != 3 || stack.is_empty() {
                    continue;
                }
                let target = pumpkin_util::math::position::BlockPos(
                    pumpkin_util::math::vector3::Vector3::new(parts[0], parts[1], parts[2]),
                );
                let mut remaining = stack;
                if let Some(entity) = world.get_block_entity(&target)
                    && let Some(container) = entity.get_inventory()
                {
                    for slot in 0..container.size() {
                        if remaining.is_empty() {
                            break;
                        }
                        let existing = container.get_stack(slot);
                        if existing.is_empty() {
                            container.set_stack(slot, remaining.clone());
                            remaining = ItemStack::EMPTY.clone();
                        } else if existing.item.id == remaining.item.id {
                            let room = existing.get_max_stack_size() - existing.item_count;
                            if room > 0 {
                                let moved = room.min(remaining.item_count);
                                let mut grown = existing.clone();
                                grown.item_count += moved;
                                container.set_stack(slot, grown);
                                remaining.item_count -= moved;
                                if remaining.item_count == 0 {
                                    remaining = ItemStack::EMPTY.clone();
                                }
                            }
                        }
                    }
                    container.mark_dirty();
                }
                if !remaining.is_empty() {
                    world.drop_stack(&target, remaining);
                }
            }
        } else if let Some(spec) = part.strip_prefix("DROPS=") {
            for drop in spec.split(',').filter(|drop| !drop.is_empty()) {
                if let Some(stack) = parse_stack(drop) {
                    world.drop_stack(position, stack);
                }
            }
        } else {
            result = match part {
                "SUCCESS" => BlockActionResult::Success,
                "FAIL" => BlockActionResult::Fail,
                _ => BlockActionResult::Pass,
            };
        }
    }
    result
}

/// Which neighbors of a position hold a vanilla inventory (chest, hopper, barrel): the
/// mod's ejector may only see a container where one really is. Bit order matches
/// Direction ordinals: down, up, north, south, west, east.
fn vanilla_container_mask(
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
) -> i32 {
    let offsets: [(i32, i32, i32); 6] = [
        (0, -1, 0),
        (0, 1, 0),
        (0, 0, -1),
        (0, 0, 1),
        (-1, 0, 0),
        (1, 0, 0),
    ];
    let mut mask = 0i32;
    for (bit, (dx, dy, dz)) in offsets.iter().enumerate() {
        let neighbor =
            pumpkin_util::math::position::BlockPos(pumpkin_util::math::vector3::Vector3::new(
                position.0.x + dx,
                position.0.y + dy,
                position.0.z + dz,
            ));
        if world
            .get_block_entity(&neighbor)
            .and_then(crate::block::entities::BlockEntity::get_inventory)
            .is_some()
        {
            mask |= 1 << bit;
        }
    }
    mask
}

/// Routes plugin block entity ticks into the mod's own `BlockEntityTicker`.
///
/// Installed once, after the first mod finishes loading. Every tick of every mod block
/// entity crosses into the VM; block types whose blocks answer "no ticker" are remembered
/// on the Java side and return immediately, so a pedestal costs one string comparison per
/// tick rather than a reflective lookup. A tick that stops inside the mod is said once
/// per block type -- a ticking machine can fail twenty times a second, and a log that
/// repeats that fast says less than one line that names the key.
/// Routes hopper pulls on plugin block entities into the mod's own item handler.
fn install_jvm_extract_hook() {
    crate::block::entities::plugin::install_extract_hook(Box::new(|entity, world| {
        let vm = vm::current()?;
        let position = entity.position;
        let block = world.get_block(&position);
        let warn_name = block.name;
        let block_name = block.name.to_string();
        let type_name = entity.id.to_string();
        let saved = {
            let data = entity
                .data
                .read()
                .unwrap_or_else(std::sync::PoisonError::into_inner);
            data.get_string(MOD_DATA_KEY).unwrap_or("").to_string()
        };
        let (x, y, z) = (position.0.x, position.0.y, position.0.z);
        let reply: Result<String, VmError> = vm.call(move |env| {
            let block = env
                .new_string(&block_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let entity_type = env
                .new_string(&type_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let saved = env
                .new_string(&saved)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinInteractions",
                "extractBlock",
                "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;)Ljava/lang/String;",
                &[
                    (&block).into(),
                    (&entity_type).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&saved).into(),
                ],
            );
            if env.exception_check().unwrap_or(false) {
                let _ = env.exception_describe();
                let _ = env.exception_clear();
                return Err(VmError::Java("extractBlock threw".into()));
            }
            let object = returned
                .and_then(jni::objects::JValueGen::l)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.get_string(&jni::objects::JString::from(object))
                .map(Into::into)
                .map_err(|err| VmError::Java(err.to_string()))
        });
        let reply = match reply {
            Ok(reply) => reply,
            Err(err) => {
                tracing::warn!("{warn_name}: hopper pull stopped in the mod: {err}");
                return None;
            }
        };
        if !reply.starts_with("EXTRACTED") {
            return None;
        }
        let mut extracted = None;
        for part in reply.split(';') {
            if let Some(spec) = part.strip_prefix("ITEM=") {
                extracted = parse_stack(spec);
            } else if let Some(spec) = part.strip_prefix("DATA=")
                && !spec.is_empty()
            {
                write_mod_data(world, &position, spec);
            }
        }
        extracted
    }));
}

fn install_jvm_tick_hook() {
    static WARNED: std::sync::Mutex<Option<std::collections::HashSet<String>>> =
        std::sync::Mutex::new(None);

    crate::block::entities::plugin::install_tick_hook(Box::new(|entity, world| {
        let Some(vm) = vm::current() else {
            return;
        };
        let position = entity.position;
        let block = world.get_block(&position);
        let block_name = block.name.to_string();
        let type_name = entity.id.to_string();
        let saved = {
            let data = entity
                .data
                .read()
                .unwrap_or_else(std::sync::PoisonError::into_inner);
            data.get_string(MOD_DATA_KEY).unwrap_or("").to_string()
        };
        let (x, y, z) = (position.0.x, position.0.y, position.0.z);
        let has_signal =
            crate::block::blocks::redstone::block_receives_redstone_power(world, &position);
        // The machine's ambient comes from the real biome; Mekanism's heat maths reads
        // it through the stand-in level. 63 is the overworld sea level, the same value
        // the stand-in level answers.
        let biome_temperature = f64::from(world.get_biome(&position).weather.compute_temperature(
            f64::from(position.0.x),
            position.0.y,
            f64::from(position.0.z),
            63,
        ));
        let container_mask = vanilla_container_mask(world, &position);

        let reply = vm.call(move |env| {
            let block = env
                .new_string(&block_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let entity_type = env
                .new_string(&type_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let saved = env
                .new_string(&saved)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinInteractions",
                "tickBlock",
                "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;ZDI)Ljava/lang/String;",
                &[
                    (&block).into(),
                    (&entity_type).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&saved).into(),
                    has_signal.into(),
                    biome_temperature.into(),
                    container_mask.into(),
                ],
            );
            if env.exception_check().unwrap_or(false) {
                let _ = env.exception_describe();
                let _ = env.exception_clear();
                return Err(VmError::Java("tickBlock threw".into()));
            }
            let object = returned
                .and_then(jni::objects::JValueGen::l)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.get_string(&jni::objects::JString::from(object))
                .map(Into::into)
                .map_err(|err| VmError::Java(err.to_string()))
        });

        let block_name = block.name;
        match reply {
            Ok(reply) => {
                let reply: String = reply;
                if reply.starts_with("TICKED") {
                    let mut warned = WARNED
                        .lock()
                        .unwrap_or_else(std::sync::PoisonError::into_inner);
                    if warned
                        .get_or_insert_with(std::collections::HashSet::new)
                        .insert(format!("ticked:{block_name}"))
                    {
                        tracing::info!("{block_name}: its mod ticker is running");
                    }
                    drop(warned);
                    apply_interaction_reply(&reply, world, &position, None, None);
                }
            }
            Err(err) => {
                let mut warned = WARNED
                    .lock()
                    .unwrap_or_else(std::sync::PoisonError::into_inner);
                if warned
                    .get_or_insert_with(std::collections::HashSet::new)
                    .insert(block_name.to_string())
                {
                    tracing::warn!("{block_name}: its ticker stopped in the mod: {err}");
                }
            }
        }
    }));
}

/// Routes a container click on a JVM window into the mod's own menu.
///
/// Returns true when the click belonged to a JVM menu (window id 100 and up) and was
/// handled -- the caller must not run its own screen-handler logic for it. The player's
/// main inventory rides along so the stand-in the menu holds player slots over answers
/// with real contents, and the reply writes back everything the click changed: menu
/// slots, carried stack, player inventory and the machine's save blob.
pub fn handle_menu_click(
    player: &Arc<crate::entity::player::Player>,
    window_id: i32,
    slot: i16,
    button: i8,
    mode: i32,
    world: &Arc<crate::world::World>,
) -> bool {
    if window_id < 100 {
        return false;
    }
    let Some(vm) = vm::current() else {
        return false;
    };

    let inventory = player.inventory();
    let player_inv = player_inventory_csv(inventory);

    let reply = vm.call(move |env| {
        let inv = env
            .new_string(&player_inv)
            .map_err(|err| VmError::Java(err.to_string()))?;
        let returned = env.call_static_method(
            "dev/pumpkin/bridge/PumpkinMenus",
            "click",
            "(IIIILjava/lang/String;)Ljava/lang/String;",
            &[
                window_id.into(),
                i32::from(slot).into(),
                i32::from(button).into(),
                mode.into(),
                (&inv).into(),
            ],
        );
        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java("menu click threw".into()));
        }
        let object = returned
            .and_then(jni::objects::JValueGen::l)
            .map_err(|err| VmError::Java(err.to_string()))?;
        env.get_string(&jni::objects::JString::from(object))
            .map(Into::into)
            .map_err(|err| VmError::Java(err.to_string()))
    });
    let reply: String = match reply {
        Ok(reply) => reply,
        Err(err) => {
            tracing::warn!("menu click stopped in the mod: {err}");
            return true;
        }
    };
    tracing::debug!("menu click {window_id}/{slot}/{mode}: {reply}");
    if reply == "GONE" {
        return true;
    }

    apply_menu_click_reply(&reply, player, world, inventory, window_id);
    true
}

/// The player's main 36 slots as `slot:id:count` entries, comma-joined.
fn player_inventory_csv(
    inventory: &Arc<pumpkin_inventory::player::player_inventory::PlayerInventory>,
) -> String {
    use std::fmt::Write;

    let mut player_inv = String::new();
    for index in 0..36usize {
        let stack = pumpkin_world::inventory::Inventory::get_stack(&**inventory, index);
        if stack.item_count == 0 {
            continue;
        }
        let key = stack.item.registry_key;
        let id = if key.contains(':') {
            key.to_string()
        } else {
            format!("minecraft:{key}")
        };
        if !player_inv.is_empty() {
            player_inv.push(',');
        }
        let _ = write!(player_inv, "{index}:{id}:{}", stack.item_count);
    }
    player_inv
}

/// Applies a click reply: menu slots and carried to the client, player inventory and the
/// machine's save blob to the server.
fn apply_menu_click_reply(
    reply: &str,
    player: &Arc<crate::entity::player::Player>,
    world: &Arc<crate::world::World>,
    inventory: &Arc<pumpkin_inventory::player::player_inventory::PlayerInventory>,
    window_id: i32,
) {
    use pumpkin_protocol::codec::item_stack_seralizer::ItemStackSerializer;

    let mut slots: Vec<pumpkin_data::item_stack::ItemStack> = Vec::new();
    let mut carried = pumpkin_data::item_stack::ItemStack::EMPTY.clone();
    let mut pos: Option<pumpkin_util::math::position::BlockPos> = None;
    for part in reply.split(';') {
        if let Some(spec) = part.strip_prefix("SLOTS=") {
            slots = spec
                .split(',')
                .filter(|entry| !entry.is_empty())
                .map(|entry| {
                    parse_stack(entry)
                        .unwrap_or_else(|| pumpkin_data::item_stack::ItemStack::EMPTY.clone())
                })
                .collect();
        } else if let Some(spec) = part.strip_prefix("CARRIED=") {
            carried = parse_stack(spec)
                .unwrap_or_else(|| pumpkin_data::item_stack::ItemStack::EMPTY.clone());
        } else if let Some(spec) = part.strip_prefix("PLAYERINV=") {
            for (index, entry) in spec.split(',').enumerate().take(36) {
                let stack = parse_stack(entry)
                    .unwrap_or_else(|| pumpkin_data::item_stack::ItemStack::EMPTY.clone());
                pumpkin_world::inventory::Inventory::set_stack(&**inventory, index, stack.clone());
                player.sync_hand_slot(index, stack);
            }
        } else if let Some(spec) = part.strip_prefix("POS=") {
            let coords: Vec<i32> = spec.split(',').filter_map(|c| c.parse().ok()).collect();
            if coords.len() == 3 {
                pos = Some(pumpkin_util::math::position::BlockPos::new(
                    coords[0], coords[1], coords[2],
                ));
            }
        } else if let Some(spec) = part.strip_prefix("DROPS=") {
            if let Some(pos) = pos.as_ref() {
                for drop in spec.split(',').filter(|drop| !drop.is_empty()) {
                    if let Some(stack) = parse_stack(drop) {
                        world.drop_stack(pos, stack);
                    }
                }
            }
        } else if let Some(spec) = part.strip_prefix("DATA=")
            && !spec.is_empty()
            && let Some(pos) = pos.as_ref()
        {
            write_mod_data(world, pos, spec);
        }
    }

    let serialized: Vec<ItemStackSerializer<'_>> = slots
        .iter()
        .map(|stack| ItemStackSerializer::from(stack.clone()))
        .collect();
    let carried = ItemStackSerializer::from(carried);
    let content = pumpkin_protocol::java::client::play::CSetContainerContent::new(
        window_id.into(),
        0.into(),
        &serialized,
        &carried,
    );
    let crate::net::ClientPlatform::Java(java) = player.client.as_ref() else {
        return;
    };
    if let Ok(data) = java.serialize_packet(&content) {
        java.try_enqueue_packet(data);
    }
}

/// Forgets a closed JVM menu window. Returns true when the id was one of ours.
#[must_use]
pub fn handle_menu_close(window_id: i32) -> bool {
    if window_id < 100 {
        return false;
    }
    let Some(vm) = vm::current() else {
        return true;
    };
    let _ = vm.call(move |env| {
        env.call_static_method(
            "dev/pumpkin/bridge/PumpkinMenus",
            "close",
            "(I)V",
            &[window_id.into()],
        )
        .map(|_| ())
        .map_err(|err| VmError::Java(err.to_string()))
    });
    true
}

/// Opens a mod menu on the client: the screen the mod registered, with its contents.
///
/// `spec` is the bridge's `type|windowId|title`. The window id is the Java side's --
/// clicks will come back carrying it, which is how they find their menu again. The
/// content follows in a second bridge call because the menu's slots exist only after the
/// mod's constructor ran, inside the interaction that produced `spec`.
fn open_jvm_menu(spec: &str, player: &Arc<crate::entity::player::Player>) {
    use pumpkin_protocol::codec::item_stack_seralizer::ItemStackSerializer;
    use pumpkin_util::text::TextComponent;

    let mut parts = spec.splitn(3, '|');
    let (Some(type_name), Some(window_id), Some(title)) =
        (parts.next(), parts.next(), parts.next())
    else {
        return;
    };
    let Ok(window_id) = window_id.parse::<i32>() else {
        return;
    };
    let Some(menu_type_id) = pumpkin_data::dynamic::menu_type_id(type_name) else {
        tracing::warn!("{type_name}: the mod opened a menu whose type never registered");
        return;
    };

    let crate::net::ClientPlatform::Java(java) = player.client.as_ref() else {
        return;
    };
    let title = TextComponent::text(title.to_string());
    let open = pumpkin_protocol::java::client::play::COpenScreen::new(
        window_id.into(),
        i32::from(menu_type_id).into(),
        &title,
    );
    if let Ok(data) = java.serialize_packet(&open) {
        java.try_enqueue_packet(data);
    }

    // Second trip: the slot contents, now that the menu exists.
    let Some(vm) = vm::current() else {
        return;
    };
    let contents = vm.call(move |env| {
        let returned = env.call_static_method(
            "dev/pumpkin/bridge/PumpkinMenus",
            "slotContents",
            "(I)Ljava/lang/String;",
            &[window_id.into()],
        );
        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java("slotContents threw".into()));
        }
        let object = returned
            .and_then(jni::objects::JValueGen::l)
            .map_err(|err| VmError::Java(err.to_string()))?;
        env.get_string(&jni::objects::JString::from(object))
            .map(Into::into)
            .map_err(|err| VmError::Java(err.to_string()))
    });
    let Ok(contents) = contents else {
        return;
    };
    let contents: String = contents;
    let stacks: Vec<pumpkin_data::item_stack::ItemStack> = contents
        .split(',')
        .filter(|entry| !entry.is_empty())
        .map(|entry| {
            parse_stack(entry).unwrap_or_else(|| pumpkin_data::item_stack::ItemStack::EMPTY.clone())
        })
        .collect();
    let serialized: Vec<ItemStackSerializer<'_>> = stacks
        .iter()
        .map(|stack| ItemStackSerializer::from(stack.clone()))
        .collect();
    let carried = ItemStackSerializer::from(pumpkin_data::item_stack::ItemStack::EMPTY.clone());
    let content = pumpkin_protocol::java::client::play::CSetContainerContent::new(
        window_id.into(),
        0.into(),
        &serialized,
        &carried,
    );
    if let Ok(data) = java.serialize_packet(&content) {
        java.try_enqueue_packet(data);
    }
}

/// Plays a mod-registered sound to everyone: `name:vol:pitch:x:y:z`.
///
/// Sent by name, not id -- a modded client numbers its sound registry differently from
/// this server's dynamic table, and the name is the one spelling both sides agree on.
fn play_mod_sound(world: &Arc<crate::world::World>, spec: &str) {
    let parts: Vec<&str> = spec.split(':').collect();
    // name is namespaced (two segments) followed by vol, pitch, x, y, z.
    if parts.len() != 7 {
        return;
    }
    let name = format!("{}:{}", parts[0], parts[1]);
    let (Ok(volume), Ok(pitch), Ok(x), Ok(y), Ok(z)) = (
        parts[2].parse::<f32>(),
        parts[3].parse::<f32>(),
        parts[4].parse::<i32>(),
        parts[5].parse::<i32>(),
        parts[6].parse::<i32>(),
    ) else {
        return;
    };
    let seed: f64 = rand::random();
    let packet = pumpkin_protocol::java::client::play::CSoundEffect::new(
        pumpkin_protocol::IdOr::Value(pumpkin_protocol::SoundEvent {
            sound_name: name,
            range: None,
        }),
        pumpkin_data::sound::SoundCategory::Blocks,
        &pumpkin_util::math::vector3::Vector3::new(
            f64::from(x) + 0.5,
            f64::from(y) + 0.5,
            f64::from(z) + 0.5,
        ),
        volume,
        pitch,
        seed,
    );
    world.broadcast_packet_all(&packet);
}

/// The saved mod-entity blob at a position, or empty when there is none.
///
/// Lives under one key in the generic plugin block entity's data bag, which saves and
/// loads with the chunk. The blob is the bridge's own JSON, base64-wrapped so it stays
/// opaque to the reply protocol -- a world saved by real `NeoForge` does not interchange
/// with this, which the bridge's `ValueIO` says out loud too.
use crate::block::entities::plugin_mod_data::MOD_DATA_KEY;

fn read_mod_data(
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
) -> String {
    world
        .get_block_entity(position)
        .and_then(|entity| {
            entity
                .as_any()
                .downcast_ref::<crate::block::entities::plugin::PluginBlockEntity>()
                .and_then(|plugin| {
                    plugin
                        .data
                        .read()
                        .unwrap_or_else(std::sync::PoisonError::into_inner)
                        .get_string(MOD_DATA_KEY)
                        .map(ToString::to_string)
                })
        })
        .unwrap_or_default()
}

fn write_mod_data(
    world: &Arc<crate::world::World>,
    position: &pumpkin_util::math::position::BlockPos,
    blob: &str,
) {
    let Some(entity) = world.get_block_entity(position) else {
        return;
    };
    let Some(plugin) = entity
        .as_any()
        .downcast_ref::<crate::block::entities::plugin::PluginBlockEntity>()
    else {
        return;
    };
    plugin
        .data
        .write()
        .unwrap_or_else(std::sync::PoisonError::into_inner)
        .put_string(MOD_DATA_KEY, blob.to_string());
    pumpkin_world::inventory::Inventory::mark_dirty(plugin);
    // Re-adding broadcasts the entity's client data to the chunk's watchers and refreshes
    // the pending chunk NBT -- how a pedestal's held item reaches a modded client's
    // renderer the moment it changes.
    world.add_block_entity(entity.clone());
}

/// `namespace:path:count` into a real stack; unknown items are dropped loudly.
fn parse_stack(spec: &str) -> Option<pumpkin_data::item_stack::ItemStack> {
    let (id, count) = spec.rsplit_once(':')?;
    let count: u8 = count.parse().ok()?;
    if id == "empty" || count == 0 {
        return Some(pumpkin_data::item_stack::ItemStack::EMPTY.clone());
    }
    let Some(item) = pumpkin_data::item::Item::from_registry_key(id) else {
        tracing::warn!("the mod handed back {id}, which is not a registered item");
        return None;
    };
    Some(pumpkin_data::item_stack::ItemStack::new(count, item))
}

impl crate::block::BlockBehaviour for JvmBlockBehaviour {
    fn player_placed(&self, args: crate::block::PlayerPlacedArgs<'_>) {
        let position = *args.position;
        let state_id = args.state_id;
        let world = args.world.clone();
        self.inner.player_placed(args);

        // A block that schedules work at placement (a growth accelerator asking for its
        // first tick) says so through onPlace; the captured delay becomes a real
        // scheduled tick and the tick handler keeps the chain going from there.
        let Some(vm) = vm::current() else {
            return;
        };
        let (x, y, z) = (position.0.x, position.0.y, position.0.z);
        let state_spec = pumpkin_data::dynamic::block_state_values(self.block_id, state_id)
            .map(|values| join_state_values(&values))
            .unwrap_or_default();
        let block_name = self.block_name.clone();
        let reply = vm.call(move |env| {
            let block = env
                .new_string(&block_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let state = env
                .new_string(&state_spec)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinScheduledTicks",
                "onPlace",
                "(Ljava/lang/String;IIILjava/lang/String;)Ljava/lang/String;",
                &[
                    (&block).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&state).into(),
                ],
            );
            if env.exception_check().unwrap_or(false) {
                let _ = env.exception_describe();
                let _ = env.exception_clear();
                return Err(VmError::Java("onPlace threw".into()));
            }
            let object = returned
                .and_then(jni::objects::JValueGen::l)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.get_string(&jni::objects::JString::from(object))
                .map(Into::into)
                .map_err(|err| VmError::Java(err.to_string()))
        });
        let reply: Result<String, VmError> = reply;
        match reply {
            Ok(reply) => self.apply_schedule(&reply, &world, &position),
            Err(err) => {
                tracing::warn!("{}: onPlace stopped in the mod: {err}", self.block_name);
            }
        }
    }

    fn on_scheduled_tick(&self, args: crate::block::OnScheduledTickArgs<'_>) {
        let Some(vm) = vm::current() else {
            return;
        };
        let position = *args.position;
        let (x, y, z) = (position.0.x, position.0.y, position.0.z);
        let state_id = args.world.get_block_state_id(&position);
        let state_spec = pumpkin_data::dynamic::block_state_values(self.block_id, state_id)
            .map(|values| join_state_values(&values))
            .unwrap_or_default();
        let neighborhood = column_neighborhood(args.world, &position);
        let brightness = column_brightness(args.world, &position);
        let block_name = self.block_name.clone();
        let reply = vm.call(move |env| {
            let block = env
                .new_string(&block_name)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let state = env
                .new_string(&state_spec)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let snapshot = env
                .new_string(&neighborhood)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let light = env
                .new_string(&brightness)
                .map_err(|err| VmError::Java(err.to_string()))?;
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinScheduledTicks",
                "tick",
                "(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                &[
                    (&block).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&state).into(),
                    (&snapshot).into(),
                    (&light).into(),
                ],
            );
            if env.exception_check().unwrap_or(false) {
                let _ = env.exception_describe();
                let _ = env.exception_clear();
                return Err(VmError::Java("scheduled tick threw".into()));
            }
            let object = returned
                .and_then(jni::objects::JValueGen::l)
                .map_err(|err| VmError::Java(err.to_string()))?;
            env.get_string(&jni::objects::JString::from(object))
                .map(Into::into)
                .map_err(|err| VmError::Java(err.to_string()))
        });
        let reply: Result<String, VmError> = reply;
        match reply {
            Ok(reply) if !reply.starts_with("PASS") => {
                self.apply_writes(&reply, args.world);
                self.apply_schedule(&reply, args.world, &position);
                for part in reply.split(';') {
                    if let Some(sounds) = part.strip_prefix("SOUNDS=") {
                        for sound in sounds.split(',').filter(|sound| !sound.is_empty()) {
                            play_mod_sound(args.world, sound);
                        }
                    }
                }
            }
            Ok(_) => {}
            Err(err) => {
                tracing::warn!(
                    "{}: scheduled tick stopped in the mod: {err}",
                    self.block_name
                );
            }
        }
    }

    fn is_valid_bonemeal_target(&self, args: crate::block::BonemealArgs<'_>) -> bool {
        self.bonemeal_bridge(args.world, args.position, args.state_id, "valid")
            .is_some_and(|reply| reply == "TRUE")
    }

    fn is_bonemeal_success(&self, args: crate::block::BonemealArgs<'_>) -> bool {
        self.bonemeal_bridge(args.world, args.position, args.state_id, "success")
            .is_some_and(|reply| reply == "TRUE")
    }

    fn perform_bonemeal(&self, args: crate::block::BonemealArgs<'_>) {
        if let Some(reply) =
            self.bonemeal_bridge(args.world, args.position, args.state_id, "perform")
            && reply.starts_with("TICKED")
        {
            tracing::info!(
                "{} bonemealed at {:?}: {reply}",
                self.block_name,
                args.position
            );
            self.apply_random_tick_reply(&reply, args.world, args.position);
        }
    }

    fn random_tick(&self, args: crate::block::RandomTickArgs<'_>) {
        // Growth and decay live in the mod's own randomTick. The bridge carries the
        // ticked state's values, the light level, and the neighborhood snapshot growth
        // logic reads; the reply names the state the mod wrote back, if any.
        if let Some(vm) = vm::current() {
            let position = *args.position;
            let (x, y, z) = (position.0.x, position.0.y, position.0.z);
            let state_id = args.world.get_block_state_id(&position);
            let state_spec = pumpkin_data::dynamic::block_state_values(self.block_id, state_id)
                .map(|values| join_state_values(&values))
                .unwrap_or_default();
            let brightness = i32::from(args.world.get_max_local_raw_brightness(&position));
            let neighborhood = tick_neighborhood(args.world, &position);
            let block_name = self.block_name.clone();

            let reply = vm.call(move |env| {
                let block = env
                    .new_string(&block_name)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                let state = env
                    .new_string(&state_spec)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                let snapshot = env
                    .new_string(&neighborhood)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                let returned = env.call_static_method(
                    "dev/pumpkin/bridge/PumpkinRandomTicks",
                    "randomTick",
                    "(Ljava/lang/String;IIILjava/lang/String;ILjava/lang/String;)Ljava/lang/String;",
                    &[
                        (&block).into(),
                        x.into(),
                        y.into(),
                        z.into(),
                        (&state).into(),
                        brightness.into(),
                        (&snapshot).into(),
                    ],
                );
                if env.exception_check().unwrap_or(false) {
                    let _ = env.exception_describe();
                    let _ = env.exception_clear();
                    return Err(VmError::Java("randomTick threw".into()));
                }
                let object = returned
                    .and_then(jni::objects::JValueGen::l)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                env.get_string(&jni::objects::JString::from(object))
                    .map(Into::into)
                    .map_err(|err| VmError::Java(err.to_string()))
            });

            let reply: Result<String, VmError> = reply;
            match reply {
                Ok(reply) => self.apply_random_tick_reply(&reply, args.world, &position),
                Err(err) => {
                    tracing::warn!("{}: random tick stopped in the mod: {err}", self.block_name);
                }
            }
        }
        self.inner.random_tick(args);
    }

    fn broken(&self, args: crate::block::BrokenArgs<'_>) {
        // The mod-side entity is positional state; a broken block leaves none behind.
        if let Some(vm) = vm::current() {
            let (x, y, z) = (args.position.0.x, args.position.0.y, args.position.0.z);
            let _ = vm.call(move |env| {
                env.call_static_method(
                    "dev/pumpkin/bridge/PumpkinBlockEntities",
                    "remove",
                    "(III)V",
                    &[x.into(), y.into(), z.into()],
                )
                .map(|_| ())
                .map_err(|err| VmError::Java(err.to_string()))
            });

            // Some blocks compute their drops in code -- a crop reads its age and rolls
            // its chances in getDrops. Ask the mod first; only a PASS (no override) falls
            // back to the drops parsed from loot tables.
            let position = *args.position;
            let state_spec =
                pumpkin_data::dynamic::block_state_values(self.block_id, args.state.id)
                    .map(|values| join_state_values(&values))
                    .unwrap_or_default();
            let neighborhood = tick_neighborhood(args.world, &position);
            let block_name = self.block_name.clone();
            let reply = vm.call(move |env| {
                let block = env
                    .new_string(&block_name)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                let state = env
                    .new_string(&state_spec)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                let snapshot = env
                    .new_string(&neighborhood)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                let returned = env.call_static_method(
                    "dev/pumpkin/bridge/PumpkinBlockDrops",
                    "getDrops",
                    "(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                    &[
                        (&block).into(),
                        x.into(),
                        y.into(),
                        z.into(),
                        (&state).into(),
                        (&snapshot).into(),
                    ],
                );
                if env.exception_check().unwrap_or(false) {
                    let _ = env.exception_describe();
                    let _ = env.exception_clear();
                    return Err(VmError::Java("getDrops threw".into()));
                }
                let object = returned
                    .and_then(jni::objects::JValueGen::l)
                    .map_err(|err| VmError::Java(err.to_string()))?;
                env.get_string(&jni::objects::JString::from(object))
                    .map(Into::into)
                    .map_err(|err| VmError::Java(err.to_string()))
            });
            let reply: Result<String, VmError> = reply;
            match reply {
                Ok(reply) if !reply.starts_with("PASS") => {
                    tracing::info!(
                        "{} broken at {position:?}: mod dropped {reply}",
                        self.block_name
                    );
                    for part in reply.split(';') {
                        if let Some(drops) = part.strip_prefix("DROPS=") {
                            for spec in drops.split(',').filter(|spec| !spec.is_empty()) {
                                if let Some(stack) = parse_stack(spec)
                                    && !stack.is_empty()
                                {
                                    args.world.drop_stack(&position, stack);
                                }
                            }
                        }
                    }
                    // The mod's answer IS the loot resolution; the static model would
                    // double-drop on top of it.
                    return;
                }
                Ok(_) => {}
                Err(err) => {
                    tracing::warn!("{}: getDrops stopped in the mod: {err}", self.block_name);
                }
            }
        }
        self.inner.broken(args);
    }

    fn normal_use(
        &self,
        args: crate::block::NormalUseArgs<'_>,
    ) -> crate::block::registry::BlockActionResult {
        self.bridge(args.world, args.position, None, Some(args.player))
    }

    fn use_with_item(
        &self,
        args: crate::block::UseWithItemArgs<'_>,
    ) -> crate::block::registry::BlockActionResult {
        let result = self.bridge(
            args.world,
            args.position,
            Some(args.item_stack),
            Some(args.player),
        );
        // A consuming result returns to the handler before its hand-sync runs, so a stack
        // the mod replaced has to be written back to the inventory here or the change
        // evaporates -- the pedestal takes a shard and the player keeps three.
        if result.consumes_action() {
            let hand = if *args.equipment_slot
                == pumpkin_data::data_component_impl::EquipmentSlot::MAIN_HAND
            {
                pumpkin_util::Hand::Right
            } else {
                pumpkin_util::Hand::Left
            };
            let inventory = args.player.inventory();
            let slot = if matches!(hand, pumpkin_util::Hand::Right) {
                usize::from(inventory.get_selected_slot())
            } else {
                pumpkin_inventory::player::player_inventory::PlayerInventory::OFF_HAND_SLOT
            };
            inventory.set_stack_in_hand(hand, args.item_stack.clone());
            args.player.sync_hand_slot(slot, args.item_stack.clone());
        }
        result
    }
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
