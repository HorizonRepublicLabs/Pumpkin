package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class JarScannerTest {
    /// A class that calls Level.getBlockState and extends nothing interesting.
    private static byte[] callerClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/Caller", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "go",
                "(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "net/minecraft/world/level/Level", "getBlockState",
                "(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", false);
        mv.visitInsn(Opcodes.POP);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    private static Path jarWith(byte[] classBytes) throws Exception {
        return jarWith("example/Caller.class", classBytes);
    }

    private static Path jarWith(String entryName, byte[] classBytes) throws Exception {
        Path jar = Files.createTempFile("scanner", ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry(entryName));
            out.write(classBytes);
            out.closeEntry();
        }
        return jar;
    }

    /**
     * A method whose descriptor says only {@code java/util/List} but whose generic
     * signature names {@code ItemStack} as the type argument. Erasure hides this
     * type from the descriptor entirely, so it is reachable only by parsing the
     * signature string.
     */
    private static byte[] genericReturnTypeClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/HasGeneric", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "items", "()Ljava/util/List;",
                "()Ljava/util/List<Lnet/minecraft/world/item/ItemStack;>;", null);
        mv.visitCode();
        mv.visitInsn(Opcodes.ACONST_NULL);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(1, 0);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    /**
     * A field whose descriptor says only {@code java/util/Map} but whose generic
     * signature names a NeoForge type as a type argument.
     */
    private static byte[] genericFieldClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/HasGenericField", null, "java/lang/Object", null);
        cw.visitField(Opcodes.ACC_PRIVATE, "cache", "Ljava/util/Map;",
                "Ljava/util/Map<Ljava/lang/String;Lnet/neoforged/neoforge/common/util/FakePlayer;>;", null);
        cw.visitEnd();
        return cw.toByteArray();
    }

    /** A class literal ({@code Entity.class}) loaded via LDC, named nowhere else. */
    private static byte[] classLiteralClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/HasClassLiteral", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "go", "()V", null, null);
        mv.visitCode();
        mv.visitLdcInsn(Type.getObjectType("net/minecraft/world/entity/Entity"));
        mv.visitInsn(Opcodes.POP);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 0);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    /** A multi-dimensional array allocation naming a type nowhere else. */
    private static byte[] multiANewArrayClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/HasMultiArray", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "go", "()V", null, null);
        mv.visitCode();
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitMultiANewArrayInsn("[[Lnet/minecraft/world/level/block/Block;", 2);
        mv.visitInsn(Opcodes.POP);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 0);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    @Test
    void findsCalledMembersAndTheirOwners() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith(callerClass()), used);

        assertTrue(used.classes().contains("net/minecraft/world/level/Level"));
        assertTrue(used.membersOf("net/minecraft/world/level/Level").stream()
                .anyMatch(k -> k.contains("getBlockState")));
    }

    /// Types named only in a descriptor are still referenced types.
    @Test
    void findsTypesAppearingOnlyInSignatures() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith(callerClass()), used);
        assertTrue(used.classes().contains("net/minecraft/core/BlockPos"));
        assertTrue(used.classes().contains("net/minecraft/world/level/block/state/BlockState"));
    }

    /// The mod's own classes are not part of the shim.
    @Test
    void ignoresClassesOutsideMinecraftAndNeoforge() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith(callerClass()), used);
        assertFalse(used.classes().contains("example/Caller"));
        assertFalse(used.classes().contains("java/lang/Object"));
    }

    /**
     * A type argument like {@code ItemStack} in {@code List<ItemStack>} is erased
     * out of the descriptor entirely; it is spelled out only in the method's
     * generic signature. This is the exact case a descriptor-only scan misses.
     */
    @Test
    void findsTypesNamedOnlyInAMethodsGenericSignature() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith("example/HasGeneric.class", genericReturnTypeClass()), used);
        assertTrue(used.classes().contains("net/minecraft/world/item/ItemStack"));
    }

    /// Same erasure gap, but for a field's generic signature rather than a method's.
    @Test
    void findsTypesNamedOnlyInAFieldsGenericSignature() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith("example/HasGenericField.class", genericFieldClass()), used);
        assertTrue(used.classes().contains("net/neoforged/neoforge/common/util/FakePlayer"));
    }

    /// A class literal (`Entity.class`, an LDC of a Type constant) is a real reference.
    @Test
    void findsClassLiteralsLoadedViaLdc() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith("example/HasClassLiteral.class", classLiteralClass()), used);
        assertTrue(used.classes().contains("net/minecraft/world/entity/Entity"));
    }

    /// A MULTIANEWARRAY instruction's element type is a real reference too.
    @Test
    void findsTypesUsedOnlyInMultiANewArray() throws Exception {
        UsedSet used = new UsedSet();
        JarScanner.scan(jarWith("example/HasMultiArray.class", multiANewArrayClass()), used);
        assertTrue(used.classes().contains("net/minecraft/world/level/block/Block"));
    }
}
