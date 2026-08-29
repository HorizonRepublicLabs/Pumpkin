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
                "package net.minecraft.world.entity; import net.minecraft.world.Nameable; "
                        + "public abstract class Entity implements Nameable {}");
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

    /**
     * The keep set is built from this, and it is what makes an inherited member survive:
     * a mod calling {@code Player.getHealth()} names {@code Player} in its constant pool,
     * while {@code getHealth} is declared on {@code LivingEntity}.
     */
    @Test
    void supertypesOfWalksTransitivelyThroughClassesAndInterfaces() throws Exception {
        SupertypeCloser closer = new SupertypeCloser(List.of(tree()));
        assertEquals(
                List.of("net/minecraft/world/Nameable",
                        "net/minecraft/world/entity/Entity",
                        "net/minecraft/world/entity/LivingEntity"),
                List.copyOf(closer.supertypesOf("net/minecraft/world/entity/player/Player")));
    }

    /**
     * Member owners are routinely nested types -- {@code BlockBehaviour$Properties},
     * {@code BlockBehaviour$BlockStateBase} -- which have no file of their own, so the
     * declaration has to be found inside the enclosing unit. {@link
     * SupertypeCloser#close} never faces this, because it only ever reads a file's
     * primary type.
     */
    @Test
    void supertypesOfResolvesANestedTypesOwnSupertypes() throws Exception {
        Path root = tree();
        write(root, "net/minecraft/world/level/block/state/BlockBehaviour.java",
                "package net.minecraft.world.level.block.state; import net.minecraft.world.Nameable; "
                        + "public abstract class BlockBehaviour { "
                        + "public abstract static class BlockStateBase implements Nameable {} }");
        SupertypeCloser closer = new SupertypeCloser(List.of(root));
        assertEquals(
                List.of("net/minecraft/world/Nameable"),
                List.copyOf(closer.supertypesOf(
                        "net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase")));
    }

    /// A class with no source has no known supertypes, and that is not an error.
    @Test
    void supertypesOfIsEmptyForAClassWithNoSource() throws Exception {
        SupertypeCloser closer = new SupertypeCloser(List.of(tree()));
        assertTrue(closer.supertypesOf("net/neoforged/bus/api/IEventBus").isEmpty());
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
