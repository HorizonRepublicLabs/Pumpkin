package net.minecraft.client.renderer.block;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.MaterialBaker;
import dev.pumpkin.shim.Unimplemented;

public record FluidModel(ChunkSectionLayer layer, Material.Baked stillMaterial, Material.Baked flowingMaterial, Material.Baked overlayMaterial, net.neoforged.neoforge.client.fluid.FluidTintSource fluidTintSource, net.neoforged.neoforge.client.fluid.CustomFluidRenderer customRenderer) {

    public FluidModel(ChunkSectionLayer layer, Material.Baked stillMaterial, Material.Baked flowingMaterial, Material.Baked overlayMaterial, net.neoforged.neoforge.client.fluid.FluidTintSource fluidTintSource) {
        this((ChunkSectionLayer) null, (Material.Baked) null, (Material.Baked) null, (Material.Baked) null, (net.neoforged.neoforge.client.fluid.FluidTintSource) null, (net.neoforged.neoforge.client.fluid.CustomFluidRenderer) null);
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidModel.<init>:(Lnet/minecraft/client/renderer/chunk/ChunkSectionLayer;Lnet/minecraft/client/resources/model/sprite/Material$Baked;Lnet/minecraft/client/resources/model/sprite/Material$Baked;Lnet/minecraft/client/resources/model/sprite/Material$Baked;Lnet/neoforged/neoforge/client/fluid/FluidTintSource;)V");
    }

    public FluidModel(ChunkSectionLayer layer, Material.Baked stillMaterial, Material.Baked flowingMaterial, Material.Baked overlayMaterial, BlockTintSource tintSource) {
        this((ChunkSectionLayer) null, (Material.Baked) null, (Material.Baked) null, (Material.Baked) null, (net.neoforged.neoforge.client.fluid.FluidTintSource) null, (net.neoforged.neoforge.client.fluid.CustomFluidRenderer) null);
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidModel.<init>:(Lnet/minecraft/client/renderer/chunk/ChunkSectionLayer;Lnet/minecraft/client/resources/model/sprite/Material$Baked;Lnet/minecraft/client/resources/model/sprite/Material$Baked;Lnet/minecraft/client/resources/model/sprite/Material$Baked;Lnet/minecraft/client/color/block/BlockTintSource;)V");
    }

    public record Unbaked(Material stillMaterial, Material flowingMaterial, Material overlayMaterial, net.neoforged.neoforge.client.fluid.FluidTintSource fluidTintSource, net.neoforged.neoforge.client.fluid.CustomFluidRenderer customRenderer) {

        public Unbaked(Material stillMaterial, Material flowingMaterial, Material overlayMaterial, net.neoforged.neoforge.client.fluid.FluidTintSource fluidTintSource) {
            this((Material) null, (Material) null, (Material) null, (net.neoforged.neoforge.client.fluid.FluidTintSource) null, (net.neoforged.neoforge.client.fluid.CustomFluidRenderer) null);
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidModel$Unbaked.<init>:(Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/resources/model/sprite/Material;Lnet/neoforged/neoforge/client/fluid/FluidTintSource;)V");
        }

        public Unbaked(Material stillMaterial, Material flowingMaterial, Material overlayMaterial, BlockTintSource tintSource) {
            this((Material) null, (Material) null, (Material) null, (net.neoforged.neoforge.client.fluid.FluidTintSource) null, (net.neoforged.neoforge.client.fluid.CustomFluidRenderer) null);
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidModel$Unbaked.<init>:(Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/color/block/BlockTintSource;)V");
        }

        public FluidModel bake(MaterialBaker materials, ModelDebugName modelName) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/FluidModel$Unbaked.bake:(Lnet/minecraft/client/resources/model/sprite/MaterialBaker;Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/renderer/block/FluidModel;");
        }
    }
}
