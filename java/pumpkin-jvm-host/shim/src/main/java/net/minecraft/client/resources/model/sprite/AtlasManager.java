package net.minecraft.client.resources.model.sprite;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import dev.pumpkin.shim.Unimplemented;

public class AtlasManager implements AutoCloseable, PreparableReloadListener, SpriteGetter {

    public AtlasManager(TextureManager textureManager, int maxMipmapLevels) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.<init>:(Lnet/minecraft/client/renderer/texture/TextureManager;I)V");
    }

    public TextureAtlas getAtlasOrThrow(Identifier atlasId) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.getAtlasOrThrow:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/texture/TextureAtlas;");
    }

    public void forEach(BiConsumer<Identifier, TextureAtlas> output) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.forEach:(Ljava/util/function/BiConsumer;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.close:()V");
    }

    public TextureAtlasSprite get(SpriteId sprite) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.get:(Lnet/minecraft/client/resources/model/sprite/SpriteId;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;");
    }

    public void prepareSharedState(PreparableReloadListener.SharedState currentReload) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.prepareSharedState:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;)V");
    }

    public CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    public record AtlasConfig(Identifier textureId, Identifier definitionLocation, boolean createMipmaps, Set<MetadataSectionType<?>> additionalMetadata) {

        public AtlasConfig(Identifier textureId, Identifier definitionLocation, boolean createMipmaps) {
            this((Identifier) null, (Identifier) null, (boolean) false, (Set<MetadataSectionType<?>>) null);
            throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager$AtlasConfig.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;Z)V");
        }
    }

    private record AtlasEntry(TextureAtlas atlas, AtlasManager.AtlasConfig config) implements AutoCloseable {

        public void close() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager$AtlasEntry.close:()V");
        }
    }

    private record PendingStitch(AtlasManager.AtlasEntry entry, CompletableFuture<SpriteLoader.Preparations> preparations) {
    }

    public static class PendingStitchResults {

        private PendingStitchResults(List<AtlasManager.PendingStitch> pendingStitches, Map<Identifier, CompletableFuture<SpriteLoader.Preparations>> stitchFuturesById, CompletableFuture<?> allReadyToUpload) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager$PendingStitchResults.<init>:(Ljava/util/List;Ljava/util/Map;Ljava/util/concurrent/CompletableFuture;)V");
        }

        public CompletableFuture<SpriteLoader.Preparations> get(Identifier atlasId) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/AtlasManager$PendingStitchResults.get:(Lnet/minecraft/resources/Identifier;)Ljava/util/concurrent/CompletableFuture;");
        }

        public PendingStitchResults() {
        }
    }

    public AtlasManager() {
    }
}
