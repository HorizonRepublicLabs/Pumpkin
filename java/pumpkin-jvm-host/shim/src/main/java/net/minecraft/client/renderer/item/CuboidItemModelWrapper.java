package net.minecraft.client.renderer.item;

import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public class CuboidItemModelWrapper implements ItemModel {

    public CuboidItemModelWrapper(List<ItemTintSource> tints, QuadCollection quads, ModelRenderProperties properties, Matrix4fc transformation) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/CuboidItemModelWrapper.<init>:(Ljava/util/List;Lnet/minecraft/client/resources/model/geometry/QuadCollection;Lnet/minecraft/client/renderer/item/ModelRenderProperties;Lorg/joml/Matrix4fc;)V");
    }

    public void update(ItemStackRenderState output, ItemStack item, ItemModelResolver resolver, ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/CuboidItemModelWrapper.update:(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/ItemOwner;I)V");
    }

    public record Unbaked(Identifier model, Optional<Transformation> transformation, List<ItemTintSource> tints) implements ItemModel.Unbaked {

        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/CuboidItemModelWrapper$Unbaked.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
        }

        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc transformation) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/CuboidItemModelWrapper$Unbaked.bake:(Lnet/minecraft/client/renderer/item/ItemModel$BakingContext;Lorg/joml/Matrix4fc;)Lnet/minecraft/client/renderer/item/ItemModel;");
        }

        public MapCodec<CuboidItemModelWrapper.Unbaked> type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/CuboidItemModelWrapper$Unbaked.type:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    protected CuboidItemModelWrapper() {
    }
}
