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

            // The mod class set is an input to collection, not just to resolution: a
            // reference whose owner is a mod class is exactly the inherited-member case
            // (see resolveInheritedMember), and it has to be collected to be checked.
            Refs refs = new Refs(modClasses);
            for (Path jar : parsed.modJars) {
                collectRefs(jar, refs);
            }

            Counts counts = new Counts();
            for (String modClass : modClasses) {
                resolveClassShape(modClass, loader, findings);
            }
            for (Map.Entry<String, SortedSet<String>> entry : refs.types.entrySet()) {
                resolveType(entry.getKey(), entry.getValue(), loader, findings, counts);
            }
            for (Map.Entry<Ref, SortedSet<String>> entry : refs.members.entrySet()) {
                Ref ref = entry.getKey();
                if (Shimmed.isShimmed(ref.owner())) {
                    resolveMember(ref, entry.getValue(), loader, findings, counts);
                } else {
                    resolveInheritedMember(ref, entry.getValue(), loader, findings, counts);
                }
            }

            report(parsed, libraries, modClasses.size(), counts, findings);
            if (findings.hasFailures()) {
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
            Findings findings, Counts counts) {
        counts.classRefs++;
        try {
            Class.forName(internalName.replace('/', '.'), false, loader);
        } catch (ClassNotFoundException | LinkageError e) {
            counts.classMissing++;
            String missing = missingNameOf(e);
            for (String referrer : referrers) {
                findings.add(missing, kindOf(e), referrer);
            }
        }
    }

    /** A reference whose owner is itself a shimmed class: resolve it there. */
    private static void resolveMember(Ref ref, SortedSet<String> referrers, ClassLoader loader, Findings findings,
            Counts counts) {
        counts.shimMemberRefs++;
        Class<?> owner;
        try {
            owner = Class.forName(ref.owner().replace('/', '.'), false, loader);
        } catch (ClassNotFoundException | LinkageError e) {
            // Already reported by resolveType, which sees the same owner. Counted as
            // missing all the same: the member is no more reachable than its class.
            counts.shimMemberMissing++;
            return;
        }
        Hierarchy hierarchy = hierarchyOf(owner);
        Member found = ref.isMethod() ? findMethod(owner, hierarchy, ref, findings) : findField(hierarchy, ref);
        if (found == null) {
            counts.shimMemberMissing++;
            String kind = ref.isMethod() ? "NoSuchMethodError" : "NoSuchFieldError";
            for (String referrer : referrers) {
                findings.add(ref.key(), kind, referrer);
            }
            return;
        }
        reportKindMismatch(ref, owner, found, referrers, findings);
    }

    /**
     * A reference whose owner is a <em>mod</em> class but whose target is declared in the
     * shim -- the case javac's static receiver type hides.
     *
     * <p>{@code EnchanterRenderer} calls {@code EnchanterTileEntity.getBlockState()}. The
     * constant pool records the owner as the mod's own subclass, because that is the
     * static type of the receiver; {@code getBlockState} is declared four levels up on
     * {@code BlockEntity}. Filtering references by "is the owner shimmed?" -- which both
     * this check and {@link JarScanner} used to do -- drops the reference entirely, so the
     * scanner never records the member, the pruner deletes it as uncalled, and this check
     * scores a {@code NoSuchMethodError} as resolved. There are 80 such references in the
     * two mod jars.
     *
     * <p>So: walk the loaded hierarchy. If some class declares the member, the reference
     * is fine -- and it is only <em>the shim's</em> business if that declarer is shimmed.
     * If nothing declares it, it is a real failure, attributed to the nearest shimmed
     * ancestor, which is the class that has to grow the member.
     */
    private static void resolveInheritedMember(Ref ref, SortedSet<String> referrers, ClassLoader loader,
            Findings findings, Counts counts) {
        if (ref.name().equals("<init>")) {
            // A constructor is never inherited; the owner declares it or nothing does,
            // and the owner here is the mod's own class.
            return;
        }
        Class<?> owner;
        try {
            owner = Class.forName(ref.owner().replace('/', '.'), false, loader);
        } catch (ClassNotFoundException | LinkageError e) {
            // The mod class itself did not load -- reported by the class-shape pass, and
            // its hierarchy is unknown, so nothing can be concluded about this member.
            return;
        }
        Hierarchy hierarchy = hierarchyOf(owner);
        String nearestShimmed = nearestShimmedIn(hierarchy);
        if (nearestShimmed == null) {
            // Nothing shimmed anywhere above this receiver: a mod calling its own code.
            return;
        }
        Member found = ref.isMethod() ? findMethod(owner, hierarchy, ref, findings) : findField(hierarchy, ref);
        if (found != null) {
            if (!Shimmed.isShimmed(internalNameOf(found.declaringClass()))) {
                // A mod class declares it. Resolvable, and not the shim's to supply.
                return;
            }
            counts.inheritedRefs++;
            reportKindMismatch(ref, owner, found, referrers, findings);
            return;
        }
        if (!hierarchy.complete()) {
            // A supertype is absent (a JEI or Jade class this checkout lacks), so "not
            // found" would be an accusation the evidence does not support.
            return;
        }
        counts.inheritedRefs++;
        counts.inheritedMissing++;
        String key = nearestShimmed + "." + ref.name() + ":" + ref.descriptor();
        String kind = ref.isMethod() ? "NoSuchMethodError" : "NoSuchFieldError";
        for (String referrer : referrers) {
            findings.add(key, kind, referrer + " (through " + ref.owner() + ")");
        }
    }

    /**
     * Resolved, but possibly not the way the call site expects. An {@code INVOKEINTERFACE}
     * against a class, or an {@code INVOKESTATIC} against an instance member, resolves and
     * then throws {@code IncompatibleClassChangeError} at the first call. It is reported
     * separately from an unresolved reference because it is a different failure -- but it
     * fails the run all the same: this is the defect class that found {@code IEventBus}
     * being a class where every mod emits {@code invokeinterface}, and a report nothing is
     * forced to read is not a gate.
     */
    private static void reportKindMismatch(Ref ref, Class<?> owner, Member found, SortedSet<String> referrers,
            Findings findings) {
        if (ref.expectsStatic() != found.isStatic()) {
            findings.mismatch(ref.key() + "  (call site expects " + (ref.expectsStatic() ? "static" : "instance")
                    + ", shim declares " + (found.isStatic() ? "static" : "instance") + ")", referrers);
            return;
        }
        if (!Shimmed.isShimmed(internalNameOf(owner))) {
            // The receiver type is a mod class. Whether it is a class or an interface is
            // the mod's own affair, and it is right either way -- an INVOKEVIRTUAL on a
            // class that inherits a default method from a shimmed interface is ordinary,
            // legal Java (JVMS 5.4.3.3 searches superinterfaces after superclasses), so
            // asking this question here produced four false positives.
            return;
        }
        if (ref.expectsInterfaceOwner() && !owner.isInterface()) {
            findings.mismatch(ref.key() + "  (call site expects an interface, shim declares a class)", referrers);
        } else if (!ref.expectsInterfaceOwner() && ref.isMethod() && owner.isInterface() && !ref.expectsStatic()) {
            findings.mismatch(ref.key() + "  (call site expects a class, shim declares an interface)", referrers);
        }
    }

    /** The first shimmed class in {@code hierarchy}'s breadth-first order, or {@code null}. */
    private static String nearestShimmedIn(Hierarchy hierarchy) {
        for (Class<?> c : hierarchy.classes()) {
            String internalName = internalNameOf(c);
            if (Shimmed.isShimmed(internalName)) {
                return internalName;
            }
        }
        return null;
    }

    /** A resolved field or method: where it is declared, and whether it is static. */
    private record Member(Class<?> declaringClass, boolean isStatic) {}

    /**
     * Method resolution, JVMS 5.4.3.3-shaped: the class itself, then its superclasses,
     * then every interface it transitively implements. {@code <init>} is looked up on the
     * declaring class only, which is what the JVM does.
     */
    private static Member findMethod(Class<?> owner, Hierarchy hierarchy, Ref ref, Findings findings) {
        if (ref.name().equals("<init>")) {
            try {
                for (Constructor<?> ctor : owner.getDeclaredConstructors()) {
                    if (Type.getConstructorDescriptor(ctor).equals(ref.descriptor())) {
                        return new Member(owner, false);
                    }
                }
            } catch (LinkageError e) {
                findings.add(missingNameOf(e), kindOf(e), internalNameOf(owner));
            }
            return null;
        }
        for (Class<?> c : hierarchy.classes()) {
            try {
                for (Method m : c.getDeclaredMethods()) {
                    if (m.getName().equals(ref.name()) && Type.getMethodDescriptor(m).equals(ref.descriptor())) {
                        return new Member(c, Modifier.isStatic(m.getModifiers()));
                    }
                }
            } catch (LinkageError e) {
                findings.add(missingNameOf(e), kindOf(e), internalNameOf(c));
            }
        }
        return null;
    }

    private static Member findField(Hierarchy hierarchy, Ref ref) {
        for (Class<?> c : hierarchy.classes()) {
            try {
                for (Field f : c.getDeclaredFields()) {
                    if (f.getName().equals(ref.name()) && Type.getDescriptor(f.getType()).equals(ref.descriptor())) {
                        return new Member(c, Modifier.isStatic(f.getModifiers()));
                    }
                }
            } catch (LinkageError e) {
                // The field may still be there; a sibling field's type is what is absent.
                // Reported by the class-shape pass, which probes the same declaration.
            }
        }
        return null;
    }

    /**
     * {@code owner}, its superclasses, and every interface either transitively implements,
     * breadth-first.
     *
     * <p>{@code complete} is false when a supertype could not be loaded. It is the
     * difference between "the shim does not declare this member" and "we could not see far
     * enough to say": a mod class extending a JEI class this checkout lacks has a truncated
     * hierarchy, and calling a member missing from it a linkage failure would be an
     * accusation the evidence does not support.
     */
    private record Hierarchy(List<Class<?>> classes, boolean complete) {}

    private static Hierarchy hierarchyOf(Class<?> owner) {
        List<Class<?>> ordered = new ArrayList<>();
        Set<Class<?>> seen = new LinkedHashSet<>();
        Deque<Class<?>> queue = new ArrayDeque<>();
        queue.add(owner);
        boolean complete = true;
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
                complete = false;
            }
        }
        return new Hierarchy(ordered, complete);
    }

    private static String internalNameOf(Class<?> c) {
        return c.getName().replace('.', '/');
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
        if (message.contains(" ")) {
            // Prose: "X has been compiled by a more recent version...", "Could not
            // initialize class X". The whole message is kept as the display key, because
            // truncating it to a name would throw away the only statement of what went
            // wrong; {@link #subjectOf} is what decides whose problem it is.
            return message;
        }
        return message.replace('.', '/');
    }

    /**
     * The class a finding is about, in internal form, or {@code null} when it cannot be
     * determined. Used only to decide whether a finding is the shim's to answer for.
     *
     * <p>{@code null} is not "ignore it". A finding whose subject cannot be read is
     * counted as a shim failure, because the alternative -- what this method used to do
     * by returning the whole prose message and letting it fail {@code isShimmed} -- is to
     * drop an {@code UnsupportedClassVersionError} or a {@code VerifyError} into the
     * uncounted bucket and print a green number underneath it.
     */
    private static String subjectOf(String finding) {
        String candidate = finding;
        int space = candidate.indexOf(' ');
        if (space >= 0) {
            // A JVM LinkageError message conventionally opens with the class it is about.
            candidate = candidate.substring(0, space);
        }
        int dot = candidate.indexOf('.');
        if (dot >= 0) {
            // A member key: owner.name:descriptor.
            candidate = candidate.substring(0, dot);
        }
        return candidate.matches("[A-Za-z_$][A-Za-z0-9_$]*(/[A-Za-z0-9_$]+)*") ? candidate : null;
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

    /**
     * Every reference a mod class makes that the shim could be responsible for, with who
     * made it.
     *
     * <p>Type references are filtered to shimmed packages, which is exact: a type
     * reference names the type itself. Member references cannot be filtered that way,
     * because javac writes the <em>static receiver type</em> as the owner -- so a mod
     * calling an inherited vanilla method through its own subclass produces a
     * {@code Methodref} owned by a mod class. Those are collected too, and
     * {@link #resolveInheritedMember} decides which of them land in the shim.
     */
    private static final class Refs {
        final TreeMap<String, SortedSet<String>> types = new TreeMap<>();
        final TreeMap<Ref, SortedSet<String>> members = new TreeMap<>();
        private final Set<String> modClasses;

        Refs(Set<String> modClasses) {
            this.modClasses = modClasses;
        }

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
            if (!Shimmed.isShimmed(ref.owner()) && !modClasses.contains(ref.owner())) {
                // Neither the shim's nor reachable through a mod class: the JDK, a
                // library, or another mod's API. Nothing here can be the shim's fault.
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

    /**
     * What was checked, so the headline number is a count of references and not of
     * findings. Every reference the run considered increments exactly one of the three
     * {@code *Refs} counters, and a failing one also increments its {@code *Missing}
     * partner.
     */
    private static final class Counts {
        int classRefs;
        int classMissing;
        int shimMemberRefs;
        int shimMemberMissing;
        /** Members reached through a mod class and declared -- or owed -- by the shim. */
        int inheritedRefs;
        int inheritedMissing;

        int total() {
            return classRefs + shimMemberRefs + inheritedRefs;
        }

        int missing() {
            return classMissing + shimMemberMissing + inheritedMissing;
        }
    }

    /** Unresolved references, grouped by what is missing rather than by who wanted it. */
    private static final class Findings {
        private final TreeMap<String, SortedSet<String>> shim = new TreeMap<>();
        private final TreeMap<String, String> shimKind = new TreeMap<>();
        private final TreeMap<String, SortedSet<String>> external = new TreeMap<>();
        private final TreeMap<String, SortedSet<String>> mismatches = new TreeMap<>();

        void add(String missing, String kind, String referrer) {
            String subject = subjectOf(missing);
            // subject == null means the finding could not be attributed to a class. It
            // goes in the counted bucket on purpose: an unreadable LinkageError is a
            // failure, and the uncounted bucket is only ever for findings positively
            // identified as somebody else's.
            if (subject == null || Shimmed.isShimmed(subject)) {
                shim.computeIfAbsent(missing, k -> new TreeSet<>()).add(referrer);
                shimKind.put(missing, kind);
            } else {
                external.computeIfAbsent(missing + "  [" + kind + "]", k -> new TreeSet<>()).add(referrer);
            }
        }

        void mismatch(String description, SortedSet<String> referrers) {
            mismatches.computeIfAbsent(description, k -> new TreeSet<>()).addAll(referrers);
        }

        /**
         * Mismatches fail the run as well as unresolved references. They are a different
         * defect -- they resolve, then throw {@code IncompatibleClassChangeError} at the
         * call -- but they are no less fatal to a mod, and a finding that only prints is
         * a finding the next person scrolls past.
         */
        boolean hasFailures() {
            return !shim.isEmpty() || !mismatches.isEmpty();
        }
    }

    private static void report(Args parsed, List<Path> libraries, int modClasses, Counts counts, Findings findings) {
        System.out.println("shim classes:  " + parsed.shimClasses);
        System.out.println("mod jars:      " + parsed.modJars);
        System.out.println("game libs:     " + libraries.size() + " jars from -D" + LIBRARIES_PROPERTY);
        System.out.println("mod classes:   " + modClasses);
        System.out.println();

        if (!findings.shim.isEmpty()) {
            System.out.println("UNRESOLVED references into the shim (" + findings.shim.size() + "):");
            for (Map.Entry<String, SortedSet<String>> entry : findings.shim.entrySet()) {
                System.out.println("  " + findings.shimKind.get(entry.getKey()) + "  " + entry.getKey());
                System.out.println("      referenced by " + summarise(entry.getValue()));
            }
            System.out.println();
        }

        if (!findings.mismatches.isEmpty()) {
            System.out.println("RESOLVED, but the call site and the shim disagree about the member's kind ("
                    + findings.mismatches.size() + "). These link and then throw"
                    + " IncompatibleClassChangeError at the call. They fail this run:");
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

        System.out.println("classes referenced in a shimmed package:         "
                + (counts.classRefs - counts.classMissing) + " of " + counts.classRefs + " resolved");
        System.out.println("members whose owner is a shimmed class:          "
                + (counts.shimMemberRefs - counts.shimMemberMissing) + " of " + counts.shimMemberRefs + " resolved");
        System.out.println("members reached through a mod class, declared in the shim: "
                + (counts.inheritedRefs - counts.inheritedMissing) + " of " + counts.inheritedRefs + " resolved");
        System.out.println();
        System.out.println((counts.total() - counts.missing()) + " of " + counts.total() + " references resolved"
                + (findings.mismatches.isEmpty() ? "" : ", " + findings.mismatches.size() + " resolved to the wrong kind"));
        System.out.println(findings.hasFailures() ? "LINKAGE FAILED" : "LINKAGE OK");
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
