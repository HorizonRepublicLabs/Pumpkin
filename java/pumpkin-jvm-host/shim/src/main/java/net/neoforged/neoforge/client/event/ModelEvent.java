package net.neoforged.neoforge.client.event;

import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.client.model.UnbakedModelLoader;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;
import net.neoforged.neoforge.client.model.standalone.UnbakedStandaloneModel;
import dev.pumpkin.shim.Unimplemented;

public abstract class ModelEvent extends Event {

    protected ModelEvent() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent.<init>:()V");
    }

    public static class ModifyBakingResult extends ModelEvent implements IModBusEvent {

        public ModifyBakingResult(ModelBakery.BakingResult bakingResult, Function<Identifier, TextureAtlasSprite> textureGetter, ModelBakery modelBakery) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$ModifyBakingResult.<init>:(Lnet/minecraft/client/resources/model/ModelBakery$BakingResult;Ljava/util/function/Function;Lnet/minecraft/client/resources/model/ModelBakery;)V");
        }

        public ModelBakery.BakingResult getBakingResult() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$ModifyBakingResult.getBakingResult:()Lnet/minecraft/client/resources/model/ModelBakery$BakingResult;");
        }

        protected ModifyBakingResult() {
        }
    }

    public static class BakingCompleted extends ModelEvent implements IModBusEvent {

        public BakingCompleted(ModelManager modelManager, ModelBakery.BakingResult bakingResult, ModelBakery modelBakery) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$BakingCompleted.<init>:(Lnet/minecraft/client/resources/model/ModelManager;Lnet/minecraft/client/resources/model/ModelBakery$BakingResult;Lnet/minecraft/client/resources/model/ModelBakery;)V");
        }

        protected BakingCompleted() {
        }
    }

    public static class RegisterStandalone extends ModelEvent implements IModBusEvent {

        public RegisterStandalone(Map<StandaloneModelKey<?>, UnbakedStandaloneModel<?>> modelMap) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$RegisterStandalone.<init>:(Ljava/util/Map;)V");
        }

        public <T> void register(StandaloneModelKey<T> modelKey, UnbakedStandaloneModel<T> baker) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$RegisterStandalone.register:(Lnet/neoforged/neoforge/client/model/standalone/StandaloneModelKey;Lnet/neoforged/neoforge/client/model/standalone/UnbakedStandaloneModel;)V");
        }

        protected RegisterStandalone() {
        }
    }

    public static class RegisterLoaders extends ModelEvent implements IModBusEvent {

        public RegisterLoaders(Map<Identifier, UnbakedModelLoader<?>> loaders) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$RegisterLoaders.<init>:(Ljava/util/Map;)V");
        }

        public void register(Identifier key, UnbakedModelLoader<?> loader) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$RegisterLoaders.register:(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/model/UnbakedModelLoader;)V");
        }

        protected RegisterLoaders() {
        }
    }
}
