package dev.pumpkin.jvmhost;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
    /**
     * A mod found in a jar: its declared id, and the class annotated {@code @Mod}.
     *
     * <p>{@code modIds} is every {@code [[mods]]} entry in the jar, {@code modId} the first.
     * {@code after} and {@code before} are the mod ids its {@code [[dependencies.*]]} entries
     * order it after and before.
     */
    public record ModCandidate(String modId, List<String> modIds, Set<String> after,
            Set<String> before, Class<?> mainClass, URLClassLoader loader,
            List<Class<?>> eventBusSubscribers) {
    }

    // ponytail: line-based, not a TOML parser -- handles the `key = "value"` and
    // `[[table]]` lines mods.toml uses; inline tables and multi-line strings are not read.
    // The only TOML library around is night-config core, which has no TOML reader.
    private static final Pattern TABLE = Pattern.compile("^\\s*\\[\\[\\s*([^\\]]+?)\\s*\\]\\]");
    private static final Pattern STRING_KEY =
            Pattern.compile("^\\s*(\\w+)\\s*=\\s*[\"']([^\"']*)[\"']");

    private ModLoader() {
    }

    /**
     * Reads a jar's {@code neoforge.mods.toml} and locates its {@code @Mod} class.
     *
     * @throws IOException           if the jar cannot be read
     * @throws IllegalStateException if the toml or the annotated class is missing
     */
    public static ModCandidate discover(Path jar) throws IOException {
        String toml;
        try (JarFile file = new JarFile(jar.toFile())) {
            JarEntry entry = file.getJarEntry("META-INF/neoforge.mods.toml");
            if (entry == null) {
                throw new IllegalStateException("no META-INF/neoforge.mods.toml in " + jar);
            }
            try (InputStream stream = file.getInputStream(entry)) {
                toml = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            }
        }

        // modId means the mod's own id under [[mods]] and the target's id under
        // [[dependencies.<owner>]]; which one depends on the table the line sits in.
        List<String> modIds = new ArrayList<>();
        List<String[]> dependencies = new ArrayList<>(); // {modId, ordering, type}
        String table = "";
        for (String line : toml.split("\\R")) {
            Matcher header = TABLE.matcher(line);
            if (header.find()) {
                table = header.group(1);
                if (table.startsWith("dependencies.")) {
                    dependencies.add(new String[3]);
                }
                continue;
            }
            Matcher pair = STRING_KEY.matcher(line);
            if (!pair.find()) {
                continue;
            }
            String key = pair.group(1);
            if (table.equals("mods") && key.equals("modId")) {
                modIds.add(pair.group(2));
            } else if (table.startsWith("dependencies.") && key.equals("modId")) {
                dependencies.getLast()[0] = pair.group(2);
            } else if (table.startsWith("dependencies.") && key.equals("ordering")) {
                dependencies.getLast()[1] = pair.group(2);
            } else if (table.startsWith("dependencies.") && key.equals("type")) {
                dependencies.getLast()[2] = pair.group(2);
            }
        }
        Set<String> after = new LinkedHashSet<>();
        Set<String> before = new LinkedHashSet<>();
        for (String[] dependency : dependencies) {
            // ponytail: an optional AFTER dependency is dropped, so it orders nothing even when
            // installed. Rust sorts on `after` and refuses a missing one, which is right only
            // for required dependencies; carry the type through if an optional one matters.
            if ("AFTER".equals(dependency[1]) && dependency[0] != null
                    && !"optional".equalsIgnoreCase(dependency[2])) {
                after.add(dependency[0]);
            } else if ("BEFORE".equals(dependency[1]) && dependency[0] != null) {
                before.add(dependency[0]);
            }
        }
        if (modIds.isEmpty()) {
            throw new IllegalStateException("no modId in " + jar);
        }

        URLClassLoader loader = sharedLoader(jar);
        Class<?> main = findAnnotatedClass(jar, loader);
        return new ModCandidate(modIds.get(0), List.copyOf(modIds), after, before, main, loader,
                findEventBusSubscribers(jar, loader));
    }

    /**
     * Orders mods for construction: each after the mods it declares {@code ordering =
     * "AFTER"} on and before those it declares {@code "BEFORE"} on, discovery order
     * otherwise.
     *
     * <p>A dependency on a mod that is not present is logged and ignored, as is a cycle,
     * whose members keep discovery order.
     */
    public static List<ModCandidate> constructionOrder(List<ModCandidate> discovered) {
        for (ModCandidate mod : discovered) {
            for (String id : mod.after()) {
                if (discovered.stream().noneMatch(other -> other.modIds().contains(id))
                        && !id.equals("minecraft") && !id.equals("neoforge")) {
                    System.err.println("[pumpkin] " + mod.modId() + " orders after " + id
                            + ", which is not present; ignored");
                }
            }
        }
        List<ModCandidate> ordered = new ArrayList<>();
        List<ModCandidate> pending = new ArrayList<>(discovered);
        while (!pending.isEmpty()) {
            ModCandidate next = null;
            for (ModCandidate mod : pending) {
                if (pending.stream().noneMatch(other -> other != mod && mustPrecede(other, mod))) {
                    next = mod;
                    break;
                }
            }
            if (next == null) {
                next = pending.get(0);
                System.err.println("[pumpkin] mod ordering cycle involving " + next.modId()
                        + "; constructing in discovery order");
            }
            pending.remove(next);
            ordered.add(next);
        }
        return ordered;
    }

    private static boolean mustPrecede(ModCandidate first, ModCandidate second) {
        return first.modIds().stream().anyMatch(second.after()::contains)
                || second.modIds().stream().anyMatch(first.before()::contains);
    }

    /**
     * The jar's {@code @EventBusSubscriber} classes that apply on a dedicated server.
     *
     * <p>NeoForge registers these classes' static {@code @SubscribeEvent} methods
     * automatically; a class that fails to load is reported and skipped, since a
     * handler that cannot load cannot run on real NeoForge either until its
     * references resolve.
     */
    private static List<Class<?>> findEventBusSubscribers(Path jar, URLClassLoader loader)
            throws IOException {
        List<Class<?>> subscribers = new ArrayList<>();
        try (JarFile file = new JarFile(jar.toFile())) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (!name.endsWith(".class")) {
                    continue;
                }
                String className =
                        name.substring(0, name.length() - ".class".length()).replace('/', '.');
                try {
                    Class<?> candidate = Class.forName(className, false, loader);
                    net.neoforged.fml.common.EventBusSubscriber annotation =
                            candidate.getAnnotation(net.neoforged.fml.common.EventBusSubscriber.class);
                    if (annotation == null) {
                        continue;
                    }
                    boolean serverSide = false;
                    for (net.neoforged.api.distmarker.Dist dist : annotation.value()) {
                        if (dist == net.neoforged.api.distmarker.Dist.DEDICATED_SERVER) {
                            serverSide = true;
                        }
                    }
                    if (serverSide) {
                        subscribers.add(candidate);
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    // Same contract as findAnnotatedClass: a class that will not load
                    // cannot carry live handlers, but say which one went dead and why.
                    System.err.println("[pumpkin] @EventBusSubscriber class " + className
                            + " skipped (does not load): " + e);
                }
            }
        }
        return subscribers;
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
     * <p>Exactly one loader, grown in place. Rebuilding one per new jar -- what this used
     * to do -- gave each a parent of the app loader, not the previous loader, so a class
     * from an earlier jar looked up through the newer loader was defined a second time:
     * two copies of its statics, and a ClassCastException wherever the copies met.
     * Pumpkin discovers every jar before constructing any (PluginManager.load_plugins), so
     * by the time mod code runs the loader already holds every jar.
     */
    private static final SharedLoader SHARED = new SharedLoader();

    private static final class SharedLoader extends URLClassLoader {
        SharedLoader() {
            super(new URL[0], ModLoader.class.getClassLoader());
        }

        // Widened from protected: appending a jar is the whole point of this class.
        @Override
        public void addURL(URL url) {
            super.addURL(url);
        }
    }

    private static synchronized URLClassLoader sharedLoader(Path jar) throws IOException {
        URL url = jar.toUri().toURL();
        if (!List.of(SHARED.getURLs()).contains(url)) {
            SHARED.addURL(url);
        }
        return SHARED;
    }
}
