package net.minecraft.advancements;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record AdvancementHolder(Identifier id, Advancement value) {

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementHolder.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementHolder.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementHolder.toString:()Ljava/lang/String;");
    }
}
