package net.neoforged.neoforge.client.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.math.Transformation;
import java.util.Map;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record StandardModelParameters(Identifier parent, TextureSlots.Data textures, ItemTransforms itemTransforms, Boolean ambientOcclusion, UnbakedModel.GuiLight guiLight, Transformation rootTransform, Map<String, Boolean> partVisibility) {

    public static StandardModelParameters parse(JsonObject jsonObject, JsonDeserializationContext context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/StandardModelParameters.parse:(Lcom/google/gson/JsonObject;Lcom/google/gson/JsonDeserializationContext;)Lnet/neoforged/neoforge/client/model/StandardModelParameters;");
    }
}
