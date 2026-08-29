package dev.pumpkin.shimgen;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * The finish line: proves that two real NeoForge mods link against the generated shim.
 *
 * <p>Compiling the shim proves the shim is Java. It does not prove a mod can be loaded
 * beside it: a mod's call site was compiled against the real Minecraft and carries the
 * exact owner, name and descriptor it expects to find. This check builds a classloader
 * over the compiled {@code shim} and {@code fml} classes plus the mod jars, and then, for
 * every class in the mod jars, resolves every reference that class makes into a shimmed
 * package. A missing class is a {@link NoClassDefFoundError}, a missing method a {@link
 * NoSuchMethodError}, a missing field a {@link NoSuchFieldError} -- the three errors the
 * JVM itself would raise, raised here instead, all of them, in one run.
 *
 * <p>Two passes, because neither alone is sufficient:
 * <ul>
 *   <li><b>Class resolution.</b> Load each mod class and force its shape -- supertypes,
 *       interfaces, declared fields, methods and constructors. This is what catches a
 *       missing supertype or a missing type in a signature.
 *   <li><b>Member resolution.</b> Read each mod class's constant pool with ASM and resolve
 *       every {@code Methodref}, {@code Fieldref}, {@code InterfaceMethodref} and class
 *       reference whose owner is shimmed. Reflection over the mod class can never see
 *       these: they live in method bodies, which reflection does not read.
 * </ul>
 *
 * <p>References out of shimmed packages -- a mod's own classes, the JDK, and the libraries
 * NeoForge ships (JEI, Mixin, log4j) -- are collected and reported separately but do not
 * fail the run. The shim's contract is {@code net.minecraft}, {@code net.neoforged} and
 * the three decompiled {@code com.mojang} packages; nothing else is its to supply, and a
 * checkout that happens to lack a mod's optional integration jar is not a shim defect.
 *
 * <p>Not run in CI: it needs the mod jars, which are not vendored. Run it by hand through
 * {@code :generator:linkageCheck}.
 */
public final class LinkageCheck {
    private LinkageCheck() {}

    /**
     * Where the game libraries come from. The shim compiles against DataFixerUpper,
     * Brigadier, guava and the rest ({@code compileOnly}), so a shim signature naming
     * {@code Codec<ItemStack>} needs {@code Codec} present to be resolvable at all --
     * exactly as it is at runtime, where NeoForge puts those libraries on the classpath.
     * Gradle resolves them and hands them over here rather than on the command line, so
     * the documented invocation stays the one in the task brief.
     */
    private static final String LIBRARIES_PROPERTY = "pumpkin.linkage.libraries";

    public static void main(String[] args) throws IOException {
        Args parsed = Args.parse(args);

        List<URL> urls = new ArrayList<>();
        for (Path dir : parsed.shimClasses) {
            urls.add(toUrl(dir));
        }
        for (Path jar : parsed.modJars) {
            urls.add(toUrl(jar));
        }
        List<Path> libraries = libraries();
        for (Path library : libraries) {
            urls.add(toUrl(library));
        }

        // Parent is the platform loader, not the application loader: the generator's own
        // classpath (ASM, JavaParser) must not be visible to the mods, or a reference
        // that only resolves because the generator happens to depend on something would
        // be scored as linking when it does not.
        try (URLClassLoader loader =
                new URLClassLoader("linkage", urls.toArray(URL[]::new), ClassLoader.getPlatformClassLoader())) {
            Findings findings = new Findings();

            SortedSet<String> modClasses = new TreeSet<>();
            for (Path jar : parsed.modJars) {
                modClasses.addAll(classesIn(jar));
            }

            Refs refs = new Refs();
            for (Path jar : parsed.modJars) {
                collectRefs(jar, refs);
            }

            for (String modClass : modClasses) {
                resolveClassShape(modClass, loader, findings);
            }
            for (Map.Entry<String, SortedSet<String>> entry : refs.types.entrySet()) {
                resolveType(entry.getKey(), entry.getValue(), loader, findings);
            }
            for (Map.Entry<Ref, SortedSet<String>> entry : refs.members.entrySet()) {
                resolveMember(entry.getKey(), entry.getValue(), loader, findings);
            }

            report(parsed, libraries, modClasses.size(), refs, findings);
            if (findings.hasShimFailures()) {
                System.exit(1);
            }
        }
    }

    // ---------------------------------------------------------------- resolution

    /**
     * Loads a mod class and forces its shape. {@code initialize} is deliberately {@code
     * false}: running a mod's static initialiser would call into the shim, and every
     * generated body throws {@link dev.pumpkin.shim.Unimplemented} by construction. An
     * {@code ExceptionInInitializerError} there is the shim working as designed, not a
     * linkage failure, and letting it happen would hide the linkage answer behind it.
     */
    private static void resolveClassShape(String internalName, ClassLoader loader, Findings findings) {
        Class<?> c;
        try {
            c = Class.forName(internalName.replace('/', '.'), false, loader);
        } catch (ClassNotFoundException | LinkageError e) {
            findings.add(missingNameOf(e), kindOf(e), internalName);
            return;
        }
        // Each of these resolves a different part of the shape and each can fail on its
        // own, so they are separate: one missing parameter type must not hide the fields.
        forceResolution(() -> c.getSuperclass(), internalName, findings);
        forceResolution(() -> c.getInterfaces(), internalName, findings);
        forceResolution(() -> c.getDeclaredFields(), internalName, findings);
        forceResolution(() -> c.getDeclaredMethods(), internalName, findings);
        forceResolution(() -> c.getDeclaredConstructors(), internalName, findings);
    }

    private static void forceResolution(Supplier probe, String referencedBy, Findings findings) {
        try {
            probe.get();
        } catch (LinkageError | TypeNotPresentException e) {
            findings.add(missingNameOf(e), kindOf(e), referencedBy);
        }
    }

    /** A probe that may raise a {@link LinkageError}; not {@link java.util.function.Supplier}, which cannot. */
    private interface Supplier {
        Object get();
    }

    private static void resolveType(String internalName, SortedSet<String> referrers, ClassLoader loader,
            Findings findings) {
        try {
            Class.forName(internalName.replace('/', '.'), false, loader);
        } catch (ClassNotFoundException | LinkageError e) {
            String missing = missingNameOf(e);
            for (String referrer : referrers) {
                findings.add(missing, kindOf(e), referrer);
            }
        }
    }

    private static void resolveMember(Ref ref, SortedSet<String> referrers, ClassLoader loader, Findings findings) {
        Class<?> owner;
        try {
            owner = Class.forName(ref.owner().replace('/', '.'), false, loader);
        } catch (ClassNotFoundException | LinkageError e) {
            // Already reported by resolveType, which sees the same owner.
            return;
        }
        Member found = ref.isMethod() ? findMethod(owner, ref, findings, referrers) : findField(owner, ref);
        if (found == null) {
            String kind = ref.isMethod() ? "NoSuchMethodError" : "NoSuchFieldError";
            for (String referrer : referrers) {
                findings.add(ref.key(), kind, referrer);
            }
            return;
        }
        // Resolved, but possibly not the way the call site expects. An INVOKEINTERFACE
        // against a class, or an INVOKESTATIC against an instance member, is an
        // IncompatibleClassChangeError at the first call, not at resolution -- so it is
        // reported and not counted as unresolved.
        if (ref.expectsStatic() != found.isStatic()) {
            findings.mismatch(ref.key() + "  (call site expects " + (ref.expectsStatic() ? "static" : "instance")
                    + ", shim declares " + (found.isStatic() ? "static" : "instance") + ")", referrers);
        } else if (ref.expectsInterfaceOwner() && !owner.isInterface()) {
            findings.mismatch(ref.key() + "  (call site expects an interface, shim declares a class)", referrers);
        } else if (!ref.expectsInterfaceOwner() && ref.isMethod() && owner.isInterface() && !ref.expectsStatic()) {
            findings.mismatch(ref.key() + "  (call site expects a class, shim declares an interface)", referrers);
        }
    }

    /** A resolved field or method, reduced to the one property resolution can disagree about. */
    private record Member(boolean isStatic) {}

    /**
     * Method resolution, JVMS 5.4.3.3-shaped: the class itself, then its superclasses,
     * then every interface it transitively implements. {@code <init>} is looked up on the
     * declaring class only, which is what the JVM does.
     */
    private static Member findMethod(Class<?> owner, Ref ref, Findings findings, SortedSet<String> referrers) {
        if (ref.name().equals("<init>")) {
            try {
                for (Constructor<?> ctor : owner.getDeclaredConstructors()) {
                    if (Type.getConstructorDescriptor(ctor).equals(ref.descriptor())) {
                        return new Member(false);
                    }
                }
            } catch (LinkageError e) {
                findings.add(missingNameOf(e), kindOf(e), owner.getName().replace('.', '/'));
            }
            return null;
        }
        for (Class<?> c : hierarchyOf(owner)) {
            try {
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName().equals(ref.name()) && Type.getMethodDescriptor(m).equals(ref.descriptor())) {
                        return new Member(Modifier.isStatic(m.getModifiers()));
                    }
                }
            } catch (LinkageError e) {
                findings.add(missingNameOf(e), kindOf(e), c.getName().replace('.', '/'));
            }
        }
        return null;
    }

    private static Member findField(Class<?> owner, Ref ref) {
        for (Class<?> c : hierarchyOf(owner)) {
            try {
                for (Field f : c.getDeclaredFields()) {
                    if (f.getName().equals(ref.name()) && Type.getDescriptor(f.getType()).equals(ref.descriptor())) {
                        return new Member(Modifier.isStatic(f.getModifiers()));
                    }
                }
            } catch (LinkageError e) {
                // The field may still be there; a sibling field's type is what is absent.
                // Reported by the class-shape pass, which probes the same declaration.
            }
        }
        return null;
    }

    /** {@code owner}, its superclasses, and every interface either transitively implements. */
    private static List<Class<?>> hierarchyOf(Class<?> owner) {
        List<Class<?>> ordered = new ArrayList<>();
        Set<Class<?>> seen = new LinkedHashSet<>();
        Deque<Class<?>> queue = new ArrayDeque<>();
        queue.add(owner);
        while (!queue.isEmpty()) {
            Class<?> c = queue.poll();
            if (!seen.add(c)) {
                continue;
            }
            ordered.add(c);
            try {
                // getSuperclass() is null for Object and for every interface; ArrayDeque
                // rejects null, so the check is load-bearing, not defensive.
                Class<?> superclass = c.getSuperclass();
                if (superclass != null) {
                    queue.add(superclass);
                }
                queue.addAll(List.of(c.getInterfaces()));
            } catch (LinkageError e) {
                // A supertype that is not there is the class-shape pass's finding.
            }
        }
        return ordered;
    }

    /**
     * The name the error is about, in internal form. {@link NoClassDefFoundError}'s message
     * is the internal name; {@link ClassNotFoundException}'s is the binary name.
     */
    private static String missingNameOf(Throwable e) {
        String message = e.getMessage();
        if (message == null || message.isBlank()) {
            return e.getClass().getSimpleName() + " (no message)";
        }
        // "Could not initialize class X" and similar prose; keep the whole message rather
        // than guess at a name that is not there.
        if (message.contains(" ")) {
            return message;
        }
        return message.replace('.', '/');
    }

    private static String kindOf(Throwable e) {
        return e instanceof ClassNotFoundException ? "NoClassDefFoundError" : e.getClass().getSimpleName();
    }

    // ---------------------------------------------------------------- collection

    private static SortedSet<String> classesIn(Path jar) throws IOException {
        SortedSet<String> names = new TreeSet<>();
        try (JarFile file = new JarFile(jar.toFile())) {
            for (Enumeration<JarEntry> entries = file.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                // META-INF/versions holds multi-release duplicates of classes already
                // listed, and module-info is not a class anything can reference.
                if (!name.endsWith(".class") || name.startsWith("META-INF/") || name.endsWith("module-info.class")) {
                    continue;
                }
                names.add(name.substring(0, name.length() - ".class".length()));
            }
        }
        return names;
    }

    private static void collectRefs(Path jar, Refs refs) throws IOException {
        try (JarFile file = new JarFile(jar.toFile())) {
            for (Enumeration<JarEntry> entries = file.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.endsWith(".class") || name.startsWith("META-INF/") || name.endsWith("module-info.class")) {
                    continue;
                }
                String referrer = name.substring(0, name.length() - ".class".length());
                try (InputStream in = file.getInputStream(entry)) {
                    new ClassReader(in).accept(new RefCollector(refs, referrer), ClassReader.SKIP_FRAMES);
                } catch (IOException e) {
                    throw new UncheckedIOException("cannot read " + name + " in " + jar, e);
                }
            }
        }
    }

    /** Every reference a mod class makes into a shimmed package, with who made it. */
    private static final class Refs {
        final TreeMap<String, SortedSet<String>> types = new TreeMap<>();
        final TreeMap<Ref, SortedSet<String>> members = new TreeMap<>();

        /** {@code internalName} is an internal name ({@code net/minecraft/X}), never a descriptor. */
        void type(String internalName, String referrer) {
            String name = internalName;
            if (name.startsWith("[")) {
                name = elementInternalNameOf(Type.getType(name));
            }
            if (name == null || !Shimmed.isShimmed(name)) {
                return;
            }
            types.computeIfAbsent(name, k -> new TreeSet<>()).add(referrer);
        }

        /** A field or method descriptor: every object type it names, array types unwrapped. */
        void descriptor(String descriptor, String referrer) {
            if (descriptor.startsWith("(")) {
                for (Type argument : Type.getArgumentTypes(descriptor)) {
                    objectType(argument, referrer);
                }
                objectType(Type.getReturnType(descriptor), referrer);
                return;
            }
            objectType(Type.getType(descriptor), referrer);
        }

        private void objectType(Type t, String referrer) {
            String name = elementInternalNameOf(t);
            if (name != null) {
                type(name, referrer);
            }
        }

        /**
         * The internal name {@code t} ultimately names, or {@code null} for a primitive.
         * Descriptors and internal names are not interchangeable and mixing them fails
         * silently: {@code Lnet/minecraft/X;} does not start with {@code net/minecraft/},
         * so every parameter and return type went unchecked until a class the mods name
         * only in a method signature turned up missing and unreported.
         */
        private static String elementInternalNameOf(Type t) {
            Type element = t;
            while (element.getSort() == Type.ARRAY) {
                element = element.getElementType();
            }
            return element.getSort() == Type.OBJECT ? element.getInternalName() : null;
        }

        void member(Ref ref, String referrer) {
            if (!Shimmed.isShimmed(ref.owner())) {
                return;
            }
            members.computeIfAbsent(ref, k -> new TreeSet<>()).add(referrer);
        }
    }

    /** A constant-pool member reference, plus what the call site assumed about it. */
    private record Ref(String owner, String name, String descriptor, boolean isMethod, boolean expectsInterfaceOwner,
            boolean expectsStatic) implements Comparable<Ref> {
        String key() {
            return owner + "." + name + ":" + descriptor;
        }

        @Override
        public int compareTo(Ref other) {
            int byKey = key().compareTo(other.key());
            if (byKey != 0) {
                return byKey;
            }
            return Boolean.compare(isMethod, other.isMethod);
        }
    }

    private static final class RefCollector extends ClassVisitor {
        private final Refs refs;
        private final String referrer;

        RefCollector(Refs refs, String referrer) {
            super(Opcodes.ASM9);
            this.refs = refs;
            this.referrer = referrer;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName,
                String[] interfaces) {
            if (superName != null) {
                refs.type(superName, referrer);
            }
            if (interfaces != null) {
                for (String iface : interfaces) {
                    refs.type(iface, referrer);
                }
            }
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
            refs.descriptor(descriptor, referrer);
            return null;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                String[] exceptions) {
            refs.descriptor(descriptor, referrer);
            if (exceptions != null) {
                for (String thrown : exceptions) {
                    refs.type(thrown, referrer);
                }
            }
            return new MethodVisitor(Opcodes.ASM9) {
                @Override
                public void visitTypeInsn(int opcode, String type) {
                    refs.type(type, referrer);
                }

                @Override
                public void visitFieldInsn(int opcode, String owner, String fieldName, String fieldDescriptor) {
                    refs.type(owner, referrer);
                    refs.descriptor(fieldDescriptor, referrer);
                    boolean isStatic = opcode == Opcodes.GETSTATIC || opcode == Opcodes.PUTSTATIC;
                    refs.member(new Ref(owner, fieldName, fieldDescriptor, false, false, isStatic), referrer);
                }

                @Override
                public void visitMethodInsn(int opcode, String owner, String methodName, String methodDescriptor,
                        boolean isInterface) {
                    refs.type(owner, referrer);
                    refs.descriptor(methodDescriptor, referrer);
                    if (owner.startsWith("[")) {
                        // Array methods (clone, and Object's) are supplied by the JVM.
                        return;
                    }
                    refs.member(new Ref(owner, methodName, methodDescriptor, true, isInterface,
                            opcode == Opcodes.INVOKESTATIC), referrer);
                }

                @Override
                public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrap,
                        Object... arguments) {
                    refs.descriptor(descriptor, referrer);
                    // The lambda body and the functional interface both arrive as
                    // bootstrap arguments; a method reference to a shim method lives
                    // here and nowhere else in the constant pool.
                    for (Object argument : arguments) {
                        if (argument instanceof Handle handle) {
                            handle(handle);
                        } else if (argument instanceof Type type) {
                            refs.descriptor(type.getDescriptor(), referrer);
                        }
                    }
                }

                private void handle(Handle handle) {
                    if (handle.getOwner().startsWith("[")) {
                        return;
                    }
                    refs.type(handle.getOwner(), referrer);
                    refs.descriptor(handle.getDesc(), referrer);
                    boolean isMethod = handle.getTag() >= Opcodes.H_INVOKEVIRTUAL;
                    boolean isStatic = handle.getTag() == Opcodes.H_INVOKESTATIC
                            || handle.getTag() == Opcodes.H_GETSTATIC
                            || handle.getTag() == Opcodes.H_PUTSTATIC;
                    refs.member(new Ref(handle.getOwner(), handle.getName(), handle.getDesc(), isMethod,
                            handle.isInterface(), isStatic), referrer);
                }

                @Override
                public void visitLdcInsn(Object value) {
                    if (value instanceof Type type) {
                        refs.descriptor(type.getDescriptor(), referrer);
                    }
                }

                @Override
                public void visitTryCatchBlock(org.objectweb.asm.Label start, org.objectweb.asm.Label end,
                        org.objectweb.asm.Label handler, String caught) {
                    if (caught != null) {
                        refs.type(caught, referrer);
                    }
                }
            };
        }
    }

    // ---------------------------------------------------------------- reporting

    /** Unresolved references, grouped by what is missing rather than by who wanted it. */
    private static final class Findings {
        private final TreeMap<String, SortedSet<String>> shim = new TreeMap<>();
        private final TreeMap<String, String> shimKind = new TreeMap<>();
        private final TreeMap<String, SortedSet<String>> external = new TreeMap<>();
        private final TreeMap<String, SortedSet<String>> mismatches = new TreeMap<>();

        void add(String missing, String kind, String referrer) {
            String owner = missing.contains(".") ? missing.substring(0, missing.indexOf('.')) : missing;
            if (Shimmed.isShimmed(owner)) {
                shim.computeIfAbsent(missing, k -> new TreeSet<>()).add(referrer);
                shimKind.put(missing, kind);
            } else {
                external.computeIfAbsent(missing + "  [" + kind + "]", k -> new TreeSet<>()).add(referrer);
            }
        }

        void mismatch(String description, SortedSet<String> referrers) {
            mismatches.computeIfAbsent(description, k -> new TreeSet<>()).addAll(referrers);
        }

        boolean hasShimFailures() {
            return !shim.isEmpty();
        }
    }

    private static void report(Args parsed, List<Path> libraries, int modClasses, Refs refs, Findings findings) {
        System.out.println("shim classes:  " + parsed.shimClasses);
        System.out.println("mod jars:      " + parsed.modJars);
        System.out.println("game libs:     " + libraries.size() + " jars from -D" + LIBRARIES_PROPERTY);
        System.out.println("mod classes:   " + modClasses);
        System.out.println();

        int classRefs = refs.types.size();
        int memberRefs = refs.members.size();
        int missingClasses = 0;
        int missingMembers = 0;
        for (Map.Entry<String, String> entry : findings.shimKind.entrySet()) {
            if (entry.getValue().equals("NoClassDefFoundError")) {
                missingClasses++;
            } else {
                missingMembers++;
            }
        }

        if (!findings.shim.isEmpty()) {
            System.out.println("UNRESOLVED references into the shim (" + findings.shim.size() + "):");
            for (Map.Entry<String, SortedSet<String>> entry : findings.shim.entrySet()) {
                System.out.println("  " + findings.shimKind.get(entry.getKey()) + "  " + entry.getKey());
                System.out.println("      referenced by " + summarise(entry.getValue()));
            }
            System.out.println();
        }

        if (!findings.mismatches.isEmpty()) {
            System.out.println("resolved, but the call site and the shim disagree about the member's kind ("
                    + findings.mismatches.size() + "). These link and fail at the call, not at resolution:");
            for (Map.Entry<String, SortedSet<String>> entry : findings.mismatches.entrySet()) {
                System.out.println("  " + entry.getKey());
                System.out.println("      referenced by " + summarise(entry.getValue()));
            }
            System.out.println();
        }

        if (!findings.external.isEmpty()) {
            System.out.println("unresolved outside the shim's contract (" + findings.external.size()
                    + ") -- other mods' APIs, Mixin, and libraries this checkout does not have."
                    + " Not the shim's to supply, and not counted:");
            for (Map.Entry<String, SortedSet<String>> entry : findings.external.entrySet()) {
                System.out.println("  " + entry.getKey());
                System.out.println("      referenced by " + summarise(entry.getValue()));
            }
            System.out.println();
        }

        System.out.println("classes referenced in a shimmed package: " + (classRefs - missingClasses)
                + " of " + classRefs + " resolved");
        System.out.println("members referenced in a shimmed package: " + (memberRefs - missingMembers)
                + " of " + memberRefs + " resolved");
        int total = classRefs + memberRefs;
        int missing = missingClasses + missingMembers;
        System.out.println();
        System.out.println((total - missing) + " of " + total + " references resolved");
        System.out.println(findings.shim.isEmpty() ? "LINKAGE OK" : "LINKAGE FAILED");
    }

    private static String summarise(SortedSet<String> referrers) {
        List<String> first = referrers.stream().limit(3).toList();
        String joined = String.join(", ", first);
        return referrers.size() > first.size()
                ? joined + " (+" + (referrers.size() - first.size()) + " more)"
                : joined;
    }

    // ---------------------------------------------------------------- plumbing

    private static List<Path> libraries() {
        String value = System.getProperty(LIBRARIES_PROPERTY, "");
        List<Path> paths = new ArrayList<>();
        for (String element : value.split(java.io.File.pathSeparator)) {
            if (!element.isBlank()) {
                paths.add(Path.of(element));
            }
        }
        return paths;
    }

    private static URL toUrl(Path path) {
        try {
            return path.toUri().toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("not a usable classpath entry: " + path, e);
        }
    }

    private record Args(List<Path> shimClasses, List<Path> modJars) {
        static Args parse(String[] args) {
            List<Path> shimClasses = new ArrayList<>();
            List<Path> modJars = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                String flag = args[i];
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("missing value for " + flag);
                }
                String value = args[++i];
                switch (flag) {
                    // Repeatable, and also accepts a path-separated list, so the two
                    // output directories can be given the way a classpath usually is.
                    case "--shim-classes" -> {
                        for (String element : value.split(java.io.File.pathSeparator)) {
                            if (!element.isBlank()) {
                                shimClasses.add(Path.of(element));
                            }
                        }
                    }
                    case "--mod-jar" -> modJars.add(Path.of(value));
                    default -> throw new IllegalArgumentException("unknown flag: " + flag);
                }
            }
            if (shimClasses.isEmpty()) {
                throw new IllegalArgumentException("missing required flag: --shim-classes");
            }
            if (modJars.isEmpty()) {
                throw new IllegalArgumentException("missing required flag: --mod-jar");
            }
            for (Path dir : shimClasses) {
                if (!Files.isDirectory(dir)) {
                    throw new IllegalArgumentException("shim classes directory does not exist: " + dir
                            + " -- build it first with ./gradlew :shim:classes :fml:classes");
                }
            }
            for (Path jar : modJars) {
                if (!Files.isRegularFile(jar)) {
                    throw new IllegalArgumentException("mod jar is not a file: " + jar);
                }
            }
            return new Args(shimClasses, modJars);
        }
    }
}
