# Generating the `net.minecraft` shim

Date: 2026-08-29
Branch: `neoforge-support`
Status: implemented on `shim-generator`; seven figures below were measured wrong and
are corrected in place
Predecessor: `docs/superpowers/specs/2026-08-28-neoforge-mod-hosting-design.md`

## Corrections after implementation

The design held. Its numbers did not, and the artifact disproved them. They are
corrected inline below, each marked **Corrected**, with the original figure left
visible and the reason it was wrong stated — a spec quietly rewritten to match its
output stops being evidence of anything.

One mistake produced most of the others. **"Signature closure adds 0 new types" was
measured on erased descriptors.** `List<Ingredient>` erases to `Ljava/util/List;`,
and a descriptor-level closure sees `List` and stops. But the pruner emits the
*source* signature, which writes `Ingredient`, and `Ingredient` has to be generated
for the file to compile. Erasure hides exactly the types the closure has to chase.
So the one figure recorded as zero is the whole driver: 469 seed classes close to
1698 over 8 rounds, and against the spec's 353 that is a factor of roughly five.

| | spec said | measured |
| --- | --- | --- |
| added by signature closure | 0 | **1229**, over 8 rounds |
| classes to generate | 353 | **1698** in the used set, **1667** emitted |
| reference count at the finish line | 913 | **2103** |
| client classes | out of scope | **379 generated**, by a later ruling |
| `Unimplemented` lives in | `shim` | **`fml`** |
| source roots | 3 | **5** |
| `Direction`, `Vec3i`, `BlockPos` | copied whole | **stubbed** (`Direction` keeps its constants), and rightly so |

## Where this sits

The first slice hand-wrote eleven shim classes and proved the pipe: a mod jar's
own compiled Java reaches Pumpkin's dynamic registry through a JNI native, before
the registries freeze. It deliberately did not build a generator, on the grounds
that a generator written before anything had consumed a generated stub would be
guessing at its own output format. Something has now consumed one.

This slice builds the generator.

**Finish line: MysticalAgriculture and Cucumber link against the generated shim,
with every body throwing `Unimplemented`.** Nothing runs. The burndown starts in
the slice after this one.

## Decomposition

What was loosely called "slice 2" is four subsystems. They are listed here so
their absence from this spec reads as a decision rather than an oversight.

| | scope | depends on |
| --- | --- | --- |
| **A. Shim generator** | this spec | nothing |
| **B. Block behaviour** | give the JNI native a server handle so `first_state` and `drops` get wired | nothing |
| **C. Mini-FML real surface** | `capabilities`, `transfer.item`, `transfer.transaction` | A |
| **D. Hot path** | chunk `MemorySegment` mapping, batched block-entity tick, deferred side effects | C |
| **E. Differential harness** | `join_server` in `neoforge-mcp`, scripted session, diff against a real NeoForge server | nothing |

C is deliberately not designed yet. The predecessor spec's own method is that the
`Unimplemented` list is the worklist; designing capabilities before reading that
list is guessing, and this slice is what produces the list.

## Measurements

All figures are measured, not estimated. Sources: `javap -v` over the 438 class
files in `MysticalAgriculture-26.2-9.0.7.jar` and `Cucumber-26.2-9.0.5.jar`,
reading constant-pool references; supertype closure computed over the 7055
decompiled files in `NeoForge/projects/base/src/main/java`.

| quantity | value |
| --- | --- |
| `net.minecraft` classes referenced | 353 (277 server-side, 76 client) |
| server-side after supertype closure | **353** (277 seed + 76 supertypes, 6 rounds, 1.27x) |
| added by signature closure | **0** |

Both totals being 353 is a coincidence, not a typo: 277 server-side references plus
76 client ones, and separately 277 server-side seeds plus 76 supertypes pulled in by
closure. The number that governs this slice is the second — **353 server-side classes
to generate**. Client classes are out of scope.

> **Corrected — "added by signature closure: 0".** Wrong, and wrong in the direction
> that matters: it is the largest single term, not a zero one. The figure was taken
> over *erased descriptors*, where `List<Ingredient>` is `Ljava/util/List;` and
> `Ingredient` is simply not visible. The pruner emits the source signature, generics
> and all, so `Ingredient` must exist for the file to compile, and the closure has to
> chase it. Measured over what the source actually names: **469 seed classes close to
> 1698 over 8 rounds**, adding **1229**. Against the spec's 353 that is ~4.8x.
>
> **Corrected — "353 classes".** **1667 files are emitted**: 1386 `net.minecraft`,
> 215 `net.neoforged`, 66 `com.mojang`. (1698 classes are in the used set; 31 have no
> decompiled source and are hand-written under `fml/`.) The 1698-vs-353 gap is the
> closure correction above and nothing else — no rule was loosened to get there.
>
> **Corrected — "client classes are out of scope".** They are generated: **379 of
> the 1667** are client-side (261 `net.minecraft.client`, 63 `com.mojang.blaze3d`, 55
> under `net/neoforged/**/client/**`). This was a deliberate ruling during
> implementation, not drift. Both mods ship client code — `JeiCompat`,
> `ModRenderTypes`, `CropTintSource` — in the same jar as their server code, and the
> linkage check resolves *every* class in the jar. Declaring client classes out of
> scope would mean either failing the finish line on references that are really there,
> or special-casing the gate to look away, which is the defect class the whole check
> exists to prevent. Every generated body throws, so a client class costs a file and
> nothing else.

| quantity | value |
| --- | --- |
| distinct `net.minecraft` members called | 887 (777 server-side) |
| member references with descriptors | 913 |
| `net.neoforged` classes referenced | 112 |
| of the 353 server-side: enums / records / interfaces | 16 / 23 / 63 |

> **Corrected — "913 member references".** 913 was a count of `Methodref`/`Fieldref`
> constant-pool entries whose *declared owner* is shimmed. Two things it missed, both
> found by the check itself: class references (`new`, `checkcast`, `instanceof`,
> signature types) are references too, and javac writes the **static receiver type**
> as an owner, so `EnchanterRenderer` calling `EnchanterTileEntity.getBlockState()`
> records a mod class as the owner while the member is declared four levels up on
> `BlockEntity`. **The finish line is 2103**: 542 class references, 1237 members whose
> owner is shimmed, 324 reached through a mod class.

**Body-stripping is what makes the closed set closed.** Whole decompiled files
reference far beyond 353 in their bodies. Strip the bodies and only signature-level
references remain — which measurement shows add nothing new. Without stripping,
the closure runs toward all 7055 files; with it, 353 is a fixpoint.

> **Corrected.** The mechanism is right and is what makes the generator work at all;
> only the claim that signature-level references "add nothing new" is false, per the
> first correction. Restated: strip the bodies and the closure reaches a fixpoint at
> 1698 instead of running toward all 7055. That it *terminates* is the load-bearing
> claim. That it terminates near its seed was the wrong one.

## Decisions taken before design

**Generate once, then own it.** The generator bootstraps the shim, its output is
committed, and from then on the shim is ordinary hand-maintained source.
Regeneration is a rare, deliberate, conflict-resolving act. The generator is
scaffolding, not infrastructure. This is what makes hand-folded mixins and
hand-written method bodies safe.

**Signatures come from the decompiled sources, not from bytecode.** They carry
real parameter names — `hurtAndBreak(int amount, ServerLevel level, ServerPlayer
player, Consumer<Item> onBreak)`, not `arg0` — which matters for code the team
owns permanently. They carry no javadoc, which does not.

**A source-to-source pruner using JavaParser.** A line-based stripper avoids the
dependency but will silently mangle something across 353 files, and the mangling
surfaces as a mystery compile error far from its cause. A Rust tool would match
the repo's language while being the wrong instrument: parsing Java to emit Java.

## Design

### 1. Inputs and the used-set

Three source trees, because the mods reference NeoForge as well as Minecraft:

| source | classes | note |
| --- | --- | --- |
| `NeoForge/projects/base/src/main/java` | 353 MC | all 277 seeds verified present |
| `NeoForge/src/main/java` | 69 NeoForge | NeoForge's own code |
| hand-written | 43 | separate published artifacts: `bus`, `fml`, `neoforgespi`, `distmarker` |

The last row is smaller than it looks: 23 of the 43 are `neoforge/client/*` and take
the no-op client stubs the previous slice established, and three of the remainder
(`Mod`, `Event`, `IEventBus`) already exist. The genuine hand-written work is
roughly 17 classes across `bus/api`, `fml`, `fml/loading`, `fml/config` and
`neoforgespi/language` — annotations, interfaces and enums.

> **Corrected — "three source trees".** There are **five**, and the two extra ones are
> the reason client classes exist. `regen.sh` passes:
>
> | root | why |
> | --- | --- |
> | `NeoForge/projects/neoforge/src/main/java` | patched MC, server |
> | `NeoForge/projects/neoforge/src/client/java` | patched MC, client |
> | `NeoForge/projects/base/src/main/java` | the spec's one MC root; still needed, it holds what the patched trees do not override |
> | `NeoForge/src/main/java` | NeoForge's own code, server |
> | `NeoForge/src/client/java` | NeoForge's own code, client |
>
> The `projects/base` split alone was not enough: the decompiled tree NeoForge builds
> from is the **patched** one, and reading `base` only gives an unpatched `Block` with
> no `IBlockExtension`. The hand-written count also settled at 23 files, not 43.

Extraction runs inside the generator with ASM: `ClassReader` over both mod jars,
collecting class references and `Methodref`/`Fieldref`/`InterfaceMethodref` entries
into `net.minecraft` and `net.neoforged`, plus the supertypes of the mod's own
classes. Supertype closure then runs over the JavaParser ASTs, which are loaded
anyway.

**The resolved used-set is committed** as a text manifest beside the generated
shim: every class and member, with the mod class that referenced it. It is the
answer to "why does `Level` have these 25 methods and not 300", and without it the
pruning decisions are invisible.

### 2. Pruning rules

The closed set is not one kind of thing, and the split matters more than the
mechanics.

**Value types — copied whole, real bodies, never stubbed.** `Direction`'s constants
carry the semantics: `NORTH(2, 3, 2, "north", AxisDirection.NEGATIVE, Axis.Z, new
Vec3i(0, 0, -1))`. A stubbed `Direction` is useless, and a pruned one is wrong,
because dropping a constant shifts every ordinal after it and Minecraft serialises
by ordinal. The same holds for `Vec3i`, `BlockPos` and most records. This is the
predecessor spec's value/handle split arriving as a concrete rule.

> **Corrected — this rule describes the opposite of what ships, and the shipped rule
> is the better one.** `Direction` is not copied whole: it keeps its constant *names*
> and their *order* — `DOWN, UP, NORTH, SOUTH, WEST, EAST` — and every constructor
> argument, every method body and every constant class body is stubbed. `Vec3i` and
> `BlockPos` are not value types at all; both are HANDLE, stubbed like `Level`.
>
> The spec's argument for copying whole was sound about ordinals and wrong about
> everything else, because it did not follow its own reasoning one step further.
> `NORTH(2, 3, 2, "north", AxisDirection.NEGATIVE, Axis.Z, new Vec3i(0, 0, -1))` is a
> *real body*, and a real body names arbitrary types — that is precisely the thing
> body-stripping exists to prevent, and the same "signature closure adds 0" error the
> Measurements section made. Copy `Direction` whole and its constructor arguments drag
> in whatever they mention; do it across "most records" and the closure stops being
> closed. Nothing survives that reopening at 1698 classes, let alone at 353.
>
> Keeping the names and the order preserves everything the ordinal argument actually
> needed, at no cost to the closure. What the shipped rule concedes is that a *read* of
> a value is wrong rather than loud — `Direction.NORTH.getOpposite()` throws instead of
> returning `SOUTH` — and that is the honest failure, not the quiet one. The VALUE arm
> that copied constant-holder classes verbatim was removed for the same reason: it
> fired on zero of 277 real classes and admitted unbounded bodies for nothing.

**Handle types — pruned to used members, bodies throw.** `Level`, `ItemStack`,
`Player`. This is where the 777 figure pays: 25 methods on `Level`, not 300.

**Holder classes — the trap.** `Items.DIAMOND = registerItem(ItemIds.DIAMOND, new
Item.Properties()...)` calls registry code that cannot exist here, yet `public
static final Item DIAMOND` must be assigned to compile. Assigning `null` is the
failure mode the previous slice rejected twice: code that runs and is quietly
wrong. So the field is declared, assigned `null` to satisfy `final`, and the class
gets `static { throw new Unimplemented("Items"); }` — class initialisation fails on
first touch and the `null` is unreachable by construction. `Items`, `MobEffects`
and `SoundEvents` account for 49 of the 777 members.

Two mechanical traps, named now because both bite at first compile:

- **Constructors.** A body replaced by `throw` still receives an implicit
  `super()`, which fails when the superclass has no accessible no-arg constructor.
  Kept constructors need an explicit `super(...)` with default literals ahead of
  the throw.
- **`@Override`.** Valid only when the overridden member survived pruning.
  Simplest correct rule: drop every `@Override` from generated output.

Dropped: unused methods and fields, all bodies on handle types, nested classes
outside the used set. Unused imports stay — a warning, not an error, and chasing
them costs more than it saves.

### 3. The `Unimplemented` contract

`RuntimeException`, always: a checked exception would force `throws` clauses onto
generated signatures and break override compatibility with the supertypes the shim
reproduces.

It lives at `dev.pumpkin.shim.Unimplemented`, inside the `shim` Gradle project. The
layering rule from the previous slice stands — `shim` and `fml` must not depend on
`host`.

> **Corrected — the project, and the direction of the arrow.** The class name is
> right; the placement is not. `Unimplemented` is in **`fml`**
> (`fml/src/main/java/dev/pumpkin/shim/Unimplemented.java`), and the dependency arrow
> runs **`shim` → `fml`**, the reverse of what this section assumes.
>
> It has to. The spec pictured a clean layering with generated `net.minecraft` below
> hand-written `net.neoforged`. That layering does not exist, because the decompiled
> sources are NeoForge's **patched** Minecraft: `Block implements IBlockExtension` and
> `Item implements IItemExtension`, so generated `net.minecraft` and generated
> `net.neoforged` are mutually recursive. Gradle cannot express a cycle, so both live
> in `shim` as one compilation unit, and `fml` — the hand-written surface NeoForge
> publishes as separate artifacts, plus `Unimplemented` — sits underneath as the only
> thing `shim` can depend on. Every generated body throws `Unimplemented`, so it must
> be reachable from `shim`, which fixes the arrow.
>
> The layering rule itself survives intact: neither `shim` nor `fml` depends on `host`.

**Its message is the manifest's member key, verbatim**, including the descriptor:
`net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)V`.
Overloads are not hypothetical — `ItemStack.hurtAndBreak` has three, differing only
in parameters. Sharing identifiers with the manifest also lets the committed
used-set and the runtime failures be joined mechanically.

**That join is the burndown.** A `static` registry on `Unimplemented` records every
key thrown during a run. Subtracting from the manifest gives which members the mod
reached, which remain stubs, and how many of the 777 were never touched. The
expectation is that the last group is large, and it is the difference between
"implement 777 methods" and "implement the few dozen a boot needs". If that
expectation is wrong, the cost of subsystem C changes and it will be visible early.

It throws every time and never records-and-continues.

### 4. The Cucumber mixins

All three server-side mixin targets are in the closed set. **Four of the six members
they need are not in the used-set**, and cannot be:

| member | in used-set | why |
| --- | --- | --- |
| `ItemStack.copy` | yes | called normally elsewhere |
| `ItemStack.getItem` | yes | called normally elsewhere |
| `ItemStack.applyDamage` | no | named only in an `@Inject(method = "...")` string |
| `ItemStack.<init>` | no | same |
| `RecipeManager.prepare` | no | same |
| `ReloadableServerResources.updateComponentsAndStaticRegistryTags` | no | same |

Mixin targets live in annotation string literals and `@Shadow` members are
declarations on the mixin, not calls into the target, so a constant-pool scan
cannot see them. Pruning on the ASM used-set alone therefore deletes exactly the
members the mixins exist to patch, and fails late — when someone sits down to fold
one in and finds the method gone.

**So the extractor takes a second input: the mixin configs.** `cucumber.mixins.json`
is authoritative; it names the mixin classes and separates `mixins` from `client`.
For each server-side mixin, read its `@Mixin` target, add every
`@Inject`/`@Redirect`/`@ModifyArg` `method = "..."` target to the used-set, and add
every `@Shadow`-declared field and method as a member of the target class. `<init>`
is a legal target and is handled as one.

Folding stays manual and one-time, consistent with generate-once-then-own-it: the
three `@Inject`s become ordinary edits to `ItemStack.java`, `RecipeManager.java` and
`ReloadableServerResources.java`. `ModelBakeryMixin` is client-side and skipped.
The previous slice's requirement that these three carry explicit tests — a
hand-folded patch has no upstream to diff against — carries forward.

### 5. Verification

The primary gate is linkage, not compilation. The mods are already built; what
matters is that all 913 member references resolve against the shim with matching
descriptors. Loading every shim class and all 438 mod classes in one classloader
and forcing resolution surfaces failures as `NoSuchMethodError` /
`NoClassDefFoundError` naming the exact missing member. It covers precisely the
references that exist and runs in seconds.

> **Corrected — 913 is 2103**, for the reasons under Measurements. The mechanism is
> exactly as described and is what found the error.
>
> **And a boundary this section does not draw.** Linkage proves the shim is *complete
> with respect to the mods' references*. It does **not** prove the shim *loads on the
> classpath Pumpkin actually builds*, and the two are easy to read as one. The check
> runs with the fifteen declared game libraries (30 jars with transitives) on its
> classloader, because the shim reproduces real vanilla signatures that name them.
> The real VM `crates/pumpkin/tests/jvm_host.rs` boots has none of them: `shim`, `fml`
> and `host` jars only. `net/minecraft/world/level/block/Block` imports
> `com.mojang.serialization.MapCodec` and declares a member returning it, and the
> server has no copy of `MapCodec` anywhere. Nothing has broken because JVM resolution
> is lazy — no code path has touched that stubbed signature, so the reference is never
> resolved. Shipping fifteen game libraries with Pumpkin is a product decision well
> beyond this slice, so the gap is recorded rather than closed. The same paragraph is
> in `LinkageCheck`'s class javadoc, where someone reading a green "2103 of 2103" will
> meet it.

Compiling MysticalAgriculture from source against the shim would additionally
prove source-level compatibility, at the cost of rewiring a `build.gradle` in a
checkout we do not own, for a weaker guarantee than linkage. Not done.

Five checks, in running order:

1. `:shim:compileJava` succeeds. The constructor/`super(...)` and `@Override` traps
   surface here first.
2. **Linkage: 913 of 913 references resolve.** This is the finish line.
   **Corrected: 2103 of 2103**, and that is what the check reports today.
3. Manifest and output agree in both directions. Catches a silent drop, otherwise
   invisible until runtime.
4. Regeneration is deterministic — run twice, require byte-identical output. Map
   iteration order is the usual culprit, and without this the committed shim churns
   and code review stops working.
5. The four mixin-only members exist, named individually. If mixin-config parsing
   regresses, this is what catches it. **Asserted against the emitted source**, not
   against the manifest: the manifest is written before pruning runs, so a manifest
   assertion is green whatever the pruner does to the file that ships.

Not a finish-line criterion: that anything runs. Every body throws by construction.

## Open questions

- Whether the 17 hand-written `bus`/`fml`/`neoforgespi` classes are better written by
  hand or fetched from their published `-sources` artifacts. Hand-writing is
  proposed because the surfaces are small and slice 1 already wrote three of them,
  but a fetch would be more faithful.
- Whether `record` types in the closed set survive body-stripping intact. Records
  carry implicit members and a canonical constructor; the intent is to copy them
  whole as value types, but the generator must recognise them rather than treat
  them as handle types.
