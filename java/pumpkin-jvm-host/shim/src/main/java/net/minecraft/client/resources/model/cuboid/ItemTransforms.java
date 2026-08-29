package net.minecraft.client.resources.model.cuboid;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.world.item.ItemDisplayContext;
import dev.pumpkin.shim.Unimplemented;

public record ItemTransforms(ItemTransform thirdPersonLeftHand, ItemTransform thirdPersonRightHand, ItemTransform firstPersonLeftHand, ItemTransform firstPersonRightHand, ItemTransform head, ItemTransform gui, ItemTransform ground, ItemTransform fixed, ItemTransform fixedFromBottom, com.google.common.collect.ImmutableMap<ItemDisplayContext, ItemTransform> moddedTransforms) {

    public ItemTransforms(ItemTransform thirdPersonLeftHand, ItemTransform thirdPersonRightHand, ItemTransform firstPersonLeftHand, ItemTransform firstPersonRightHand, ItemTransform head, ItemTransform gui, ItemTransform ground, ItemTransform fixed, ItemTransform fixedFromBottom) {
        this((ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (ItemTransform) null, (com.google.common.collect.ImmutableMap<ItemDisplayContext, ItemTransform>) null);
        throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/ItemTransforms.<init>:(Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;Lnet/minecraft/client/resources/model/cuboid/ItemTransform;)V");
    }

    public static class Deserializer implements JsonDeserializer<ItemTransforms> {

        public ItemTransforms deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/ItemTransforms$Deserializer.deserialize:(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/resources/model/cuboid/ItemTransforms;");
        }

        protected Deserializer() {
        }
    }
}
