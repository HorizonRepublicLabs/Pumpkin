package dev.pumpkin.jvmhost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import javax.tools.ToolProvider;
import dev.pumpkin.shim.PumpkinEventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ModLoaderTest {
    // Must match HelloMod.STATIC_INITIALIZED_PROPERTY. Hardcoded, not referenced, because this
    // module has no compile-time dependency on testmod - a real mod jar is loaded only at
    // runtime, from a path, the same way Bootstrap loads one.
    private static final String HELLOMOD_STATIC_INITIALIZED_PROPERTY =
            "pumpkin.testmod.hellomod.staticInitialized";

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

    @Test
    void theModClassIsNotInitializedUntilConstructed() throws Exception {
        System.clearProperty(HELLOMOD_STATIC_INITIALIZED_PROPERTY);
        try {
            ModLoader.ModCandidate candidate = ModLoader.discover(testmodJar());

            // Scanning the jar for @Mod must not run HelloMod's static initializer. If
            // ModLoader.findAnnotatedClass initialized eagerly, this would already be "true"
            // here, before the mod is ever constructed.
            assertNull(
                    System.getProperty(HELLOMOD_STATIC_INITIALIZED_PROPERTY),
                    "HelloMod's static initializer ran while merely discovering it");

            Constructor<?> constructor = candidate.mainClass().getConstructor(IEventBus.class);
            constructor.newInstance(new PumpkinEventBus());

            assertEquals("true", System.getProperty(HELLOMOD_STATIC_INITIALIZED_PROPERTY));
        } finally {
            System.clearProperty(HELLOMOD_STATIC_INITIALIZED_PROPERTY);
        }
    }

    /**
     * Compiles {@code source} (one top-level class, package {@code pkg}) and jars it with a
     * mods.toml, compiling against the test classpath plus {@code extraClasspath}.
     */
    private static Path modJar(Path dir, String name, String toml, String pkg, String source,
            String extraClasspath) throws Exception {
        Path src = dir.resolve(name + "-src/" + pkg + "/" + name + ".java");
        Path classes = dir.resolve(name + "-classes");
        Files.createDirectories(src.getParent());
        Files.createDirectories(classes);
        Files.writeString(src, source);
        int status = ToolProvider.getSystemJavaCompiler().run(null, null, null,
                "-d", classes.toString(),
                "-cp", System.getProperty("java.class.path") + java.io.File.pathSeparator
                        + extraClasspath,
                src.toString());
        assertEquals(0, status, "compiling " + name);
        Path jar = dir.resolve(name + ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry("META-INF/neoforge.mods.toml"));
            out.write(toml.getBytes(StandardCharsets.UTF_8));
            String entry = pkg + "/" + name + ".class";
            out.putNextEntry(new JarEntry(entry));
            out.write(Files.readAllBytes(classes.resolve(entry)));
        }
        return jar;
    }

    @Test
    void modsShareOneClassLoader(@TempDir Path dir) throws Exception {
        Path a = modJar(dir, "A", "[[mods]]\nmodId = \"moda\"\n", "sharea",
                "package sharea; @net.neoforged.fml.common.Mod(\"moda\")"
                        + " public class A { public static int VALUE; }", "");
        Path b = modJar(dir, "B", "[[mods]]\nmodId = \"modb\"\n", "shareb",
                "package shareb; @net.neoforged.fml.common.Mod(\"modb\")"
                        + " public class B { public static int read() { return sharea.A.VALUE; } }",
                dir.resolve("A-classes").toString());

        ModLoader.ModCandidate modA = ModLoader.discover(a);
        ModLoader.ModCandidate modB = ModLoader.discover(b);

        // B's view of A must be A itself, not a second definition with its own statics.
        assertSame(modA.mainClass(), Class.forName("sharea.A", false, modB.loader()));
        modA.mainClass().getField("VALUE").setInt(null, 42);
        assertEquals(42, modB.mainClass().getMethod("read").invoke(null));
    }

    @Test
    void everyModIdAndOrderingIsRead(@TempDir Path dir) throws Exception {
        String toml = """
                [[mods]]
                modId = "first"
                [[mods]]
                modId = "second"
                [[dependencies.first]]
                    ordering = "AFTER"
                    modId = "lib"
                [[dependencies.first]]
                    modId = "addon"
                    ordering = "BEFORE"
                [[dependencies.first]]
                    modId = "neoforge"
                    ordering = "NONE"
                [[dependencies.first]]
                    modId = "jei"
                    type = "optional"
                    ordering = "AFTER"
                """;
        Path jar = modJar(dir, "Multi", toml, "multi",
                "package multi; @net.neoforged.fml.common.Mod(\"first\") public class Multi {}", "");

        ModLoader.ModCandidate mod = ModLoader.discover(jar);

        assertEquals(List.of("first", "second"), mod.modIds());
        assertEquals(Set.of("lib"), mod.after());
        assertEquals(Set.of("addon"), mod.before());
    }

    @Test
    void modsConstructAfterWhatTheyOrderAfter() {
        ModLoader.ModCandidate addon = mod("addon", Set.of("lib"), Set.of());
        ModLoader.ModCandidate lib = mod("lib", Set.of("missing"), Set.of());
        ModLoader.ModCandidate early = mod("early", Set.of(), Set.of("lib"));
        ModLoader.ModCandidate plain = mod("plain", Set.of(), Set.of());

        assertEquals(List.of(early, lib, addon, plain),
                ModLoader.constructionOrder(List.of(addon, lib, early, plain)));
    }

    private static ModLoader.ModCandidate mod(String id, Set<String> after, Set<String> before) {
        return new ModLoader.ModCandidate(id, List.of(id), after, before, null, null, List.of());
    }
}
