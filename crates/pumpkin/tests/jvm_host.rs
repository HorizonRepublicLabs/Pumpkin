#![cfg(feature = "jvm-plugins")]
#![allow(clippy::unwrap_used, clippy::expect_used, clippy::panic)]

use std::path::PathBuf;

use jni::JNIEnv;
use pumpkin::plugin::loader::jvm::vm::VmError;

/// The classpath the Gradle build produces. Built by `gradle :host:jar` before tests run.
fn host_classpath() -> Vec<PathBuf> {
    let root = PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../java/pumpkin-jvm-host");
    ["shim", "fml", "host"]
        .iter()
        .map(|project| {
            root.join(project)
                .join("build/libs")
                .join(format!("{project}.jar"))
        })
        .collect()
}

/// `Integer.parseInt(text)`, wrapping every JNI failure as a [`VmError::Java`].
fn parse_int(env: &mut JNIEnv, text: &str) -> Result<i32, VmError> {
    let text = env
        .new_string(text)
        .map_err(|err| VmError::Java(err.to_string()))?;
    env.call_static_method(
        "java/lang/Integer",
        "parseInt",
        "(Ljava/lang/String;)I",
        &[(&text).into()],
    )
    .and_then(jni::objects::JValueGen::i)
    .map_err(|err| VmError::Java(err.to_string()))
}

#[test]
fn the_vm_runs_java_on_the_mod_thread() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");

    let answer = vm
        .call(|env| parse_int(env, "42"))
        .expect("the call succeeds");

    assert_eq!(answer, 42);
}

#[test]
fn booting_twice_returns_the_same_vm() {
    let first = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("first boot");
    let second = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("second boot");
    assert!(
        std::ptr::eq(first, second),
        "a second JNI_CreateJavaVM would fail"
    );
}

#[test]
fn a_reentrant_call_runs_inline_instead_of_deadlocking() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");

    // The outer closure runs on the mod thread; calling `vm.call` again from inside it
    // mimics Java calling back into Rust which then calls back into a plugin. If `call`
    // queued this instead of running it inline, it would deadlock forever: the mod
    // thread — the only thread that could service the queue — is the one waiting here.
    let answer = vm
        .call(move |_outer_env| vm.call(|inner_env| parse_int(inner_env, "7")))
        .expect("the reentrant call completes instead of deadlocking");

    assert_eq!(answer, 7);
}

#[test]
fn java_can_register_a_block() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");

    let assigned = vm
        .call(|env| {
            let id = env
                .new_string("testmod:ruby_block")
                .map_err(|err| pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string()))?;
            let template = env
                .new_string("stone")
                .map_err(|err| pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string()))?;

            env.call_static_method(
                "dev/pumpkin/jvmhost/PumpkinHost",
                "registerBlock",
                "(Ljava/lang/String;Ljava/lang/String;)I",
                &[(&id).into(), (&template).into()],
            )
            .and_then(jni::objects::JValueGen::i)
            .map_err(|err| pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string()))
        })
        .expect("the registration succeeds");

    assert!(assigned > 0, "a real block id was assigned, got {assigned}");

    // `Block::from_name` only sees published content, and freezing the registry here would
    // make every later registration in this process fail forever — including whatever a
    // future test registers through a loaded mod jar. `registering_block_id` sees staged
    // entries without publishing anything, so it can confirm the block exists without
    // arming that trap for tests that have not run yet.
    assert!(
        pumpkin_data::dynamic::registering_block_id("testmod:ruby_block").is_some(),
        "the block Java registered is in Pumpkin's registry"
    );
}

#[test]
fn the_loader_claims_jars_and_nothing_else() {
    use pumpkin::plugin::loader::{PluginLoader, jvm::JvmPluginLoader};

    let loader = JvmPluginLoader::new(host_classpath());
    assert!(loader.can_load(std::path::Path::new("plugins/hellomod.jar")));
    assert!(!loader.can_load(std::path::Path::new("plugins/other.wasm")));
}

#[test]
fn loading_the_test_mod_jar_registers_its_block() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");

    let jar = std::path::PathBuf::from(env!("CARGO_MANIFEST_DIR"))
        .join("../../java/pumpkin-jvm-host/testmod/build/libs/testmod.jar");
    let jar = jar.to_string_lossy().into_owned();

    let mod_id = pumpkin::plugin::loader::jvm::load_mod(vm, &jar).expect("the mod loads");

    assert_eq!(mod_id, "hellomod");

    // Not `Block::from_name`: that only sees published content, and publishing means
    // calling `pumpkin_data::dynamic::freeze`, which is one-way and process-global — every
    // test in this binary shares the one registry, so freezing it here would break any
    // other test that still needs to register something. Freezing is `PluginManager`'s
    // job in production (see `pumpkin::init_plugins`, called once after every plugin has
    // loaded), not something a test should trigger. `registering_block_id` sees staged
    // entries without publishing anything, which is enough to prove the mod's own code
    // put the block in the registry.
    assert!(
        pumpkin_data::dynamic::registering_block_id("hellomod:ruby_block").is_some(),
        "the mod's own code registered the block"
    );
}
