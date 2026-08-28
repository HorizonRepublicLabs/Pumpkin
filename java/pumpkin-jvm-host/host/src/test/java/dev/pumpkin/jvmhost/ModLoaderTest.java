package dev.pumpkin.jvmhost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.junit.jupiter.api.Test;

class ModLoaderTest {
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
}
