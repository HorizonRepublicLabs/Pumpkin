package net.neoforged.fml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Reads the class-level annotations out of a mod jar.
 *
 * <p>Not part of NeoForge's API. FML does this scan during mod discovery and hands the
 * result back through {@link ModList#getAllScanData()}; Pumpkin's host discovers mods one at
 * a time, so the scan happens here instead.
 *
 * <p>A mod uses this to find its own add-ons: MysticalAgriculture's {@code PluginRegistry}
 * walks every annotation looking for its plugin marker, then loads the class named by {@code
 * memberName}. That is why {@code memberName} carries a class's binary name -- for a
 * class-level annotation, FML puts the annotated class there.
 *
 * <p><strong>Class-level annotations only.</strong> FML also reports annotations on fields
 * and methods, where {@code memberName} means something different. Nothing in the manifest
 * records either mod reading those, and inventing a spelling for them would be a guess this
 * code could not check. A mod that needs them gets a smaller set than it expects rather than
 * a wrong one -- see the caveat on {@link ModList#getAllScanData()}.
 */
final class PumpkinJarScan {
    private PumpkinJarScan() {
    }

    /**
     * Scans one jar.
     *
     * @throws IOException if the jar cannot be read; the caller decides whether a mod whose
     *                     file has gone missing is fatal
     */
    static ModFileScanData of(Path jar) throws IOException {
        Set<ModFileScanData.AnnotationData> found = new HashSet<>();
        try (JarFile file = new JarFile(jar.toFile())) {
            var entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }
                try (InputStream in = file.getInputStream(entry)) {
                    new ClassReader(in).accept(new Collector(found), ClassReader.SKIP_CODE
                            | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                }
            }
        }
        return new ModFileScanData(found);
    }

    /** Records every class-level annotation against the class carrying it. */
    private static final class Collector extends ClassVisitor {
        private final Set<ModFileScanData.AnnotationData> found;
        private String className;

        Collector(Set<ModFileScanData.AnnotationData> found) {
            super(Opcodes.ASM9);
            this.found = found;
        }

        @Override
        public void visit(int version, int access, String name, String signature,
                String superName, String[] interfaces) {
            // Binary name, because that is what a mod passes to Class.forName.
            className = name.replace('/', '.');
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            // Invisible annotations are recorded too: FML reports both, and a mod marker
            // compiled without RUNTIME retention would otherwise vanish from the scan while
            // still being present in the file.
            found.add(new ModFileScanData.AnnotationData(Type.getType(descriptor), className));
            return null;
        }
    }
}
