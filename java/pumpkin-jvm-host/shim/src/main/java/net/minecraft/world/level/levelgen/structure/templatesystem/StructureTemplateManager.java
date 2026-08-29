package net.minecraft.world.level.levelgen.structure.templatesystem;

import com.mojang.datafixers.DataFixer;
import java.util.Optional;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.LevelStorageSource;
import dev.pumpkin.shim.Unimplemented;

public class StructureTemplateManager {

    public StructureTemplateManager(ResourceManager resourceManager, LevelStorageSource.LevelStorageAccess storage, DataFixer fixerUpper, HolderGetter<Block> blockLookup) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager.<init>:(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/core/HolderGetter;)V");
    }

    public Optional<StructureTemplate> get(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager.get:(Lnet/minecraft/resources/Identifier;)Ljava/util/Optional;");
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager.onResourceManagerReload:(Lnet/minecraft/server/packs/resources/ResourceManager;)V");
    }

    public void remove(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager.remove:(Lnet/minecraft/resources/Identifier;)V");
    }

    protected StructureTemplateManager() {
    }
}
