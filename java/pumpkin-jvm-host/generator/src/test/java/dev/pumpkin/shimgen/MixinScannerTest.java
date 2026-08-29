package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

class MixinScannerTest {
    private static final String CONFIG = """
            {
              "package": "example.mixin",
              "mixins": ["ItemStackMixin"],
              "client": ["ModelBakeryMixin"]
            }
            """;

    /// A mixin shaped like Cucumber's: @Mixin(ItemStack.class), one @Inject naming a
    /// method by descriptor string, one @Shadow method.
    private static byte[] mixinClass() {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(Opcodes.V21, Opcodes.ACC_PUBLIC, "example/mixin/ItemStackMixin", null, "java/lang/Object", null);

        AnnotationVisitor mixin = cw.visitAnnotation("Lorg/spongepowered/asm/mixin/Mixin;", false);
        AnnotationVisitor targets = mixin.visitArray("value");
        targets.visit(null, Type.getObjectType("net/minecraft/world/item/ItemStack"));
        targets.visitEnd();
        mixin.visitEnd();

        MethodVisitor inject = cw.visitMethod(Opcodes.ACC_PUBLIC, "onApplyDamage", "()V", null, null);
        AnnotationVisitor at = inject.visitAnnotation("Lorg/spongepowered/asm/mixin/injection/Inject;", false);
        AnnotationVisitor methods = at.visitArray("method");
        methods.visit(null, "applyDamage(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V");
        methods.visitEnd();
        at.visitEnd();
        inject.visitCode();
        inject.visitInsn(Opcodes.RETURN);
        inject.visitMaxs(0, 1);
        inject.visitEnd();

        MethodVisitor shadow = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT, "copy",
                "()Lnet/minecraft/world/item/ItemStack;", null, null);
        shadow.visitAnnotation("Lorg/spongepowered/asm/mixin/Shadow;", false).visitEnd();
        shadow.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static Path jar() throws Exception {
        Path jar = Files.createTempFile("mixin", ".jar");
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar))) {
            out.putNextEntry(new JarEntry("example.mixins.json"));
            out.write(CONFIG.getBytes(StandardCharsets.UTF_8));
            out.closeEntry();
            out.putNextEntry(new JarEntry("example/mixin/ItemStackMixin.class"));
            out.write(mixinClass());
            out.closeEntry();
        }
        return jar;
    }

    /// The whole point: a member named only inside an @Inject string.
    @Test
    void addsInjectTargetsNamedOnlyInAnnotationStrings() throws Exception {
        UsedSet used = new UsedSet();
        MixinScanner.scan(jar(), used);
        assertTrue(used.membersOf("net/minecraft/world/item/ItemStack").stream()
                        .anyMatch(k -> k.contains("applyDamage")),
                "an @Inject target must survive pruning");
    }

    @Test
    void addsShadowedMembersToTheTargetClass() throws Exception {
        UsedSet used = new UsedSet();
        MixinScanner.scan(jar(), used);
        // NOTE: the brief's fixture asserted `k.contains(".copy:")`. UsedSet.membersOf
        // (Task 2, already landed) returns "name:descriptor" with the owner already
        // stripped -- see UsedSetTest and JarScannerTest's identical-shaped assertions
        // (e.g. `k.contains("getBlockState")`, no owner, no leading dot). There is no
        // owner segment here for a dot to precede, so ".copy:" can never appear no
        // matter what MixinScanner does. Asserting "copy:" instead matches the
        // established, working convention used everywhere else in this suite.
        assertTrue(used.membersOf("net/minecraft/world/item/ItemStack").stream()
                .anyMatch(k -> k.contains("copy:")));
    }

    /// Client mixins are listed separately in the config and are out of scope.
    @Test
    void skipsMixinsListedUnderClient() throws Exception {
        UsedSet used = new UsedSet();
        MixinScanner.scan(jar(), used);
        assertFalse(used.classes().contains("net/minecraft/client/resources/model/ModelBakery"));
    }
}
