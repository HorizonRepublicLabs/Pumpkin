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
        wired += 1;
    }

    install_jvm_tick_hook();

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
            let returned = env.call_static_method(
                "dev/pumpkin/bridge/PumpkinInteractions",
                "useBlockOn",
                "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;ILjava/lang/String;Z)Ljava/lang/String;",
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
        } else if let Some(spec) = part.strip_prefix("DATA=") {
            // An empty DATA means "nothing to say" -- no entity, or an unchanged one --
            // never "erase what was stored": a truly emptied machine serialises to a
            // non-empty blob describing empty slots.
            if !spec.is_empty() {
                write_mod_data(world, position, spec);
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

/// Routes plugin block entity ticks into the mod's own `BlockEntityTicker`.
///
/// Installed once, after the first mod finishes loading. Every tick of every mod block
/// entity crosses into the VM; block types whose blocks answer "no ticker" are remembered
/// on the Java side and return immediately, so a pedestal costs one string comparison per
/// tick rather than a reflective lookup. A tick that stops inside the mod is said once
/// per block type -- a ticking machine can fail twenty times a second, and a log that
/// repeats that fast says less than one line that names the key.
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
                "(Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Z)Ljava/lang/String;",
                &[
                    (&block).into(),
                    (&entity_type).into(),
                    x.into(),
                    y.into(),
                    z.into(),
                    (&saved).into(),
                    has_signal.into(),
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
    use pumpkin_protocol::codec::item_stack_seralizer::ItemStackSerializer;

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
        self.inner.player_placed(args);
    }

    fn random_tick(&self, args: crate::block::RandomTickArgs<'_>) {
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
