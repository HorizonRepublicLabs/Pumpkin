package net.minecraft.client;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import net.minecraft.CrashReport;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.thread.ReentrantBlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.client.extensions.IMinecraftExtension;
import dev.pumpkin.shim.Unimplemented;

public class Minecraft extends ReentrantBlockableEventLoop<Runnable> implements WindowEventHandler, IMinecraftExtension {

    public final Font font = null;

    public final GameRenderer gameRenderer = null;

    public final Gui gui = null;

    public final Options options = null;

    public final KeyboardHandler keyboardHandler = null;

    public MultiPlayerGameMode gameMode;

    public ClientLevel level;

    public LocalPlayer player;

    public HitResult hitResult;

    public Minecraft(GameConfig gameConfig) {
    }

    public boolean hasShiftDown() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.hasShiftDown:()Z");
    }

    public void run() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.run:()V");
    }

    public void delayCrash(CrashReport crash) {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.delayCrash:(Lnet/minecraft/CrashReport;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.close:()V");
    }

    public void framebufferSizeChanged() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.framebufferSizeChanged:()V");
    }

    public void resizeGui() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.resizeGui:()V");
    }

    public void cursorEntered() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.cursorEntered:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.tick:()V");
    }

    public ClientPacketListener getConnection() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getConnection:()Lnet/minecraft/client/multiplayer/ClientPacketListener;");
    }

    public static Minecraft getInstance() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getInstance:()Lnet/minecraft/client/Minecraft;");
    }

    public TextureManager getTextureManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getTextureManager:()Lnet/minecraft/client/renderer/texture/TextureManager;");
    }

    public ResourceManager getResourceManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getResourceManager:()Lnet/minecraft/server/packs/resources/ResourceManager;");
    }

    public boolean isPaused() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.isPaused:()Z");
    }

    public SoundManager getSoundManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getSoundManager:()Lnet/minecraft/client/sounds/SoundManager;");
    }

    public boolean shouldEntityAppearGlowing(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.shouldEntityAppearGlowing:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public Thread getRunningThread() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getRunningThread:()Ljava/lang/Thread;");
    }

    public Runnable wrapRunnable(Runnable runnable) {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.wrapRunnable:(Ljava/lang/Runnable;)Ljava/lang/Runnable;");
    }

    protected boolean shouldRun(Runnable task) {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.shouldRun:(Ljava/lang/Runnable;)Z");
    }

    public EntityRenderDispatcher getEntityRenderDispatcher() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getEntityRenderDispatcher:()Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;");
    }

    public BlockEntityRenderDispatcher getBlockEntityRenderDispatcher() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getBlockEntityRenderDispatcher:()Lnet/minecraft/client/renderer/blockentity/BlockEntityRenderDispatcher;");
    }

    public DeltaTracker getDeltaTracker() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getDeltaTracker:()Lnet/minecraft/client/DeltaTracker;");
    }

    public ModelManager getModelManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getModelManager:()Lnet/minecraft/client/resources/model/ModelManager;");
    }

    public AtlasManager getAtlasManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getAtlasManager:()Lnet/minecraft/client/resources/model/sprite/AtlasManager;");
    }

    public Window getWindow() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getWindow:()Lcom/mojang/blaze3d/platform/Window;");
    }

    public EntityModelSet getEntityModels() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getEntityModels:()Lnet/minecraft/client/model/geom/EntityModelSet;");
    }

    public PlayerSkinRenderCache playerSkinRenderCache() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.playerSkinRenderCache:()Lnet/minecraft/client/renderer/PlayerSkinRenderCache;");
    }

    public BlockModelResolver getBlockModelResolver() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getBlockModelResolver:()Lnet/minecraft/client/renderer/block/BlockModelResolver;");
    }

    public ItemModelResolver getItemModelResolver() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getItemModelResolver:()Lnet/minecraft/client/renderer/item/ItemModelResolver;");
    }

    public Minecraft() {
    }
}
