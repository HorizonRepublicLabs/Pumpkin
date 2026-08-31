package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class TerrainParticle extends SingleQuadParticle {

    public TerrainParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, BlockState blockState) {
    }

    public TerrainParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, BlockState blockState, BlockPos pos) {
    }

    public SingleQuadParticle.Layer getLayer() {
        throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle.getLayer:()Lnet/minecraft/client/particle/SingleQuadParticle$Layer;");
    }

    protected float getU0() {
        throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle.getU0:()F");
    }

    protected float getU1() {
        throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle.getU1:()F");
    }

    protected float getV0() {
        throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle.getV0:()F");
    }

    protected float getV1() {
        throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle.getV1:()F");
    }

    public TerrainParticle updateSprite(BlockState state, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle.updateSprite:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/client/particle/TerrainParticle;");
    }

    public static class CrumblingProvider implements ParticleProvider<BlockParticleOption> {

        public Particle createParticle(BlockParticleOption options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle$CrumblingProvider.createParticle:(Lnet/minecraft/core/particles/BlockParticleOption;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;");
        }

        public CrumblingProvider() {
        }
    }

    public static class DustPillarProvider implements ParticleProvider<BlockParticleOption> {

        public Particle createParticle(BlockParticleOption options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle$DustPillarProvider.createParticle:(Lnet/minecraft/core/particles/BlockParticleOption;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;");
        }

        public DustPillarProvider() {
        }
    }

    public static class Provider implements ParticleProvider<BlockParticleOption> {

        public Particle createParticle(BlockParticleOption options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/TerrainParticle$Provider.createParticle:(Lnet/minecraft/core/particles/BlockParticleOption;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;");
        }

        public Provider() {
        }
    }

    public TerrainParticle() {
    }
}
