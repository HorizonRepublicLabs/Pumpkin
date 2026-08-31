package net.minecraft.world.entity;

import dev.pumpkin.shim.Unimplemented;

public enum ConversionType {

    SINGLE {

        public void convert(Mob from, Mob to, ConversionParams params) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ConversionType$SINGLE.convert:()");
        }
    }
    , SPLIT_ON_DEATH {

        public void convert(Mob from, Mob to, ConversionParams params) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ConversionType$SPLIT_ON_DEATH.convert:()");
        }
    }
    ;

    public abstract void convert(Mob from, Mob to, ConversionParams params);
}
