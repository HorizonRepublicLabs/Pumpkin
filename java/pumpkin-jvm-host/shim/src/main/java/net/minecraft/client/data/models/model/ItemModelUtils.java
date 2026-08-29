package net.minecraft.client.data.models.model;

import com.mojang.math.Transformation;
import java.util.List;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ItemModelUtils {

    public static ItemModel.Unbaked plainModel(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ItemModelUtils.plainModel:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;");
    }

    public static ItemModel.Unbaked plainModel(Identifier id, Transformation transformation) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ItemModelUtils.plainModel:(Lnet/minecraft/resources/Identifier;Lcom/mojang/math/Transformation;)Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;");
    }

    public static ItemModel.Unbaked tintedModel(Identifier id, ItemTintSource... tints) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ItemModelUtils.tintedModel:(Lnet/minecraft/resources/Identifier;[Lnet/minecraft/client/color/item/ItemTintSource;)Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;");
    }

    public static <T> ItemModel.Unbaked select(SelectItemModelProperty<T> property, SelectItemModel.SwitchCase<T>... cases) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ItemModelUtils.select:(Lnet/minecraft/client/renderer/item/properties/select/SelectItemModelProperty;[Lnet/minecraft/client/renderer/item/SelectItemModel$SwitchCase;)Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;");
    }

    public static <T> ItemModel.Unbaked select(SelectItemModelProperty<T> property, List<SelectItemModel.SwitchCase<T>> cases) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/ItemModelUtils.select:(Lnet/minecraft/client/renderer/item/properties/select/SelectItemModelProperty;Ljava/util/List;)Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;");
    }

    public ItemModelUtils() {
    }
}
