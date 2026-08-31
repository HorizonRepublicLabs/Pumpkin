package dev.pumpkin.jvmhost;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.neoforged.fml.common.Mod;

/**
 * Finds the mod inside a jar.
 *
 * <p>NeoForge scans annotations with its own index; this walks the jar instead. Slower and
 * entirely adequate for one mod, and it avoids depending on the loader's data format.
 */
public final class ModLoader {
    /** A mod found in a jar: its declared id, and the class annotated {@code @Mod}. */
    public record ModCandidate(String modId, Class<?> mainClass, URLClassLoader loader) {
    }

    private static final Pattern MOD_ID =
            Pattern.compile("^\\s*modId\\s*=\\s*\"([^\"]+)\"\\s*$", Pattern.MULTILINE);

    private ModLoader() {
    }

    /**
     * Reads a jar's {@code neoforge.mods.toml} and locates its {@code @Mod} class.
     *
     * @throws IOException           if the jar cannot be read
     * @throws IllegalStateException if the toml or the annotated class is missing
     */
    public static ModCandidate discover(Path jar) throws IOException {
        String modId;
        try (JarFile file = new JarFile(jar.toFile())) {
            JarEntry entry = file.getJarEntry("META-INF/neoforge.mods.toml");
            if (entry == null) {
                throw new IllegalStateException("no META-INF/neoforge.mods.toml in " + jar);
            }
            try (InputStream stream = file.getInputStream(entry)) {
                String toml = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                Matcher matcher = MOD_ID.matcher(toml);
                if (!matcher.find()) {
                    throw new IllegalStateException("no modId in " + jar);
                }
                modId = matcher.group(1);
            }
        }

        URLClassLoader loader = sharedLoader(jar);
        Class<?> main = findAnnotatedClass(jar, loader);
        return new ModCandidate(modId, main, loader);
    }

    private static Class<?> findAnnotatedClass(Path jar, URLClassLoader loader) throws IOException {
        List<String> failures = new ArrayList<>();
        Throwable firstFailure = null;
        try (JarFile file = new JarFile(jar.toFile())) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.endsWith(".class")) {
                    continue;
                }
                String className = name.substring(0, name.length() - ".class".length()).replace('/', '.');
                try {
                    Class<?> candidate = Class.forName(className, false, loader);
                    Mod annotation = candidate.getAnnotation(Mod.class);
                    if (annotation != null) {
                        // A client-only entry point never constructs on a dedicated
                        // server; the jar's real entry is another class further along.
                        boolean serverSide = false;
                        for (net.neoforged.api.distmarker.Dist dist : annotation.dist()) {
                            if (dist == net.neoforged.api.distmarker.Dist.DEDICATED_SERVER) {
                                serverSide = true;
                            }
                        }
                        if (serverSide) {
                            return candidate;
                        }
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    // A class referencing shim types that do not exist yet is expected while
                    // the shim is incomplete, and it cannot be the entry point if it will not
                    // load - so skipping it is correct. But if this unloadable class was the
                    // actual @Mod class, discarding the reason silently turns a two-minute fix
                    // (missing shim type X) into "no @Mod class found", which sends whoever is
                    // debugging looking in the wrong place entirely. Remember it instead.
                    failures.add(className + ": " + e);
                    if (firstFailure == null) {
                        firstFailure = e;
                    }
                }
            }
        }
        String message = "no @Mod class in " + jar;
        if (!failures.isEmpty()) {
            message += "; " + failures.size() + " class" + (failures.size() == 1 ? "" : "es")
                    + " could not be loaded: " + String.join("; ", failures);
        }
        if (failures.size() == 1) {
            throw new IllegalStateException(message, firstFailure);
        }
        throw new IllegalStateException(message);
    }

    /**
     * One classloader for every mod, not one each.
     *
     * <p>Mods depend on each other: MysticalAgriculture extends Cucumber's {@code BaseBlock}.
     * With a loader per jar, the class is simply not visible and the mod dies on
     * {@code NoClassDefFoundError} naming a class that is sitting in the next jar along.
     * NeoForge puts every mod on one loader, and so must this.
     *
     * <p>Jars are added as they are discovered, so a mod can only see those loaded before
     * it. That is the same ordering constraint {@code ModList.isLoaded} carries and it is
     * why NeoForge mods do cross-mod work in setup events rather than constructors.
     */
    private static final java.util.List<URL> JARS = new java.util.ArrayList<>();

    private static URLClassLoader shared;

    private static synchronized URLClassLoader sharedLoader(Path jar) throws IOException {
        URL url = jar.toUri().toURL();
        if (!JARS.contains(url)) {
            JARS.add(url);
            // Rebuilt rather than mutated: URLClassLoader has no public way to add a URL, and
            // classes already loaded stay reachable through the new loader's parent chain
            // only if that loader is the one asked next -- which it is, since every lookup
            // goes through here.
            shared = new URLClassLoader(JARS.toArray(new URL[0]), ModLoader.class.getClassLoader());
        }
        return shared;
    }
}
