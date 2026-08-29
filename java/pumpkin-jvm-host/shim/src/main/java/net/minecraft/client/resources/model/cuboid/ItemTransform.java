package net.minecraft.client.resources.model.cuboid;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.blaze3d.vertex.PoseStack;
import java.lang.reflect.Type;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public record ItemTransform(Vector3fc rotation, Vector3fc translation, Vector3fc scale, Vector3fc rightRotation) {

    public ItemTransform(Vector3fc rotation, Vector3fc translation, Vector3fc scale) {
        this((Vector3fc) null, (Vector3fc) null, (Vector3fc) null, (Vector3fc) null);
        throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/ItemTransform.<init>:(Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;Lorg/joml/Vector3fc;)V");
    }

    public void apply(boolean applyLeftHandFix, PoseStack.Pose pose) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/ItemTransform.apply:(ZLcom/mojang/blaze3d/vertex/PoseStack$Pose;)V");
    }

    public static class Deserializer implements JsonDeserializer<ItemTransform> {

        public ItemTransform deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/ItemTransform$Deserializer.deserialize:(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/resources/model/cuboid/ItemTransform;");
        }

        public Deserializer() {
        }
    }
}
