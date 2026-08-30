//! The functions Java calls, bound explicitly with `RegisterNatives`.
//!
//! Pumpkin is the executable rather than a loadable library, so the JVM has no library to
//! resolve names out of. Binding by hand also means an unbound method is a boot failure
//! rather than an `UnsatisfiedLinkError` in the middle of a mod's registration.

use jni::{
    JNIEnv, NativeMethod,
    objects::{JClass, JString},
    sys::{jboolean, jfloat, jint},
};

use crate::plugin::{
    host::registry::{BlockSpec, register_block_spec},
    loader::jvm::vm::VmError,
};
use pumpkin_data::item::Item;
use std::sync::Mutex;

/// A block Java registered, waiting for its behaviour.
///
/// The native that registers a block runs deep inside a mod's registration pass and has
/// no server handle, so it cannot install behaviour itself -- the exact gap the comment
/// in [`register_block_impl`] describes. It records what it knows here instead, and
/// `JvmPlugin::on_load`, which does hold the server, drains the list once the mod is up
/// and wires each block's drops from its extracted loot table.
pub struct PendingJvmBlock {
    /// Namespaced block id, e.g. `examplemod:ruby_block`.
    pub name: String,
    pub block_id: pumpkin_data::BlockId,
    pub first_state: u16,
}

static PENDING_BLOCKS: Mutex<Vec<PendingJvmBlock>> = Mutex::new(Vec::new());

/// Every block registered since the last call. Drained by `on_load`.
pub fn take_pending_blocks() -> Vec<PendingJvmBlock> {
    std::mem::take(
        &mut PENDING_BLOCKS
            .lock()
            .unwrap_or_else(std::sync::PoisonError::into_inner),
    )
}

/// Binds every native on `PumpkinHost`.
///
/// # Errors
/// Returns [`VmError::Java`] if the class is missing or a signature does not match.
pub fn bind(env: &mut JNIEnv) -> Result<(), VmError> {
    let class = env
        .find_class("dev/pumpkin/jvmhost/PumpkinHost")
        .map_err(|err| VmError::Java(format!("PumpkinHost is not on the classpath: {err}")))?;

    env.register_native_methods(
        &class,
        &[
            NativeMethod {
                name: "registerBlock".into(),
                sig: "(Ljava/lang/String;Ljava/lang/String;)I".into(),
                fn_ptr: register_block_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerBlockWithProperties".into(),
                sig: "(Ljava/lang/String;Ljava/lang/String;FFZ)I".into(),
                fn_ptr: register_block_with_properties_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerItem".into(),
                sig: "(Ljava/lang/String;Ljava/lang/String;)I".into(),
                fn_ptr: register_item_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerItemWithProperties".into(),
                sig: "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)I".into(),
                fn_ptr: register_item_with_properties_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerBlockEntityType".into(),
                sig: "(Ljava/lang/String;)I".into(),
                fn_ptr: register_block_entity_type_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerMenuType".into(),
                sig: "(Ljava/lang/String;)I".into(),
                fn_ptr: register_menu_type_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerSoundEvent".into(),
                sig: "(Ljava/lang/String;)I".into(),
                fn_ptr: register_sound_event_native as *mut std::ffi::c_void,
            },
            NativeMethod {
                name: "registerDataComponentType".into(),
                sig: "(Ljava/lang/String;)I".into(),
                fn_ptr: register_data_component_type_native as *mut std::ffi::c_void,
            },
        ],
    )
    .map_err(|err| VmError::Java(format!("Failed to bind PumpkinHost natives: {err}")))
}

/// Reads a Java string, or throws and returns `None`.
fn read_string(env: &mut JNIEnv, value: &JString, what: &str) -> Option<String> {
    match env.get_string(value) {
        Ok(text) => Some(text.into()),
        Err(err) => {
            throw(env, &format!("{what} could not be read: {err}"));
            None
        }
    }
}

/// Throws `IllegalStateException` on the Java side. Failure to throw is unrecoverable and
/// only logged, because there is nowhere left to report it.
fn throw(env: &mut JNIEnv, message: &str) {
    // `throw_new` calls `FindClass` internally, and JNI forbids calling `FindClass` (or
    // almost anything else) while an exception is already pending. A caller can reach here
    // with one pending already — `read_string` throws on a bad string and then the caller
    // still falls through to here in some paths — so describe and clear it first, or
    // `throw_new` fails for an unrelated reason and the real error is lost.
    if env.exception_check().unwrap_or(false) {
        let _ = env.exception_describe();
        let _ = env.exception_clear();
    }

    if let Err(err) = env.throw_new("java/lang/IllegalStateException", message) {
        tracing::error!("Failed to throw into Java: {err}");
    }
}

extern "system" fn register_block_with_properties_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
    template: JString,
    destroy_time: jfloat,
    explosion_resistance: jfloat,
    requires_tool: jboolean,
) -> jint {
    register_block_impl(
        &mut env,
        &id,
        &template,
        // NaN is the Java side's "the mod did not say"; the template's value applies.
        (!destroy_time.is_nan()).then_some(destroy_time),
        (!explosion_resistance.is_nan()).then_some(explosion_resistance),
        Some(requires_tool != 0),
    )
}

extern "system" fn register_block_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
    template: JString,
) -> jint {
    register_block_impl(&mut env, &id, &template, None, None, None)
}

fn register_block_impl(
    env: &mut JNIEnv,
    id: &JString,
    template: &JString,
    hardness: Option<f32>,
    blast_resistance: Option<f32>,
    requires_tool: Option<bool>,
) -> jint {
    let Some(id) = read_string(env, id, "the block id") else {
        return 0;
    };
    let Some(template) = read_string(env, template, "the block template") else {
        return 0;
    };

    let spec = BlockSpec {
        id,
        template,
        hardness,
        blast_resistance,
        luminance: None,
        requires_tool,
        properties: Vec::new(),
        default_state: 0,
        item: None,
        drops: Vec::new(),
        block_entity: None,
    };

    match register_block_spec(&spec) {
        Ok(registered) => {
            // This native has no server handle to install behaviour with, so the block
            // is recorded and `on_load` -- which does hold the server -- wires its drops
            // from the mod's extracted loot table once the mod is up.
            PENDING_BLOCKS
                .lock()
                .unwrap_or_else(std::sync::PoisonError::into_inner)
                .push(PendingJvmBlock {
                    name: spec.id.clone(),
                    block_id: registered.block_id,
                    first_state: registered.first_state,
                });
            jint::from(registered.block_id.as_u16())
        }
        Err(message) => {
            throw(env, &message);
            0
        }
    }
}

extern "system" fn register_item_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
    template: JString,
) -> jint {
    register_item_impl(&mut env, &id, &template, None, None, None)
}

extern "system" fn register_item_with_properties_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
    template: JString,
    max_stack_size: jint,
    max_damage: jint,
    block: JString,
) -> jint {
    // -1 is the Java side's "the mod did not say"; the template's component applies. A
    // null block string means the item places nothing.
    let block_name = if block.is_null() {
        None
    } else {
        match read_string(&mut env, &block, "the placed block id") {
            Some(name) => Some(name),
            None => return 0,
        }
    };
    register_item_impl(
        &mut env,
        &id,
        &template,
        (max_stack_size >= 0).then_some(max_stack_size),
        (max_damage >= 0).then_some(max_damage),
        block_name,
    )
}

fn register_item_impl(
    env: &mut JNIEnv,
    id: &JString,
    template: &JString,
    max_stack_size: Option<jint>,
    max_damage: Option<jint>,
    block: Option<String>,
) -> jint {
    use pumpkin_data::data_component::DataComponent;
    use pumpkin_data::data_component_impl::{
        DataComponentImpl, ItemNameImpl, MaxDamageImpl, MaxStackSizeImpl,
    };

    let Some(id) = read_string(env, id, "the item id") else {
        return 0;
    };
    let Some(template) = read_string(env, template, "the item template") else {
        return 0;
    };

    // Same shape as the WASM host's register_item: the template is an existing item whose
    // definition (components, stack size) the new item copies. Behaviour beyond what a
    // component can carry — right-click handlers, tools acting like tools — is a future
    // slice, exactly like the dropped `first_state`/`drops` on blocks above.
    let Some(template_item) = Item::from_registry_key(&template) else {
        throw(env, &format!("unknown item template {template}"));
        return 0;
    };

    let mut item = template_item.clone();

    // A declared property replaces the template's component wholesale. The vec is leaked
    // for the same reason every dynamic registry entry is: the item outlives every reader.
    let mut overrides: Vec<(DataComponent, &'static dyn DataComponentImpl)> = Vec::new();

    // The template's display name would otherwise come along -- a prosperity ingot showing
    // up as "Stone". Vanilla's makeDescriptionId convention gives every item a translation
    // key derived from its id; a client with the mod installed translates it, and any other
    // client shows the raw key, which is at least honestly the mod's own name.
    let kind = if block.is_some() { "block" } else { "item" };
    let key = match id.split_once(':') {
        Some((namespace, path)) => format!("{kind}.{namespace}.{path}"),
        None => format!("{kind}.{id}"),
    };
    overrides.push((
        DataComponent::ItemName,
        Box::leak(Box::new(ItemNameImpl {
            name: std::borrow::Cow::Owned(key),
        })),
    ));

    if let Some(size) = max_stack_size {
        let Ok(size) = u8::try_from(size) else {
            throw(env, &format!("max stack size {size} does not fit in a u8"));
            return 0;
        };
        overrides.push((
            DataComponent::MaxStackSize,
            Box::leak(Box::new(MaxStackSizeImpl { size })),
        ));
    }
    if let Some(max_damage) = max_damage {
        overrides.push((
            DataComponent::MaxDamage,
            Box::leak(Box::new(MaxDamageImpl { max_damage })),
        ));
    }
    if !overrides.is_empty() {
        let mut components: Vec<(DataComponent, &'static dyn DataComponentImpl)> = item
            .components
            .iter()
            .filter(|(kind, _)| !overrides.iter().any(|(new_kind, _)| new_kind == kind))
            .copied()
            .collect();
        components.extend(overrides);
        item.components = Box::leak(components.into_boxed_slice());
    }

    let assigned =
        match pumpkin_data::dynamic::register_item(pumpkin_data::dynamic::ItemRegistration {
            name: id,
            item,
        }) {
            Ok(assigned) => assigned,
            Err(err) => {
                throw(env, &err.to_string());
                return 0;
            }
        };

    // Mods register every block before any item, so the block is already staged by the
    // time its placing item shows up; the link reaches back and completes the pair.
    if let Some(block_name) = block
        && let Err(err) = pumpkin_data::dynamic::link_block_item(&block_name, assigned)
    {
        throw(
            env,
            &format!("could not link {block_name} to its item: {err}"),
        );
        return 0;
    }

    jint::from(assigned)
}

extern "system" fn register_block_entity_type_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
) -> jint {
    let Some(id) = read_string(&mut env, &id, "the block entity type id") else {
        return 0;
    };

    // An id and a name are all a block entity type needs to survive the protocol; concrete
    // behaviour (`create_block_entity` knowing the type) is a future slice — see the
    // dynamic module's docs.
    match pumpkin_data::dynamic::register_block_entity_type(id) {
        Ok(assigned) => jint::from(assigned),
        Err(err) => {
            throw(&mut env, &err.to_string());
            0
        }
    }
}

extern "system" fn register_menu_type_native(mut env: JNIEnv, _class: JClass, id: JString) -> jint {
    let Some(id) = read_string(&mut env, &id, "the menu type id") else {
        return 0;
    };

    // The id gets the client to draw the mod's own screen when a window opens with it; the
    // window's contents are ordinary container work, not this registry's — see the dynamic
    // module's docs.
    match pumpkin_data::dynamic::register_menu_type(id) {
        Ok(assigned) => jint::from(assigned),
        Err(err) => {
            throw(&mut env, &err.to_string());
            0
        }
    }
}

extern "system" fn register_sound_event_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
) -> jint {
    let Some(id) = read_string(&mut env, &id, "the sound event id") else {
        return 0;
    };

    // The id survives the protocol so the server can point at the sound; the audio ships
    // in the mod's client half, like a menu type's screen.
    match pumpkin_data::dynamic::register_sound_event(id) {
        Ok(assigned) => jint::from(assigned),
        Err(err) => {
            throw(&mut env, &err.to_string());
            0
        }
    }
}

extern "system" fn register_data_component_type_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
) -> jint {
    let Some(id) = read_string(&mut env, &id, "the data component type id") else {
        return 0;
    };

    // A name the server can acknowledge over the protocol. Reading a mod component's
    // payload needs its codec, which lives in the mod's Java half -- see the dynamic
    // module's docs.
    match pumpkin_data::dynamic::register_data_component_type(id) {
        Ok(assigned) => jint::from(assigned),
        Err(err) => {
            throw(&mut env, &err.to_string());
            0
        }
    }
}
