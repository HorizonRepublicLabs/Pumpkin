#![cfg(feature = "jvm-plugins")]
#![allow(clippy::unwrap_used, clippy::expect_used, clippy::panic)]

use std::path::PathBuf;

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

#[test]
fn the_vm_runs_java_on_the_mod_thread() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("the VM boots");

    let answer = vm
        .call(|env| {
            let value = env
                .call_static_method(
                    "java/lang/Integer",
                    "parseInt",
                    "(Ljava/lang/String;)I",
                    &[(&env.new_string("42").map_err(|err| {
                        pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string())
                    })?)
                        .into()],
                )
                .and_then(jni::objects::JValueGen::i)
                .map_err(|err| pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string()))?;
            Ok(value)
        })
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
