# JVM Mod Host, Slice 1 — Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** A NeoForge-shaped mod jar, loaded by Pumpkin, registers a block into Pumpkin's dynamic registry by running its own compiled Java through `DeferredRegister`.

**Architecture:** Pumpkin boots a JVM in-process behind a third plugin loader (`plugin/loader/jvm/`), alongside the existing native and wasm loaders. Java calls back into Rust through `RegisterNatives`-bound functions that go to a loader-agnostic host layer extracted from the current wasm host. A hand-written `net.minecraft` / `net.neoforged` shim plus a minimal FML stub is enough for a mod to reach that path.

**Tech Stack:** Rust (`jni` 0.21 with the `invocation` feature), Java 25 (OpenJDK 25.0.4.1 at `/opt/homebrew/opt/openjdk@25`), Gradle multi-project under `java/pumpkin-jvm-host/`.

**Spec:** `docs/superpowers/specs/2026-08-28-neoforge-mod-hosting-design.md`

## Global Constraints

- Workspace clippy denies `all`, `nursery`, `pedantic`, `cargo`, plus `unwrap_used`, `expect_used`, `panic`, `todo`, `unimplemented`, `print_stdout`, `print_stderr`. **No `unwrap`, `expect`, `panic!`, `todo!`, `unimplemented!`, `println!` in non-test Rust.** Tests may use `unwrap`/`expect` only where the existing codebase already does.
- Java source and target release: **25**. Shim classes live under the literal packages `net.minecraft.*` and `net.neoforged.*`; host classes under `dev.pumpkin.jvmhost`.
- `JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home` must be set for both `cargo build --features jvm-plugins` and any test that boots the VM.
- Everything JVM-related is behind the cargo feature `jvm-plugins`, off by default. A default build must not require a JDK.
- **One JVM per process, ever.** `JNI_CreateJavaVM` fails on a second call. All Rust tests that need a VM share one via a process-wide `OnceLock`.
- Branch: `neoforge-support`. Commit after every task.

## Deviation from the spec, deliberate

The spec's section 1 calls for an ASM-driven shim generator. **This slice hand-writes roughly eight shim classes instead.** A generator built before anything has consumed a generated stub would be guessing at its own output format. The generator is plan 2, informed by the shape these eight classes settle into.

Everything else in the spec's sections 1–3 is honoured. Sections 4 (hot path) and 5 (differential testing) are out of scope here — this slice is about correctness of the pipe, not its throughput.

## File Structure

**Rust — new:**
- `crates/pumpkin/src/plugin/host/mod.rs` — loader-agnostic host layer, re-exports submodules
- `crates/pumpkin/src/plugin/host/registry.rs` — plain-Rust content registration, no WIT types
- `crates/pumpkin/src/plugin/loader/jvm/mod.rs` — `JvmPluginLoader`, implements `PluginLoader`
- `crates/pumpkin/src/plugin/loader/jvm/vm.rs` — VM boot and the single mod thread
- `crates/pumpkin/src/plugin/loader/jvm/handles.rs` — generational handle slab
- `crates/pumpkin/src/plugin/loader/jvm/natives.rs` — `extern "system"` functions bound via `RegisterNatives`
- `crates/pumpkin/tests/jvm_host.rs` — integration tests that boot the VM

**Rust — modified:**
- `crates/pumpkin/Cargo.toml` — `jvm-plugins` feature, `jni` dependency
- `crates/pumpkin/src/plugin/mod.rs:231-233` — register the loader
- `crates/pumpkin/src/plugin/loader/mod.rs:5-6` — declare the module
- `crates/pumpkin/src/plugin/loader/wasm/wasm_host/wit/v0_1/registry.rs:33-149` — becomes an adapter over `plugin/host/registry.rs`

**Java — new, under `java/pumpkin-jvm-host/`:**
- `settings.gradle`, `build.gradle` — multi-project root, Java 25 toolchain
- `host/src/main/java/dev/pumpkin/jvmhost/PumpkinHost.java` — native method declarations
- `host/src/main/java/dev/pumpkin/jvmhost/ModLoader.java` — jar scan, `neoforge.mods.toml`, `@Mod` construction
- `host/src/main/java/dev/pumpkin/jvmhost/Bootstrap.java` — the entry point Rust calls
- `shim/src/main/java/net/minecraft/resources/ResourceLocation.java`
- `shim/src/main/java/net/minecraft/core/registries/Registries.java`
- `shim/src/main/java/net/minecraft/core/ResourceKey.java`
- `shim/src/main/java/net/minecraft/world/level/block/Block.java`
- `shim/src/main/java/net/minecraft/world/level/block/state/BlockBehaviour.java`
- `fml/src/main/java/net/neoforged/fml/common/Mod.java`
- `fml/src/main/java/net/neoforged/bus/api/IEventBus.java`
- `fml/src/main/java/net/neoforged/bus/api/Event.java`
- `fml/src/main/java/net/neoforged/neoforge/registries/DeferredRegister.java`
- `fml/src/main/java/net/neoforged/neoforge/registries/DeferredHolder.java`
- `fml/src/main/java/net/neoforged/neoforge/registries/RegisterEvent.java`
- `testmod/src/main/java/dev/pumpkin/testmod/HelloMod.java`
- `testmod/src/main/resources/META-INF/neoforge.mods.toml`

Rationale for the split: `shim` and `fml` are the code that will eventually be generated and must not depend on `host`; `host` depends on both; `testmod` depends on `shim` + `fml` only, exactly as a real mod does. That dependency direction is what keeps the shim honest.

---

### Task 0: Toolchain and skeleton

**Files:**
- Modify: `crates/pumpkin/Cargo.toml`
- Create: `java/pumpkin-jvm-host/settings.gradle`
- Create: `java/pumpkin-jvm-host/build.gradle`
- Create: `.gitignore` entries for Gradle output

**Interfaces:**
- Consumes: nothing
- Produces: cargo feature `jvm-plugins`; Gradle projects `:host`, `:shim`, `:fml`, `:testmod`

- [ ] **Step 1: Confirm the JDK**

```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home
"$JAVA_HOME/bin/java" -version
ls "$JAVA_HOME/lib/server/libjvm.dylib"
```

Expected: `openjdk version "25.0.4.1"` and the dylib path echoed. If either fails, stop — nothing later works.

- [ ] **Step 2: Add the feature and dependency**

In `crates/pumpkin/Cargo.toml`, under `[dependencies]`:

```toml
jni = { version = "0.21", features = ["invocation"], optional = true }
```

Replace the `[features]` block:

```toml
[features]
console-subscriber = ["dep:console-subscriber"]
jvm-plugins = ["dep:jni"]
```

- [ ] **Step 3: Verify the default build still needs no JDK**

```bash
env -u JAVA_HOME cargo build -p pumpkin
```

Expected: success. If this fails, the dependency was not made optional.

- [ ] **Step 4: Verify the feature build finds libjvm**

```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo build -p pumpkin --features jvm-plugins
```

Expected: success.

- [ ] **Step 5: Create the Gradle root**

`java/pumpkin-jvm-host/settings.gradle`:

```groovy
rootProject.name = 'pumpkin-jvm-host'
include 'shim', 'fml', 'host', 'testmod'
```

`java/pumpkin-jvm-host/build.gradle`:

```groovy
subprojects {
    apply plugin: 'java-library'

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(25)
        }
    }

    repositories { mavenCentral() }

    dependencies {
        testImplementation 'org.junit.jupiter:junit-jupiter:5.11.3'
        testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    }

    test { useJUnitPlatform() }
}

project(':fml')     { dependencies { api project(':shim') } }
project(':host')    { dependencies { api project(':fml') } }
project(':testmod') { dependencies { compileOnly project(':fml') } }
```

`testmod` uses `compileOnly` on purpose: a real mod jar does not bundle the API it compiles against.

- [ ] **Step 6: Create source roots so Gradle configures**

```bash
cd java/pumpkin-jvm-host
mkdir -p shim/src/main/java fml/src/main/java host/src/main/java testmod/src/main/java testmod/src/main/resources/META-INF
```

- [ ] **Step 7: Build the empty projects**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle build
```

Expected: `BUILD SUCCESSFUL`. If `gradle` is not on PATH, use the wrapper from a sibling project or install one with `gradle wrapper`.

- [ ] **Step 8: Ignore Gradle output**

Append to `.gitignore`:

```
java/pumpkin-jvm-host/.gradle/
java/pumpkin-jvm-host/*/build/
```

- [ ] **Step 9: Commit**

```bash
git add crates/pumpkin/Cargo.toml Cargo.lock java/pumpkin-jvm-host .gitignore
git commit -m "Give the JVM host somewhere to live"
```

---

### Task 1: Extract the loader-agnostic registry host

The wasm host's `register_block` currently takes a WIT-generated `BlockDefinition` and does all the template-copying work inline. A JVM caller cannot construct a WIT type. Move the work behind plain Rust types; leave wasm as a converter.

**Files:**
- Create: `crates/pumpkin/src/plugin/host/mod.rs`
- Create: `crates/pumpkin/src/plugin/host/registry.rs`
- Modify: `crates/pumpkin/src/plugin/mod.rs` (add `pub mod host;` beside `pub mod loader;`)
- Modify: `crates/pumpkin/src/plugin/loader/wasm/wasm_host/wit/v0_1/registry.rs`

**Interfaces:**
- Consumes: `pumpkin_data::dynamic::{BlockRegistration, register_block}`
- Produces:
  - `pub struct BlockSpec { pub id: String, pub template: String, pub hardness: Option<f32>, pub blast_resistance: Option<f32>, pub luminance: Option<u8>, pub requires_tool: Option<bool>, pub properties: Vec<BlockProperty>, pub default_state: u32, pub item: Option<String>, pub drops: Vec<BlockDrop>, pub block_entity: Option<String> }`
  - `pub struct BlockProperty { pub name: String, pub values: Vec<String> }`
  - `pub struct BlockDrop { pub item: String, pub min: u8, pub max: u8, pub from_state: Option<u32>, pub to_state: Option<u32> }`
  - `pub fn register_block_spec(spec: &BlockSpec) -> Result<u32, String>`

- [ ] **Step 1: Write the failing test**

Create `crates/pumpkin/src/plugin/host/registry.rs` with only the test module at the bottom:

```rust
#[cfg(test)]
mod tests {
    use super::{BlockSpec, register_block_spec};

    #[test]
    fn an_unknown_template_is_reported_rather_than_registered() {
        let spec = BlockSpec {
            id: "testmod:ruby_block".to_owned(),
            template: "definitely_not_a_block".to_owned(),
            hardness: None,
            blast_resistance: None,
            luminance: None,
            requires_tool: None,
            properties: Vec::new(),
            default_state: 0,
            item: None,
            drops: Vec::new(),
            block_entity: None,
        };

        let error = register_block_spec(&spec).expect_err("an unknown template cannot register");
        assert!(error.contains("definitely_not_a_block"), "got: {error}");
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cargo test -p pumpkin plugin::host::registry -- --nocapture
```

Expected: FAIL — `cannot find struct BlockSpec`.

- [ ] **Step 3: Move the implementation**

Cut the bodies of `register_block`, `copy_state`, `template_states`, `resolve_drops` and `unknown_template` out of `crates/pumpkin/src/plugin/loader/wasm/wasm_host/wit/v0_1/registry.rs` and into `crates/pumpkin/src/plugin/host/registry.rs`, replacing every WIT type with the plain type of the same shape defined in the Interfaces block above. Keep the doc comments — they carry the reasoning about state counts and template inheritance, and that reasoning did not move.

The public entry point:

```rust
/// Registers a block built from a vanilla template, returning the id it was assigned.
///
/// Errors are strings rather than a typed error because every caller — wasm, JVM — hands
/// them straight back to a plugin that can only log them.
pub fn register_block_spec(spec: &BlockSpec) -> Result<u32, String> {
    let Some(template) = Block::from_name(&spec.template) else {
        return Err(unknown_template("block", &spec.template));
    };
    // ... the moved body, reading from `spec` instead of `definition`
}
```

Create `crates/pumpkin/src/plugin/host/mod.rs`:

```rust
//! Host behaviour shared by every plugin loader.
//!
//! The wasm and JVM loaders differ only in how a call arrives. What the call *does* lives
//! here, so the two cannot drift.

pub mod registry;
```

Add `pub mod host;` to `crates/pumpkin/src/plugin/mod.rs` next to the existing `pub mod loader;`.

- [ ] **Step 4: Make the wasm host an adapter**

In `wasm_host/wit/v0_1/registry.rs`, `register_block` becomes conversion plus delegation:

```rust
async fn register_block(
    &mut self,
    definition: BlockDefinition,
) -> wasmtime::Result<Result<u32, String>> {
    Ok(crate::plugin::host::registry::register_block_spec(
        &block_spec_from_wit(definition),
    ))
}
```

with a private `fn block_spec_from_wit(definition: BlockDefinition) -> BlockSpec` doing the field-by-field copy.

- [ ] **Step 5: Run the whole registry test set**

```bash
cargo test -p pumpkin registry
```

Expected: PASS, including the five pre-existing wasm tests (`copied_states_inherit_the_template`, `luminance_overrides_the_template_and_clamps_to_the_vanilla_range`, `hardness_and_tool_rules_reach_the_states_mining_reads`, `a_state_copied_from_a_crop_is_still_randomly_ticked`, `a_template_with_many_states_copies_all_of_them`). If any of those five now fail, the extraction changed behaviour — fix the extraction, not the test.

- [ ] **Step 6: Lint**

```bash
cargo clippy -p pumpkin --all-targets -- -D warnings
```

Expected: clean.

- [ ] **Step 7: Commit**

```bash
git add crates/pumpkin/src/plugin/host crates/pumpkin/src/plugin/mod.rs \
        crates/pumpkin/src/plugin/loader/wasm/wasm_host/wit/v0_1/registry.rs
git commit -m "Let something other than wasm register a block"
```

---

### Task 2: Generational handle slab

Java holds `long` handles into Rust-owned state. A mod that stashes one and reuses it later must get an error, not a dangling read.

**Files:**
- Create: `crates/pumpkin/src/plugin/loader/jvm/handles.rs`
- Create: `crates/pumpkin/src/plugin/loader/jvm/mod.rs` (module declarations only, for now)
- Modify: `crates/pumpkin/src/plugin/loader/mod.rs`

Nothing in this slice calls it: registration passes strings, not handles. It is built now because the reentrancy and hot-path work in the next slice cannot start without it, and because its correctness is testable in isolation exactly once — here, before anything depends on its bugs.

**Interfaces:**
- Consumes: nothing
- Produces:
  - `pub struct HandleTable<T> { .. }` with `pub fn new() -> Self`, `pub fn insert(&mut self, value: T) -> Handle`, `pub fn get(&self, handle: Handle) -> Option<&T>`, `pub fn remove(&mut self, handle: Handle) -> Option<T>`
  - `pub struct Handle(u64)` with `pub const fn raw(self) -> i64` and `pub const fn from_raw(raw: i64) -> Self`
  - `pub const NULL_HANDLE: i64 = 0;`

- [ ] **Step 1: Write the failing tests**

`crates/pumpkin/src/plugin/loader/jvm/handles.rs`, test module only:

```rust
#[cfg(test)]
mod tests {
    use super::{Handle, HandleTable};

    #[test]
    fn a_handle_reads_back_the_value_it_was_given() {
        let mut table = HandleTable::new();
        let handle = table.insert("world");
        assert_eq!(table.get(handle), Some(&"world"));
    }

    #[test]
    fn a_handle_reused_after_removal_reads_nothing() {
        let mut table = HandleTable::new();
        let stale = table.insert("world");
        table.remove(stale);
        assert_eq!(table.get(stale), None);
    }

    #[test]
    fn a_reused_slot_does_not_answer_to_the_old_handle() {
        let mut table = HandleTable::new();
        let stale = table.insert("first");
        table.remove(stale);
        let fresh = table.insert("second");

        assert_ne!(stale.raw(), fresh.raw(), "the generation has to move");
        assert_eq!(table.get(stale), None);
        assert_eq!(table.get(fresh), Some(&"second"));
    }

    #[test]
    fn a_handle_survives_the_trip_through_java_as_an_i64() {
        let mut table = HandleTable::new();
        let handle = table.insert(7_u32);
        assert_eq!(table.get(Handle::from_raw(handle.raw())), Some(&7));
    }
}
```

- [ ] **Step 2: Run and watch them fail**

```bash
cargo test -p pumpkin --features jvm-plugins jvm::handles
```

Expected: FAIL — `cannot find struct HandleTable`.

- [ ] **Step 3: Implement**

```rust
//! Opaque handles into Rust-owned state, for a Java side that can only hold a `long`.
//!
//! A slot is index plus generation. Reusing a slot bumps its generation, so a handle kept
//! across the removal reads as absent rather than as whatever now occupies the slot. Mods
//! stash references in statics routinely; this is what turns that into an error message.

/// A handle as it travels to Java and back.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct Handle(u64);

/// The value Java uses for "no handle". Slot 0 generation 0 is never issued.
pub const NULL_HANDLE: i64 = 0;

impl Handle {
    /// The `long` Java holds.
    #[must_use]
    pub const fn raw(self) -> i64 {
        self.0 as i64
    }

    /// Rebuilds a handle from the `long` Java handed back.
    #[must_use]
    pub const fn from_raw(raw: i64) -> Self {
        Self(raw as u64)
    }

    const fn new(index: u32, generation: u32) -> Self {
        Self(((generation as u64) << 32) | index as u64)
    }

    const fn index(self) -> u32 {
        self.0 as u32
    }

    const fn generation(self) -> u32 {
        (self.0 >> 32) as u32
    }
}

struct Slot<T> {
    generation: u32,
    value: Option<T>,
}

/// A slab of values addressed by [`Handle`].
pub struct HandleTable<T> {
    slots: Vec<Slot<T>>,
    free: Vec<u32>,
}

impl<T> Default for HandleTable<T> {
    fn default() -> Self {
        Self::new()
    }
}

impl<T> HandleTable<T> {
    /// An empty table.
    #[must_use]
    pub const fn new() -> Self {
        Self {
            slots: Vec::new(),
            free: Vec::new(),
        }
    }

    /// Stores a value and returns the handle naming it.
    pub fn insert(&mut self, value: T) -> Handle {
        if let Some(index) = self.free.pop() {
            let slot = &mut self.slots[index as usize];
            slot.generation = slot.generation.wrapping_add(1);
            slot.value = Some(value);
            return Handle::new(index, slot.generation);
        }

        // Generation starts at 1 so that slot 0 never yields the null handle.
        let index = u32::try_from(self.slots.len()).unwrap_or(u32::MAX);
        self.slots.push(Slot {
            generation: 1,
            value: Some(value),
        });
        Handle::new(index, 1)
    }

    /// The value a handle names, or `None` if it has been removed or superseded.
    pub fn get(&self, handle: Handle) -> Option<&T> {
        let slot = self.slots.get(handle.index() as usize)?;
        if slot.generation == handle.generation() {
            slot.value.as_ref()
        } else {
            None
        }
    }

    /// Takes the value back, freeing the slot for reuse under a new generation.
    pub fn remove(&mut self, handle: Handle) -> Option<T> {
        let slot = self.slots.get_mut(handle.index() as usize)?;
        if slot.generation != handle.generation() {
            return None;
        }
        let value = slot.value.take();
        if value.is_some() {
            self.free.push(handle.index());
        }
        value
    }
}
```

`crates/pumpkin/src/plugin/loader/jvm/mod.rs`:

```rust
//! Hosting a JVM so mods written in Java can run against Pumpkin.

pub mod handles;
```

In `crates/pumpkin/src/plugin/loader/mod.rs`, beside `pub mod native;`:

```rust
#[cfg(feature = "jvm-plugins")]
pub mod jvm;
```

- [ ] **Step 4: Run and watch them pass**

```bash
cargo test -p pumpkin --features jvm-plugins jvm::handles
```

Expected: 4 passed.

- [ ] **Step 5: Lint**

```bash
cargo clippy -p pumpkin --features jvm-plugins --all-targets -- -D warnings
```

Expected: clean. `as` casts in `Handle` may trip `cast_possible_wrap`; if so, keep the cast and add a scoped `#[expect(clippy::cast_possible_wrap, reason = "the bit pattern is the point")]` rather than changing the representation.

- [ ] **Step 6: Commit**

```bash
git add crates/pumpkin/src/plugin/loader/jvm crates/pumpkin/src/plugin/loader/mod.rs
git commit -m "Give Java a handle it cannot use after we drop it"
```

---

### Task 3: Boot the VM on its own thread

**Files:**
- Create: `crates/pumpkin/src/plugin/loader/jvm/vm.rs`
- Modify: `crates/pumpkin/src/plugin/loader/jvm/mod.rs`
- Create: `crates/pumpkin/tests/jvm_host.rs`

**Interfaces:**
- Consumes: nothing
- Produces:
  - `pub struct ModVm { .. }`
  - `pub fn boot(classpath: &[PathBuf]) -> Result<&'static ModVm, VmError>` — process-wide, idempotent, returns the same VM on every call
  - `pub fn call<R, F>(&self, work: F) -> Result<R, VmError>` where `F: FnOnce(&mut JNIEnv) -> Result<R, VmError> + Send + 'static`, `R: Send + 'static` — runs `work` on the mod thread
  - `pub enum VmError { Boot(String), Java(String), ThreadGone }`

- [ ] **Step 1: Write the failing test**

`crates/pumpkin/tests/jvm_host.rs`:

```rust
#![cfg(feature = "jvm-plugins")]

use std::path::PathBuf;

/// The classpath the Gradle build produces. Built by `gradle :host:jar` before tests run.
fn host_classpath() -> Vec<PathBuf> {
    let root = PathBuf::from(env!("CARGO_MANIFEST_DIR"))
        .join("../../java/pumpkin-jvm-host");
    ["shim", "fml", "host"]
        .iter()
        .map(|project| root.join(project).join("build/libs").join(format!("{project}.jar")))
        .collect()
}

#[test]
fn the_vm_runs_java_on_the_mod_thread() {
    let vm = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath())
        .expect("the VM boots");

    let answer = vm
        .call(|env| {
            let value = env
                .call_static_method("java/lang/Integer", "parseInt", "(Ljava/lang/String;)I", &[
                    (&env.new_string("42").map_err(|err| {
                        pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string())
                    })?)
                        .into(),
                ])
                .and_then(|value| value.i())
                .map_err(|err| {
                    pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string())
                })?;
            Ok(value)
        })
        .expect("the call succeeds");

    assert_eq!(answer, 42);
}

#[test]
fn booting_twice_returns_the_same_vm() {
    let first = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("first boot");
    let second = pumpkin::plugin::loader::jvm::vm::boot(&host_classpath()).expect("second boot");
    assert!(std::ptr::eq(first, second), "a second JNI_CreateJavaVM would fail");
}
```

- [ ] **Step 2: Run and watch it fail**

```bash
cd java/pumpkin-jvm-host && JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle :host:jar && cd -
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins --test jvm_host
```

Expected: FAIL — `could not find 'vm' in 'jvm'`.

- [ ] **Step 3: Implement the VM**

`crates/pumpkin/src/plugin/loader/jvm/vm.rs`:

```rust
//! The JVM and the one thread allowed to talk to it.
//!
//! Mod code assumes vanilla's single-threaded world tick, so all of it runs on one thread.
//! That the thread is also where `JNIEnv` is attached is a convenience, not the reason.
//!
//! There is exactly one VM per process for the life of the process: `JNI_CreateJavaVM`
//! fails on a second call and there is no way to unmake one.

use std::{
    path::PathBuf,
    sync::{OnceLock, mpsc},
    thread,
};

use jni::{InitArgsBuilder, JNIEnv, JavaVM};
use tracing::info;

/// What can go wrong either side of the boundary.
#[derive(Debug, thiserror::Error)]
pub enum VmError {
    /// The VM could not be created.
    #[error("Failed to start the JVM: {0}")]
    Boot(String),
    /// A call into Java failed, or Java threw.
    #[error("Java call failed: {0}")]
    Java(String),
    /// The mod thread died; nothing further can run.
    #[error("The mod thread is gone")]
    ThreadGone,
}

type Job = Box<dyn FnOnce(&mut JNIEnv) + Send>;

/// A booted VM and the channel to its mod thread.
pub struct ModVm {
    jobs: mpsc::Sender<Job>,
}

static VM: OnceLock<ModVm> = OnceLock::new();

/// Starts the VM, or returns the one already running.
///
/// `classpath` is ignored on any call after the first, because the VM's classpath is fixed
/// at creation. Callers needing more classes add them through a child classloader.
///
/// # Errors
/// Returns [`VmError::Boot`] if the VM cannot be created.
pub fn boot(classpath: &[PathBuf]) -> Result<&'static ModVm, VmError> {
    if let Some(vm) = VM.get() {
        return Ok(vm);
    }

    let joined = join_classpath(classpath);
    info!("Starting the JVM with classpath {joined}");

    let (jobs, requests) = mpsc::channel::<Job>();
    let (ready, booted) = mpsc::channel::<Result<(), String>>();

    thread::Builder::new()
        .name("pumpkin-mod-thread".to_owned())
        .spawn(move || mod_thread(&joined, &ready, &requests))
        .map_err(|err| VmError::Boot(err.to_string()))?;

    booted
        .recv()
        .map_err(|_| VmError::Boot("the mod thread died during boot".to_owned()))?
        .map_err(VmError::Boot)?;

    Ok(VM.get_or_init(|| ModVm { jobs }))
}

fn join_classpath(classpath: &[PathBuf]) -> String {
    classpath
        .iter()
        .map(|path| path.to_string_lossy().into_owned())
        .collect::<Vec<_>>()
        .join(":")
}

fn mod_thread(
    classpath: &str,
    ready: &mpsc::Sender<Result<(), String>>,
    requests: &mpsc::Receiver<Job>,
) {
    let args = match InitArgsBuilder::new()
        .version(jni::JNIVersion::V8)
        .option(format!("-Djava.class.path={classpath}"))
        .option("-XX:+UseZGC")
        .option("-XX:+ZGenerational")
        .build()
    {
        Ok(args) => args,
        Err(err) => {
            let _ = ready.send(Err(err.to_string()));
            return;
        }
    };

    let vm = match JavaVM::new(args) {
        Ok(vm) => vm,
        Err(err) => {
            let _ = ready.send(Err(err.to_string()));
            return;
        }
    };

    let mut env = match vm.attach_current_thread_permanently() {
        Ok(env) => env,
        Err(err) => {
            let _ = ready.send(Err(err.to_string()));
            return;
        }
    };

    if ready.send(Ok(())).is_err() {
        return;
    }

    while let Ok(job) = requests.recv() {
        job(&mut env);
    }
}

impl ModVm {
    /// Runs `work` on the mod thread and waits for its result.
    ///
    /// # Errors
    /// Returns [`VmError::ThreadGone`] if the mod thread has stopped, or whatever `work`
    /// returned.
    pub fn call<R, F>(&self, work: F) -> Result<R, VmError>
    where
        F: FnOnce(&mut JNIEnv) -> Result<R, VmError> + Send + 'static,
        R: Send + 'static,
    {
        let (reply, answer) = mpsc::channel();
        self.jobs
            .send(Box::new(move |env| {
                let _ = reply.send(work(env));
            }))
            .map_err(|_| VmError::ThreadGone)?;
        answer.recv().map_err(|_| VmError::ThreadGone)?
    }
}
```

Add `pub mod vm;` to `crates/pumpkin/src/plugin/loader/jvm/mod.rs`.

- [ ] **Step 4: Run and watch it pass**

```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins --test jvm_host
```

Expected: 2 passed. If the process aborts with `Unable to load libjvm`, `JAVA_HOME` was not exported for the *test* run, not just the build.

- [ ] **Step 5: Lint and commit**

```bash
cargo clippy -p pumpkin --features jvm-plugins --all-targets -- -D warnings
git add crates/pumpkin/src/plugin/loader/jvm crates/pumpkin/tests/jvm_host.rs
git commit -m "Start a JVM and keep one thread talking to it"
```

---

### Task 4: Java reaches the registry

**Files:**
- Create: `java/pumpkin-jvm-host/host/src/main/java/dev/pumpkin/jvmhost/PumpkinHost.java`
- Create: `crates/pumpkin/src/plugin/loader/jvm/natives.rs`
- Modify: `crates/pumpkin/src/plugin/loader/jvm/{mod.rs,vm.rs}`
- Modify: `crates/pumpkin/tests/jvm_host.rs`

**Interfaces:**
- Consumes: `plugin::host::registry::{BlockSpec, register_block_spec}` (Task 1), `vm::ModVm::call` (Task 3)
- Produces:
  - Java: `public final class PumpkinHost { public static native int registerBlock(String id, String template); }`
  - Rust: `pub fn bind(env: &mut JNIEnv) -> Result<(), VmError>` in `natives.rs`, called once during boot

- [ ] **Step 1: Write the Java side**

```java
package dev.pumpkin.jvmhost;

/**
 * The calls Java makes into Pumpkin.
 *
 * <p>Bound with {@code RegisterNatives} while the VM starts, not with
 * {@code System.loadLibrary}: Pumpkin is the executable, not a library the JVM can find by
 * name.
 */
public final class PumpkinHost {
    private PumpkinHost() {
    }

    /**
     * Registers a block copied from a vanilla template.
     *
     * @param id       namespaced id, e.g. {@code testmod:ruby_block}
     * @param template vanilla block whose definition is copied, e.g. {@code stone}
     * @return the assigned block id
     * @throws IllegalStateException if registration failed or the registries are frozen
     */
    public static native int registerBlock(String id, String template);
}
```

- [ ] **Step 2: Write the failing test**

Append to `crates/pumpkin/tests/jvm_host.rs`:

```rust
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
            .and_then(|value| value.i())
            .map_err(|err| pumpkin::plugin::loader::jvm::vm::VmError::Java(err.to_string()))
        })
        .expect("the registration succeeds");

    assert!(assigned > 0, "a real block id was assigned, got {assigned}");
    assert!(
        pumpkin_data::Block::from_name("testmod:ruby_block").is_some(),
        "the block Java registered is in Pumpkin's registry"
    );
}
```

- [ ] **Step 3: Run and watch it fail**

```bash
cd java/pumpkin-jvm-host && JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle :host:jar && cd -
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins --test jvm_host java_can_register
```

Expected: FAIL — `UnsatisfiedLinkError: PumpkinHost.registerBlock`.

- [ ] **Step 4: Implement the natives**

`crates/pumpkin/src/plugin/loader/jvm/natives.rs`:

```rust
//! The functions Java calls, bound explicitly with `RegisterNatives`.
//!
//! Pumpkin is the executable rather than a loadable library, so the JVM has no library to
//! resolve names out of. Binding by hand also means an unbound method is a boot failure
//! rather than an `UnsatisfiedLinkError` in the middle of a mod's registration.

use jni::{
    JNIEnv, NativeMethod,
    objects::{JClass, JString},
    sys::jint,
};

use crate::plugin::{
    host::registry::{BlockSpec, register_block_spec},
    loader::jvm::vm::VmError,
};

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
        &[NativeMethod {
            name: "registerBlock".into(),
            sig: "(Ljava/lang/String;Ljava/lang/String;)I".into(),
            fn_ptr: register_block_native as *mut std::ffi::c_void,
        }],
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
    if let Err(err) = env.throw_new("java/lang/IllegalStateException", message) {
        tracing::error!("Failed to throw into Java: {err}");
    }
}

extern "system" fn register_block_native(
    mut env: JNIEnv,
    _class: JClass,
    id: JString,
    template: JString,
) -> jint {
    let Some(id) = read_string(&mut env, &id, "the block id") else {
        return 0;
    };
    let Some(template) = read_string(&mut env, &template, "the block template") else {
        return 0;
    };

    let spec = BlockSpec {
        id,
        template,
        hardness: None,
        blast_resistance: None,
        luminance: None,
        requires_tool: None,
        properties: Vec::new(),
        default_state: 0,
        item: None,
        drops: Vec::new(),
        block_entity: None,
    };

    match register_block_spec(&spec) {
        Ok(assigned) => jint::try_from(assigned).unwrap_or(0),
        Err(message) => {
            throw(&mut env, &message);
            0
        }
    }
}
```

Add `pub mod natives;` to `crates/pumpkin/src/plugin/loader/jvm/mod.rs`.

In `vm.rs`, call `bind` right after the env is attached and before `ready.send(Ok(()))`:

```rust
    if let Err(err) = super::natives::bind(&mut env) {
        let _ = ready.send(Err(err.to_string()));
        return;
    }
```

- [ ] **Step 5: Run and watch it pass**

```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins --test jvm_host
```

Expected: 3 passed.

- [ ] **Step 6: Lint and commit**

```bash
cargo clippy -p pumpkin --features jvm-plugins --all-targets -- -D warnings
git add crates/pumpkin/src/plugin/loader/jvm java/pumpkin-jvm-host/host crates/pumpkin/tests/jvm_host.rs
git commit -m "Let Java put a block in the registry"
```

---

### Task 5: The shim a mod compiles against

Eight classes, hand-written. Enough for `DeferredRegister.create(Registries.BLOCK, ..).register(..)` to typecheck and run.

**Files:**
- Create: the eight `shim/` and `fml/` files listed in File Structure
- Create: `java/pumpkin-jvm-host/fml/src/test/java/net/neoforged/neoforge/registries/DeferredRegisterTest.java`

**Interfaces:**
- Consumes: `PumpkinHost.registerBlock` (Task 4)
- Produces:
  - `net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(String, String)`, `.toString()`
  - `net.minecraft.core.ResourceKey<T>` with `ResourceKey.createRegistryKey(ResourceLocation)`
  - `net.minecraft.core.registries.Registries.BLOCK`
  - `net.minecraft.world.level.block.Block(BlockBehaviour.Properties)`, `Block.pumpkinTemplate()`
  - `net.minecraft.world.level.block.state.BlockBehaviour.Properties.of()`, `.pumpkinTemplate(String)`
  - `net.neoforged.fml.common.Mod` annotation with `value()`
  - `net.neoforged.bus.api.IEventBus.addListener(Consumer<T>)`, `.post(Event)`
  - `net.neoforged.neoforge.registries.DeferredRegister.create(ResourceKey, String)`, `.register(String, Supplier<T>)`, `.register(IEventBus)`
  - `net.neoforged.neoforge.registries.DeferredHolder.get()`, `.getId()`
  - `net.neoforged.neoforge.registries.RegisterEvent`

- [ ] **Step 1: Write the failing Java test**

`fml/src/test/java/net/neoforged/neoforge/registries/DeferredRegisterTest.java`:

```java
package net.neoforged.neoforge.registries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import org.junit.jupiter.api.Test;

class DeferredRegisterTest {
    @Test
    void registrationIsDeferredUntilTheEventFires() {
        List<String> registered = new ArrayList<>();
        DeferredRegister.setSink((id, template) -> {
            registered.add(id + " from " + template);
            return registered.size();
        });

        IEventBus bus = new IEventBus();
        DeferredRegister<Block> blocks = DeferredRegister.create(Registries.BLOCK, "testmod");
        DeferredHolder<Block> ruby =
                blocks.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of().pumpkinTemplate("stone")));
        blocks.register(bus);

        assertTrue(registered.isEmpty(), "nothing registers before the event");

        bus.post(new RegisterEvent());

        assertEquals(List.of("testmod:ruby_block from stone"), registered);
        assertEquals("testmod:ruby_block", ruby.getId().toString());
    }
}
```

`DeferredRegister.setSink` exists so the FML stub is testable without a JVM booted by Pumpkin. Production code sets the sink to `PumpkinHost::registerBlock` during bootstrap.

- [ ] **Step 2: Run and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle :fml:test
```

Expected: compilation failure — the classes do not exist.

- [ ] **Step 3: Write the shim**

`shim/.../ResourceLocation.java`:

```java
package net.minecraft.resources;

/** A namespaced id. Value type: never crosses the bridge. */
public record ResourceLocation(String namespace, String path) {
    public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
```

`shim/.../ResourceKey.java`:

```java
package net.minecraft.core;

import net.minecraft.resources.ResourceLocation;

/** Names a registry. Only the registry-key half of vanilla's type is modelled. */
public record ResourceKey<T>(ResourceLocation location) {
    public static <T> ResourceKey<T> createRegistryKey(ResourceLocation location) {
        return new ResourceKey<>(location);
    }
}
```

`shim/.../Registries.java`:

```java
package net.minecraft.core.registries;

import net.minecraft.core.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

/** The registry keys a mod names. Only those Pumpkin can service are present. */
public final class Registries {
    public static final ResourceKey<Block> BLOCK =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "block"));

    private Registries() {
    }
}
```

`shim/.../BlockBehaviour.java`:

```java
package net.minecraft.world.level.block.state;

/**
 * Vanilla's block property builder, reduced to what Pumpkin's registration reads.
 *
 * <p>{@code pumpkinTemplate} has no vanilla counterpart. Pumpkin registers a block by
 * copying a vanilla one, so something has to say which; a mod that never calls it gets
 * stone. This is the one place the shim knowingly diverges from vanilla's API.
 */
public class BlockBehaviour {
    public static final class Properties {
        private String template = "stone";

        private Properties() {
        }

        public static Properties of() {
            return new Properties();
        }

        public Properties pumpkinTemplate(String template) {
            this.template = template;
            return this;
        }

        public String template() {
            return template;
        }
    }
}
```

`shim/.../Block.java`:

```java
package net.minecraft.world.level.block;

import net.minecraft.world.level.block.state.BlockBehaviour;

/** A block. Carries only the template Pumpkin registers from. */
public class Block {
    private final BlockBehaviour.Properties properties;

    public Block(BlockBehaviour.Properties properties) {
        this.properties = properties;
    }

    public String pumpkinTemplate() {
        return properties.template();
    }
}
```

- [ ] **Step 4: Write the FML stub**

`fml/.../Mod.java`:

```java
package net.neoforged.fml.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Marks a mod's entry point. Read reflectively by the loader. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod {
    String value();
}
```

`fml/.../Event.java`:

```java
package net.neoforged.bus.api;

/** Base of everything the mod bus carries. */
public abstract class Event {
}
```

`fml/.../IEventBus.java`:

```java
package net.neoforged.bus.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The mod event bus.
 *
 * <p>A class in vanilla NeoForge, an interface here would need a second implementation for
 * no gain. Dispatch is by runtime type and is not thread-safe: everything on it runs on the
 * mod thread.
 */
public class IEventBus {
    private record Listener<T extends Event>(Class<?> type, Consumer<T> handler) {
    }

    private final List<Listener<? extends Event>> listeners = new ArrayList<>();

    public <T extends Event> void addListener(Class<T> type, Consumer<T> handler) {
        listeners.add(new Listener<>(type, handler));
    }

    @SuppressWarnings("unchecked")
    public void post(Event event) {
        for (Listener<? extends Event> listener : List.copyOf(listeners)) {
            if (listener.type().isInstance(event)) {
                ((Consumer<Event>) listener.handler()).accept(event);
            }
        }
    }
}
```

`fml/.../RegisterEvent.java`:

```java
package net.neoforged.neoforge.registries;

import net.neoforged.bus.api.Event;

/** Fired once, when the server is ready to take registrations. */
public class RegisterEvent extends Event {
}
```

`fml/.../DeferredHolder.java`:

```java
package net.neoforged.neoforge.registries;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;

/** A registered object plus the id it got. Resolves only after registration has run. */
public final class DeferredHolder<T> implements Supplier<T> {
    private final ResourceLocation id;
    private final Supplier<T> factory;
    private T value;

    DeferredHolder(ResourceLocation id, Supplier<T> factory) {
        this.id = id;
        this.factory = factory;
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public T get() {
        if (value == null) {
            value = factory.get();
        }
        return value;
    }
}
```

`fml/.../DeferredRegister.java`:

```java
package net.neoforged.neoforge.registries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

/**
 * Collects registrations and replays them when {@link RegisterEvent} fires.
 *
 * <p>The sink is settable so the stub can be tested without a Pumpkin host; the bootstrap
 * points it at {@code PumpkinHost::registerBlock}.
 */
public final class DeferredRegister<T> {
    /** Where a registration ends up. Returns the assigned id. */
    @FunctionalInterface
    public interface Sink {
        int registerBlock(String id, String template);
    }

    private static Sink sink = (id, template) -> {
        throw new IllegalStateException("no registration sink installed for " + id);
    };

    public static void setSink(Sink replacement) {
        sink = replacement;
    }

    private final ResourceKey<T> registry;
    private final String namespace;
    private final List<DeferredHolder<T>> pending = new ArrayList<>();

    private DeferredRegister(ResourceKey<T> registry, String namespace) {
        this.registry = registry;
        this.namespace = namespace;
    }

    public static <T> DeferredRegister<T> create(ResourceKey<T> registry, String namespace) {
        return new DeferredRegister<>(registry, namespace);
    }

    public DeferredHolder<T> register(String path, Supplier<T> factory) {
        DeferredHolder<T> holder =
                new DeferredHolder<>(ResourceLocation.fromNamespaceAndPath(namespace, path), factory);
        pending.add(holder);
        return holder;
    }

    public void register(IEventBus bus) {
        bus.addListener(RegisterEvent.class, event -> flush());
    }

    private void flush() {
        for (DeferredHolder<T> holder : pending) {
            Object object = holder.get();
            if (object instanceof Block block) {
                sink.registerBlock(holder.getId().toString(), block.pumpkinTemplate());
            } else {
                throw new IllegalStateException(
                        "registry " + registry.location() + " is not supported yet: " + holder.getId());
            }
        }
    }
}
```

- [ ] **Step 5: Run and watch it pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle :fml:test
```

Expected: 1 test passing.

- [ ] **Step 6: Commit**

```bash
git add java/pumpkin-jvm-host/shim java/pumpkin-jvm-host/fml
git commit -m "Write the least net.minecraft a mod can register against"
```

---

### Task 6: Load a mod jar and run it

**Files:**
- Create: `java/pumpkin-jvm-host/host/src/main/java/dev/pumpkin/jvmhost/ModLoader.java`
- Create: `java/pumpkin-jvm-host/host/src/main/java/dev/pumpkin/jvmhost/Bootstrap.java`
- Create: `java/pumpkin-jvm-host/testmod/src/main/java/dev/pumpkin/testmod/HelloMod.java`
- Create: `java/pumpkin-jvm-host/testmod/src/main/resources/META-INF/neoforge.mods.toml`
- Create: `java/pumpkin-jvm-host/host/src/test/java/dev/pumpkin/jvmhost/ModLoaderTest.java`

**Interfaces:**
- Consumes: `DeferredRegister.setSink` (Task 5), `PumpkinHost.registerBlock` (Task 4)
- Produces:
  - `ModLoader.discover(Path jar) -> ModCandidate` (record of `modId`, `mainClass`)
  - `Bootstrap.loadAndRegister(String jarPath) -> String[]` — returns the mod ids loaded; called from Rust

- [ ] **Step 1: Write the test mod**

`testmod/.../HelloMod.java`:

```java
package dev.pumpkin.testmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(HelloMod.ID)
public class HelloMod {
    public static final String ID = "hellomod";

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ID);

    static {
        BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of().pumpkinTemplate("stone")));
    }

    public HelloMod(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
```

`testmod/src/main/resources/META-INF/neoforge.mods.toml`:

```toml
modLoader = "javafml"
loaderVersion = "[4,)"
license = "MIT"

[[mods]]
modId = "hellomod"
version = "1.0.0"
displayName = "Hello Mod"
```

- [ ] **Step 2: Write the failing test**

`host/src/test/java/dev/pumpkin/jvmhost/ModLoaderTest.java`:

```java
package dev.pumpkin.jvmhost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.junit.jupiter.api.Test;

class ModLoaderTest {
    private static Path testmodJar() {
        return Path.of(System.getProperty("pumpkin.testmod.jar"));
    }

    @Test
    void aModIdIsReadFromItsToml() throws Exception {
        ModLoader.ModCandidate candidate = ModLoader.discover(testmodJar());
        assertEquals("hellomod", candidate.modId());
        assertNotNull(candidate.mainClass());
    }

    @Test
    void loadingTheModRunsItsRegistrations() throws Exception {
        List<String> registered = new ArrayList<>();
        DeferredRegister.setSink((id, template) -> {
            registered.add(id + " from " + template);
            return registered.size();
        });

        Bootstrap.loadAndRegister(testmodJar().toString());

        assertEquals(List.of("hellomod:ruby_block from stone"), registered);
    }
}
```

Wire the jar path in `build.gradle`:

```groovy
project(':host') {
    test {
        dependsOn ':testmod:jar'
        systemProperty 'pumpkin.testmod.jar',
                project(':testmod').tasks.jar.archiveFile.get().asFile.absolutePath
    }
}
```

- [ ] **Step 3: Run and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle :host:test
```

Expected: compilation failure — `ModLoader` does not exist.

- [ ] **Step 4: Implement the loader**

`host/.../ModLoader.java`:

```java
package dev.pumpkin.jvmhost;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.neoforged.fml.common.Mod;

/**
 * Finds the mod inside a jar.
 *
 * <p>NeoForge scans annotations with its own index; this walks the jar instead. Slower and
 * entirely adequate for one mod, and it avoids depending on the loader's data format.
 */
public final class ModLoader {
    /** A mod found in a jar: its declared id, and the class annotated {@code @Mod}. */
    public record ModCandidate(String modId, Class<?> mainClass, URLClassLoader loader) {
    }

    private static final Pattern MOD_ID =
            Pattern.compile("^\\s*modId\\s*=\\s*\"([^\"]+)\"\\s*$", Pattern.MULTILINE);

    private ModLoader() {
    }

    /**
     * Reads a jar's {@code neoforge.mods.toml} and locates its {@code @Mod} class.
     *
     * @throws IOException           if the jar cannot be read
     * @throws IllegalStateException if the toml or the annotated class is missing
     */
    public static ModCandidate discover(Path jar) throws IOException {
        String modId;
        try (JarFile file = new JarFile(jar.toFile())) {
            JarEntry entry = file.getJarEntry("META-INF/neoforge.mods.toml");
            if (entry == null) {
                throw new IllegalStateException("no META-INF/neoforge.mods.toml in " + jar);
            }
            try (InputStream stream = file.getInputStream(entry)) {
                String toml = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                Matcher matcher = MOD_ID.matcher(toml);
                if (!matcher.find()) {
                    throw new IllegalStateException("no modId in " + jar);
                }
                modId = matcher.group(1);
            }
        }

        URLClassLoader loader =
                new URLClassLoader(new URL[] {jar.toUri().toURL()}, ModLoader.class.getClassLoader());
        Class<?> main = findAnnotatedClass(jar, loader);
        return new ModCandidate(modId, main, loader);
    }

    private static Class<?> findAnnotatedClass(Path jar, URLClassLoader loader) throws IOException {
        try (JarFile file = new JarFile(jar.toFile())) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.endsWith(".class")) {
                    continue;
                }
                String className = name.substring(0, name.length() - ".class".length()).replace('/', '.');
                try {
                    Class<?> candidate = Class.forName(className, false, loader);
                    if (candidate.isAnnotationPresent(Mod.class)) {
                        return candidate;
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError ignored) {
                    // A class referencing shim types that do not exist yet is expected while
                    // the shim is incomplete. It cannot be the entry point if it will not load.
                }
            }
        }
        throw new IllegalStateException("no @Mod class in " + jar);
    }
}
```

`host/.../Bootstrap.java`:

```java
package dev.pumpkin.jvmhost;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;

/** The entry point Rust calls to bring a mod up. */
public final class Bootstrap {
    private Bootstrap() {
    }

    /**
     * Loads a mod jar, constructs its {@code @Mod} class, and fires {@link RegisterEvent}.
     *
     * @param jarPath absolute path to the mod jar
     * @return the mod id that was loaded
     * @throws Exception if discovery, construction or registration failed; Rust turns this
     *                   into a loader error
     */
    public static String loadAndRegister(String jarPath) throws Exception {
        ModLoader.ModCandidate candidate = ModLoader.discover(Path.of(jarPath));
        IEventBus bus = new IEventBus();

        Constructor<?> constructor = candidate.mainClass().getConstructor(IEventBus.class);
        constructor.newInstance(bus);

        bus.post(new RegisterEvent());
        return candidate.modId();
    }
}
```

- [ ] **Step 5: Run and watch it pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle :host:test
```

Expected: 2 tests passing.

- [ ] **Step 6: Commit**

```bash
git add java/pumpkin-jvm-host/host java/pumpkin-jvm-host/testmod java/pumpkin-jvm-host/build.gradle
git commit -m "Construct a mod from its jar and let it register"
```

---

### Task 7: Wire it into Pumpkin

**Files:**
- Modify: `crates/pumpkin/src/plugin/loader/jvm/mod.rs`
- Modify: `crates/pumpkin/src/plugin/mod.rs:226-235`
- Modify: `crates/pumpkin/tests/jvm_host.rs`

**Interfaces:**
- Consumes: `vm::boot`, `vm::ModVm::call` (Task 3), `Bootstrap.loadAndRegister` (Task 6), `PluginLoader` (`plugin/loader/mod.rs`)
- Produces: `pub struct JvmPluginLoader` implementing `PluginLoader`, `can_load` true for `.jar`

- [ ] **Step 1: Write the failing test**

Append to `crates/pumpkin/tests/jvm_host.rs`:

```rust
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
    assert!(
        pumpkin_data::Block::from_name("hellomod:ruby_block").is_some(),
        "the mod's own code registered the block"
    );
}
```

- [ ] **Step 2: Run and watch it fail**

```bash
cd java/pumpkin-jvm-host && JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home gradle build && cd -
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins --test jvm_host
```

Expected: FAIL — `JvmPluginLoader` not found.

- [ ] **Step 3: Implement the loader**

Add to `crates/pumpkin/src/plugin/loader/jvm/mod.rs`:

```rust
//! Hosting a JVM so mods written in Java can run against Pumpkin.

pub mod handles;
pub mod natives;
pub mod vm;

use std::{any::Any, path::{Path, PathBuf}, sync::Arc};

use crate::plugin::{
    Context, Plugin, PluginFuture, PluginMetadata,
    loader::{LoaderError, PluginLoadFuture, PluginLoader, PluginUnloadFuture},
};

use vm::{ModVm, VmError};

/// Brings one mod up inside the VM and returns its declared mod id.
///
/// # Errors
/// Returns [`VmError::Java`] if discovery, construction or registration threw.
pub fn load_mod(vm: &'static ModVm, jar: &str) -> Result<String, VmError> {
    let jar = jar.to_owned();
    vm.call(move |env| {
        let path = env
            .new_string(&jar)
            .map_err(|err| VmError::Java(err.to_string()))?;

        let returned = env.call_static_method(
            "dev/pumpkin/jvmhost/Bootstrap",
            "loadAndRegister",
            "(Ljava/lang/String;)Ljava/lang/String;",
            &[(&path).into()],
        );

        // A Java exception leaves the env in a pending state; describe and clear it, or
        // the next call fails for the wrong reason.
        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java(format!("the mod at {jar} threw during load")));
        }

        let object = returned
            .and_then(jni::objects::JValueGen::l)
            .map_err(|err| VmError::Java(err.to_string()))?;

        env.get_string(&jni::objects::JString::from(object))
            .map(Into::into)
            .map_err(|err| VmError::Java(err.to_string()))
    })
}

/// A loaded Java mod, seen by Pumpkin as an ordinary plugin.
struct JvmPlugin {
    mod_id: String,
}

impl Plugin for JvmPlugin {
    fn on_load(&self, _context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
        // Registration already ran during load: it has to happen before the registries
        // freeze, which is earlier than a plugin's on_load.
        Box::pin(async { Ok(()) })
    }

    fn on_unload(&self, _context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
        let mod_id = self.mod_id.clone();
        Box::pin(async move {
            Err(format!("{mod_id} cannot be unloaded: the JVM outlives the server"))
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
            let mod_id = load_mod(vm, &jar)
                .map_err(|err| LoaderError::InitializationFailed(err.to_string()))?;

            let metadata = PluginMetadata {
                name: mod_id.clone().into(),
                version: "0.0.0".into(),
                authors: "unknown".into(),
                description: "A NeoForge mod hosted on the JVM".into(),
            };

            Ok((
                Arc::new(JvmPlugin { mod_id }) as Arc<dyn Plugin>,
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
```

Check `PluginMetadata`'s real field names and types before writing that literal — read `crates/pumpkin/src/plugin/mod.rs` and match it exactly. If the fields differ from the four above, use the real ones; the values are the point, not the shape.

- [ ] **Step 4: Register the loader**

In `crates/pumpkin/src/plugin/mod.rs`, in the `loaders` vec at line 231:

```rust
            loaders: RwLock::new(vec![
                Arc::new(NativePluginLoader),
                Arc::new(WasmPluginLoader::new(verify_plugin_signatures)),
                #[cfg(feature = "jvm-plugins")]
                Arc::new(crate::plugin::loader::jvm::JvmPluginLoader::new(
                    jvm_host_classpath(),
                )),
            ]),
```

and a private helper beside it:

```rust
/// Where the JVM host's jars live, relative to the server's working directory.
#[cfg(feature = "jvm-plugins")]
fn jvm_host_classpath() -> Vec<PathBuf> {
    ["shim", "fml", "host"]
        .iter()
        .map(|project| {
            PathBuf::from("java/pumpkin-jvm-host")
                .join(project)
                .join("build/libs")
                .join(format!("{project}.jar"))
        })
        .collect()
}
```

- [ ] **Step 5: Run and watch them pass**

```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins --test jvm_host
```

Expected: 5 passed.

- [ ] **Step 6: Verify the default build is untouched**

```bash
env -u JAVA_HOME cargo test -p pumpkin
```

Expected: PASS, and the JVM tests are compiled out.

- [ ] **Step 7: Lint and commit**

```bash
cargo clippy -p pumpkin --features jvm-plugins --all-targets -- -D warnings
git add crates/pumpkin/src/plugin
git commit -m "Load a Java mod the same way any other plugin is loaded"
```

---

## Done when

```bash
cd java/pumpkin-jvm-host && gradle build && cd -
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home \
  cargo test -p pumpkin --features jvm-plugins
```

passes, and `hellomod:ruby_block` exists in Pumpkin's dynamic registry because Java put it there.

## Explicitly not in this slice

The spec's sections 4 and 5, and work items 6–7 of its order of work. Named here so a reviewer does not read their absence as an oversight:

- No chunk `MemorySegment` mapping, no batched block entity tick, no deferred side-effect queue. **The reentrancy rule is unimplemented**, which is safe only because nothing in this slice calls Java from inside a tick.
- No FFM hot path. Everything goes through JNI, which is the wrong tool for the tick loop and the right one for registration.
- No capabilities, no `transfer.item`, no transactions. No block *behaviour* of any kind — this slice registers content, which is what the existing WIT already did; what is new is that a mod's own compiled code drove it.
- No ASM shim generator, no real MysticalAgriculture, no differential harness, no `join_server` in `neoforge-mcp`.
