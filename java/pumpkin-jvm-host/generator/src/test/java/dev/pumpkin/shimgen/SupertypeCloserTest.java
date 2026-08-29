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
