package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrunerTest {
    @BeforeEach
    void resetReport() {
        Pruner.resetReport();
    }

    /// The fixtures below include records and other post-Java-11 syntax, which
    /// StaticJavaParser rejects at its default language level.
    private static CompilationUnit parse(String src) {
        StaticJavaParser.getParserConfiguration()
                .setLanguageLevel(com.github.javaparser.ParserConfiguration.LanguageLevel.JAVA_21);
        return StaticJavaParser.parse(src);
    }

    /// An enum's constants carry the semantics and its ordinals are serialised, so every
    /// constant is kept, in order. Its BODIES are not: a copied-whole enum keeps real
    /// expressions naming arbitrary types that were never generated, which is the closure
    /// argument that body-stripping exists to preserve. Constructor arguments go with the
    /// constructors; a constant is left bare.
    @Test
    void enumsKeepEveryConstantAndLoseEveryBody() {
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
        used.addMember(new UsedSet.MemberRef("net/minecraft/core/Direction", "getId", "()I"), "mod");
        Pruner.prune(cu, "net/minecraft/core/Direction", used);
        String out = cu.toString();
        assertTrue(out.contains("DOWN") && out.contains("UP") && out.contains("NORTH"),
                "dropping a constant would shift every ordinal after it");
        assertFalse(out.contains("DOWN(0"), "a constant's constructor arguments are real expressions and go");
        assertFalse(out.contains("Direction(int id, String name)"),
                "with no arguments left, the declared constructor would have nothing to match");
        assertTrue(out.contains("Unimplemented"), "a used method keeps its signature and throws");
    }

    /// A record's components are its accessors and its canonical constructor, so the header
    /// is kept whole. Its declared bodies are stripped like anything else, and a
    /// non-canonical constructor is rewritten to delegate to the canonical one with cast
    /// defaults -- casts, because a bare `null` would be ambiguous between two constructors
    /// of the same arity.
    @Test
    void recordsKeepTheirComponentsAndLoseTheirBodies() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public record ItemCost(Item item, int count) {
                    public ItemCost(Item item) {
                        this(item, item.defaultCount());
                    }
                }
                """);
        assertEquals(Treatment.VALUE, Pruner.treatmentOf(cu.getType(0)));
        Pruner.prune(cu, "net/minecraft/world/item/ItemCost", new UsedSet());
        String out = cu.toString();
        assertTrue(out.contains("record ItemCost(Item item, int count)"), "the header is the record's identity");
        assertFalse(out.contains("item.defaultCount()"), "no decompiled expression may survive into the shim");
        assertTrue(out.contains("this((Item) null, (int) 0)"),
                "a non-canonical constructor must delegate, and its arguments must be unambiguous");
    }

    /// The pruner strips every comment. A `{@link Foo}` in a doc comment is enough to keep
    /// an import for a class the closure never generated, and that fails the compile on a
    /// line that is a comment.
    @Test
    void commentsAndTheImportsTheyKeepAliveAreStripped() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level;
                import net.minecraft.world.entity.Mob;
                import net.minecraft.world.entity.Player;
                /** See {@link Mob}. */
                public class Level {
                    public Player getPlayer() { return null; }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/level/Level", "getPlayer",
                "()Lnet/minecraft/world/entity/Player;"), "mod");
        Pruner.prune(cu, "net/minecraft/world/level/Level", used);
        String out = cu.toString();
        assertFalse(out.contains("Mob"), "an import kept alive only by a doc comment must go with the comment");
        assertTrue(out.contains("import net.minecraft.world.entity.Player;"),
                "an import a surviving signature still names must stay");
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
        assertTrue(Pruner.keptByFallback().isEmpty(),
                "String is a known java.lang name and resolves exactly; no fallback needed");
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

    /**
     * A holder keeps the static methods something actually calls, stubbed.
     *
     * <p>They used to be removed wholesale. {@code isHolder} matches any class of
     * constants with no instance methods, which is {@code Registries} but is equally
     * {@code Mth}, {@code Shapes} and {@code ARGB} -- static utilities the mods call
     * constantly. Removing their methods left {@code Mth.floor} to link against a file
     * containing nothing but a throwing static block, and the shim compiled perfectly
     * while the mods could not link. Keeping the method concedes nothing: the stub throws
     * on its own, naming itself.
     */
    @Test
    void holderClassesKeepTheStaticMethodsSomethingCalls() {
        CompilationUnit cu = parse("""
                package net.minecraft.util;
                public class Mth {
                    public static final float PI = 3.14F;
                    public static int floor(double value) { return (int) Math.floor(value); }
                    public static int unused(double value) { return 0; }
                }
                """);
        assertEquals(Treatment.HOLDER, Pruner.treatmentOf(cu.getType(0)));

        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/util/Mth", "floor", "(D)I"), "mod");
        Pruner.prune(cu, "net/minecraft/util/Mth", used);
        String out = cu.toString();

        assertTrue(out.contains("floor"), "a called static method must survive so the mod can link");
        assertFalse(out.contains("Math.floor"), "but its real body must not");
        assertFalse(out.contains("unused"), "a method nothing calls is still pruned");
        assertTrue(out.contains("Unimplemented"), "and what survives throws");
    }

    /**
     * A holder whose fields all got pruned away gets no static initializer.
     *
     * <p>The clinit protects against exactly one thing: a kept {@code final} field whose
     * real initializer was replaced by a default literal, so the class states a value --
     * {@code PI} as {@code 0.0F} -- that is silently wrong. When nothing survived to be
     * wrong, the clinit only makes the error worse. It fires before any stub can, with a
     * key naming the class and no member, which is how {@code Mth.floor} would become
     * unreachable behind {@code net/minecraft/util/Mth} the moment someone implements it.
     * Twenty-five of the forty-seven files carrying one were in this shape.
     */
    @Test
    void aHolderWithNoSurvivingFieldGetsNoStaticInitializer() {
        CompilationUnit cu = parse("""
                package net.minecraft.util;
                public class Mth {
                    public static final float PI = 3.14F;
                    public static int floor(double value) { return (int) Math.floor(value); }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/util/Mth", "floor", "(D)I"), "mod");
        Pruner.prune(cu, "net/minecraft/util/Mth", used);
        String out = cu.toString();

        assertFalse(out.contains("PI"), "nothing calls the field, so it is pruned like any other");
        assertFalse(out.contains("static {"),
                "and with no field left to misstate there is nothing for a clinit to protect");
        assertTrue(out.contains("floor"), "the stub is still there, and still throws on its own");
    }

    /**
     * The other side of the same rule: a holder that did keep a field keeps its clinit.
     *
     * <p>{@code Registries.BLOCK} survives pruning because a mod names it, and survives it
     * as {@code null} -- the registry call that built it cannot exist in the shim. A mod
     * reading it would get {@code null} back with nothing to say so, which is precisely
     * what the throwing initializer is for.
     */
    @Test
    void aHolderWithASurvivingFieldKeepsItsStaticInitializer() {
        CompilationUnit cu = parse("""
                package net.minecraft.core.registries;
                public class Registries {
                    public static final Object BLOCK = createRegistryKey("block");
                    public static final Object ITEM = createRegistryKey("item");
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/core/registries/Registries", "BLOCK",
                "Ljava/lang/Object;"), "mod");
        Pruner.prune(cu, "net/minecraft/core/registries/Registries", used);
        String out = cu.toString();

        assertTrue(out.contains("BLOCK"), "the field must exist to compile");
        assertFalse(out.contains("createRegistryKey"), "but its real initializer cannot survive");
        assertTrue(out.contains("static {") && out.contains("Unimplemented"),
                "so touching the class fails loudly rather than handing out null");
    }

    /// A class that merely declares its properties as static final constants but also
    /// declares an instance method (the `HorizontalDirectionalBlock`/`RuleTest` shape)
    /// is not a holder: it is behaviour-bearing, and mods extend it. Giving it a
    /// throwing static initializer would break every subclass at class-initialisation.
    @Test
    void classesWithInstanceMethodsAreNotHoldersEvenWithOnlyConstantFields() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level.block;
                public class HorizontalDirectionalBlock {
                    public static final Object FACING = null;
                    public Object getStateForPlacement(Object context) { return null; }
                }
                """);
        assertEquals(Treatment.HANDLE, Pruner.treatmentOf(cu.getType(0)),
                "an instance method disqualifies HOLDER regardless of the field shape");
    }

    /// The same shape again, but with the field a *primitive* constant — the case the
    /// sibling test above cannot reach, because its `Object` field never satisfied the
    /// constant-shape check in the first place. Deleting `hasOnlyStaticMethods` from
    /// `isHolder` makes this class a HOLDER: its fields are all `static final` with
    /// initializers, so nothing else stands between it and a throwing static
    /// initializer that would break every mod subclass at class-initialisation.
    @Test
    void constantOnlyClassesWithAnInstanceMethodAreHandlesNotHolders() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level.block;
                public class HorizontalDirectionalBlock {
                    public static final int FACING = 1;
                    public Object getStateForPlacement(Object context) { return null; }
                }
                """);
        assertEquals(Treatment.HANDLE, Pruner.treatmentOf(cu.getType(0)),
                "an instance method disqualifies HOLDER even when every field is a primitive constant");
    }

    /// VALUE means enum or record, full stop. A class of nothing but primitive/`String`
    /// constants used to classify VALUE and be copied verbatim — real bodies included,
    /// naming arbitrary types that were never generated. Body-stripping is the whole
    /// reason the emitted class set is closed, so this must be stubbed like anything
    /// else, whether or not it declares instance methods.
    @Test
    void constantOnlyClassesAreNeverValueTypes() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public class ItemNames {
                    public static final String DIAMOND = "diamond";
                    public static final int MAX_STACK = 64;
                    public static Object lookUp(String name) { return Registry.get(name); }
                }
                """);
        assertEquals(Treatment.HOLDER, Pruner.treatmentOf(cu.getType(0)),
                "a constant-only class is stubbed, not copied whole with its real bodies");

        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/ItemNames", "DIAMOND",
                "Ljava/lang/String;"), "mod");
        Pruner.prune(cu, "net/minecraft/world/item/ItemNames", used);
        String out = cu.toString();
        assertFalse(out.contains("Registry.get"), "the real body must not survive into the emitted source");
        assertTrue(out.contains("Unimplemented"), "the class must fail loudly instead");
    }

    /// A nested member type (the `Item$Properties` shape: a heavily-used nested class
    /// on real input) must be pruned recursively, exactly like a top-level type — not
    /// left whole. Left whole, its real bodies could reference arbitrary types outside
    /// the emitted set, and the closure argument for emitting 353 classes instead of
    /// ~7000 (stripping bodies to signature-level references is what stops the
    /// reference graph from spreading further) collapses for every class that has one.
    ///
    /// `Properties` naming itself in its own return type is a self-reference, resolved
    /// from the nesting context `prune` tracks while recursing — `Item$Properties` is
    /// known outright, with no lookup and so no chance for the by-name fallback to be
    /// needed here at all.
    @Test
    void nestedTypesArePrunedRecursivelyAndUnusedNestedMembersAreDropped() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public class Item {
                    public static class Properties {
                        public Properties stacksTo(int n) { return this; }
                        public Properties neverCalled() { return this; }
                    }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/Item$Properties", "stacksTo",
                "(I)Lnet/minecraft/world/item/Item$Properties;"), "mod");

        Pruner.prune(cu, "net/minecraft/world/item/Item", used);
        String out = cu.toString();
        assertTrue(out.contains("stacksTo"), "a used member of a nested type must survive");
        assertFalse(out.contains("neverCalled"),
                "an unused member of a nested type is pruned, not left whole with the type");
        assertTrue(out.contains("Unimplemented"), "the nested type's surviving body throws too");
        assertTrue(Pruner.keptByFallback().isEmpty(),
                "a nested type's self-reference is known outright, not guessed at");
    }

    /// When resolution genuinely cannot know the answer — a cross-package Minecraft
    /// type used with no import to say where it really lives — the by-name fallback is
    /// what keeps the member, and it must say so: the descriptor it guessed names a
    /// type that was never part of the closure `UsedSet` was built from, so that type
    /// will not have a generated file to compile against. That gap has to be visible
    /// at generation time, not discovered later as an opaque `:shim:compileJava` miss.
    @Test
    void fallbackKeptMembersReportThemselvesAndAnyMissingSignatureType() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level;
                public class Level {
                    public BlockPos getSpawnPos() { return null; }
                }
                """);
        UsedSet used = new UsedSet();
        // The real BlockPos lives in net.minecraft.core; nothing in this file says so
        // (no import), so resolution can only guess net/minecraft/world/level/BlockPos
        // — wrong, and a case the by-name fallback exists to keep safely anyway.
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/level/Level", "getSpawnPos",
                "()Lnet/minecraft/core/BlockPos;"), "mod");

        Pruner.prune(cu, "net/minecraft/world/level/Level", used);

        String fallbackKey = "net/minecraft/world/level/Level.getSpawnPos:()Lnet/minecraft/world/level/BlockPos;";
        assertTrue(Pruner.keptByFallback().contains(fallbackKey),
                "the exact descriptor guess misses, so this member was only kept by the by-name fallback");
        assertTrue(Pruner.missingTypesInKeptSignatures().contains("net/minecraft/world/level/BlockPos"),
                "the guessed return type was never added to the used set and will not be generated");
    }

    /// A `final float` field's default must carry the `F` suffix. `0.0` alone is a
    /// double literal, and `static final float X = 0.0;` is "incompatible types:
    /// possible lossy conversion from double to float" under javac. Minecraft declares
    /// many `static final float` constants, so this fires on the very next run.
    @Test
    void floatFieldsGetAnFSuffixedDefaultLiteral() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.entity;
                public class Mob {
                    public static final float DEFAULT_SPEED = 1.0F;
                    public void tick() {}
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/entity/Mob", "DEFAULT_SPEED", "F"), "mod");
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/entity/Mob", "tick", "()V"), "mod");

        Pruner.prune(cu, "net/minecraft/world/entity/Mob", used);
        String out = cu.toString();
        assertTrue(out.contains("DEFAULT_SPEED = 0.0F"),
                "a bare 0.0 double literal on a final float field does not compile");
    }

    /// A `final char` field's default must be written as the two-character escape
    /// `'\0'`. Emitting a raw NUL byte into the source text corrupts the diff this
    /// output is committed and reviewed as. `CharLiteralExpr.escape` does not do this:
    /// it escapes end-of-line characters only, and passes a NUL straight through.
    @Test
    void charFieldsGetAnEscapedNulDefaultNotARawNulByte() {
        CompilationUnit cu = parse("""
                package net.minecraft.util;
                public class ChatFormatting {
                    public static final char PREFIX_CODE = 'x';
                    public void apply() {}
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/util/ChatFormatting", "PREFIX_CODE", "C"), "mod");
        used.addMember(new UsedSet.MemberRef("net/minecraft/util/ChatFormatting", "apply", "()V"), "mod");

        Pruner.prune(cu, "net/minecraft/util/ChatFormatting", used);
        String out = cu.toString();
        assertEquals(-1, out.indexOf('\0'), "a raw NUL byte in emitted source corrupts the diff it is reviewed as");
        assertTrue(out.contains("PREFIX_CODE = '\\0'"),
                "the default must be the escaped source form, not the character itself");
    }

    /// `missingTypesInKeptSignatures` has to walk the *unerased* source signature. A
    /// kept `List<Ingredient>` writes `Ingredient` into the emitted source, and a
    /// `throws SomeMcException` writes that — both need a generated file to compile
    /// against, and an erasure-based walk reports neither (it sees only `java/util/List`
    /// and never looks at the throws clause at all).
    @Test
    void keptSignaturesReportGenericArgumentsAndThrownTypes() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item.crafting;
                import java.util.List;
                public class RecipeManager {
                    public List<Ingredient> ingredients() throws SomeMcException { return null; }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/crafting/RecipeManager", "ingredients",
                "()Ljava/util/List;"), "mod");

        Pruner.prune(cu, "net/minecraft/world/item/crafting/RecipeManager", used);

        assertTrue(Pruner.keptByFallback().isEmpty(),
                "the erased descriptor matches exactly; this test is about what erasure hides, not the fallback");
        assertTrue(Pruner.missingTypesInKeptSignatures()
                        .contains("net/minecraft/world/item/crafting/Ingredient"),
                "a generic type argument is named by the emitted source and must be reported when ungenerated");
        assertTrue(Pruner.missingTypesInKeptSignatures()
                        .contains("net/minecraft/world/item/crafting/SomeMcException"),
                "a thrown type is named by the emitted source and must be reported when ungenerated");
    }

    /// An interface field written `int FOO = 5;` carries no explicit `final` in
    /// source, but is final by JLS regardless. Trusting only the syntactic modifier
    /// strips the initializer without replacing it, emitting an uncompilable blank
    /// `int FOO;` inside the interface — a live path, since an interface always
    /// classifies HANDLE.
    @Test
    void interfaceConstantFieldsKeepACompilableDefaultInitializer() {
        CompilationUnit cu = parse("""
                package net.minecraft.tags;
                public interface BlockTags {
                    int FOO = 5;
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/tags/BlockTags", "FOO", "I"), "mod");

        Pruner.prune(cu, "net/minecraft/tags/BlockTags", used);
        String out = cu.toString();
        assertTrue(out.contains("FOO = 0"),
                "an interface constant is implicitly final and must keep a compilable initializer");
    }

    /// Constructors are never pruned by usage, used or not: `SupertypeCloser` closes
    /// the used set over classes, never members, so a superclass constructor a kept
    /// subclass's preserved `super(...)` call targets is very often simply absent from
    /// the used set. Pruning constructors by usage would delete the very constructor
    /// that call needs to keep compiling.
    @Test
    void everyDeclaredConstructorSurvivesRegardlessOfUsage() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public class BlockItem extends Item {
                    public BlockItem(int id) { super(id); }
                    public BlockItem(int id, String name) { super(id); }
                }
                """);
        UsedSet used = new UsedSet();
        // Only one of the two constructors is actually called by any mod.
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/BlockItem", "<init>", "(I)V"), "mod");

        Pruner.prune(cu, "net/minecraft/world/item/BlockItem", used);
        String out = cu.toString();
        assertTrue(out.contains("BlockItem(int id, String name)"),
                "an unused overload must still survive so a preserved super(...) elsewhere keeps compiling");
        // Keeping it unconditionally is not the same as vouching for its descriptor:
        // the key built here is embedded verbatim in its forMember(...) string, and
        // nothing in the used set confirms it. That has to stay visible in the report.
        assertTrue(Pruner.keptByFallback()
                        .contains("net/minecraft/world/item/BlockItem.<init>:(ILjava/lang/String;)V"),
                "a constructor kept with no matching used entry is not an exact descriptor match");
        assertFalse(Pruner.keptByFallback().contains("net/minecraft/world/item/BlockItem.<init>:(I)V"),
                "the constructor the used set does confirm is an exact match and must not be flagged");
    }

    /// A constructor's explicit `super(...)` used to be preserved, so that the implicit
    /// `super()` replacing it would not fail against a superclass with no no-arg
    /// constructor. It is dropped instead, because preserving the call preserved its
    /// ARGUMENTS -- real expressions calling real methods, the one place decompiled bodies
    /// survived into the shim. On the real input they produced "cannot find symbol",
    /// "invalid method reference" and "recursive constructor invocation". What makes
    /// dropping safe is the synthesised no-arg constructor every generated class now gets,
    /// which the implicit `super()` always has to bind to.
    @Test
    void constructorBodiesDropTheirSuperCallAndEveryClassGetsANoArgConstructor() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.item;
                public class BlockItem extends Item {
                    public BlockItem(int id, String name) { super(id, name.length()); }
                }
                """);
        UsedSet used = new UsedSet();
        used.addMember(new UsedSet.MemberRef("net/minecraft/world/item/BlockItem", "<init>", "(ILjava/lang/String;)V"), "mod");
        Pruner.prune(cu, "net/minecraft/world/item/BlockItem", used);
        String out = cu.toString();
        assertFalse(out.contains("super("),
                "the super call goes, and with it the argument expressions that named arbitrary members");
        assertFalse(out.contains("name.length()"), "no decompiled expression may survive into the shim");
        assertTrue(out.contains("Unimplemented"));
        assertTrue(out.contains("protected BlockItem() {"),
                "a synthesised no-arg constructor is what the dropped super call now binds to");
    }

    /// The synthesised constructor is empty rather than throwing: nothing calls it on
    /// purpose, and making it throw would mean no generated object could ever be built --
    /// including by the handful of classes whose real behaviour is re-applied by hand.
    @Test
    void theSynthesisedNoArgConstructorDoesNotThrow() {
        CompilationUnit cu = parse("""
                package net.minecraft.world.level.block;
                public class Block {
                    public Block(int id) {}
                }
                """);
        Pruner.prune(cu, "net/minecraft/world/level/block/Block", new UsedSet());
        String out = cu.toString();
        int at = out.indexOf("protected Block() {");
        assertTrue(at >= 0, "every class gets one");
        assertEquals("protected Block() {\n    }", out.substring(at, at + "protected Block() {\n    }".length()));
    }

    /// A class whose superclass is not in the generated set -- Netty's `ByteBuf` under
    /// `FriendlyByteBuf` -- keeps every method, because nothing here can enumerate the
    /// abstract members it is obliged to implement.
    @Test
    void aClassExtendingSomethingUngeneratedKeepsEveryMethod() {
        CompilationUnit cu = parse("""
                package net.minecraft.network;
                import io.netty.buffer.ByteBuf;
                public class FriendlyByteBuf extends ByteBuf {
                    public void writeZero(int length) {}
                }
                """);
        UsedSet used = new UsedSet();
        used.addClass("net/minecraft/network/FriendlyByteBuf", "mod");
        Pruner.prune(cu, "net/minecraft/network/FriendlyByteBuf", used);
        assertTrue(cu.toString().contains("writeZero"),
                "nothing here knows ByteBuf declares writeZero abstract, so nothing may be pruned");
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
