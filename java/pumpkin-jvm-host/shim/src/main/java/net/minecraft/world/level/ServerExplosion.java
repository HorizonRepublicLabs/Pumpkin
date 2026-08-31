package net.minecraft.world.level;

import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ServerExplosion implements Explosion {

    private final boolean fire = false;

    private final ServerLevel level = null;

    private final Vec3 center = null;

    private final float radius = 0.0F;

    private final ExplosionDamageCalculator damageCalculator = null;

    public ServerExplosion(ServerLevel level, Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, Vec3 center, float radius, boolean fire, Explosion.BlockInteraction blockInteraction) {
    }

    public float radius() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.radius:()F");
    }

    public Vec3 center() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.center:()Lnet/minecraft/world/phys/Vec3;");
    }

    private List<BlockPos> calculateExplodedPositions() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.calculateExplodedPositions:()Ljava/util/List;");
    }

    private void hurtEntities() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.hurtEntities:()V");
    }

    private void hurtEntities(List<BlockPos> blocks) {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.hurtEntities:(Ljava/util/List;)V");
    }

    private void interactWithBlocks(List<BlockPos> targetBlocks) {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.interactWithBlocks:(Ljava/util/List;)V");
    }

    private void createFire(List<BlockPos> targetBlocks) {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.createFire:(Ljava/util/List;)V");
    }

    public int explode() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.explode:()I");
    }

    private boolean interactsWithBlocks() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.interactsWithBlocks:()Z");
    }

    public Map<Player, Vec3> getHitPlayers() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.getHitPlayers:()Ljava/util/Map;");
    }

    public ServerLevel level() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.level:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public LivingEntity getIndirectSourceEntity() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.getIndirectSourceEntity:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public Entity getDirectSourceEntity() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.getDirectSourceEntity:()Lnet/minecraft/world/entity/Entity;");
    }

    public Explosion.BlockInteraction getBlockInteraction() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.getBlockInteraction:()Lnet/minecraft/world/level/Explosion$BlockInteraction;");
    }

    public boolean canTriggerBlocks() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.canTriggerBlocks:()Z");
    }

    public boolean shouldAffectBlocklikeEntities() {
        throw Unimplemented.forMember("net/minecraft/world/level/ServerExplosion.shouldAffectBlocklikeEntities:()Z");
    }

    private static class StackCollector {

        private StackCollector(BlockPos pos, ItemStack stack) {
        }

        protected StackCollector() {
        }
    }

    public ServerExplosion() {
    }
}
