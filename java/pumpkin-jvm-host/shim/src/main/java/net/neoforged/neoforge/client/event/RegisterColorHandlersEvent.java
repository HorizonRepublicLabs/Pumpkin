package net.neoforged.neoforge.client.event;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import java.util.List;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.ColorResolver;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class RegisterColorHandlersEvent extends Event implements IModBusEvent {

    protected RegisterColorHandlersEvent() {
    }

    public static class BlockTintSources extends RegisterColorHandlersEvent {

        public BlockTintSources(BlockColors blockColors) {
        }

        public void register(List<BlockTintSource> tintSources, net.minecraft.world.level.block.Block... blocks) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterColorHandlersEvent$BlockTintSources.register:(Ljava/util/List;[Lnet/minecraft/world/level/block/Block;)V");
        }

        public BlockTintSources() {
        }
    }

    public static class ColorResolvers extends RegisterColorHandlersEvent {

        public ColorResolvers(ImmutableList.Builder<ColorResolver> builder) {
        }

        public void register(ColorResolver resolver) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterColorHandlersEvent$ColorResolvers.register:(Lnet/minecraft/world/level/ColorResolver;)V");
        }

        public ColorResolvers() {
        }
    }

    public static class ItemTintSources extends RegisterColorHandlersEvent {

        public ItemTintSources(ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ItemTintSource>> idMapper) {
        }

        public void register(Identifier location, MapCodec<? extends ItemTintSource> source) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterColorHandlersEvent$ItemTintSources.register:(Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/MapCodec;)V");
        }

        public ItemTintSources() {
        }
    }
}
