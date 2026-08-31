package net.neoforged.neoforge.client.model.obj;

import java.util.Set;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextMap;
import net.neoforged.neoforge.client.model.ExtendedUnbakedGeometry;
import dev.pumpkin.shim.Unimplemented;

public class ObjGeometry implements ExtendedUnbakedGeometry {

    private ObjGeometry(Settings settings) {
    }

    public QuadCollection bake(TextureSlots textureSlots, ModelBaker baker, ModelState state, ModelDebugName debugName, ContextMap additionalProperties) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/obj/ObjGeometry.bake:(Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/dispatch/ModelState;Lnet/minecraft/client/resources/model/ModelDebugName;Lnet/minecraft/util/context/ContextMap;)Lnet/minecraft/client/resources/model/geometry/QuadCollection;");
    }

    public Set<String> getRootComponentNames() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/obj/ObjGeometry.getRootComponentNames:()Ljava/util/Set;");
    }

    public class ModelObject {

        ModelObject(String name) {
        }

        public String name() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/obj/ObjGeometry$ModelObject.name:()Ljava/lang/String;");
        }

        public ModelObject() {
        }
    }

    public class ModelGroup extends ModelObject {

        ModelGroup(String name) {
        }

        public void addQuads(QuadCollection.Builder builder, TextureSlots slots, ModelBaker baker, ModelState state, ModelDebugName debugName, ContextMap additionalProperties) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/obj/ObjGeometry$ModelGroup.addQuads:(Lnet/minecraft/client/resources/model/geometry/QuadCollection$Builder;Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/dispatch/ModelState;Lnet/minecraft/client/resources/model/ModelDebugName;Lnet/minecraft/util/context/ContextMap;)V");
        }

        protected void addNamesRecursively(Set<String> names) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/obj/ObjGeometry$ModelGroup.addNamesRecursively:(Ljava/util/Set;)V");
        }

        public ModelGroup() {
        }
    }

    private class ModelMesh {

        public ModelMesh(ObjMaterialLibrary.Material currentMat, String currentSmoothingGroup) {
        }

        protected ModelMesh() {
        }
    }

    public record Settings(Identifier modelLocation, boolean automaticCulling, boolean shadeQuads, boolean flipV, boolean emissiveAmbient, String mtlOverride) {
    }

    public ObjGeometry() {
    }
}
