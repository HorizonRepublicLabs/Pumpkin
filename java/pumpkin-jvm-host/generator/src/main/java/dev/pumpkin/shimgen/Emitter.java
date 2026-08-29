package dev.pumpkin.shimgen;

import com.github.javaparser.ast.CompilationUnit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes a pruned {@link CompilationUnit} to {@code <outputRoot>/<internalName>.java}.
 *
 * <p>Unconditional: it always writes, creating parent directories as needed, and
 * overwrites whatever was already there. It does not decide which classes to
 * regenerate — that is the caller's job (Task 7's pipeline), not this one's.
 */
public final class Emitter {
    private Emitter() {}

    public static void emit(CompilationUnit cu, String internalName, Path outputRoot) throws IOException {
        Path outFile = outputRoot.resolve(internalName + ".java");
        Path parent = outFile.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        String content = cu.toString();
        if (!content.endsWith("\n")) {
            content = content + "\n";
        }
        Files.writeString(outFile, content, StandardCharsets.UTF_8);
    }
}
