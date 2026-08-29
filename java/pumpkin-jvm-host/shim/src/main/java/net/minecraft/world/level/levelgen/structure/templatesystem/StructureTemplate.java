package net.minecraft.world.level.levelgen.structure.templatesystem;

import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class StructureTemplate {

    public Vec3i getSize() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate.getSize:()Lnet/minecraft/core/Vec3i;");
    }

    public void load(HolderGetter<Block> blockLookup, CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate.load:(Lnet/minecraft/core/HolderGetter;Lnet/minecraft/nbt/CompoundTag;)V");
    }

    public record JigsawBlockInfo(StructureTemplate.StructureBlockInfo info, JigsawBlockEntity.JointType jointType, Identifier name, ResourceKey<StructureTemplatePool> pool, Identifier target, int placementPriority, int selectionPriority) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate$JigsawBlockInfo.toString:()Ljava/lang/String;");
        }
    }

    public static final class Palette {

        private Palette(List<StructureTemplate.StructureBlockInfo> blocks) {
        }

        public Palette() {
        }
    }

    private static class SimplePalette implements Iterable<BlockState> {

        public Iterator<BlockState> iterator() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate$SimplePalette.iterator:()Ljava/util/Iterator;");
        }

        protected SimplePalette() {
        }
    }

    public record StructureBlockInfo(BlockPos pos, BlockState state, CompoundTag nbt) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate$StructureBlockInfo.toString:()Ljava/lang/String;");
        }
    }

    public static class StructureEntityInfo {

        public StructureEntityInfo(Vec3 pos, BlockPos blockPos, CompoundTag nbt) {
        }

        public StructureEntityInfo() {
        }
    }

    public StructureTemplate() {
    }
}
