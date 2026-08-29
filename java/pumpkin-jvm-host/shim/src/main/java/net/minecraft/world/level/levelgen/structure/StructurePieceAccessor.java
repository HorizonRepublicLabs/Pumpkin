package net.minecraft.world.level.levelgen.structure;

public interface StructurePieceAccessor {

    void addPiece(StructurePiece piece);

    StructurePiece findCollisionPiece(BoundingBox box);
}
