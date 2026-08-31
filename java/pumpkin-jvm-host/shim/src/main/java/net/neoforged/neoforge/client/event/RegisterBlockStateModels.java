package net.neoforged.neoforge.client.event;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.client.model.block.CustomBlockModelDefinition;
import net.neoforged.neoforge.client.model.block.CustomUnbakedBlockStateModel;
import dev.pumpkin.shim.Unimplemented;

public class RegisterBlockStateModels extends Event implements IModBusEvent {

    public RegisterBlockStateModels(ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends CustomUnbakedBlockStateModel>> modelIdMapper, ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends CustomBlockModelDefinition>> defintionIdMapper) {
    }

    public void registerModel(Identifier location, MapCodec<? extends CustomUnbakedBlockStateModel> codec) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterBlockStateModels.registerModel:(Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/MapCodec;)V");
    }

    public RegisterBlockStateModels() {
    }
}
