package dev.pumpkin.jvmhost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import dev.pumpkin.shim.PumpkinEventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.junit.jupiter.api.Test;

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
}
