package net.neoforged.neoforge.client.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.pumpkin.shim.Unimplemented;

public record ExtraFaceData(int color, int lightEmission, boolean ambientOcclusion) {

    public static ExtraFaceData read(JsonElement obj, ExtraFaceData fallback) throws JsonParseException {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/ExtraFaceData.read:(Lcom/google/gson/JsonElement;Lnet/neoforged/neoforge/client/model/ExtraFaceData;)Lnet/neoforged/neoforge/client/model/ExtraFaceData;");
    }
}
