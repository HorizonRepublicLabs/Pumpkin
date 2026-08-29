package net.minecraft.world.level.levelgen.structure.pieces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import dev.pumpkin.shim.Unimplemented;

public interface StructurePieceType {

    StructurePiece load(StructurePieceSerializationContext context, CompoundTag tag);

    interface ContextlessType extends StructurePieceType {

        StructurePiece load(final CompoundTag tag);

        default StructurePiece load(StructurePieceSerializationContext context, CompoundTag tag) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePieceType$ContextlessType.load:(Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceSerializationContext;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/level/levelgen/structure/StructurePiece;");
        }
    }

    interface StructureTemplateType extends StructurePieceType {

        StructurePiece load(final StructureTemplateManager structureTemplateManager, final CompoundTag tag);

        default StructurePiece load(StructurePieceSerializationContext context, CompoundTag tag) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePieceType$StructureTemplateType.load:(Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceSerializationContext;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/level/levelgen/structure/StructurePiece;");
        }
    }
}
