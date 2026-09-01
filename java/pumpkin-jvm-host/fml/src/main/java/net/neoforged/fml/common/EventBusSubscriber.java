package net.neoforged.fml.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.neoforged.api.distmarker.Dist;

/**
 * NeoForge's marker for a class whose static {@code @SubscribeEvent} methods register
 * automatically. The host's mod loader collects annotated classes per jar and registers
 * the server-side ones on the game bus; without this class on the classpath the JVM
 * silently drops the annotation and every such handler goes dead, which is exactly the
 * silent failure this shim refuses to have.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventBusSubscriber {
    Dist[] value() default {Dist.CLIENT, Dist.DEDICATED_SERVER};

    String modid() default "";
}
