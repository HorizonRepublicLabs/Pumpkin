package net.minecraft.client.renderer.item;

import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public class SelectItemModel<T> implements ItemModel {

    public SelectItemModel(SelectItemModelProperty<T> property, SelectItemModel.ModelSelector<T> models) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel.<init>:(Lnet/minecraft/client/renderer/item/properties/select/SelectItemModelProperty;Lnet/minecraft/client/renderer/item/SelectItemModel$ModelSelector;)V");
    }

    public void update(ItemStackRenderState output, ItemStack item, ItemModelResolver resolver, ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel.update:(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/ItemOwner;I)V");
    }

    public interface ModelSelector<T> {

        ItemModel get(T value, ClientLevel context);
    }

    public record SwitchCase<T>(List<T> values, ItemModel.Unbaked model) {
    }

    public record Unbaked(Optional<Transformation> transformation, SelectItemModel.UnbakedSwitch<?, ?> unbakedSwitch, Optional<ItemModel.Unbaked> fallback) implements ItemModel.Unbaked {

        public MapCodec<SelectItemModel.Unbaked> type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel$Unbaked.type:()Lcom/mojang/serialization/MapCodec;");
        }

        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc transformation) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel$Unbaked.bake:(Lnet/minecraft/client/renderer/item/ItemModel$BakingContext;Lorg/joml/Matrix4fc;)Lnet/minecraft/client/renderer/item/ItemModel;");
        }

        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel$Unbaked.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
        }
    }

    public record UnbakedSwitch<P extends SelectItemModelProperty<T>, T>(P property, List<SelectItemModel.SwitchCase<T>> cases) {

        public ItemModel bake(ItemModel.BakingContext context, Matrix4fc transformation, ItemModel fallback) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel$UnbakedSwitch.bake:(Lnet/minecraft/client/renderer/item/ItemModel$BakingContext;Lorg/joml/Matrix4fc;Lnet/minecraft/client/renderer/item/ItemModel;)Lnet/minecraft/client/renderer/item/ItemModel;");
        }

        public void resolveDependencies(ResolvableModel.Resolver resolver) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/SelectItemModel$UnbakedSwitch.resolveDependencies:(Lnet/minecraft/client/resources/model/ResolvableModel$Resolver;)V");
        }
    }

    protected SelectItemModel() {
    }
}
