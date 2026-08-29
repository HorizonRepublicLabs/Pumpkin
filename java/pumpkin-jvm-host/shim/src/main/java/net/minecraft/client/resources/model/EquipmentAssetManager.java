package net.minecraft.client.resources.model;

import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.equipment.EquipmentAsset;
import dev.pumpkin.shim.Unimplemented;

public class EquipmentAssetManager extends SimpleJsonResourceReloadListener<EquipmentClientInfo> {

    public EquipmentAssetManager() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/EquipmentAssetManager.<init>:()V");
    }

    protected void apply(Map<Identifier, EquipmentClientInfo> preparations, ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/EquipmentAssetManager.apply:(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V");
    }

    public EquipmentClientInfo get(ResourceKey<EquipmentAsset> id) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/EquipmentAssetManager.get:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/client/resources/model/EquipmentClientInfo;");
    }
}
