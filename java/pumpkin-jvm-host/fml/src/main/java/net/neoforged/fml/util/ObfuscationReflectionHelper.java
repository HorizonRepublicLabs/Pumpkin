package net.neoforged.fml.util;

import dev.pumpkin.shim.Unimplemented;
import java.lang.reflect.Method;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>Only {@code findMethod} is modelled, which is all the manifest records the mods
 * calling ({@code CropHelper} reaches for a private vanilla method through it).
 *
 * <p>It throws rather than delegating to {@link Class#getDeclaredMethod}, which would
 * "work". Every method it could find on a shim class is a stub that throws, so a
 * successful lookup would hand the caller a handle to nothing and move the failure to a
 * later, less explicable place.
 */
public final class ObfuscationReflectionHelper {
    private ObfuscationReflectionHelper() {
    }

    public static <T> Method findMethod(Class<? super T> clazz, String methodName, Class<?>... parameterTypes) {
        throw Unimplemented.forMember("net/neoforged/fml/util/ObfuscationReflectionHelper.findMethod:"
                + "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;");
    }
}
