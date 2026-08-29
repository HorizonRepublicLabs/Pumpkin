package net.neoforged.neoforge.model.data;

import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public final class ModelData {

    private ModelData(Map<ModelProperty<?>, Object> properties) {
        throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelData.<init>:(Ljava/util/Map;)V");
    }

    public <T> T get(ModelProperty<T> property) {
        throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelData.get:(Lnet/neoforged/neoforge/model/data/ModelProperty;)Ljava/lang/Object;");
    }

    public static final class Builder {

        private Builder(ModelData parent) {
            throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelData$Builder.<init>:(Lnet/neoforged/neoforge/model/data/ModelData;)V");
        }

        public <T> Builder with(ModelProperty<T> property, T value) {
            throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelData$Builder.with:(Lnet/neoforged/neoforge/model/data/ModelProperty;Ljava/lang/Object;)Lnet/neoforged/neoforge/model/data/ModelData$Builder;");
        }

        public ModelData build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelData$Builder.build:()Lnet/neoforged/neoforge/model/data/ModelData;");
        }

        public Builder() {
        }
    }

    public ModelData() {
    }
}
