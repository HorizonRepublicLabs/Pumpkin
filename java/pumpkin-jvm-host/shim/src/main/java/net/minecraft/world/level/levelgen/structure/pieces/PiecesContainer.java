package net.minecraft.world.level.levelgen.structure.pieces;

import java.util.List;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import dev.pumpkin.shim.Unimplemented;

public record PiecesContainer(List<StructurePiece> pieces) {

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/PiecesContainer.isEmpty:()Z");
    }

    public static PiecesContainer load(ListTag children, StructurePieceSerializationContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/PiecesContainer.load:(Lnet/minecraft/nbt/ListTag;Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceSerializationContext;)Lnet/minecraft/world/level/levelgen/structure/pieces/PiecesContainer;");
    }
}
