package net.minecraft.client.data.models.model;

import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class ModelTemplate {

    public ModelTemplate(Optional<Identifier> model, Optional<String> suffix, TextureSlot... requiredSlots) {
    }

    public Identifier create(Block block, TextureMapping textures, BiConsumer<Identifier, ModelInstance> output) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelTemplate.create:(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/client/data/models/model/TextureMapping;Ljava/util/function/BiConsumer;)Lnet/minecraft/resources/Identifier;");
    }

    public Identifier create(Item item, TextureMapping textures, BiConsumer<Identifier, ModelInstance> output) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelTemplate.create:(Lnet/minecraft/world/item/Item;Lnet/minecraft/client/data/models/model/TextureMapping;Ljava/util/function/BiConsumer;)Lnet/minecraft/resources/Identifier;");
    }

    public Identifier create(Identifier target, TextureMapping textures, BiConsumer<Identifier, ModelInstance> output) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelTemplate.create:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/data/models/model/TextureMapping;Ljava/util/function/BiConsumer;)Lnet/minecraft/resources/Identifier;");
    }

    public JsonObject createBaseTemplate(Identifier target, Map<TextureSlot, Material> slots) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelTemplate.createBaseTemplate:(Lnet/minecraft/resources/Identifier;Ljava/util/Map;)Lcom/google/gson/JsonObject;");
    }

    public ModelTemplate() {
    }
}
