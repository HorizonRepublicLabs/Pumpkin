# NeoForge hosting: assessment and next-phase design

Date: 2026-10-18
Branch: `master` (fork, 27 commits ahead of `origin/master`, merged with upstream `998c3f872`)
Supersedes the "Order of work" in `2026-08-28-neoforge-mod-hosting-design.md`.
That spec's "Status: not yet implemented" line is out of date. This document records where things stand.

## Where it stands

The project has done more than the August spec planned. Mekanism was out of scope there, yet the bridge
runs Mekanism, Generators, Tools, Additions, MysticalAgriculture and Cucumber server-side: machines smelt,
cables carry energy, pipes and tubes carry fluids and chemicals, transporters move items into vanilla
chests, GUIs open and slot clicks follow the mod's own rules. Underneath that is a working shim pipeline
(scan, close, prune, generate, reconcile), a mini-FML, dynamic registries in `pumpkin-data`, and a
NeoForge configuration-phase task queue.

All of this was measured by asking "does it crash?", never "is it right?". The spec's correctness
oracle (differential testing against real NeoForge) was never built, and most of the hot-path design
was replaced by the simplest thing that worked.

### Spec vs code

| Spec item | State | Evidence |
| --- | --- | --- |
| NeoForge client detection, `neoforge:network`, `minecraft:register`, server config push | Done | `net/java/neoforge/mod.rs`, `payloads.rs` |
| Frozen registry sync | 5 of 6: `minecraft:fluid` dropped on purpose | `payloads.rs:33-49` |
| Channel negotiation | **Not done.** The client's query reply only sets a flag and is never parsed; every client gets the full list | `pending.rs:651-659` |
| Plugin-declared channels | wasm only. JVM natives cannot declare one | `jvm/natives.rs:54-131` |
| Dynamic block/item/entity/BE/menu/sound/component/tag registries | Done. `register_fluid` has no caller | `pumpkin-data/src/dynamic/*` |
| Loader-agnostic `plugin/host/` | Partial: block specs only. The two wasm hosts' `registry.rs` are byte-identical copies | `plugin/host/registry.rs` |
| JVM loader | Done, via JNI with string-encoded arguments | `plugin/loader/jvm/*` |
| u64 slab+generation handles | Built and tested, **never used** | `jvm/handles.rs` |
| FFM hot path, protobuf cold path, MemorySegment chunk view | Not done | none |
| Reentrancy depth guard | Done (64) | `vm.rs:54-82` |
| End-of-tick side-effect queue | Not done ("deferred past this slice") | `vm.rs:69-71` |
| Batched block-entity tick | Not done: one blocking JNI round trip per BE per tick | `block/entities/plugin.rs:109` |
| Perf counters / perf gate | Not done | none |
| Mixins hand-folded into the shim | **Not done.** Cucumber's targets (`ItemStack.applyDamage`, `RecipeManager.prepare`) still throw | shim `ItemStack.java:203-209` |
| Differential testing against NeoForge | Not done | none |
| `Unimplemented` burndown in CI | Not done. `run-burndown.sh` is manual | |
| Shim conformance tests | Not done | |

### Defects found (each verified in code)

1. **Saved worlds remap when the mod set changes.** Pump is the default chunk format
   (`pumpkin-config/src/chunk.rs:18-20`), and it writes raw `u16` state ids (`palette.rs:952-975`)
   and raw ids for scheduled ticks. Dynamic ids are appended in registration order
   (`dynamic/blocks.rs:148-163`), and nothing records an id→name map. If you add, remove or reorder
   a mod, or an upstream vanilla bump changes `BASE_COUNT`, every modded block in every saved chunk
   silently becomes a different block or air. Anvil is safe because it saves blocks by name.
2. **Mod classloaders split.** `ModLoader.sharedLoader` builds a new `URLClassLoader(allJars, appLoader)`
   for each jar (`ModLoader.java:180-191`). Its parent is the app loader, not the previous loader,
   so a lookup through the newest loader redefines classes an earlier loader already defined. That
   means duplicate statics, and `ClassCastException` once objects cross between mods. The comment
   there claims the opposite.
3. **Version drift.** After the upstream merge the server accepts only 26.3 (protocol 777), but the
   shim was generated from NeoForge 26.2, the mod jars are 26.2, and all smoke bots send `PROTO = 776`.
   `regen.sh` reads the sibling `../../../NeoForge` checkout, which is now on `26.3.x`, so the next
   regen produces a 26.3 shim against 26.2 jars.
4. **Unsigned code loads by default.** `plugins.allow_unsigned = true` (`pumpkin-config/src/plugins.rs:58`).
   The JVM gets no options besides the classpath: no heap cap. Tokio workers block on `recv()` for every
   JVM call (`vm.rs:331-346`), so one slow mod stalls the server's async runtime.
5. **Unbounded per-connection channel set.** `client_channels` grows on every `minecraft:register`
   (`pending.rs:661-670`). This is the same class of bug NeoForge fixed upstream in 26.2 ("Enforce
   ad-hoc channel identifier limits").
6. **Licensing exposure.** About 1,745 generated `net/minecraft` shim files are committed, and 88 of
   them carry vanilla method bodies "verbatim" (about 298 markers). About 372 files derived from
   NeoForge (LGPL-2.1-only) have lost their SPDX headers, inside a GPL-3.0 repo.

## Goal of this phase

Turn "runs without crashing" into "behaves like NeoForge, and doesn't destroy worlds", on 26.3.
No new mods and no new subsystems until the oracle exists.

## Design

Ordered by the damage each one prevents. Each step is separately shippable.

### 1. A world remembers its own state ids — implemented

`crates/pumpkin-world/src/chunk/format/world_ids.rs`, wired into the Pump palette reader and
writer and into scheduled-tick block ids; `PumpkinServer::init_world_block_ids` runs it right
after the registry freezes.

**Decision:** the world owns a stable id space, the way Forge kept a `level.dat` id map in the
numeric-id era. Remapping on load and then overwriting the map was rejected: chunks not re-saved
in that run would be read with the wrong map next time.

- `<world>/pumpkin_block_ids.txt`: one `name state_count` line per block, in world-id order.
  Text, not NBT: it's read once per boot, and an operator can diff it.
- **Append-only.** A block seen for the first time is appended. A removed block keeps its line,
  so the ids after it never move. A block whose state count changed gets a new line, so the
  runtime→world mapping stays one-to-one and a written palette never repeats a value.
- Chunks are written in world ids and read back into this run's ids. When the list equals this
  run's registry, which is every run in which the block set didn't change, no translation
  happens and the hot path costs one `OnceLock` read.
- Reading with translation active rebuilds indexed palettes through `from_cube`, because two
  saved ids may now name one block (both removed → air).
- A world with no list is assumed to be in the current ids, which is exactly how it was read
  before.
- Failing to read or write the list is fatal at boot: running on could rewrite chunks as the
  wrong blocks.
- **Known ceiling:** chunk IO that happens before plugins finish loading uses raw ids, as before.
- **Tests:** four unit tests on the mapping (identity, reorder, removal keeps later ids, file
  round trip). The palette wiring is covered only by the existing untranslated tests, so
  translation through a real Pump file is not yet tested end to end.

### 2. One classloader — implemented

- `ModLoader` now keeps one `URLClassLoader` and appends each jar to it, instead of rebuilding
  it per jar. Every mod and every lookup goes through that one loader, so classes are never
  defined twice. A test compiles two jars where B reads a static of A, and asserts it's the
  same `Class` and the same value; that test fails against the old code.
- `mods.toml`: every `[[mods]]` modId is read, along with each dependency's `modId`, `ordering`
  and `type` (a minimal line parser, since the classpath has no TOML reader).
- **Ordering:** `Bootstrap.discoverLoadAfter` hands Rust a jar's required `AFTER` dependencies,
  and the JVM loader puts them in `PluginMetadata.dependencies`, so the plugin manager's
  existing topological sort constructs mods in NeoForge's order. A missing required dependency
  fails plugin loading, as NeoForge refuses to start.
- **Known ceilings:**
  - An optional `AFTER` dependency orders nothing, even when it's installed.
  - `BEFORE` is parsed but not used by the Rust sort; none of the current jars uses it.
  - `ModLoader.constructionOrder` (the Java sort) has no production caller.

### 3. Pin to 26.3 — blocked on the mods

Done:
- The smoke scripts share `tools/mod-smoke/smoke_env.py`: `PROTO = 777` and `PUMPKIN_BIN`
  (env var, defaults to `~/.cargo/target/debug/pumpkin`). The hardcoded home paths are gone.
  The bots hand-roll packets, so 26.2→26.3 packet changes may still break them; they haven't
  been run against 26.3.

Blocked: no 26.3 builds of MysticalAgriculture, Cucumber or Mekanism exist, locally or
upstream. Mekanism declares `minecraft_version_range=[26.2,26.3)`. Regenerating the shim now
would pair a 26.3 shim with 26.2 jars, so `regen.sh` was **not** run. Note that `regen.sh`
reads the sibling NeoForge checkout, which is now on `26.3.x`; don't run it until the jars
move. The version-range refusal in `Bootstrap` is deferred too, because today it would refuse
every jar. Options:
- **A.** Port the three mods to 26.3 locally (Mekanism is "homebaked" already).
- **B.** Wait for upstream 26.3 releases.
- **C.** Bring back 26.2 (protocol 776) in Pumpkin's version set. Upstream dropped it, so this
  is a fork-only protocol translation layer. Not recommended.

### 4. Real channel negotiation, plus common networking — implemented

The flow is now `NeoForge` 26.3's own:
- Every client gets an empty `neoforge:register` query before the brand.
- A `NeoForge` client answers with every payload it registered.
- `net/java/neoforge/negotiate.rs` ports `NetworkComponentNegotiator.negotiate`, and its
  result decides what happens next:
  - **Agreed:** the agreed set goes out on `neoforge:network`, followed by config files, the
    registry sync, then `c:version` → `c:register`.
  - **Refused:** `neoforge:modded_network_setup_failed` goes out with `NeoForge`'s own
    translation keys, and the connection is closed.

Notes:
- **The client never checks the agreed set; it trusts it.** So this server-side negotiation
  is the whole compatibility check. Before this change, every client got the full set
  unchecked.
- **What Pumpkin offers:**
  - `NeoForge`'s built-ins that Pumpkin implements, optional as `NeoForge` registers them:
    the frozen registry sync channels, `config_file`, and `advanced_open_screen`.
    `advanced_open_screen` was previously missing from the setup even though Pumpkin
    sends it.
  - Every declared channel, as required.
- **One deliberate difference from `NeoForge`:** a server channel with no flow accepts the
  client's. Declarations carry no flow, so strict rules would refuse every flowed mod
  channel.
- **NeoForge work waits for the query answer, not the brand.** The brand-based trigger is
  gone. Ordering is safe because the answer reaches the server before the known-packs
  reply.
- **JVM mods declare their channels.** `RegisterPayloadHandlersEvent` is posted per mod.
  The pinned shim `PayloadRegistrar` sends each registration through a sink to the new
  `declareNetworkChannel` native. Declaring the same channel and version for the other
  direction merges into one channel. Codecs and handlers are still dropped (mod payloads
  aren't delivered yet).
- **Common networking:** `c:version` (a version must be shared, otherwise disconnect, with
  NeoForge's missing `return` fixed) and `c:register` (records the client's play channels).
  These run for `NeoForge` clients, and for any client that announced both channels.
- **Channel cap:** the per-connection channel set is capped at `NeoForge`'s default of
  2048 for both `minecraft:register` and `c:register`.
- **Tests:** 7 negotiation rule tests, 2 parser tests, 3 end-to-end `negotiate_client`/
  payload tests, a shim registrar test, and the merge rule in the channel lifecycle test.
- **Live check:** `tools/mod-smoke/neoforgeprobe.py` now answers like a stock client. It also
  had a real 26.3 bug, fixed: clientbound known-packs moved from id 14 to 15.
  - Without mods, the full sequence runs through to finish.
  - With the Mekanism/MA jars, it is refused naming `mekanism:batch_security`, which is
    correct for a client without the mods.
  - Not yet run against a real NeoForge client.
- **Not done:**
  - Data-map, extensible-enum and feature-flag negotiation payloads.
  - `c:register` content beyond the declared serverbound play channels.
  - Identifier-length limits; packet size already bounds them.

### 5. Correctness oracle

The August spec said this layer "decides whether the project is real". It is also the cheapest
thing to build now, because the smoke drivers already do most of the work: boot the server, send
console commands, read the saved world back as NBT.

- `tools/mod-smoke/differential.py`: run one scenario (the existing `*drive.py` command scripts)
  twice, once against a real NeoForge 26.3 dedicated server with the same jars, `level-seed`
  and gamerules, and once against Pumpkin. Stop both servers, then diff:
  block states in the scenario's box, block-entity NBT (minus a small ignore list: timestamps, UUIDs),
  and container contents.
- Start with three scenarios already proven on Pumpkin: furnace-smelt + hopper, cable energy,
  transporter. A scenario counts as passing only on an empty diff.
- Use `/tick freeze` / `/tick step N` on both sides so tick counts line up. Don't try to make
  randomness deterministic beyond the seed; scenarios that depend on randomness don't go in the
  oracle.
- Run nightly in CI, not per-PR. The jars aren't redistributable, so they come from a private
  cache or a release-asset fetch keyed by version.
- Report the `Unimplemented` hit set from the same run as the burndown number, and track it next
  to the diff count.

**Rejected for now:** neoforge-mcp with a real client. It's a better test of rendering and client
sync, but that is not this phase's risk, and it needs a GPU-capable runner.

### 6. Bridge: measure before rebuilding

The spec's FFM/protobuf/MemorySegment design was skipped in favour of JNI plus strings. Don't
rebuild it on speculation:

- Add two counters per tick: JNI crossings and µs spent on the mod thread. Log a p50/p99 line once
  a minute, and add a `/pumpkin modstats` command.
- Then do only what the numbers justify, in this order:
  1. **Batched block-entity tick:** one call per world per tick with the due handles, instead of
     one blocking round trip per BE. This is the obvious first win at any non-trivial machine count.
  2. **End-of-tick side-effect queue** (spec §2/§4): needed for correctness anyway once a mod's
     `setBlock` triggers neighbour updates that call back into Java.
  3. **Use `HandleTable`** for level/BE references in that batched path. It already exists and is
     tested, and nothing calls it yet.
  4. Typed arguments instead of CSV strings on any path the counters show in the top five.
- FFM and MemorySegment chunk views stay deferred until a measured scenario misses the 50 ms budget.
- Stop blocking tokio: submit work from `spawn_blocking`, or make JVM calls `async` over a
  oneshot. The mod thread stays single and owns the JVM.

### 7. Cucumber's mixins

The spec planned this; it was never done. Fold the three server-side `@Inject`s into the pinned
shim sources (`RecipeManager.prepare`, `ItemStack.<init>`/`applyDamage`,
`ReloadableServerResources.updateComponentsAndStaticRegistryTags`). Then make
`MixinMembersPresentTest` assert the injected call, not just that the member exists. One test per
mixin, as the spec already asked.

### 8. Safe defaults

- When `jvm-plugins` is enabled, refuse unsigned JVM jars unless `allow_unsigned_jvm = true` is set
  explicitly (separate from the wasm setting: wasm is sandboxed, the JVM is not). Log loudly at boot.
- Pass `-Xmx` and `-XX:+ExitOnOutOfMemoryError` from config, plus `-XX:+UseZGC` per the spec's GC
  note.
- Doc note: a JVM mod runs with the server's full privileges. That's acceptable for operators who
  chose the jar, and it's the same trust model as NeoForge itself.

### 9. Licensing: decision needed before this goes public

Two options. Choosing between them is not an engineering call:

- **A. Stop committing generated `net/minecraft` sources.** Commit only the generator, the reconcile
  edits and the pinned files. CI and developers run NeoForge `setup` and `regen.sh`. This costs
  a much slower CI job and a NeoForge checkout dependency for every contributor.
- **B. Keep committing**, and restore `SPDX-License-Identifier: LGPL-2.1-only` plus NeoForge
  attribution on the 372 NeoForge-derived files. The Mojang-derived files stay a risk.

A is the safer default for a public repo. B restores the NeoForge attribution, but the
Mojang-derived files remain a risk under either option.

## Explicitly not in this phase

- Create (64 mixins into vanilla internals), and any mixin runtime.
- Rendering and `neoforge.client.*` beyond the existing no-op stubs.
- Hot-reload of JVM mods: the dynamic registry freezes once, by design.
- Merging `pumpkin-wasm-host-v0_1`/`v0_2` registry duplication. Real, but only a merge-friction
  cost; do it when one of them next changes.

## Order of work

1. §1 world state-id map: prevents data loss, needs no JVM, and could go upstream.
2. §2 single classloader.
3. §3 pin to 26.3: unblocks every later item, since nothing connects today.
4. §5 differential oracle on the three proven scenarios.
5. §4 negotiation and channel caps.
6. §7 Cucumber mixins (the oracle will show whether they matter for MA).
7. §6 counters, then batched BE tick and the side-effect queue.
8. §8 safe defaults; §9 once decided.

## Open questions

- Does upstream want §1? If yes, build it in upstream's shape first and rebase the fork onto it.
- Which NeoForge reason strings must be byte-identical for clients to show the right disconnect
  screen? Check against `NetworkRegistry` 26.3 when porting §4.
- How the jars get into CI for §5 (private cache vs self-hosted runner).
