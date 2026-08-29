package net.minecraft.client.renderer.block.dispatch;

import java.util.List;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.client.extensions.BlockStateModelPartExtension;

public interface BlockStateModelPart extends BlockStateModelPartExtension {

    List<BakedQuad> getQuads(Direction direction);

    boolean useAmbientOcclusion();

    Material.Baked particleMaterial();

    int materialFlags();

    interface Unbaked extends ResolvableModel {

        BlockStateModelPart bake(ModelBaker modelBakery);
    }
}
