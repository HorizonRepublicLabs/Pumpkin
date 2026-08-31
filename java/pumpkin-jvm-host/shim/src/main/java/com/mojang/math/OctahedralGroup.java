package com.mojang.math;

import net.minecraft.util.StringRepresentable;
import org.joml.Matrix3fc;
import dev.pumpkin.shim.Unimplemented;

public enum OctahedralGroup implements StringRepresentable {

    IDENTITY,
    ROT_180_FACE_XY,
    ROT_180_FACE_XZ,
    ROT_180_FACE_YZ,
    ROT_120_NNN,
    ROT_120_NNP,
    ROT_120_NPN,
    ROT_120_NPP,
    ROT_120_PNN,
    ROT_120_PNP,
    ROT_120_PPN,
    ROT_120_PPP,
    ROT_180_EDGE_XY_NEG,
    ROT_180_EDGE_XY_POS,
    ROT_180_EDGE_XZ_NEG,
    ROT_180_EDGE_XZ_POS,
    ROT_180_EDGE_YZ_NEG,
    ROT_180_EDGE_YZ_POS,
    ROT_90_X_NEG,
    ROT_90_X_POS,
    ROT_90_Y_NEG,
    ROT_90_Y_POS,
    ROT_90_Z_NEG,
    ROT_90_Z_POS,
    INVERSION,
    INVERT_X,
    INVERT_Y,
    INVERT_Z,
    ROT_60_REF_NNN,
    ROT_60_REF_NNP,
    ROT_60_REF_NPN,
    ROT_60_REF_NPP,
    ROT_60_REF_PNN,
    ROT_60_REF_PNP,
    ROT_60_REF_PPN,
    ROT_60_REF_PPP,
    SWAP_XY,
    SWAP_YZ,
    SWAP_XZ,
    SWAP_NEG_XY,
    SWAP_NEG_YZ,
    SWAP_NEG_XZ,
    ROT_90_REF_X_NEG,
    ROT_90_REF_X_POS,
    ROT_90_REF_Y_NEG,
    ROT_90_REF_Y_POS,
    ROT_90_REF_Z_NEG,
    ROT_90_REF_Z_POS;

    public OctahedralGroup inverse() {
        throw Unimplemented.forMember("com/mojang/math/OctahedralGroup.inverse:()Lcom/mojang/math/OctahedralGroup;");
    }

    public Matrix3fc transformation() {
        throw Unimplemented.forMember("com/mojang/math/OctahedralGroup.transformation:()Lorg/joml/Matrix3fc;");
    }

    public String toString() {
        throw Unimplemented.forMember("com/mojang/math/OctahedralGroup.toString:()Ljava/lang/String;");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("com/mojang/math/OctahedralGroup.getSerializedName:()Ljava/lang/String;");
    }
}
