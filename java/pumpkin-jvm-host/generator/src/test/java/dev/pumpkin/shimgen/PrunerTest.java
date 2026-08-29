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

    /// A nested member type (the `Item$Properties` shape: a heavily-used nested class
    /// on real input) must be pruned recursively, exactly like a top-level type — not
    /// left whole. Left whole, its real bodies could reference arbitrary types outside
    /// the emitted set, and the closure argument for emitting 353 classes instead of
    /// ~7000 (stripping bodies to signature-level references is what stops the
    /// reference graph from spreading further) collapses for every class that has one.
    ///
    /// This also exercises the by-name fallback: `Properties` is a self-reference with
    /// no import, so `SupertypeCloser`'s resolution order (import, then package, then
    /// `java.lang`) misresolves it to `net/minecraft/world/item/Properties`, missing
    /// the `Item$` prefix a real descriptor carries. The exact `name:descriptor` lookup
    /// therefore misses even for the used method, and only the by-name fallback saves it.
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
