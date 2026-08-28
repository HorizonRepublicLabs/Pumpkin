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

/// Recurses through the inline reentrant path in `ModVm::call` far past its depth limit. If
/// the limit were not enforced, this would eventually overflow the mod thread's native stack
/// instead of returning cleanly.
fn recurse(
    vm: &'static pumpkin::plugin::loader::jvm::vm::ModVm,
    remaining: u32,
) -> Result<i32, VmError> {
    if remaining == 0 {
        return Ok(0);
    }
    vm.call(move |_env| recurse(vm, remaining - 1))
}

#[test]
fn a_reentrant_call_chain_past_the_depth_limit_errors_instead_of_overflowing_the_stack() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");

    let result = vm.call(move |_env| recurse(vm, 1000));

    assert!(
        matches!(result, Err(VmError::Java(_))),
        "a reentrant call chain past the depth limit should return a clear error instead of \
         dying, got {result:?}"
    );
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

/// Absolute path to the jar `HelloMod` builds into.
fn testmod_jar() -> PathBuf {
    PathBuf::from(env!("CARGO_MANIFEST_DIR"))
        .join("../../java/pumpkin-jvm-host/testmod/build/libs/testmod.jar")
}

#[tokio::test]
async fn loading_through_the_plugin_loader_defers_registration_to_on_load() {
    use pumpkin::plugin::loader::{PluginLoader, jvm::JvmPluginLoader};

    let loader = JvmPluginLoader::new(host_classpath());
    let jar_path = testmod_jar();

    let (_instance, metadata, _loader_data) =
        loader.load(&jar_path).await.expect("the jar is discovered");

    assert_eq!(
        metadata.name, "hellomod",
        "load() reads the mod id straight out of the jar's declared metadata"
    );

    // `load()` only discovers the jar: PluginManager checks a plugin's config override and
    // its permissions against this same metadata before the plugin is allowed to run at
    // all (crates/pumpkin/src/plugin/mod.rs, the override/disabled skip at line 755 and the
    // permission check at line 829), and both happen after `load()` has already returned. A
    // jar that is disabled or denied permissions must never have executed by then, so
    // nothing here may have registered anything yet.
    assert!(
        pumpkin_data::dynamic::registering_block_id("hellomod:ruby_block").is_none(),
        "load() must not have executed any of the mod's code"
    );

    // `Plugin::on_load` is what actually constructs the mod and fires `RegisterEvent` —
    // `JvmPlugin::on_load` boots (with the classpath `load` already discovered the mod on)
    // then calls `load_mod`, ignoring the `Context` it is handed (see that method's own
    // comment for the full reasoning).
    // Driving the trait method itself here would need a real `Arc<Context>`, which needs a
    // real `Arc<Server>` — and building one does disk-backed world I/O and terrain
    // generation, which is too heavy a price for a test scoped to the JVM host. Calling
    // `load_mod` directly exercises the identical registration path `on_load` runs.
    let jar = jar_path.to_string_lossy().into_owned();
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");
    let mod_id = pumpkin::plugin::loader::jvm::load_mod(vm, &jar).expect("construction succeeds");
    assert_eq!(mod_id, "hellomod");

    assert!(
        pumpkin_data::dynamic::registering_block_id("hellomod:ruby_block").is_some(),
        "the mod's own code registered the block only once construction actually ran"
    );
}
