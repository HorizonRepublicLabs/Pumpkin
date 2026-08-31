package net.neoforged.fml.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Marks a mod's entry point. Read reflectively by the loader. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod {
    String value();

    /**
     * Which sides construct this entry point. A dedicated server skips CLIENT-only
     * entries entirely -- Mekanism ships a second {@code @Mod(dist = CLIENT)} class per
     * jar, and constructing it server-side reaches for screens that do not exist here.
     */
    net.neoforged.api.distmarker.Dist[] dist() default {
        net.neoforged.api.distmarker.Dist.CLIENT,
        net.neoforged.api.distmarker.Dist.DEDICATED_SERVER
    };
}
