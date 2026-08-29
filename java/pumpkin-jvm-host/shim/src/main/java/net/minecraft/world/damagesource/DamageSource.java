package net.minecraft.world.damagesource;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class DamageSource {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSource.toString:()Ljava/lang/String;");
    }

    public DamageSource(Holder<DamageType> type, Entity directEntity, Entity causingEntity, Vec3 damageSourcePosition) {
    }

    public DamageSource(Holder<DamageType> type, Entity directEntity, Entity causingEntity) {
    }

    public DamageSource(Holder<DamageType> type, Vec3 damageSourcePosition) {
    }

    public DamageSource(Holder<DamageType> type, Entity causingEntity) {
    }

    public DamageSource(Holder<DamageType> type) {
    }

    public Entity getEntity() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSource.getEntity:()Lnet/minecraft/world/entity/Entity;");
    }

    public boolean is(TagKey<DamageType> tag) {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSource.is:(Lnet/minecraft/tags/TagKey;)Z");
    }

    public boolean is(ResourceKey<DamageType> typeKey) {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSource.is:(Lnet/minecraft/resources/ResourceKey;)Z");
    }

    public DamageType type() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSource.type:()Lnet/minecraft/world/damagesource/DamageType;");
    }

    public Holder<DamageType> typeHolder() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSource.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public DamageSource() {
    }
}
