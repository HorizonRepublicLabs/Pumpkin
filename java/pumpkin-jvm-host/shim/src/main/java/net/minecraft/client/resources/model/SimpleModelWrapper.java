package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record SimpleModelWrapper(QuadCollection quads, boolean useAmbientOcclusion, Material.Baked particleMaterial) implements BlockStateModelPart {

    public static BlockStateModelPart bake(ModelBaker modelBakery, Identifier location, ModelState state) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/SimpleModelWrapper.bake:(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/renderer/block/dispatch/ModelState;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModelPart;");
    }

    public static BlockStateModelPart bake(ModelBaker modelBakery, ResolvedModel model, ModelState state) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/SimpleModelWrapper.bake:(Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/resources/model/ResolvedModel;Lnet/minecraft/client/renderer/block/dispatch/ModelState;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModelPart;");
    }

    public List<BakedQuad> getQuads(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/SimpleModelWrapper.getQuads:(Lnet/minecraft/core/Direction;)Ljava/util/List;");
    }

    public int materialFlags() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/SimpleModelWrapper.materialFlags:()I");
    }
}
