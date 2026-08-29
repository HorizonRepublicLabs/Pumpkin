package net.minecraft.server.level;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public record TicketType(long timeout, int flags, boolean forceNaturalSpawning) {

    public TicketType(long timeout, int flags) {
        this((long) 0L, (int) 0, (boolean) false);
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface Flags {
    }
}
