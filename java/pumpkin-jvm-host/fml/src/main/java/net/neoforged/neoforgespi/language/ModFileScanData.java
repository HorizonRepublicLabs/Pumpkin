package net.neoforged.neoforgespi.language;

import dev.pumpkin.shim.Unimplemented;
import java.util.Set;
import org.objectweb.asm.Type;

/**
 * Hand-written, not generated: {@code neoforgespi} is published as a separate NeoForge
 * artifact whose sources are not in the decompiled tree. On the generator's "no source
 * found" list; do not delete it as un-regenerable.
 *
 * <p>What FML's classpath scan found in one mod file. Both real mods walk {@code
 * getAnnotations} to discover their own plugin classes, which is how a mod finds its
 * add-ons without a registry.
 *
 * <p>{@link AnnotationData#annotationType()} really does return ASM's {@code Type}: FML
 * scans bytecode and hands the result back in the scanner's own vocabulary. That is why
 * {@code fml} compiles against ASM, and it is the only reason.
 */
public class ModFileScanData {
    public ModFileScanData() {
    }

    public Set<AnnotationData> getAnnotations() {
        throw Unimplemented.forMember(
                "net/neoforged/neoforgespi/language/ModFileScanData.getAnnotations:()Ljava/util/Set;");
    }

    /**
     * One annotation found on one class member.
     *
     * <p>Two components, not NeoForge's five: these are the two the manifest records the
     * mods reading, and a record's accessors are matched by name and descriptor, never by
     * position. Modelling components nothing calls would mean guessing at their order and
     * types, and guessing wrong in a way no check here could catch.
     */
    public record AnnotationData(Type annotationType, String memberName) {
    }
}
