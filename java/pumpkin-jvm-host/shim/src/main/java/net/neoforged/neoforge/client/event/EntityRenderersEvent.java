package net.neoforged.neoforge.client.event;

import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.entity.ClientMannequin;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.PlayerModelType;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityRenderersEvent extends Event implements IModBusEvent {

    protected EntityRenderersEvent() {
    }

    public static class RegisterLayerDefinitions extends EntityRenderersEvent {

        public RegisterLayerDefinitions() {
        }
    }

    public static class RegisterRenderers extends EntityRenderersEvent {

        public RegisterRenderers() {
        }

        public <T extends BlockEntity, S extends BlockEntityRenderState> void registerBlockEntityRenderer(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T, S> blockEntityRendererProvider) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/EntityRenderersEvent$RegisterRenderers.registerBlockEntityRenderer:(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/client/renderer/blockentity/BlockEntityRendererProvider;)V");
        }
    }

    public static class AddLayers extends EntityRenderersEvent {

        public AddLayers(Map<EntityType<?>, EntityRenderer<?, ?>> renderers, Map<PlayerModelType, AvatarRenderer<AbstractClientPlayer>> playerRenderers, Map<PlayerModelType, AvatarRenderer<ClientMannequin>> mannequinRenderers, EntityRendererProvider.Context context) {
        }

        public AddLayers() {
        }
    }

    public static class CreateSkullModels extends EntityRenderersEvent {

        public CreateSkullModels(Map<SkullBlock.Type, Function<EntityModelSet, SkullModelBase>> skullModels, Map<SkullBlock.Type, Identifier> skullTextures) {
        }

        public CreateSkullModels() {
        }
    }
}
