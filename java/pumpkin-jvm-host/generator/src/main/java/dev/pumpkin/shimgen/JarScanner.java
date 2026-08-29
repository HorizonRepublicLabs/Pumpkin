package dev.pumpkin.shimgen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;

/**
 * Reads a mod jar's bytecode and records every {@code net.minecraft} and {@code
 * net.neoforged} class and member it touches into a {@link UsedSet}.
 *
 * <p>This is the sole source of truth for what the generated shim must contain: a
 * reference missed here becomes a missing class or method at link time, and one
 * recorded spuriously only costs generated code that a later pruning pass discards.
 */
public final class JarScanner {
    private JarScanner() {}

    public static int scan(Path jar, UsedSet into) throws IOException {
        return scan(List.of(jar), into);
    }

    /**
     * Scans every jar as one unit, which is required and not merely convenient: {@link
     * Inherited} resolves a mod class's supertype chain, and MysticalAgriculture's classes
     * extend Cucumber's. Scanned one jar at a time, half of those chains end at a name
     * this scanner has never seen.
     *
     * @return how many possibly-inherited references were skipped for want of the owner's
     *     hierarchy; see {@link Inherited#resolveInto}. Mostly the JDK, and expected to be
     *     large.
     */
    public static int scan(List<Path> jars, UsedSet into) throws IOException {
        Inherited inherited = new Inherited();
        for (Path jar : jars) {
            try (JarInputStream in = new JarInputStream(Files.newInputStream(jar))) {
                JarEntry entry;
                while ((entry = in.getNextJarEntry()) != null) {
                    if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                        continue;
                    }
                    byte[] bytes = in.readAllBytes();
                    new ClassReader(bytes)
                            .accept(new Visitor(into, inherited), ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                }
            }
        }
        return inherited.resolveInto(into);
    }

    /**
     * The references whose owner is a mod class but whose target may well be the shim's.
     *
     * <p>javac writes the <em>static receiver type</em> as a {@code Methodref}'s owner. So
     * {@code EnchanterRenderer} calling {@code tile.getBlockState()} on an {@code
     * EnchanterTileEntity} emits an owner of {@code EnchanterTileEntity} -- a mod class --
     * even though {@code getBlockState} is declared four levels up on vanilla's {@code
     * BlockEntity}. {@link #recordMember} filtered those out on the owner's package, so the
     * member was never recorded, the pruner deleted it as uncalled, and the shim shipped
     * without a method two mods call. There are 80 such references in the two mod jars and
     * they accounted for 236 unresolved references once the linkage check learned to look.
     *
     * <p>Resolution is deliberately coarse: walk the owner's superclass chain and stop at
     * the first shimmed class, or -- if the chain reaches the end of the mod's own code
     * without meeting one -- record against every nearest shimmed interface instead. It
     * does not work out which ancestor <em>declares</em> the member, because it has no
     * source tree and no classpath here. It does not need to: {@code Main.keepSet} closes
     * every recorded member over its owner's supertypes, so recording against the entry
     * point into the shim is enough for the declaring class to keep it. The superclass
     * chain is tried first because that is the order the JVM resolves in; interfaces
     * supply only default methods.
     */
    private static final class Inherited {
        /** Every class in the scanned jars, mapped to its superclass then its interfaces. */
        private final Map<String, List<String>> supertypes = new HashMap<>();
        private final Set<Deferred> deferred = new TreeSet<>();

        private record Deferred(String owner, String name, String descriptor, String referencedBy)
                implements Comparable<Deferred> {
            @Override
            public int compareTo(Deferred other) {
                return (owner + "." + name + ":" + descriptor + "\t" + referencedBy)
                        .compareTo(other.owner + "." + other.name + ":" + other.descriptor + "\t"
                                + other.referencedBy);
            }
        }

        void declare(String internalName, String superName, String[] interfaces) {
            List<String> parents = new ArrayList<>();
            if (superName != null) {
                parents.add(superName);
            }
            if (interfaces != null) {
                parents.addAll(List.of(interfaces));
            }
            supertypes.put(internalName, parents);
        }

        void defer(String owner, String name, String descriptor, String referencedBy) {
            deferred.add(new Deferred(owner, name, descriptor, referencedBy));
        }

        /**
         * @return how many deferred references were skipped because their owner is not a
         *     class from these jars. Overwhelmingly the JDK and the libraries -- every
         *     {@code List.add} in the mods lands here -- so a large number is the normal
         *     state and not a warning. It is returned rather than swallowed because it is
         *     the only bucket in this pass that drops a reference on the floor, and the
         *     one thing that would make it interesting -- it moving sharply between runs
         *     over the same jars -- is invisible while nothing prints it.
         */
        int resolveInto(UsedSet into) {
            int skipped = 0;
            for (Deferred ref : deferred) {
                if (!supertypes.containsKey(ref.owner())) {
                    // Not a class from these jars: the JDK, a library, or another mod's
                    // API. Its hierarchy is unknown and nothing here can be the shim's.
                    skipped++;
                    continue;
                }
                for (String entryPoint : shimEntryPointsAbove(ref.owner())) {
                    recordMember(into, entryPoint, ref.name(), ref.descriptor(), ref.referencedBy());
                }
            }
            return skipped;
        }

        /**
         * The shimmed classes a member lookup starting at {@code owner} would first meet:
         * the nearest shimmed superclass if the chain has one, otherwise the nearest
         * shimmed interfaces.
         */
        private Set<String> shimEntryPointsAbove(String owner) {
            for (String current = owner; current != null; ) {
                List<String> parents = supertypes.get(current);
                String superName = parents == null || parents.isEmpty() ? null : parents.get(0);
                if (superName == null) {
                    break;
                }
                if (Shimmed.isShimmed(Shimmed.outerOf(superName))) {
                    return Set.of(superName);
                }
                current = supertypes.containsKey(superName) ? superName : null;
            }
            Set<String> interfaces = new TreeSet<>();
            collectNearestShimmedInterfaces(owner, interfaces, new HashSet<>());
            return interfaces;
        }

        private void collectNearestShimmedInterfaces(String internalName, Set<String> found, Set<String> seen) {
            if (!seen.add(internalName)) {
                return;
            }
            List<String> parents = supertypes.get(internalName);
            if (parents == null) {
                return;
            }
            for (String parent : parents) {
                if (Shimmed.isShimmed(Shimmed.outerOf(parent))) {
                    found.add(parent);
                } else {
                    collectNearestShimmedInterfaces(parent, found, seen);
                }
            }
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
     * Records a member whose owner may be a mod class, deferring it to {@link Inherited}
     * when it is. A member is not the shim's business only when neither its owner nor
     * anything above its owner is shimmed, and that is not decidable until every jar has
     * been read.
     */
    private static void recordPossiblyInherited(UsedSet into, Inherited inherited, String owner, String name,
            String descriptor, String referencedBy) {
        if (Shimmed.isShimmed(Shimmed.outerOf(owner))) {
            recordMember(into, owner, name, descriptor, referencedBy);
        } else if (!name.equals("<init>")) {
            // A constructor is never inherited: the owner declares it or nothing does.
            inherited.defer(owner, name, descriptor, referencedBy);
        }
    }

    /** Records every object type named in a descriptor, unwrapping array types. */
    private static void recordDescriptorTypes(UsedSet into, String descriptor, String referencedBy) {
        for (Type type : typesIn(descriptor)) {
            recordObjectType(into, type, referencedBy);
        }
    }

    private static void recordObjectType(UsedSet into, Type type, String referencedBy) {
        Type t = type;
        while (t.getSort() == Type.ARRAY) {
            t = t.getElementType();
        }
        if (t.getSort() == Type.OBJECT) {
            recordClass(into, t.getInternalName(), referencedBy);
        }
    }

    /**
     * Every type mentioned by a descriptor: for a method descriptor, its argument
     * types and return type; for a field descriptor, the field's type itself.
     */
    private static Iterable<Type> typesIn(String descriptor) {
        if (descriptor.startsWith("(")) {
            Type methodType = Type.getMethodType(descriptor);
            Type[] args = methodType.getArgumentTypes();
            Type[] all = new Type[args.length + 1];
            System.arraycopy(args, 0, all, 0, args.length);
            all[args.length] = methodType.getReturnType();
            return List.of(all);
        }
        return List.of(Type.getType(descriptor));
    }

    /**
     * A {@link SignatureVisitor} that records every class type it visits. Every
     * method on {@link SignatureVisitor} that returns a nested visitor defaults to
     * returning {@code this}, so overriding only {@code visitClassType} is enough to
     * see every class type anywhere in the signature: type arguments, bounds, array
     * element types, and superclass/interfaces alike.
     */
    private static SignatureVisitor classTypeRecorder(UsedSet into, String referencedBy) {
        return new SignatureVisitor(Opcodes.ASM9) {
            @Override
            public void visitClassType(String name) {
                recordClass(into, name, referencedBy);
            }
        };
    }

    /**
     * Records the class types named in a class or method generic signature (the
     * {@code signature} parameter of {@code visit}/{@code visitMethod}). Erasure
     * discards a type argument like {@code ItemStack} in {@code List<ItemStack>};
     * the signature is the only place that type is still spelled out, so a
     * descriptor-only scan misses it entirely.
     */
    private static void recordSignatureTypes(UsedSet into, String signature, String referencedBy) {
        if (signature != null) {
            new SignatureReader(signature).accept(classTypeRecorder(into, referencedBy));
        }
    }

    /**
     * Same as {@link #recordSignatureTypes}, but for a field's generic signature,
     * which is a single field-type-signature rather than the class/method grammar.
     */
    private static void recordFieldSignatureTypes(UsedSet into, String signature, String referencedBy) {
        if (signature != null) {
            new SignatureReader(signature).acceptType(classTypeRecorder(into, referencedBy));
        }
    }

    private static final class Visitor extends ClassVisitor {
        private final UsedSet into;
        private final Inherited inherited;
        private String className;

        Visitor(UsedSet into, Inherited inherited) {
            super(Opcodes.ASM9);
            this.into = into;
            this.inherited = inherited;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName,
                String[] interfaces) {
            this.className = name;
            inherited.declare(name, superName, interfaces);
            if (superName != null) {
                recordClass(into, superName, className);
            }
            if (interfaces != null) {
                for (String itf : interfaces) {
                    recordClass(into, itf, className);
                }
            }
            recordSignatureTypes(into, signature, className);
            super.visit(version, access, name, signature, superName, interfaces);
        }

        @Override
        public FieldVisitor visitField(int access, String name, String descriptor, String signature,
                Object value) {
            recordDescriptorTypes(into, descriptor, className);
            recordFieldSignatureTypes(into, signature, className);
            return super.visitField(access, name, descriptor, signature, value);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                String[] exceptions) {
            recordDescriptorTypes(into, descriptor, className);
            recordSignatureTypes(into, signature, className);
            return new MethodVisitor(Opcodes.ASM9) {
                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String descriptor,
                        boolean isInterface) {
                    if (!owner.startsWith("[")) {
                        recordPossiblyInherited(into, inherited, owner, name, descriptor, className);
                    }
                    recordDescriptorTypes(into, descriptor, className);
                }

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                    recordPossiblyInherited(into, inherited, owner, name, descriptor, className);
                    recordDescriptorTypes(into, descriptor, className);
                }

                @Override
                public void visitTypeInsn(int opcode, String type) {
                    Type t = Type.getObjectType(type);
                    recordObjectType(into, t, className);
                }

                /**
                 * A lambda or method reference. The target it captures is a {@code
                 * MethodHandle} in the {@code BootstrapMethods} attribute, not a {@code
                 * Methodref} in an instruction, so {@link #visitMethodInsn} never sees
                 * it: {@code entity::isAlive} and {@code BlockEntity::saveWithFullMetadata}
                 * were both invisible to this scanner until the linkage check found the
                 * mods calling members no manifest entry had ever asked for.
                 */
                @Override
                public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrap,
                        Object... arguments) {
                    recordDescriptorTypes(into, descriptor, className);
                    for (Object argument : arguments) {
                        if (argument instanceof Handle handle) {
                            if (!handle.getOwner().startsWith("[")) {
                                recordPossiblyInherited(into, inherited, handle.getOwner(), handle.getName(),
                                        handle.getDesc(), className);
                            }
                            recordDescriptorTypes(into, handle.getDesc(), className);
                        } else if (argument instanceof Type type) {
                            if (type.getSort() == Type.METHOD) {
                                recordDescriptorTypes(into, type.getDescriptor(), className);
                            } else {
                                recordObjectType(into, type, className);
                            }
                        }
                    }
                }

                @Override
                public void visitLdcInsn(Object value) {
                    if (value instanceof Type t) {
                        recordObjectType(into, t, className);
                    }
                }

                @Override
                public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
                    recordObjectType(into, Type.getType(descriptor), className);
                }
            };
        }
    }
}
