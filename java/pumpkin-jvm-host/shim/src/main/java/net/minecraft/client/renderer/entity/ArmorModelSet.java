package net.minecraft.client.renderer.entity;

import java.util.function.Function;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import dev.pumpkin.shim.Unimplemented;

public record ArmorModelSet<T>(T head, T chest, T legs, T feet) {

    public T get(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/ArmorModelSet.get:(Lnet/minecraft/world/entity/EquipmentSlot;)Ljava/lang/Object;");
    }

    public <U> ArmorModelSet<U> map(Function<? super T, ? extends U> mapper) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/ArmorModelSet.map:(Ljava/util/function/Function;)Lnet/minecraft/client/renderer/entity/ArmorModelSet;");
    }

    public static <M extends HumanoidModel<?>> ArmorModelSet<M> bake(ArmorModelSet<ModelLayerLocation> locations, EntityModelSet modelSet, Function<ModelPart, M> factory) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/entity/ArmorModelSet.bake:(Lnet/minecraft/client/renderer/entity/ArmorModelSet;Lnet/minecraft/client/model/geom/EntityModelSet;Ljava/util/function/Function;)Lnet/minecraft/client/renderer/entity/ArmorModelSet;");
    }
}
