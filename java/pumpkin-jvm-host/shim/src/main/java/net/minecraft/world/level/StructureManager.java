package net.minecraft.world.level;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import dev.pumpkin.shim.Unimplemented;

public class StructureManager {

    public StructureManager(LevelAccessor level, WorldOptions worldOptions, StructureCheck structureCheck) {
        throw Unimplemented.forMember("net/minecraft/world/level/StructureManager.<init>:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/level/levelgen/WorldOptions;Lnet/minecraft/world/level/levelgen/structure/StructureCheck;)V");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/world/level/StructureManager.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    public StructureManager() {
    }
}
