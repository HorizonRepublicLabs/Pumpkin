package net.minecraft.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import java.util.function.Consumer;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class ShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {

    private final SpriteGetter sprites = Stubs.of(SpriteGetter.class, "net/minecraft/client/resources/model/sprite/SpriteGetter");

    private final ShieldModel model = null;

    public ShieldSpecialRenderer(SpriteGetter sprites, ShieldModel model) {
    }

    public DataComponentMap extractArgument(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/special/ShieldSpecialRenderer.extractArgument:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/core/component/DataComponentMap;");
    }

    public void submit(DataComponentMap components, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/special/ShieldSpecialRenderer.submit:(Lnet/minecraft/core/component/DataComponentMap;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V");
    }

    public void getExtents(Consumer<Vector3fc> output) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/special/ShieldSpecialRenderer.getExtents:(Ljava/util/function/Consumer;)V");
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<DataComponentMap> {

        public MapCodec<ShieldSpecialRenderer.Unbaked> type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/special/ShieldSpecialRenderer$Unbaked.type:()Lcom/mojang/serialization/MapCodec;");
        }

        public ShieldSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/special/ShieldSpecialRenderer$Unbaked.bake:(Lnet/minecraft/client/renderer/special/SpecialModelRenderer$BakingContext;)Lnet/minecraft/client/renderer/special/ShieldSpecialRenderer;");
        }
    }

    public ShieldSpecialRenderer() {
    }
}
