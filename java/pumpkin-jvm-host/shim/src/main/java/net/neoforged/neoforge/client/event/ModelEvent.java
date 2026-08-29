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
    }

    public static class ModifyBakingResult extends ModelEvent implements IModBusEvent {

        public ModifyBakingResult(ModelBakery.BakingResult bakingResult, Function<Identifier, TextureAtlasSprite> textureGetter, ModelBakery modelBakery) {
        }

        public ModelBakery.BakingResult getBakingResult() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$ModifyBakingResult.getBakingResult:()Lnet/minecraft/client/resources/model/ModelBakery$BakingResult;");
        }

        public ModifyBakingResult() {
        }
    }

    public static class BakingCompleted extends ModelEvent implements IModBusEvent {

        public BakingCompleted(ModelManager modelManager, ModelBakery.BakingResult bakingResult, ModelBakery modelBakery) {
        }

        public BakingCompleted() {
        }
    }

    public static class RegisterStandalone extends ModelEvent implements IModBusEvent {

        public RegisterStandalone(Map<StandaloneModelKey<?>, UnbakedStandaloneModel<?>> modelMap) {
        }

        public <T> void register(StandaloneModelKey<T> modelKey, UnbakedStandaloneModel<T> baker) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$RegisterStandalone.register:(Lnet/neoforged/neoforge/client/model/standalone/StandaloneModelKey;Lnet/neoforged/neoforge/client/model/standalone/UnbakedStandaloneModel;)V");
        }

        public RegisterStandalone() {
        }
    }

    public static class RegisterLoaders extends ModelEvent implements IModBusEvent {

        public RegisterLoaders(Map<Identifier, UnbakedModelLoader<?>> loaders) {
        }

        public void register(Identifier key, UnbakedModelLoader<?> loader) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ModelEvent$RegisterLoaders.register:(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/model/UnbakedModelLoader;)V");
        }

        public RegisterLoaders() {
        }
    }
}
