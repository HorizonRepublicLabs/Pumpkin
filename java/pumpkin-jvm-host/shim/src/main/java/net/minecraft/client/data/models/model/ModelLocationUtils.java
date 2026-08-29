package net.minecraft.client.data.models.model;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class ModelLocationUtils {

    public static Identifier getModelLocation(Block block, String suffix) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelLocationUtils.getModelLocation:(Lnet/minecraft/world/level/block/Block;Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }

    public static Identifier getModelLocation(Block block) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelLocationUtils.getModelLocation:(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/resources/Identifier;");
    }

    public static Identifier getModelLocation(Item item) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelLocationUtils.getModelLocation:(Lnet/minecraft/world/item/Item;)Lnet/minecraft/resources/Identifier;");
    }

    public static Identifier getModelLocation(Item item, String suffix) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ModelLocationUtils.getModelLocation:(Lnet/minecraft/world/item/Item;Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }

    public ModelLocationUtils() {
    }
}
