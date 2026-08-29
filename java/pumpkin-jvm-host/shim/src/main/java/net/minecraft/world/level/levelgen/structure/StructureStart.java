package net.minecraft.world.level.levelgen.structure;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import dev.pumpkin.shim.Unimplemented;

public final class StructureStart {

    public StructureStart(Structure structure, ChunkPos chunkPos, int references, PiecesContainer pieceContainer) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructureStart.<init>:(Lnet/minecraft/world/level/levelgen/structure/Structure;Lnet/minecraft/world/level/ChunkPos;ILnet/minecraft/world/level/levelgen/structure/pieces/PiecesContainer;)V");
    }

    public BoundingBox getBoundingBox() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructureStart.getBoundingBox:()Lnet/minecraft/world/level/levelgen/structure/BoundingBox;");
    }

    public boolean isValid() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructureStart.isValid:()Z");
    }

    public StructureStart() {
    }
}
