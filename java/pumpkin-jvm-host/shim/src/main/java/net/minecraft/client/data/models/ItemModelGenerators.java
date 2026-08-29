package net.minecraft.client.data.models;

import java.util.function.BiConsumer;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class ItemModelGenerators {

    public final ItemModelOutput itemModelOutput = Stubs.of(ItemModelOutput.class, "net/minecraft/client/data/models/ItemModelOutput");

    public final BiConsumer<Identifier, ModelInstance> modelOutput = null;

    public ItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ItemModelGenerators.<init>:(Lnet/minecraft/client/data/models/ItemModelOutput;Ljava/util/function/BiConsumer;)V");
    }

    public void run() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/ItemModelGenerators.run:()V");
    }

    public record TrimMaterialData(MaterialAssetGroup assets, ResourceKey<TrimMaterial> materialKey) {
    }

    public ItemModelGenerators() {
    }
}
