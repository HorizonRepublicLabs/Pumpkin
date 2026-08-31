package net.minecraft.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public class ItemStackRenderState {

    public ItemStackRenderState.LayerRenderState newLayer() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.newLayer:()Lnet/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState;");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.clear:()V");
    }

    public void setAnimated() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.setAnimated:()V");
    }

    public void appendModelIdentityElement(Object element) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.appendModelIdentityElement:(Ljava/lang/Object;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.isEmpty:()Z");
    }

    public Material.Baked pickParticleMaterial(RandomSource randomSource) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.pickParticleMaterial:(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, int outlineColor) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.submit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V");
    }

    public AABB getModelBoundingBox() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState.getModelBoundingBox:()Lnet/minecraft/world/phys/AABB;");
    }

    public enum FoilType {

        NONE, STANDARD, SPECIAL
    }

    public class LayerRenderState {

        private IntList tintLayers;

        public void clear() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.clear:()V");
        }

        public List<BakedQuad> prepareQuadList() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.prepareQuadList:()Ljava/util/List;");
        }

        public void setExtents(Supplier<Vector3fc[]> extents) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.setExtents:(Ljava/util/function/Supplier;)V");
        }

        public void setLocalTransform(Matrix4fc transform) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.setLocalTransform:(Lorg/joml/Matrix4fc;)V");
        }

        public void setFoilType(ItemStackRenderState.FoilType foilType) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.setFoilType:(Lnet/minecraft/client/renderer/item/ItemStackRenderState$FoilType;)V");
        }

        public IntList tintLayers() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemStackRenderState$LayerRenderState.tintLayers:()Lit/unimi/dsi/fastutil/ints/IntList;");
        }

        public LayerRenderState() {
        }
    }

    public ItemStackRenderState() {
    }
}
