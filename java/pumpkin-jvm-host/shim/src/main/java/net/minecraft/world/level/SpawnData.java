package net.minecraft.world.level;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.entity.EquipmentTable;
import dev.pumpkin.shim.Unimplemented;

public record SpawnData(CompoundTag entityToSpawn, Optional<SpawnData.CustomSpawnRules> customSpawnRules, Optional<EquipmentTable> equipment) {

    public static final Codec<SpawnData> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.world.level.SpawnData.CODEC");

    public SpawnData() {
        this((CompoundTag) null, (Optional<SpawnData.CustomSpawnRules>) null, (Optional<EquipmentTable>) null);
    }

    public CompoundTag getEntityToSpawn() {
        throw Unimplemented.forMember("net/minecraft/world/level/SpawnData.getEntityToSpawn:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public record CustomSpawnRules(InclusiveRange<Integer> blockLightLimit, InclusiveRange<Integer> skyLightLimit) {
    }
}
