package net.minecraft.client.resources.model.cuboid;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import net.minecraft.core.Direction;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public record CuboidModelElement(Vector3fc from, Vector3fc to, Map<Direction, CuboidFace> faces, CuboidRotation rotation, boolean shade, int lightEmission, net.neoforged.neoforge.client.model.ExtraFaceData faceData) {

    public CuboidModelElement(Vector3fc from, Vector3fc to, Map<Direction, CuboidFace> faces, CuboidRotation rotation, boolean shade, int lightEmission) {
        this((Vector3fc) null, (Vector3fc) null, (Map<Direction, CuboidFace>) null, (CuboidRotation) null, (boolean) false, (int) 0, (net.neoforged.neoforge.client.model.ExtraFaceData) null);
    }

    public CuboidModelElement(Vector3fc from, Vector3fc to, Map<Direction, CuboidFace> faces, net.neoforged.neoforge.client.model.ExtraFaceData faceData) {
        this((Vector3fc) null, (Vector3fc) null, (Map<Direction, CuboidFace>) null, (CuboidRotation) null, (boolean) false, (int) 0, (net.neoforged.neoforge.client.model.ExtraFaceData) null);
    }

    public CuboidModelElement(Vector3fc from, Vector3fc to, Map<Direction, CuboidFace> faces) {
        this((Vector3fc) null, (Vector3fc) null, (Map<Direction, CuboidFace>) null, (CuboidRotation) null, (boolean) false, (int) 0, (net.neoforged.neoforge.client.model.ExtraFaceData) null);
    }

    public static class Deserializer implements JsonDeserializer<CuboidModelElement> {

        public CuboidModelElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/CuboidModelElement$Deserializer.deserialize:(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/resources/model/cuboid/CuboidModelElement;");
        }

        private CuboidRotation getRotation(JsonObject object) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/CuboidModelElement$Deserializer.getRotation:(Lcom/google/gson/JsonObject;)Lnet/minecraft/client/resources/model/cuboid/CuboidRotation;");
        }

        public Deserializer() {
        }
    }
}
