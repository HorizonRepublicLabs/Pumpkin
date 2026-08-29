package net.neoforged.neoforge.common;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import dev.pumpkin.shim.Unimplemented;

public class CommonHooks {

    public static float onLivingDamagePre(LivingEntity entity, DamageContainer container) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/CommonHooks.onLivingDamagePre:(Lnet/minecraft/world/entity/LivingEntity;Lnet/neoforged/neoforge/common/damagesource/DamageContainer;)F");
    }

    public static void onLivingDamagePost(LivingEntity entity, DamageContainer container) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/CommonHooks.onLivingDamagePost:(Lnet/minecraft/world/entity/LivingEntity;Lnet/neoforged/neoforge/common/damagesource/DamageContainer;)V");
    }

    public static BreakBlockEvent fireBlockBreak(Level level, GameType gameType, Player player, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/CommonHooks.fireBlockBreak:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/GameType;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/neoforged/neoforge/event/level/block/BreakBlockEvent;");
    }

    public interface BiomeCallbackFunction {

        Biome apply(final Biome.ClimateSettings climate, final BiomeSpecialEffects effects, final BiomeGenerationSettings gen, final MobSpawnSettings spawns);
    }

    public CommonHooks() {
    }
}
