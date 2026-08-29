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
        Path jar = Files.createTempFile("scanner", ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry("example/Caller.class"));
            out.write(classBytes);
            out.closeEntry();
        }
        return jar;
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
}
