package net.neoforged.neoforge.model.data;

import java.util.function.Predicate;
import dev.pumpkin.shim.Unimplemented;

public class ModelProperty<T> implements Predicate<T> {

    public ModelProperty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelProperty.<init>:()V");
    }

    public ModelProperty(Predicate<T> predicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelProperty.<init>:(Ljava/util/function/Predicate;)V");
    }

    public boolean test(T value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelProperty.test:(Ljava/lang/Object;)Z");
    }
}
