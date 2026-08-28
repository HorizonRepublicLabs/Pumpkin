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

    // A staged registration is invisible to `Block::from_name` until the dynamic registry
    // is frozen; freezing is otherwise a server-startup step, and calling it a second time
    // is harmless, so it is safe to do here rather than nowhere.
    pumpkin_data::dynamic::freeze();
    assert!(
        pumpkin_data::Block::from_name("testmod:ruby_block").is_some(),
        "the block Java registered is in Pumpkin's registry"
    );
}
