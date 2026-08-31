package net.neoforged.neoforge.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class RenderArmEvent<AvatarlikeEntity extends Avatar & ClientAvatarEntity> extends Event implements ICancellableEvent {

    public RenderArmEvent(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, Identifier skinTexture, boolean hasSleeve, AvatarlikeEntity avatar, HumanoidArm arm, ModelPart armPart) {
    }

    public HumanoidArm getArm() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderArmEvent.getArm:()Lnet/minecraft/world/entity/HumanoidArm;");
    }

    public PoseStack getPoseStack() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderArmEvent.getPoseStack:()Lcom/mojang/blaze3d/vertex/PoseStack;");
    }

    public SubmitNodeCollector getSubmitNodeCollector() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderArmEvent.getSubmitNodeCollector:()Lnet/minecraft/client/renderer/SubmitNodeCollector;");
    }

    public int getLightCoords() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderArmEvent.getLightCoords:()I");
    }

    public ModelPart getArmPart() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderArmEvent.getArmPart:()Lnet/minecraft/client/model/geom/ModelPart;");
    }

    public AvatarlikeEntity getAvatar() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RenderArmEvent.getAvatar:()Lnet/minecraft/world/entity/Avatar;");
    }

    public RenderArmEvent() {
    }
}
