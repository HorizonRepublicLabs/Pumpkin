# Hosting NeoForge mod code on Pumpkin

Date: 2026-08-28
Branch: `neoforge-support`
Status: design approved, not yet implemented

## The problem

Pumpkin can already put a NeoForge client in a world with a mod's blocks in it.
What it cannot do is make those blocks *do* anything.

`registry.wit` registers a block's shape — states, drops, hardness, block entity
type, the item that places it. It says so directly of block entity types: "does
not give it behaviour." The only logic a plugin can attach today arrives through
generic events: random tick, interact, break, redstone. That is enough for a crop
advancing its age. It is not enough for a reprocessor.

Closing that gap by hand, per mod, in Rust, was rejected. The chosen direction is
to run the mod's own compiled code.

## What already works

Built on `neoforge-support`, verified by reading the tree:

| piece | location |
| --- | --- |
| Brand-based NeoForge client detection | `net/java/neoforge/mod.rs::is_neoforge_client` |
| `neoforge:network` payload setup | `neoforge/payloads.rs::modded_network_setup` |
| `minecraft:register` announcement | `mod.rs::negotiation_payloads` |
| `neoforge-server.toml` push | `CONFIG_FILE_CHANNEL` config task |
| Frozen registry sync, 6 registries | `payloads.rs::SYNCED_REGISTRIES` |
| Plugin-declared mod channels | `neoforge/channels.rs`, `registry.wit::declare-network-channel` |
| Modded screen open | `neoforge/mod.rs::advanced_open_screen` |
| Dynamic block/item/entity/fluid/BE/menu registries | `pumpkin-data/src/dynamic/*` |
| Block behaviour, block entities, random tick, hardness and tool rules | recent commits |

The protocol layer is done. This design builds on it rather than revisiting it.

Note: `pumpkin.toml` carries no `[networking.neoforge]` section, so the feature
runs on its default of `enabled = false`. Testing requires enabling it explicitly.

## Measurements

Taken from the local mod checkouts by counting distinct `net.minecraft` and
`net.neoforged` imports across `*.java`.

| mod | .java files | `net.minecraft` types | server-side | `net.neoforged` types | `@Mixin` classes |
| --- | --- | --- | --- | --- | --- |
| MysticalAgriculture | 284 | 224 | 180 | 68 | 0 |
| Cucumber | 118 | 166 | 144 | 51 | 4 |
| MA + Cucumber union | — | — | **224** | **94** | 4 |
| Create | 2018 | 839 | 718 | 220 | 64 |
| Mekanism | 2984 | 841 | 617 | 311 | 1 |

Import counts undercount the true surface. They miss supertypes, inner classes,
same-package references and fully-qualified references, and each named type still
needs whichever members the mod actually calls.

### Measured at the bytecode level, 2026-08-29

The estimate above ("300 to 400 types") has been replaced by a measurement.
`javap -v` over the 438 class files in the built `MysticalAgriculture-26.2-9.0.7.jar`
and `Cucumber-26.2-9.0.5.jar`, reading constant-pool references rather than imports:

| surface | import count said | bytecode says |
| --- | --- | --- |
| `net.minecraft` classes | 224 | **353** (277 server-side, 76 client) |
| `net.neoforged` classes | 94 | **112** |
| distinct `net.minecraft` members called | not measured | **887** (777 server-side) |

So the class estimate was low by a third, and the number that actually predicts the
work — members to stub — is **777**, not something the import count could have shown.
That is still before closing over supertypes and the types appearing in used
signatures, so it is a floor.

The cost is concentrated, which is the useful part:

| class | server-side members called |
| --- | --- |
| `world/entity/player/Player` | 60 |
| `world/item/ItemStack` | 34 |
| `world/level/Level` | 25 |
| `world/item/Items` | 20 |
| `world/level/block/state/BlockState` | 16 |
| `world/effect/MobEffects` | 16 |

`Items`, `MobEffects` and `SoundEvents` are static holder classes — mostly constants,
far cheaper per member than the count suggests. `Player`, `ItemStack` and `Level` are
the real work, and all three are handle types under the split in section 1.

Signature source of truth is now available: `./gradlew setup` has been run in the
NeoForge checkout and `projects/base/src/main/java/net/minecraft/**` holds 7055
decompiled files. It independently confirms two corrections made during slice 1 —
`net/minecraft/resources/Identifier.java` exists and `ResourceLocation.java` does
not, and `ResourceKey.java` sits in `resources`, not `core`.

Create remains out of scope and the measurement does not change that.

## Scope

**In scope:** MysticalAgriculture and its library Cucumber, server-side logic
only, running as real compiled Java against a shim.

**Out of scope, deliberately:** Create (718 server types, 64 mixins into real
vanilla internals the shim will never have) and Mekanism (617). Rendering, and
anything under `neoforge.client.*`. Do not design for these.

## Approach: why not compile the mod to WASM

Compiling mod bytecode to WASM ahead of time was considered and rejected. It does
not avoid any of the work below — it needs the same `net.minecraft` shim — and it
adds a whole-program AOT closure requirement to a mod loading model built on
annotation scanning, `ServiceLoader`, reflective dispatch through
`DeferredRegister`, and, in Cucumber's case, a runtime bytecode transformer.
Mixin and AOT are fundamentally incompatible.

An embedded JVM is therefore not the slower path to the same place; it is the
only path that does not also require a research project. The WASM option stays
open per-mod later, because the bridge (below) keeps the plugin interface
unchanged.

## Design

### 1. Shim generation

The shim is ~300 to 400 Java classes standing in for `net.minecraft`. It is
generated, not written.

Signature source of truth is the local NeoForge checkout after `./gradlew setup`,
which populates `projects/base/src/main/java/net/minecraft/**` with decompiled
vanilla at the version Pumpkin's data targets. That directory is currently empty;
setup has not been run.

Pipeline:

1. ASM-scan the compiled MA and Cucumber jars for every reference into
   `net.minecraft.*` and `net.neoforged.*`: class, method and field references,
   supertypes of anything the mod extends or implements, and annotation types.
2. Close the set to a fixpoint — for each type, add its supertypes and every type
   appearing in the signature of a used member.
3. Emit stubs from the decompiled sources, retaining used members plus whatever is
   structurally required to compile: abstract methods of implemented interfaces,
   super constructors, enum constants, final field initialisers.
4. Every body throws `Unimplemented("Level.getBlockState")`. Never a silent
   default — a silent default yields a mod that runs and is quietly wrong.
5. Boot the mod, catch, implement against the bridge, repeat. The runtime surface
   is much smaller than the compile surface, and the thrown exceptions are the
   worklist, already ordered by what the mod does.

Two categories of shim type, handled differently:

- **Value types** — `ResourceLocation`, `BlockPos`, `Direction`, `Component`,
  read-only `ItemStack` views. Implemented fully in Java. Never cross the bridge.
- **Handle types** — `Level`, `ServerLevel`, `BlockEntity`, `Player`, mutable
  `ItemStack`. A thin wrapper over an opaque Pumpkin handle; every method crosses.

`BlockPos.offset()` must not be an FFI call. Getting this split right is most of
the performance story.

### 2. Bridge

The JVM runs in-process behind a third plugin loader, `plugin/loader/jvm/`,
sibling to the existing `native.rs` and `wasm/`. Pumpkin core keeps talking to one
plugin abstraction.

**Refactor before building.** Host semantics currently live in
`plugin/loader/wasm/wasm_host/wit/v0_1/{registry,world,inventory,block_entity,…}.rs`
— 33 modules. Extract the behaviour into a loader-agnostic `plugin/host/`, leaving
wasm and jvm as thin ABI adapters. Without this, every world operation is written
twice and the two copies drift.

**Threading: one mod thread per world, permanently attached.** `JNIEnv` is
per-thread and needs attaching, but the stronger reason is semantic: mod code
assumes vanilla's single-threaded world tick, so give it exactly that. Correct
semantics and zero attach cost follow from the same decision.

**Two call paths, split by cost rather than direction:**

- **Hot — raw FFM downcall, primitive arguments, no serialisation.**
  `get_block_state(level: u64, x: i32, y: i32, z: i32) -> u32`. Block state reads
  and writes, slot reads, energy reads: the machine tick.
- **Cold — protobuf, reusing PatchBukkit's `protoc-gen-ffi` generator.**
  Registration, screen opens, NBT blobs, configuration-phase payloads.

**Handles are `u64`: slab index plus generation counter,** with the slab owned by
Rust. A mod that stashes a `Level` in a static and touches it three ticks later
hits a generation mismatch and receives a clean Java exception rather than reading
freed memory. Mods do this routinely.

**Reentrancy needs an explicit rule, decided now.** Rust tick calls Java block
entity tick, which calls `setBlock`, which mutates Rust state, which fires a block
update event, which wants to call Java again on the same thread mid-call. There is
a call-depth guard, and nested events queue and drain at end of tick. Retrofitting
this after the mod thread deadlocks means unpicking every host function.

### 3. Mini-FML

94 distinct `net.neoforged` types across MA and Cucumber, of which 14 are
`client.event`. The real server surface is roughly 60 to 70 types. MA registers
through plain `DeferredRegister` and `@Mod`; there is no Registrate and no
Forge-era API in play.

The real NeoForge jar is not used. It carries 844 patches against decompiled
vanilla and assumes internals the shim does not have, and `fml.loading` is
ModLauncher — a transforming classloader and secure-jar layer that would have to
be emulated rather than used.

Subsystems to stub, ordered by what MA hits:

1. **`@Mod` and the mod event bus** (`bus.api`, `fml`) — read
   `neoforge.mods.toml` from the jar, construct the mod class, dispatch
   `FMLCommonSetupEvent` and `RegisterEvent`.
2. **`registries.DeferredRegister` and `DeferredHolder`** — maps onto the existing
   `register-block`, `register-item` and `register-menu-type` WIT calls. This is
   where the work already on the branch plugs in.
3. **`capabilities`, `transfer.item`, `transfer.transaction`** — machine item I/O.
   This is the missing block logic, and the hardest item here: transactions carry
   rollback semantics, so it is not a thin facade.
4. **Game events** (`event.entity.player`, `event.entity.living`) — routed from
   Pumpkin's existing event bus.
5. **`common.crafting` and `common.conditions`** — recipe serialisers and datapack
   load conditions.

Everything under `neoforge.client.*` becomes a no-op stub that accepts the
registration and discards it.

**Mixins are hand-folded, not executed.** Cucumber ships four. `ModelBakeryMixin`
is client-side and skipped. The three server ones —
`RecipeManager.prepare`, `ItemStack.<init>` and `ItemStack.applyDamage`, and
`ReloadableServerResources.updateComponentsAndStaticRegistryTags` — are plain
`@Inject` at method entry or return with `@Shadow` accessors, and every target is
a class the shim owns. They become three edits to shim source. No Mixin runtime,
no ModLauncher, no transforming classloader.

This is the payoff of owning the shim, and equally the reason Create's 64 mixins
into genuine vanilla internals stay out of scope.

### 4. Hot path

At 20 TPS the budget is 50ms per tick. Assume 2000 mod block entities ticking:
25µs each, all in. A chatty machine reading six neighbours plus inventory plus
energy costs roughly 20 crossings per tick, so 40,000 crossings per tick overall.

Order-of-magnitude estimates, to be measured rather than trusted:

| path | est. per crossing | at 40k crossings |
| --- | --- | --- |
| FFM downcall, primitives | ~10ns | 0.4ms |
| JNI upcall | ~100ns | 4ms |
| protobuf round trip | ~2µs | 80ms — over budget |

Rules:

1. **Map chunk state arrays into Java as `MemorySegment`s for the duration of the
   tick.** `getBlockState` then becomes a memory read in Java rather than a
   crossing, removing the largest crossing category outright. Reads are free;
   writes still go through the bridge.
2. **Batch the block entity tick.** One upcall per world per tick carrying the
   list of due block entity handles, with Java looping internally.
3. **Writes apply immediately; side effects defer.** A mod must observe its own
   `setBlock`, so writes go through to the mapped snapshot at once while their
   Rust-side consequences — neighbour updates, client packets, comparator updates
   — queue and drain at the end of the batch. This is the same queue as the
   reentrancy rule above, not a second mechanism.
4. **Zero per-tick allocation in shim value types.** A 20ms GC pause inside a 50ms
   budget is a visible lag spike, and generational ZGC only helps while the
   allocation rate stays low. This constrains the shim generator, and cannot be
   fixed after the fact.
5. **Instrument from the first commit** — µs/tick and crossings-per-tick counters.

### 5. Testing

`neoforge-mcp` is the acceptance driver: 13 agent-drivable tools including
`screenshot`, `get_block`, `insert_item`, `wait_ticks` and `run_command`. Two gaps
to close before it can drive this work:

- It is integrated-server only. There is no tool to join a remote server, which is
  required to point it at Pumpkin.
- `insert_item` goes through the block's server-side item capability. Against a
  remote Pumpkin it must route via a command or a test-only channel.

Layers:

1. **Differential testing against real NeoForge.** The same scripted session run
   against a real NeoForge dedicated server with MA and Cucumber, and against
   Pumpkin plus shim, diffing `get_block`, slot contents and entity state. This is
   the only layer that establishes correctness rather than absence of crashes.
   Seed the RNG; Minecraft is not otherwise deterministic.
2. **`Unimplemented` count as the burndown metric,** collected from a scripted
   session and tracked in CI. It is the best single progress number available.
3. **Shim conformance tests** generated alongside each stub. Cheap at generation
   time, impractical to backfill across 350 classes.
4. **Performance gate** in CI on µs/tick and crossings-per-tick.
5. **Explicit tests for the three hand-folded Cucumber mixins.** They are patches
   with no upstream to diff against, so nothing else catches a regeneration that
   silently drops one.

Layer 1 decides whether the project is real. The rest is bookkeeping around it.

## Order of work

1. Run `./gradlew setup` in the NeoForge checkout to obtain decompiled sources.
2. Extract `plugin/host/` from the wasm host modules.
3. Build the ASM scanner and stub generator; emit the shim; get MA to compile.
4. Stand up `plugin/loader/jvm/` with the handle table, the hot and cold call
   paths, and the reentrancy queue.
5. Mini-FML far enough to construct the mod and run registration through the
   existing WIT calls.
6. Add `join_server` to `neoforge-mcp`; stand up the differential harness.
7. Burn down `Unimplemented` against the scripted session.

## Open questions

- Whether `transfer.transaction` rollback can be expressed over the handle model
  without a Rust-side transaction log. Unresolved, and it is the riskiest single
  item in mini-FML.
- Whether chunk state arrays can be exposed as `MemorySegment`s without breaking
  Pumpkin's existing chunk locking.
