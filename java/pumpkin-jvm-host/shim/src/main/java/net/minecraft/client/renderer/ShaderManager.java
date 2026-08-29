package net.minecraft.client.renderer;

import com.mojang.blaze3d.shaders.ShaderType;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import dev.pumpkin.shim.Unimplemented;

public class ShaderManager extends SimplePreparableReloadListener<ShaderManager.Configs> implements AutoCloseable {

    public ShaderManager(TextureManager textureManager, Consumer<Exception> recoveryHandler) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager.<init>:(Lnet/minecraft/client/renderer/texture/TextureManager;Ljava/util/function/Consumer;)V");
    }

    protected ShaderManager.Configs prepare(ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager.prepare:(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/client/renderer/ShaderManager$Configs;");
    }

    protected void apply(ShaderManager.Configs preparations, ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager.apply:(Lnet/minecraft/client/renderer/ShaderManager$Configs;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager.getName:()Ljava/lang/String;");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager.close:()V");
    }

    private class CompilationCache implements AutoCloseable {

        private CompilationCache(ShaderManager.Configs configs) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager$CompilationCache.<init>:(Lnet/minecraft/client/renderer/ShaderManager$Configs;)V");
        }

        public void close() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager$CompilationCache.close:()V");
        }

        protected CompilationCache() {
        }
    }

    public static class CompilationException extends Exception {

        public CompilationException(String message) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager$CompilationException.<init>:(Ljava/lang/String;)V");
        }

        protected CompilationException() {
        }
    }

    public record Configs(Map<ShaderManager.ShaderSourceKey, String> shaderSources, Map<Identifier, PostChainConfig> postChains) {
    }

    private record ShaderSourceKey(Identifier id, ShaderType type) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderManager$ShaderSourceKey.toString:()Ljava/lang/String;");
        }
    }

    protected ShaderManager() {
    }
}
