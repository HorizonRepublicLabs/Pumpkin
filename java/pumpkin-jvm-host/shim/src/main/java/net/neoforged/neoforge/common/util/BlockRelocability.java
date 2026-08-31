package net.neoforged.neoforge.common.util;

import java.util.Set;
import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public interface BlockRelocability {

    public abstract boolean isRelocatable(Set<BlockPos> relocatingPositions);

    public static enum No implements BlockRelocability {

        INSTANCE;

        public boolean isRelocatable(Set<BlockPos> relocatingPositions) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockRelocability$No.isRelocatable:(Ljava/util/Set;)Z");
        }
    }

    public static enum Yes implements BlockRelocability {

        INSTANCE;

        public boolean isRelocatable(Set<BlockPos> relocatingPositions) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockRelocability$Yes.isRelocatable:(Ljava/util/Set;)Z");
        }
    }

    public static record Multiblock(Set<BlockPos> requiredPositions) implements BlockRelocability {

        public boolean isRelocatable(Set<BlockPos> relocatingPositions) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/BlockRelocability$Multiblock.isRelocatable:(Ljava/util/Set;)Z");
        }
    }
}
