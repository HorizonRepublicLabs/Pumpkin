package net.minecraft.client.resources.model.sprite;

import java.util.Map;
import net.minecraft.client.resources.model.ModelDebugName;
import dev.pumpkin.shim.Unimplemented;

public class TextureSlots {

    private TextureSlots(Map<String, Material> resolvedValues) {
    }

    public record Data(Map<String, TextureSlots.SlotContents> values) {

        public static class Builder {

            public TextureSlots.Data.Builder addTexture(String slot, Material material) {
                throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/TextureSlots$Data$Builder.addTexture:(Ljava/lang/String;Lnet/minecraft/client/resources/model/sprite/Material;)Lnet/minecraft/client/resources/model/sprite/TextureSlots$Data$Builder;");
            }

            public TextureSlots.Data build() {
                throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/TextureSlots$Data$Builder.build:()Lnet/minecraft/client/resources/model/sprite/TextureSlots$Data;");
            }

            public Builder() {
            }
        }
    }

    private record Reference(String target) implements TextureSlots.SlotContents {
    }

    public static class Resolver {

        public TextureSlots.Resolver addLast(TextureSlots.Data data) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/TextureSlots$Resolver.addLast:(Lnet/minecraft/client/resources/model/sprite/TextureSlots$Data;)Lnet/minecraft/client/resources/model/sprite/TextureSlots$Resolver;");
        }

        public TextureSlots resolve(ModelDebugName debugNameProvider) {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/TextureSlots$Resolver.resolve:(Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/resources/model/sprite/TextureSlots;");
        }

        public Resolver() {
        }
    }

    public interface SlotContents {
    }

    private record Value(Material material) implements TextureSlots.SlotContents {
    }

    public TextureSlots() {
    }
}
