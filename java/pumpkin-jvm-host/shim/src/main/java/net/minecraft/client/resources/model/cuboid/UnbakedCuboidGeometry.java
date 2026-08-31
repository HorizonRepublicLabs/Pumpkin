package net.minecraft.client.resources.model.cuboid;

import java.util.List;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.geometry.UnbakedGeometry;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.neoforged.neoforge.client.model.ExtendedUnbakedGeometry;
import dev.pumpkin.shim.Unimplemented;

public record UnbakedCuboidGeometry(List<CuboidModelElement> elements) implements UnbakedGeometry, ExtendedUnbakedGeometry {

    public QuadCollection bake(TextureSlots textures, ModelBaker modelBaker, ModelState modelState, ModelDebugName name, net.minecraft.util.context.ContextMap additionalProperties) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/UnbakedCuboidGeometry.bake:(Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/dispatch/ModelState;Lnet/minecraft/client/resources/model/ModelDebugName;Lnet/minecraft/util/context/ContextMap;)Lnet/minecraft/client/resources/model/geometry/QuadCollection;");
    }

    public static QuadCollection bake(List<CuboidModelElement> elements, TextureSlots textures, ModelBaker modelBaker, ModelState modelState, ModelDebugName name) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/cuboid/UnbakedCuboidGeometry.bake:(Ljava/util/List;Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/dispatch/ModelState;Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/resources/model/geometry/QuadCollection;");
    }
}
