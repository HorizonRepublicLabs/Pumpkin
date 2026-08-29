package dev.pumpkin.shimgen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
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

    public static void scan(Path jar, UsedSet into) throws IOException {
        try (JarInputStream in = new JarInputStream(Files.newInputStream(jar))) {
            JarEntry entry;
            while ((entry = in.getNextJarEntry()) != null) {
                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }
                byte[] bytes = in.readAllBytes();
                new ClassReader(bytes).accept(new Visitor(into), ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            }
        }
    }

    private static void recordClass(UsedSet into, String internalName, String referencedBy) {
        String outer = Shimmed.outerOf(internalName);
        if (Shimmed.isShimmed(outer)) {
            into.addClass(outer, referencedBy);
        }
    }

    private static void recordMember(UsedSet into, String owner, String name, String descriptor,
            String referencedBy) {
        String outer = Shimmed.outerOf(owner);
        if (Shimmed.isShimmed(outer)) {
            into.addMember(new UsedSet.MemberRef(outer, name, descriptor), referencedBy);
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
        private String className;

        Visitor(UsedSet into) {
            super(Opcodes.ASM9);
            this.into = into;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName,
                String[] interfaces) {
            this.className = name;
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
                    recordMember(into, owner, name, descriptor, className);
                    recordDescriptorTypes(into, descriptor, className);
                }

                @Override
                public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
                    recordMember(into, owner, name, descriptor, className);
                    recordDescriptorTypes(into, descriptor, className);
                }

                @Override
                public void visitTypeInsn(int opcode, String type) {
                    Type t = Type.getObjectType(type);
                    recordObjectType(into, t, className);
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
