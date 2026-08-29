package dev.pumpkin.shimgen;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Reads a mod jar's {@code *.mixins.json} configs and the mixin classes they name,
 * and records into a {@link UsedSet} the {@code net.minecraft}/{@code net.neoforged}
 * classes and members those mixins touch.
 *
 * <p>{@link JarScanner} sees only ordinary bytecode references (method calls, field
 * accesses, type usages). It cannot see a mixin's target: that is a string literal
 * inside an annotation ({@code @Mixin}'s {@code value}, an injector's {@code method}
 * array) or a bare declaration on the mixin class itself ({@code @Shadow}). Both are
 * invisible to a bytecode scan, and a member that exists only to be patched by a
 * mixin would otherwise be pruned as unused.
 */
public final class MixinScanner {
    private MixinScanner() {}

    private static final String MIXIN_ANNOTATION = "Lorg/spongepowered/asm/mixin/Mixin;";
    private static final String SHADOW_ANNOTATION = "Lorg/spongepowered/asm/mixin/Shadow;";

    /**
     * Every injector annotation whose {@code method} array names a target member by
     * string. Each of these can multi-target, hence a {@code List} of strings rather
     * than a single one.
     */
    private static final Set<String> INJECTOR_ANNOTATIONS = Set.of(
            "Lorg/spongepowered/asm/mixin/injection/Inject;",
            "Lorg/spongepowered/asm/mixin/injection/Redirect;",
            "Lorg/spongepowered/asm/mixin/injection/ModifyArg;",
            "Lorg/spongepowered/asm/mixin/injection/ModifyArgs;",
            "Lorg/spongepowered/asm/mixin/injection/ModifyVariable;",
            "Lorg/spongepowered/asm/mixin/injection/ModifyConstant;");

    public static void scan(Path jar, UsedSet into) throws IOException {
        try (JarFile jarFile = new JarFile(jar.toFile())) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".mixins.json")) {
                    scanConfig(jarFile, entry, into);
                }
            }
        }
    }

    private static void scanConfig(JarFile jarFile, JarEntry configEntry, UsedSet into) throws IOException {
        String json = new String(readEntry(jarFile, configEntry), StandardCharsets.UTF_8);
        MixinConfig config = MixinConfig.parse(json);
        String packagePath = config.mixinPackage().replace('.', '/');
        for (String mixinName : config.mixins()) {
            String classEntryName = packagePath + "/" + mixinName + ".class";
            JarEntry classEntry = jarFile.getJarEntry(classEntryName);
            if (classEntry == null) {
                throw new IOException("Mixin config " + configEntry.getName() + " names " + mixinName
                        + ", but " + classEntryName + " is not present in the jar");
            }
            byte[] bytes = readEntry(jarFile, classEntry);
            new ClassReader(bytes).accept(new MixinVisitor(into), ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        }
    }

    private static byte[] readEntry(JarFile jarFile, JarEntry entry) throws IOException {
        try (var in = jarFile.getInputStream(entry)) {
            return in.readAllBytes();
        }
    }

    private static void recordClass(UsedSet into, String internalName, String referencedBy) {
        String outer = Shimmed.outerOf(internalName);
        if (Shimmed.isShimmed(outer)) {
            into.addClass(outer, referencedBy);
        }
    }

    /**
     * Records a member under its real owner, and the file that owner lives in as a class.
     *
     * <p>The owner keeps its {@code $Nested} suffix. Only the <em>class</em> half is
     * collapsed with {@link Shimmed#outerOf}, because that names the file to generate.
     * Collapsing the member half too was a bug with a large blast radius: {@code
     * BlockBehaviour$Properties.of} was filed under {@code BlockBehaviour}, so when the
     * pruner asked for {@code BlockBehaviour$Properties}'s used members it got none and
     * pruned every one of them away. {@code Item$Properties} and {@code
     * BlockBehaviour$Properties} are among the most-called types in the whole manifest.
     *
     * <p>The class half is not redundant either. Nothing else in this visitor sees the
     * owner of a plain field read or method call: {@code visitTypeInsn} fires for {@code
     * new}, {@code checkcast} and {@code instanceof}, and a class touched only through a
     * static member -- {@code Registries.BLOCK} is exactly this -- appears in no type
     * instruction at all. Recorded as a member but not as a class, it would be listed in
     * the manifest and never generated.
     */
    private static void recordMember(UsedSet into, String owner, String name, String descriptor,
            String referencedBy) {
        String outer = Shimmed.outerOf(owner);
        if (Shimmed.isShimmed(outer)) {
            into.addMember(new UsedSet.MemberRef(owner, name, descriptor), referencedBy);
            into.addClass(outer, referencedBy);
        }
    }

    /**
     * Splits an injector's {@code method} target string at its first {@code (}. The
     * string is either a bare name ({@code <init>} included — it is a legal injection
     * target, not a malformed one) or {@code name(descriptor)ret}; the descriptor,
     * when present, is exactly the substring from the {@code (} onward, already in
     * JVM method-descriptor form.
     */
    private static void recordInjectTarget(UsedSet into, List<String> targets, String target, String referencedBy) {
        int paren = target.indexOf('(');
        String name = paren < 0 ? target : target.substring(0, paren);
        String descriptor = paren < 0 ? "" : target.substring(paren);
        for (String owner : targets) {
            recordMember(into, owner, name, descriptor, referencedBy);
        }
    }

    /**
     * The three keys this generator cares about in a {@code *.mixins.json}: {@code
     * package}, {@code mixins}, and {@code client}. {@code client} is parsed only so
     * the hand-rolled reader understands the file's shape; its contents are never
     * used — client mixins never run on a dedicated server and are out of scope.
     */
    private record MixinConfig(String mixinPackage, List<String> mixins) {
        static MixinConfig parse(String json) {
            String mixinPackage = requireString(json, "package");
            List<String> mixins = stringArray(json, "mixins");
            return new MixinConfig(mixinPackage, mixins);
        }

        private static String requireString(String json, String key) {
            Matcher m = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"]*)\"").matcher(json);
            if (!m.find()) {
                throw new IllegalArgumentException("Mixin config has no \"" + key + "\" string: " + json);
            }
            return m.group(1);
        }

        private static List<String> stringArray(String json, String key) {
            Matcher m = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\\[([^\\]]*)\\]").matcher(json);
            if (!m.find()) {
                return List.of();
            }
            List<String> values = new ArrayList<>();
            Matcher entries = Pattern.compile("\"([^\"]*)\"").matcher(m.group(1));
            while (entries.find()) {
                values.add(entries.group(1));
            }
            return values;
        }
    }

    /**
     * Visits one mixin class. {@code targets} accumulates the internal names read
     * from {@code @Mixin}'s {@code value} array; every method- and field-level
     * annotation is processed after {@link #visit}, so the target list is always
     * complete by the time an {@code @Inject}/{@code @Shadow} member needs it.
     */
    private static final class MixinVisitor extends ClassVisitor {
        private final UsedSet into;
        private final List<String> targets = new ArrayList<>();
        private String mixinClass;

        MixinVisitor(UsedSet into) {
            super(Opcodes.ASM9);
            this.into = into;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName,
                String[] interfaces) {
            this.mixinClass = name;
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            if (!descriptor.equals(MIXIN_ANNOTATION)) {
                return super.visitAnnotation(descriptor, visible);
            }
            return new AnnotationVisitor(Opcodes.ASM9) {
                @Override
                public AnnotationVisitor visitArray(String name) {
                    if (!"value".equals(name)) {
                        return null;
                    }
                    return new AnnotationVisitor(Opcodes.ASM9) {
                        @Override
                        public void visit(String name, Object value) {
                            if (value instanceof Type type) {
                                String target = type.getInternalName();
                                targets.add(target);
                                recordClass(into, target, mixinClass);
                            }
                        }
                    };
                }
            };
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature,
                Object value) {
            return new FieldVisitor(Opcodes.ASM9) {
                @Override
                public AnnotationVisitor visitAnnotation(String annDescriptor, boolean visible) {
                    if (annDescriptor.equals(SHADOW_ANNOTATION)) {
                        for (String target : targets) {
                            recordMember(into, target, name, descriptor, mixinClass);
                        }
                    }
                    return null;
                }
            };
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                String[] exceptions) {
            return new MethodVisitor(Opcodes.ASM9) {
                @Override
                public AnnotationVisitor visitAnnotation(String annDescriptor, boolean visible) {
                    if (annDescriptor.equals(SHADOW_ANNOTATION)) {
                        for (String target : targets) {
                            recordMember(into, target, name, descriptor, mixinClass);
                        }
                        return null;
                    }
                    if (INJECTOR_ANNOTATIONS.contains(annDescriptor)) {
                        return new AnnotationVisitor(Opcodes.ASM9) {
                            @Override
                            public AnnotationVisitor visitArray(String arrayName) {
                                if (!"method".equals(arrayName)) {
                                    return null;
                                }
                                return new AnnotationVisitor(Opcodes.ASM9) {
                                    @Override
                                    public void visit(String name, Object value) {
                                        if (value instanceof String target) {
                                            recordInjectTarget(into, targets, target, mixinClass);
                                        }
                                    }
                                };
                            }
                        };
                    }
                    return null;
                }
            };
        }
    }
}
