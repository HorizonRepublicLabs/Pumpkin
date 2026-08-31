package net.minecraft.client.model;

import java.util.Set;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import dev.pumpkin.shim.Unimplemented;

public record BabyModelTransform(boolean scaleHead, float babyYHeadOffset, float babyZHeadOffset, float babyHeadScale, float babyBodyScale, float bodyYOffset, Set<String> headParts) implements MeshTransformer {

    public BabyModelTransform(Set<String> headParts) {
        this((boolean) false, (float) 0.0F, (float) 0.0F, (float) 0.0F, (float) 0.0F, (float) 0.0F, (Set<String>) null);
    }

    public BabyModelTransform(boolean scaleHead, float babyYHeadOffset, float babyZHeadOffset, Set<String> headParts) {
        this((boolean) false, (float) 0.0F, (float) 0.0F, (float) 0.0F, (float) 0.0F, (float) 0.0F, (Set<String>) null);
    }

    public MeshDefinition apply(MeshDefinition mesh) {
        throw Unimplemented.forMember("net/minecraft/client/model/BabyModelTransform.apply:(Lnet/minecraft/client/model/geom/builders/MeshDefinition;)Lnet/minecraft/client/model/geom/builders/MeshDefinition;");
    }
}
