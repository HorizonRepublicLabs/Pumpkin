package net.minecraft.client.resources.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public record EquipmentClientInfo(Map<EquipmentClientInfo.LayerType, List<EquipmentClientInfo.Layer>> layers) {

    public static class Builder {

        protected Builder() {
        }

        public EquipmentClientInfo build() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/EquipmentClientInfo$Builder.build:()Lnet/minecraft/client/resources/model/EquipmentClientInfo;");
        }
    }

    public record Dyeable(Optional<Integer> colorWhenUndyed) {
    }

    public record Layer(Identifier textureId, Optional<EquipmentClientInfo.Dyeable> dyeable, boolean usePlayerTexture) {

        public Layer(Identifier textureId) {
            this((Identifier) null, (Optional<EquipmentClientInfo.Dyeable>) null, (boolean) false);
        }

        public Identifier getTextureLocation(EquipmentClientInfo.LayerType type) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/EquipmentClientInfo$Layer.getTextureLocation:(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;)Lnet/minecraft/resources/Identifier;");
        }
    }

    public enum LayerType implements StringRepresentable, IExtensibleEnum {

        HUMANOID,
        HUMANOID_LEGGINGS,
        HUMANOID_BABY,
        WINGS,
        WOLF_BODY,
        HORSE_BODY,
        LLAMA_BODY,
        PIG_SADDLE,
        STRIDER_SADDLE,
        CAMEL_SADDLE,
        CAMEL_HUSK_SADDLE,
        HORSE_SADDLE,
        DONKEY_SADDLE,
        MULE_SADDLE,
        ZOMBIE_HORSE_SADDLE,
        SKELETON_HORSE_SADDLE,
        HAPPY_GHAST_BODY,
        NAUTILUS_SADDLE,
        NAUTILUS_BODY;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/EquipmentClientInfo$LayerType.getSerializedName:()Ljava/lang/String;");
        }
    }
}
