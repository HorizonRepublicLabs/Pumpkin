package net.minecraft.client;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import net.minecraft.CrashReport;
import net.minecraft.client.gui.Font;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PlayerSkinRenderCache;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.util.thread.ReentrantBlockableEventLoop;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.client.extensions.IMinecraftExtension;
import dev.pumpkin.shim.Unimplemented;

public class Minecraft extends ReentrantBlockableEventLoop<Runnable> implements WindowEventHandler, IMinecraftExtension {

    public final Font font = null;

    public final GameRenderer gameRenderer = null;

    public final Options options = null;

    public ClientLevel level;

    public LocalPlayer player;

    public HitResult hitResult;

    public Minecraft(GameConfig gameConfig) {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.<init>:(Lnet/minecraft/client/main/GameConfig;)V");
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

    public SoundManager getSoundManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getSoundManager:()Lnet/minecraft/client/sounds/SoundManager;");
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

    public AtlasManager getAtlasManager() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getAtlasManager:()Lnet/minecraft/client/resources/model/sprite/AtlasManager;");
    }

    public Window getWindow() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.getWindow:()Lcom/mojang/blaze3d/platform/Window;");
    }

    public PlayerSkinRenderCache playerSkinRenderCache() {
        throw Unimplemented.forMember("net/minecraft/client/Minecraft.playerSkinRenderCache:()Lnet/minecraft/client/renderer/PlayerSkinRenderCache;");
    }

    protected Minecraft() {
    }
}
