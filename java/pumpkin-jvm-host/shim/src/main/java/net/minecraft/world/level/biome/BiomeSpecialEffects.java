package net.minecraft.world.level.biome;

import java.util.Optional;
import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public record BiomeSpecialEffects(int waterColor, Optional<Integer> foliageColorOverride, Optional<Integer> dryFoliageColorOverride, Optional<Integer> grassColorOverride, BiomeSpecialEffects.GrassColorModifier grassColorModifier) {

    public static class Builder {

        public BiomeSpecialEffects build() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSpecialEffects$Builder.build:()Lnet/minecraft/world/level/biome/BiomeSpecialEffects;");
        }

        protected Builder() {
        }
    }

    public enum GrassColorModifier implements StringRepresentable, IExtensibleEnum {

        NONE {

            public int modifyColor(double x, double z, int baseColor) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSpecialEffects$GrassColorModifier$NONE.modifyColor:()");
            }
        }
        , DARK_FOREST {

            public int modifyColor(double x, double z, int baseColor) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSpecialEffects$GrassColorModifier$DARK_FOREST.modifyColor:()");
            }
        }
        , SWAMP {

            public int modifyColor(double x, double z, int baseColor) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSpecialEffects$GrassColorModifier$SWAMP.modifyColor:()");
            }
        }
        ;

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSpecialEffects$GrassColorModifier.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/BiomeSpecialEffects$GrassColorModifier.getSerializedName:()Ljava/lang/String;");
        }

        public interface ColorModifier {

            int modifyGrassColor(double x, double z, int color);
        }
    }
}
