package net.minecraft.world.level.levelgen.structure.pieces;

import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import dev.pumpkin.shim.Unimplemented;

public class StructurePiecesBuilder implements StructurePieceAccessor {

    public void addPiece(StructurePiece piece) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder.addPiece:(Lnet/minecraft/world/level/levelgen/structure/StructurePiece;)V");
    }

    public StructurePiece findCollisionPiece(BoundingBox box) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder.findCollisionPiece:(Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)Lnet/minecraft/world/level/levelgen/structure/StructurePiece;");
    }

    public PiecesContainer build() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder.build:()Lnet/minecraft/world/level/levelgen/structure/pieces/PiecesContainer;");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder.clear:()V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder.isEmpty:()Z");
    }

    public BoundingBox getBoundingBox() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder.getBoundingBox:()Lnet/minecraft/world/level/levelgen/structure/BoundingBox;");
    }

    protected StructurePiecesBuilder() {
    }
}
