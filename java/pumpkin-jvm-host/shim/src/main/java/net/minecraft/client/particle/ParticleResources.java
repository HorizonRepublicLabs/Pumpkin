package net.minecraft.client.particle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class ParticleResources implements PreparableReloadListener {

    public ParticleResources() {
    }

    public <T extends ParticleOptions> void register(ParticleType<T> type, ParticleProvider<T> provider) {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleResources.register:(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/client/particle/ParticleProvider;)V");
    }

    public <T extends ParticleOptions> void register(ParticleType<T> type, ParticleResources.SpriteParticleRegistration<T> provider) {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleResources.register:(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/client/particle/ParticleResources$SpriteParticleRegistration;)V");
    }

    public CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/client/particle/ParticleResources.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    private static class MutableSpriteSet implements SpriteSet {

        public TextureAtlasSprite get(int index, int max) {
            throw Unimplemented.forMember("net/minecraft/client/particle/ParticleResources$MutableSpriteSet.get:(II)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;");
        }

        public TextureAtlasSprite get(RandomSource random) {
            throw Unimplemented.forMember("net/minecraft/client/particle/ParticleResources$MutableSpriteSet.get:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;");
        }

        public TextureAtlasSprite first() {
            throw Unimplemented.forMember("net/minecraft/client/particle/ParticleResources$MutableSpriteSet.first:()Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;");
        }

        protected MutableSpriteSet() {
        }
    }

    public interface SpriteParticleRegistration<T extends ParticleOptions> {

        ParticleProvider<T> create(SpriteSet spriteSet);
    }
}
