# Shim Generator Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Generate the 353-class `net.minecraft` shim from decompiled sources so MysticalAgriculture and Cucumber link against it, every body throwing `Unimplemented`.

**Architecture:** A one-shot Java tool under `java/pumpkin-jvm-host/generator/`. ASM reads the two mod jars for the used-set; JavaParser reads the decompiled NeoForge sources, closes over supertypes, prunes to the used members, replaces bodies with `throw new Unimplemented(...)`, and emits into `shim/` and `fml/`. Output and the used-set manifest are committed; the generator is scaffolding, never re-run in CI.

**Tech Stack:** Java 25, Gradle 9.2.1 (committed wrapper), JavaParser, ASM, JUnit 5.

**Spec:** `docs/superpowers/specs/2026-08-29-shim-generator-design.md`

## Global Constraints

- Java source and target release **25**. Shim classes live under the literal packages `net.minecraft.*` and `net.neoforged.*`; host classes under `dev.pumpkin.jvmhost`; the generator under `dev.pumpkin.shimgen`.
- **`shim` and `fml` must not depend on `host`.** The dependency arrow is `host` → `fml` → `shim`. `generator` depends on nothing in that chain and nothing depends on it.
- `JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home`.
- **There is no `gradle` on PATH.** Use `cd java/pumpkin-jvm-host && ./gradlew`.
- Rust is untouched by this plan. `crates/` must not appear in any diff.
- External inputs live outside the repo, at paths relative to `java/pumpkin-jvm-host`:
  - decompiled Minecraft: `../../../NeoForge/projects/base/src/main/java`
  - NeoForge's own sources: `../../../NeoForge/src/main/java`
  - mod jars: `../../../MysticalAgriculture/build/libs/MysticalAgriculture-26.2-9.0.7.jar`, `../../../Cucumber/build/libs/Cucumber-26.2-9.0.5.jar`
  These are **never hardcoded** — they arrive as CLI arguments. CI does not have them.
- Commit after every task.

## Ruling the spec did not make

**All eleven of slice 1's hand-written shim classes collide with the generated set** — `Identifier`, `ResourceKey`, `Registries`, `Block`, `BlockBehaviour`, `Mod`, `Event`, `IEventBus`, `DeferredRegister`, `DeferredHolder`, `RegisterEvent`. Three carry behaviour the working `hellomod` path depends on and that vanilla does not have: `DeferredRegister.Sink` / `setSink`, `BlockBehaviour.Properties.pumpkinTemplate(String)`, and `Block.pumpkinTemplate()`.

Overwriting them breaks slice 1's seven passing integration tests. Skipping them means MysticalAgriculture cannot link, because it needs the real, far larger `Block` and `BlockBehaviour` API. Only one option reaches the finish line:

**Generated wins, and the Pumpkin-specific behaviour is re-applied as implemented bodies on the generated classes.** They become the first entries in the burndown the next slice inherits, and they are marked in the source as deliberate divergences from vanilla. Task 7 does this, and its gate is that slice 1's tests still pass.

## What CI can and cannot check

The generator's inputs are three checkouts that exist only on a developer machine. So:

| check | where |
| --- | --- |
| `:shim:compileJava` and `:fml:compileJava` succeed | CI |
| manifest and generated output agree both ways | CI |
| the four mixin-only members exist | CI |
| slice 1's `host` and Rust tests still pass | CI |
| **linkage: 913 of 913 references resolve** | local only — needs the mod jars |
| regeneration is byte-identical | local only — needs the NeoForge checkout |

The two local-only gates are run by hand at the end of Task 8 and their output pasted into the task report. Do not fake them in CI by committing the mod jars.

## File Structure

**New, under `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/`:**
- `Main.java` — argument parsing and pipeline wiring, nothing else
- `UsedSet.java` — the `(class, member)` model and the manifest's read/write format
- `JarScanner.java` — ASM over the mod jars; produces a `UsedSet`
- `MixinScanner.java` — mixin JSON configs and annotations; adds to a `UsedSet`
- `SupertypeCloser.java` — JavaParser ASTs; expands the class set to a fixpoint
- `Pruner.java` — decides what survives in one compilation unit
- `Emitter.java` — writes pruned sources to disk

**New, under `java/pumpkin-jvm-host/shim/src/main/java/dev/pumpkin/shim/`:**
- `Unimplemented.java` — the exception plus its static hit registry

**New, committed data:**
- `java/pumpkin-jvm-host/generator/used-set.txt` — the manifest

**Modified:**
- `java/pumpkin-jvm-host/settings.gradle` — add `generator`
- `java/pumpkin-jvm-host/build.gradle` — generator deps and its exclusion from the shim chain
- `.github/workflows/rust.yml` — extend the `jvm_plugins` job with the CI-runnable checks

Each generator class has one job and is separately testable against a fixture; that is why the pipeline is seven small files rather than one.

---

### Task 1: Generator module and the `Unimplemented` contract

**Files:**
- Modify: `java/pumpkin-jvm-host/settings.gradle`
- Modify: `java/pumpkin-jvm-host/build.gradle`
- Create: `java/pumpkin-jvm-host/shim/src/main/java/dev/pumpkin/shim/Unimplemented.java`
- Create: `java/pumpkin-jvm-host/shim/src/test/java/dev/pumpkin/shim/UnimplementedTest.java`

**Interfaces:**
- Consumes: nothing
- Produces:
  - Gradle project `:generator` with JavaParser and ASM on its compile classpath
  - `public final class Unimplemented extends RuntimeException`, constructor `Unimplemented(String memberKey)`, `public String memberKey()`, `public static java.util.Set<String> hits()`, `public static void resetHits()`

- [ ] **Step 1: Write the failing test**

`shim/src/test/java/dev/pumpkin/shim/UnimplementedTest.java`:

```java
package dev.pumpkin.shim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnimplementedTest {
    private static final String KEY =
            "net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)V";

    @BeforeEach
    void clearHits() {
        Unimplemented.resetHits();
    }

    /// Unchecked, or every generated signature would need a `throws` clause and
    /// would stop overriding the supertype it reproduces.
    @Test
    void isUnchecked() {
        assertTrue(RuntimeException.class.isAssignableFrom(Unimplemented.class));
    }

    /// The message is the manifest key verbatim, including the descriptor, because
    /// ItemStack.hurtAndBreak has three overloads and a bare method name cannot say
    /// which one a mod reached.
    @Test
    void carriesTheFullMemberKey() {
        Unimplemented thrown = new Unimplemented(KEY);
        assertEquals(KEY, thrown.memberKey());
        assertEquals(KEY, thrown.getMessage());
    }

    /// Constructing one records it, so subtracting hits from the manifest gives the
    /// burndown.
    @Test
    void recordsEveryKeyItWasConstructedWith() {
        new Unimplemented(KEY);
        new Unimplemented("net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
        assertEquals(2, Unimplemented.hits().size());
        assertTrue(Unimplemented.hits().contains(KEY));
    }

    /// The registry is read from tests and written from many mod threads.
    @Test
    void hitsAreNotMutableByCallers() {
        new Unimplemented(KEY);
        var hits = Unimplemented.hits();
        try {
            hits.clear();
        } catch (UnsupportedOperationException expected) {
            // an unmodifiable view is the intent
        }
        assertTrue(Unimplemented.hits().contains(KEY), "the registry survived a caller clearing its view");
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :shim:test
```

Expected: compilation failure — `Unimplemented` does not exist.

- [ ] **Step 3: Write `Unimplemented`**

```java
package dev.pumpkin.shim;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thrown by a generated shim member that has no implementation yet.
 *
 * <p>Unchecked on purpose: a checked exception would force `throws` clauses onto
 * generated signatures, and a generated method that declares a checked exception its
 * supertype does not no longer overrides it.
 *
 * <p>Never caught-and-defaulted anywhere in the shim. A member that returns a
 * plausible zero produces a mod that runs and is quietly wrong, which is worse than
 * one that stops.
 */
public final class Unimplemented extends RuntimeException {
    private static final Set<String> HITS = ConcurrentHashMap.newKeySet();

    private final String memberKey;

    /**
     * @param memberKey the manifest's key for this member, descriptor included, e.g.
     *                  {@code net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;}
     */
    public Unimplemented(String memberKey) {
        super(memberKey);
        this.memberKey = memberKey;
        HITS.add(memberKey);
    }

    /** The key this was constructed with. Joins against the committed manifest. */
    public String memberKey() {
        return memberKey;
    }

    /** Every key thrown so far in this JVM. Manifest minus this is the burndown. */
    public static Set<String> hits() {
        return Collections.unmodifiableSet(HITS);
    }

    /** Clears the registry. For tests; a server never calls this. */
    public static void resetHits() {
        HITS.clear();
    }
}
```

- [ ] **Step 4: Add the generator project**

`settings.gradle` becomes:

```groovy
rootProject.name = 'pumpkin-jvm-host'
include 'shim', 'fml', 'host', 'testmod', 'generator'
```

Append to `build.gradle`:

```groovy
// The generator is one-shot scaffolding. Nothing in the shim chain may depend on it,
// and it may not depend on the shim chain: it reads and writes Java as text.
project(':generator') {
    dependencies {
        implementation 'com.github.javaparser:javaparser-core:3.26.4'
        implementation 'org.ow2.asm:asm:9.7'
    }
    tasks.register('generateShim', JavaExec) {
        group = 'shim'
        description = 'Regenerates the shim. Requires the NeoForge and mod checkouts; not run in CI.'
        classpath = sourceSets.main.runtimeClasspath
        mainClass = 'dev.pumpkin.shimgen.Main'
    }
}
```

If either dependency fails to resolve, bump to the newest published version and record which you used in the report.

- [ ] **Step 5: Run the tests and watch them pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :shim:test :generator:compileJava
```

Expected: 4 tests pass; `:generator:compileJava` succeeds with no sources yet.

- [ ] **Step 6: Commit**

```bash
git add java/pumpkin-jvm-host/settings.gradle java/pumpkin-jvm-host/build.gradle \
        java/pumpkin-jvm-host/shim/src
git commit -m "Give a stubbed member a way to fail loudly, and name itself"
```

---

### Task 2: The used-set and its manifest format

**Files:**
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/UsedSet.java`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/UsedSetTest.java`

**Interfaces:**
- Consumes: nothing
- Produces:
  - `public final class UsedSet`, containing the **nested** `public record MemberRef(String owner, String name, String descriptor)` with `public String key()` returning `owner + "." + name + ":" + descriptor` — every call site writes `UsedSet.MemberRef`
  - `UsedSet` with `void addClass(String internalName, String referencedBy)`, `void addMember(MemberRef ref, String referencedBy)`, `java.util.SortedSet<String> classes()`, `java.util.SortedSet<MemberRef> members()`, `java.util.SortedSet<String> membersOf(String internalName)`, `void writeTo(java.io.Writer w)`, `static UsedSet readFrom(java.io.Reader r)`

- [ ] **Step 1: Write the failing test**

```java
package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;

class UsedSetTest {
    private static UsedSet sample() {
        UsedSet used = new UsedSet();
        used.addClass("net/minecraft/world/level/Level", "com/blakebr0/example/Thing");
        used.addMember(
                new UsedSet.MemberRef("net/minecraft/world/level/Level", "getBlockState",
                        "(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"),
                "com/blakebr0/example/Thing");
        return used;
    }

    @Test
    void aMemberKeyIsOwnerDotNameColonDescriptor() {
        var ref = new UsedSet.MemberRef("net/minecraft/world/item/ItemStack", "getItem",
                "()Lnet/minecraft/world/item/Item;");
        assertEquals("net/minecraft/world/item/ItemStack.getItem:()Lnet/minecraft/world/item/Item;", ref.key());
    }

    /// Two overloads differ only in descriptor, so the descriptor is part of identity.
    @Test
    void overloadsAreDistinctMembers() {
        UsedSet used = new UsedSet();
        String owner = "net/minecraft/world/item/ItemStack";
        used.addMember(new UsedSet.MemberRef(owner, "hurtAndBreak", "(ILnet/minecraft/server/level/ServerLevel;)V"), "x");
        used.addMember(new UsedSet.MemberRef(owner, "hurtAndBreak", "(ILnet/minecraft/world/entity/LivingEntity;)V"), "x");
        assertEquals(2, used.membersOf(owner).size());
    }

    /// The manifest is committed and diffed, so its order must not depend on hashing.
    @Test
    void theManifestRoundTripsAndIsSorted() {
        StringWriter out = new StringWriter();
        sample().writeTo(out);
        String text = out.toString();

        UsedSet reread = UsedSet.readFrom(new StringReader(text));
        StringWriter again = new StringWriter();
        reread.writeTo(again);
        assertEquals(text, again.toString(), "round trip is lossless");

        StringWriter second = new StringWriter();
        sample().writeTo(second);
        assertEquals(text, second.toString(), "two runs produce identical bytes");
    }

    @Test
    void theManifestSaysWhoReferencedEachEntry() {
        StringWriter out = new StringWriter();
        sample().writeTo(out);
        assertTrue(out.toString().contains("com/blakebr0/example/Thing"),
                "a reader must be able to see why an entry is present");
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test
```

Expected: compilation failure — `UsedSet` does not exist.

- [ ] **Step 3: Implement `UsedSet`**

Use `TreeSet`/`TreeMap` throughout — never `HashSet` or `HashMap` in anything that reaches the manifest, because the committed output must be byte-identical across runs. `MemberRef` implements `Comparable<MemberRef>` by comparing `key()`.

Manifest format, one entry per line, classes first then members, each with its referrers comma-separated after a tab:

```
CLASS	net/minecraft/world/level/Level	com/blakebr0/example/Thing
MEMBER	net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)L...;	com/blakebr0/example/Thing
```

`readFrom` parses exactly what `writeTo` produces and rejects any line that is neither `CLASS` nor `MEMBER` with an `IllegalArgumentException` naming the line.

- [ ] **Step 4: Run the tests and watch them pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test
```

Expected: 4 tests pass.

- [ ] **Step 5: Commit**

```bash
git add java/pumpkin-jvm-host/generator/src
git commit -m "Model the used-set as something that can be committed and diffed"
```

---

### Task 3: Scan the mod jars with ASM

**Files:**
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/JarScanner.java`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/JarScannerTest.java`

**Interfaces:**
- Consumes: `UsedSet`, `UsedSet.MemberRef` (Task 2)
- Produces: `public final class JarScanner` with `public static void scan(java.nio.file.Path jar, UsedSet into) throws java.io.IOException`

- [ ] **Step 1: Write the failing test**

The test builds its own tiny jar so it does not need the mod checkouts:

```java
package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class JarScannerTest {
    /// A class that calls Level.getBlockState and extends nothing interesting.
    private static byte[] callerClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/Caller", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "go",
                "(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/level/Level", "getBlockState",
                "(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", false);
        mv.visitInsn(Opcodes.POP);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    private static Path jarWith(byte[] classBytes) throws Exception {
        Path jar = Files.createTempFile("scanner", ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry("example/Caller.class"));
            out.write(classBytes);
            out.closeEntry();
        }
        return jar;
    }

    @Test
    void findsCalledMembersAndTheirOwners() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith(callerClass()), used);

        assertTrue(used.classes().contains("net/minecraft/world/level/Level"));
        assertTrue(used.membersOf("net/minecraft/world/level/Level").stream()
                .anyMatch(k -> k.contains("getBlockState")));
    }

    /// Types named only in a descriptor are still referenced types.
    @Test
    void findsTypesAppearingOnlyInSignatures() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith(callerClass()), used);
        assertTrue(used.classes().contains("net/minecraft/core/BlockPos"));
        assertTrue(used.classes().contains("net/minecraft/world/level/block/state/BlockState"));
    }

    /// The mod's own classes are not part of the shim.
    @Test
    void ignoresClassesOutsideMinecraftAndNeoforge() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith(callerClass()), used);
        assertFalse(used.classes().contains("example/Caller"));
        assertFalse(used.classes().contains("java/lang/Object"));
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*JarScannerTest*'
```

Expected: compilation failure — `JarScanner` does not exist.

- [ ] **Step 3: Implement `JarScanner`**

Walk every `.class` entry with `ClassReader.accept`. Use a `ClassVisitor` that records, keeping only names starting `net/minecraft/` or `net/neoforged/`:

- from `visitMethodInsn` / `visitFieldInsn`: a `MemberRef(owner, name, descriptor)`, plus `owner` as a class
- from every descriptor it sees, via `org.objectweb.asm.Type.getType(...)`: each object type's internal name as a class, unwrapping array types
- from `visit`'s `superName` and `interfaces` on the mod's own classes: those names as classes

Record the visited class's own internal name as the `referencedBy` for everything it contributes. Strip `$` and everything after it before recording a class, so nested types collapse onto their outer class.

- [ ] **Step 4: Run the tests and watch them pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*JarScannerTest*'
```

Expected: 3 tests pass.

- [ ] **Step 5: Commit**

```bash
git add java/pumpkin-jvm-host/generator/src
git commit -m "Read what a mod actually calls out of its own bytecode"
```

---

### Task 4: Scan the mixin configs

The measurement behind this task: of the six members Cucumber's mixins touch, **four are invisible to Task 3** because mixin targets are string literals inside annotations. Pruning without this deletes exactly the members the mixins exist to patch.

**Files:**
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/MixinScanner.java`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/MixinScannerTest.java`

**Interfaces:**
- Consumes: `UsedSet`, `UsedSet.MemberRef` (Task 2)
- Produces: `public final class MixinScanner` with `public static void scan(java.nio.file.Path jar, UsedSet into) throws java.io.IOException`

- [ ] **Step 1: Write the failing test**

```java
package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class MixinScannerTest {
    private static final String CONFIG = """
            {
              "package": "example.mixin",
              "mixins": ["ItemStackMixin"],
              "client": ["ModelBakeryMixin"]
            }
            """;

    /// A mixin shaped like Cucumber's: @Mixin(ItemStack.class), one @Inject naming a
    /// method by descriptor string, one @Shadow method.
    private static byte[] mixinClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/mixin/ItemStackMixin", null, "java/lang/Object", null);

        AnnotationVisitor mixin = cw.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        AnnotationVisitor targets = mixin.visitArray("value");
        targets.visit(null, Type.getObjectType("net/minecraft/world/item/ItemStack"));
        targets.visitEnd();
        mixin.visitEnd();

        MethodVisitor inject = cw.visitMethod(Opcodes.ACC_PUBLIC, "onApplyDamage", "()V", null, null);
        AnnotationVisitor at = inject.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/Inject;", false);
        AnnotationVisitor methods = at.visitArray("method");
        methods.visit(null, "applyDamage(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V");
        methods.visitEnd();
        at.visitEnd();
        inject.visitCode();
        inject.visitInsn(Opcodes.RETURN);
        inject.visitMaxs(0, 1);
        inject.visitEnd();

        MethodVisitor shadow = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT, "copy",
                "()Lnet/minecraft/world/item/ItemStack;", null, null);
        shadow.visitAnnotation("Lorg/spongepowered/asm/mixin/Shadow;", false).visitEnd();
        shadow.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static Path jar() throws Exception {
        Path jar = Files.createTempFile("mixin", ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry("example.mixins.json"));
            out.write(CONFIG.getBytes(StandardCharsets.UTF_8));
            out.closeEntry();
            out.putNextEntry(new JarEntry("example/mixin/ItemStackMixin.class"));
            out.write(mixinClass());
            out.closeEntry();
        }
        return jar;
    }

    /// The whole point: a member named only inside an @Inject string.
    @Test
    void addsInjectTargetsNamedOnlyInAnnotationStrings() throws Exception {
        UsedSet used = new UsedSet();
        MixinScanner.scan(jar(), used);
        assertTrue(used.membersOf("net/minecraft/world/item/ItemStack").stream()
                        .anyMatch(k -> k.contains("applyDamage")),
                "an @Inject target must survive pruning");
    }

    @Test
    void addsShadowedMembersToTheTargetClass() throws Exception {
        UsedSet used = new UsedSet();
        MixinScanner.scan(jar(), used);
        assertTrue(used.membersOf("net/minecraft/world/item/ItemStack").stream()
                .anyMatch(k -> k.contains(".copy:")));
    }

    /// Client mixins are listed separately in the config and are out of scope.
    @Test
    void skipsMixinsListedUnderClient() throws Exception {
        UsedSet used = new UsedSet();
        MixinScanner.scan(jar(), used);
        assertFalse(used.classes().contains("net/minecraft/client/resources/model/ModelBakery"));
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*MixinScannerTest*'
```

Expected: compilation failure — `MixinScanner` does not exist.

- [ ] **Step 3: Implement `MixinScanner`**

1. Find every `*.mixins.json` entry in the jar. Parse it without a JSON library — the files are flat, and adding a dependency for three string arrays is not worth it. Read `package`, `mixins`, and `client`; ignore everything else. Use only the classes listed under `mixins`, never `client`.
2. For each, load `<package>/<name>.class` from the jar and visit it with ASM.
3. From the class's `@Mixin` annotation, read the target internal names from its `value` array (`Type` constants) and add each as a class.
4. For each method annotated `@Inject`, `@Redirect`, `@ModifyArg`, `@ModifyArgs`, `@ModifyVariable` or `@ModifyConstant`, read the `method` array of strings. Each entry is either `name` or `name(descriptor)ret`; split at the first `(` and record a `MemberRef` against every target class, with the descriptor when present and the empty string when not.
5. For each field or method annotated `@Shadow`, record a `MemberRef` on every target class using the mixin member's own name and descriptor.

`referencedBy` is the mixin's internal name, so the manifest shows why the member is there.

- [ ] **Step 4: Run the tests and watch them pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*MixinScannerTest*'
```

Expected: 3 tests pass.

- [ ] **Step 5: Commit**

```bash
git add java/pumpkin-jvm-host/generator/src
git commit -m "See the members a mixin patches, which no call site mentions"
```

---

### Task 5: Close over supertypes

**Files:**
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/SupertypeCloser.java`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/SupertypeCloserTest.java`

**Interfaces:**
- Consumes: `UsedSet` (Task 2)
- Produces: `public final class SupertypeCloser` with constructor `SupertypeCloser(java.util.List<java.nio.file.Path> sourceRoots)`, `public com.github.javaparser.ast.CompilationUnit parse(String internalName)`, and `public void close(UsedSet used)`

- [ ] **Step 1: Write the failing test**

The test writes its own miniature source tree, so it needs no checkout:

```java
package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class SupertypeCloserTest {
    private static Path tree() throws Exception {
        Path root = Files.createTempDirectory("srcroot");
        write(root, "net/minecraft/world/entity/Entity.java",
                "package net.minecraft.world.entity; public abstract class Entity implements Nameable {}");
        write(root, "net/minecraft/world/entity/LivingEntity.java",
                "package net.minecraft.world.entity; public abstract class LivingEntity extends Entity {}");
        write(root, "net/minecraft/world/entity/player/Player.java",
                "package net.minecraft.world.entity.player; import net.minecraft.world.entity.LivingEntity; public abstract class Player extends LivingEntity {}");
        write(root, "net/minecraft/world/Nameable.java",
                "package net.minecraft.world; public interface Nameable {}");
        return root;
    }

    private static void write(Path root, String rel, String body) throws Exception {
        Path p = root.resolve(rel);
        Files.createDirectories(p.getParent());
        Files.writeString(p, body);
    }

    @Test
    void pullsInEveryTransitiveSupertype() throws Exception {
        UsedSet used = new UsedSet();
        used.addClass("net/minecraft/world/entity/player/Player", "mod/Thing");
        new SupertypeCloser(List.of(tree())).close(used);

        assertTrue(used.classes().contains("net/minecraft/world/entity/LivingEntity"), "direct super");
        assertTrue(used.classes().contains("net/minecraft/world/entity/Entity"), "transitive super");
        assertTrue(used.classes().contains("net/minecraft/world/Nameable"), "interface of a transitive super");
    }

    /// Closure must terminate, and running it twice must add nothing.
    @Test
    void isAFixpoint() throws Exception {
        UsedSet used = new UsedSet();
        used.addClass("net/minecraft/world/entity/player/Player", "mod/Thing");
        SupertypeCloser closer = new SupertypeCloser(List.of(tree()));
        closer.close(used);
        int afterFirst = used.classes().size();
        closer.close(used);
        assertEquals(afterFirst, used.classes().size());
    }

    /// A class outside every source root is not an error; it is hand-written instead.
    @Test
    void ignoresClassesWithNoSourceFile() throws Exception {
        UsedSet used = new UsedSet();
        used.addClass("net/neoforged/bus/api/IEventBus", "mod/Thing");
        new SupertypeCloser(List.of(tree())).close(used);
        assertTrue(used.classes().contains("net/neoforged/bus/api/IEventBus"));
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*SupertypeCloserTest*'
```

Expected: compilation failure — `SupertypeCloser` does not exist.

- [ ] **Step 3: Implement `SupertypeCloser`**

`parse` maps an internal name to `<root>/<name>.java` across the roots in order, returns the JavaParser `CompilationUnit`, and caches by internal name. Return `null` when no root has the file.

`close` runs a worklist to fixpoint: for each class in the set, parse it; if parsing yielded nothing, skip it; otherwise take the primary type's `extends` and `implements` names and resolve each to an internal name using, in order, the compilation unit's imports, its own package, then `java.lang`. Add any newly resolved `net/minecraft/**` or `net/neoforged/**` name to the set with `referencedBy` set to `"supertype of " + child`. Loop until a pass adds nothing.

Resolution is deliberately syntactic. There is no classpath here and JavaParser's symbol solver would need one; imports plus same-package is enough for a decompiled tree where every referenced type is either imported or local.

- [ ] **Step 4: Run the tests and watch them pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*SupertypeCloserTest*'
```

Expected: 3 tests pass.

- [ ] **Step 5: Commit**

```bash
git add java/pumpkin-jvm-host/generator/src
git commit -m "Expand the class set until a class's parents are all present"
```

---

### Task 6: Prune and emit

This is the task the spec's section 2 describes. Its three treatments are not stylistic: a pruned enum is *wrong*, because Minecraft serialises by ordinal.

**Files:**
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/Treatment.java`
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/Pruner.java`
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/Emitter.java`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/PrunerTest.java`

**Interfaces:**
- Consumes: `UsedSet` (Task 2), `SupertypeCloser.parse` (Task 5), `dev.pumpkin.shim.Unimplemented` by name only (Task 1)
- Produces:
  - `public enum Treatment { VALUE, HANDLE, HOLDER }` — top-level in `dev.pumpkin.shimgen`, its own file, referenced unqualified from `Pruner`
  - `public final class Pruner` with `public static Treatment treatmentOf(com.github.javaparser.ast.body.TypeDeclaration<?> type)` and `public static void prune(com.github.javaparser.ast.CompilationUnit cu, String internalName, UsedSet used)`
  - `public final class Emitter` with `public static void emit(com.github.javaparser.ast.CompilationUnit cu, String internalName, java.nio.file.Path outputRoot) throws java.io.IOException`

- [ ] **Step 1: Write the failing test**

```java
package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.jupiter.api.Test;

class PrunerTest {
    private static CompilationUnit parse(String src) {
        return StaticJavaParser.parse(src);
    }

    /// An enum's constants carry the semantics and its ordinals are serialised, so it
    /// is copied whole and never pruned.
    @Test
    void enumsAreValueTypesAndKeepEveryConstant() {
        CompilationUnit cu = parse("""
                package net.minecraft.core;
                public enum Direction {
                    DOWN(0, "down"), UP(1, "up"), NORTH(2, "north");
                    Direction(int id, String name) {}
                    public int getId() { return 0; }
                }
                """);
        assertEquals(Treatment.VALUE, Pruner.treatmentOf(cu.getType(0)));

        UsedSet used = new UsedSet();
        Pruner.prune(cu, "net/minecraft/core/Direction", used);
        String out = cu.toString();
        assertTrue(out.contains("DOWN") && out.contains("UP") && out.contains("NORTH"),
                "dropping a constant would shift every ordinal after it");
        assertFalse(out.contains("Unimplemented"), "a value type keeps its real bodies");
    }

    /// A handle type keeps only what the mods call, and every body throws.
    @Test
    void handleTypesKeepUsedMembersAndThrow() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level;
                public class Level {
                    public String getBlockState(int p) { return "real"; }
                    public String neverCalled() { return "gone"; }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/level/Level", "getBlockState", "(I)Ljava/lang/String;"), "mod");

        Pruner.prune(cu, "net/minecraft/world/level/Level", used);
        String out = cu.toString();
        assertTrue(out.contains("getBlockState"));
        assertFalse(out.contains("neverCalled"), "an uncalled member is pruned");
        assertTrue(out.contains("Unimplemented"), "the kept body throws");
        assertFalse(out.contains("\"real\""), "the original body is gone");
    }

    /// A holder's initializers call registry code that cannot exist. Assigning null
    /// silently would be the failure mode this project has rejected twice.
    @Test
    void holderClassesGetAThrowingStaticInitializer() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public class Items {
                    public static final Item DIAMOND = registerItem("diamond");
                    private static Item registerItem(String n) { return null; }
                }
                """);
        assertEquals(Treatment.HOLDER, Pruner.treatmentOf(cu.getType(0)));

        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/Items", "DIAMOND", "Lnet/minecraft/world/item/Item;"), "mod");
        Pruner.prune(cu, "net/minecraft/world/item/Items", used);
        String out = cu.toString();

        assertTrue(out.contains("DIAMOND"), "the field must exist to compile");
        assertFalse(out.contains("registerItem(\"diamond\")"), "the initializer cannot survive");
        assertTrue(out.contains("static {") && out.contains("Unimplemented"),
                "touching the class fails loudly rather than yielding null");
    }

    /// A body replaced by throw still gets an implicit super(), which fails when the
    /// superclass has no no-arg constructor.
    @Test
    void keptConstructorsGetAnExplicitSuperCall() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public class BlockItem extends Item {
                    public BlockItem(int id, String name) { super(id); }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/BlockItem", "<init>", "(ILjava/lang/String;)V"), "mod");
        Pruner.prune(cu, "net/minecraft/world/item/BlockItem", used);
        String out = cu.toString();
        assertTrue(out.contains("super("), "an explicit super call must precede the throw");
        assertTrue(out.contains("Unimplemented"));
    }

    /// @Override is only valid when the overridden member survived.
    @Test
    void overrideAnnotationsAreDropped() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level;
                public class Level {
                    @Override
                    public String toString() { return "x"; }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/level/Level", "toString", "()Ljava/lang/String;"), "mod");
        Pruner.prune(cu, "net/minecraft/world/level/Level", used);
        assertFalse(cu.toString().contains("@Override"));
    }
}
```

- [ ] **Step 2: Run it and watch it fail**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*PrunerTest*'
```

Expected: compilation failure — `Pruner` and `Treatment` do not exist.

- [ ] **Step 3: Implement `Treatment` and `Pruner`**

`treatmentOf`:
- `VALUE` for an enum, a record, or a class whose every field is `static final` of a primitive or `String` type — these are copied whole.
- `HOLDER` for a class where every used member is a `static final` field with an initializer, `Items` being the archetype.
- `HANDLE` otherwise.

`prune`, by treatment:
- **VALUE** — return without changing anything. The file is copied verbatim.
- **HANDLE** — remove every method, constructor and field whose `owner.name:descriptor` is absent from `used.membersOf(internalName)`, except: abstract methods declared by an implemented interface or extended abstract class, which are kept regardless because the class will not compile without them. Then replace every remaining method and constructor body with `throw new Unimplemented("<key>");`, prefixing constructors with `super(...)` filled from default literals (`0`, `false`, `null`, `'\0'`) matched to the superclass constructor's parameter types when the superclass declares no accessible no-arg constructor. Give fields that survive a default initializer if they are `final`.
- **HOLDER** — keep the used fields, strip their initializers, assign `null` for reference types and the zero literal for primitives, and add one `static { throw new Unimplemented("<internalName>"); }` block.

In all treatments except VALUE, remove every `@Override` annotation, and add `import dev.pumpkin.shim.Unimplemented;` when any throw was inserted.

`Emitter.emit` writes `cu.toString()` to `outputRoot/<internalName>.java`, creating parent directories, with a trailing newline and UTF-8. It never overwrites without writing — the caller decides what to regenerate.

- [ ] **Step 4: Run the tests and watch them pass**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:test --tests '*PrunerTest*'
```

Expected: 5 tests pass.

- [ ] **Step 5: Commit**

```bash
git add java/pumpkin-jvm-host/generator/src
git commit -m "Keep what a mod calls, keep what a value type means, throw for the rest"
```

---

### Task 7: Wire the pipeline, generate, and reconcile with slice 1

This is where the plan's ruling lands. Generation overwrites eleven classes that slice 1 wrote by hand, three of which carry behaviour the working `hellomod` path depends on and that vanilla has no equivalent for.

**Files:**
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/Main.java`
- Create: `java/pumpkin-jvm-host/generator/used-set.txt` (generated, committed)
- Create/overwrite: ~353 files under `java/pumpkin-jvm-host/shim/src/main/java/net/minecraft/`
- Create/overwrite: files under `java/pumpkin-jvm-host/fml/src/main/java/net/neoforged/`
- Modify by hand afterwards: `fml/.../DeferredRegister.java`, `fml/.../DeferredHolder.java`, `fml/.../IEventBus.java`, `shim/.../BlockBehaviour.java`, `shim/.../Block.java`

**Interfaces:**
- Consumes: everything from Tasks 1–6
- Produces: `Main.main(String[])` taking `--mc-sources`, `--neoforge-sources`, `--out-shim`, `--out-fml`, `--manifest`, and one or more `--mod-jar`

- [ ] **Step 1: Write `Main`**

Pipeline, in order: for each `--mod-jar`, `JarScanner.scan` then `MixinScanner.scan` into one `UsedSet`; `SupertypeCloser.close`; write the manifest to `--manifest`; then for each class in the set, `parse` it, and if a source was found, `Pruner.prune` and `Emitter.emit` into `--out-shim` for `net/minecraft/**` or `--out-fml` for `net/neoforged/**`. Classes with no source file are skipped and printed at the end under the heading `no source found (hand-write these):`, one per line.

Sort every iteration. The manifest and the file set must be byte-identical between runs.

- [ ] **Step 2: Run the generator**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:generateShim --args="\
 --mc-sources ../../../NeoForge/projects/base/src/main/java \
 --neoforge-sources ../../../NeoForge/src/main/java \
 --mod-jar ../../../MysticalAgriculture/build/libs/MysticalAgriculture-26.2-9.0.7.jar \
 --mod-jar ../../../Cucumber/build/libs/Cucumber-26.2-9.0.5.jar \
 --out-shim shim/src/main/java --out-fml fml/src/main/java \
 --manifest generator/used-set.txt"
```

Expected: a manifest listing roughly 353 classes and roughly 777 server-side members, and a "no source found" list of roughly 43 entries. Record the real numbers in your report; if they differ from these by more than about 10%, stop and say so rather than proceeding — the spec's cost estimate rests on them.

- [ ] **Step 3: Compile and let it fail**

```bash
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :shim:compileJava
```

Expected: failures. Work them down. The predicted categories, in the order they will appear: missing hand-written classes (Task 8 supplies them — for now, note them), constructors whose `super(...)` was not filled correctly, and generic bounds referring to pruned members. Fix the generator and regenerate rather than hand-editing generated files; a hand-edit that the generator would not reproduce is a lie the next regeneration exposes.

- [ ] **Step 4: Re-apply the three Pumpkin extensions by hand**

These are deliberate divergences from vanilla and must be marked as such. In each case add the member back to the freshly generated class with a real body and a comment saying it has no vanilla counterpart and why it exists:

- `BlockBehaviour.Properties.pumpkinTemplate(String)` and its accompanying `template()` — Pumpkin registers a block by copying a vanilla one, so something must name the template.
- `Block.pumpkinTemplate()` — reads the property set above.
- `DeferredRegister.Sink`, `setSink`, and the `flush()` that calls it — the sink exists so `fml` need not depend on `host`; without it `Bootstrap.installDefaultSink()` has nothing to install.

Preserve `DeferredHolder`'s two type parameters and `IEventBus`'s two-argument `addListener` — slice 1 corrected both against the real API, and the generated versions should already agree.

- [ ] **Step 5: Prove slice 1 still works**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew build
cd ../..
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home cargo test -p pumpkin --features jvm-plugins
```

Expected: the Java suite green, and all 7 `jvm_host` integration tests still passing. **If `hellomod` no longer registers its block, the reconciliation is wrong and the task is not done.** Do not weaken those tests.

- [ ] **Step 6: Commit**

```bash
git add java/pumpkin-jvm-host/generator/src java/pumpkin-jvm-host/generator/used-set.txt \
        java/pumpkin-jvm-host/shim/src java/pumpkin-jvm-host/fml/src
git commit -m "Generate the shim, and put back the three things vanilla does not have"
```

---

### Task 8: Hand-write the missing classes, then verify

**Files:**
- Create: ~17 files under `java/pumpkin-jvm-host/fml/src/main/java/net/neoforged/{bus/api,fml,fml/loading,fml/config,neoforgespi/language,api/distmarker}/`
- Create: ~23 no-op stubs under `java/pumpkin-jvm-host/fml/src/main/java/net/neoforged/neoforge/client/`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/ManifestConsistencyTest.java`
- Create: `java/pumpkin-jvm-host/generator/src/test/java/dev/pumpkin/shimgen/MixinMembersPresentTest.java`
- Create: `java/pumpkin-jvm-host/generator/src/main/java/dev/pumpkin/shimgen/LinkageCheck.java`
- Modify: `.github/workflows/rust.yml`

**Interfaces:**
- Consumes: the manifest at `generator/used-set.txt` and the emitted sources (Task 7)
- Produces: `LinkageCheck.main(String[])` taking `--shim-classes`, `--mod-jar` (repeatable), reporting unresolved references and exiting non-zero if any

- [ ] **Step 1: Write the hand-written classes**

Take them from the generator's "no source found" list. 23 are `neoforge/client/*` and get the no-op treatment slice 1 established: the class exists, methods accept their arguments and do nothing, because the server never draws. The remaining ~17 are annotations, interfaces and small enums across `bus/api`, `fml`, `fml/loading`, `fml/config`, `neoforgespi/language` and `api/distmarker`. Three of them — `Mod`, `Event`, `IEventBus` — already exist from slice 1; keep those.

Give every hand-written file a one-line comment saying it is hand-written because its source lives in a separately published NeoForge artifact, so a later reader does not delete it as un-regenerable.

- [ ] **Step 2: Write the two CI-runnable consistency tests**

`ManifestConsistencyTest` reads `generator/used-set.txt` and the emitted source tree and asserts both directions: every manifest class has a file or is on a documented hand-written list, and every emitted file appears in the manifest. `MixinMembersPresentTest` asserts individually that `ItemStack.applyDamage`, `ItemStack.<init>`, `RecipeManager.prepare` and `ReloadableServerResources.updateComponentsAndStaticRegistryTags` are present in the manifest — the four members a constant-pool scan cannot see, named one by one so a regression in mixin parsing is unmistakable.

- [ ] **Step 3: Run the CI-runnable checks**

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew build
```

Expected: `:shim:compileJava`, `:fml:compileJava` and all tests green.

- [ ] **Step 4: Write and run the linkage check — the finish line**

`LinkageCheck` builds a `URLClassLoader` over the compiled shim and fml classes plus the mod jars, then for every class in the mod jars calls `Class.forName(name, true, loader)` and, for each of its declared members, resolves the referenced members via reflection. Collect every `NoClassDefFoundError`, `NoSuchMethodError` and `NoSuchFieldError` with the referencing class, print them grouped by missing member, and exit non-zero if the list is non-empty.

Task 1 registered only `generateShim`, so add a second task beside it in `build.gradle`:

```groovy
    tasks.register('linkageCheck', JavaExec) {
        group = 'shim'
        description = 'Resolves every mod reference against the built shim. Needs the mod jars; not run in CI.'
        classpath = sourceSets.main.runtimeClasspath
        mainClass = 'dev.pumpkin.shimgen.LinkageCheck'
        dependsOn ':shim:classes', ':fml:classes'
    }
```

```bash
cd java/pumpkin-jvm-host
JAVA_HOME=/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home ./gradlew :generator:linkageCheck --args="\
 --shim-classes shim/build/classes/java/main:fml/build/classes/java/main \
 --mod-jar ../../../MysticalAgriculture/build/libs/MysticalAgriculture-26.2-9.0.7.jar \
 --mod-jar ../../../Cucumber/build/libs/Cucumber-26.2-9.0.5.jar"
```

Expected: `913 of 913 references resolved`. Paste the real output into your report. Anything less is the finish line unmet — report the unresolved list rather than adjusting the target.

- [ ] **Step 5: Check regeneration is deterministic**

```bash
cd java/pumpkin-jvm-host
cp generator/used-set.txt /tmp/manifest-first.txt
# re-run the Step 2 command from Task 7, then:
diff /tmp/manifest-first.txt generator/used-set.txt && echo "manifest stable"
cd ../.. && git status --short java/pumpkin-jvm-host/shim java/pumpkin-jvm-host/fml
```

Expected: no diff, and `git status` clean apart from the hand-edited files from Task 7 Step 4. A churning manifest means a `HashSet` or `HashMap` reached the output; find it and make it sorted.

- [ ] **Step 6: Extend CI with what CI can actually run**

In `.github/workflows/rust.yml`, the `jvm_plugins` job's Gradle step already runs `./gradlew build`, which now covers the shim compile and both consistency tests. Add a comment above it recording that the linkage and determinism gates are local-only because they need the NeoForge and mod checkouts, which CI does not have. Do not commit the mod jars to make them runnable.

- [ ] **Step 7: Commit**

```bash
git add java/pumpkin-jvm-host .github/workflows/rust.yml
git commit -m "Hand-write what NeoForge publishes elsewhere, and prove the mods link"
```

---

## Done when

```bash
cd java/pumpkin-jvm-host && ./gradlew build            # shim compiles, consistency tests green
cargo test -p pumpkin --features jvm-plugins           # slice 1's 7 integration tests still pass
# and, locally:
# LinkageCheck reports 913 of 913 references resolved
```

## Explicitly not in this plan

Named so their absence reads as a decision:

- **Nothing runs.** Every generated body throws by construction. The burndown — booting a mod and working down the `Unimplemented` hits — is the next slice.
- **Folding Cucumber's three `@Inject`s into the shim.** This plan makes sure their target members *survive pruning* (Task 4) and proves it (Task 8), because a constant-pool scan cannot see them and linkage would not catch their absence either. Applying the patch logic is behaviour, and nothing runs here, so it goes with the burndown.
- Subsystems B through E from the spec: block behaviour for JVM-registered blocks, the mini-FML capabilities and transaction surface, the hot path, and the differential harness.
- Compiling MysticalAgriculture from source against the shim. Linkage is the stricter check and does not require rewiring a `build.gradle` in a checkout we do not own.
- Any change to `crates/`. If a task seems to need one, stop and say so.
