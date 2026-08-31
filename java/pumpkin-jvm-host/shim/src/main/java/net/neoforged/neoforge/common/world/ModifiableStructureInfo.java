package net.neoforged.neoforge.common.world;

import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import dev.pumpkin.shim.Unimplemented;

public class ModifiableStructureInfo {

    public ModifiableStructureInfo(final StructureInfo originalStructureInfo) {
    }

    public StructureInfo get() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableStructureInfo.get:()Lnet/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo;");
    }

    public record StructureInfo(StructureSettings structureSettings) {

        public static class Builder {

            public static Builder copyOf(final StructureInfo original) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo$Builder.copyOf:(Lnet/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo;)Lnet/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo$Builder;");
            }

            private Builder(final StructureSettingsBuilder structureSettings) {
            }

            public StructureInfo build() {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo$Builder.build:()Lnet/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo;");
            }

            public StructureSettingsBuilder getStructureSettings() {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/world/ModifiableStructureInfo$StructureInfo$Builder.getStructureSettings:()Lnet/neoforged/neoforge/common/world/StructureSettingsBuilder;");
            }

            public Builder() {
            }
        }
    }

    public ModifiableStructureInfo() {
    }
}
