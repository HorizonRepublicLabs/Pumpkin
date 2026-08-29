package net.neoforged.neoforge.model.data;

import java.util.function.Predicate;
import dev.pumpkin.shim.Unimplemented;

public class ModelProperty<T> implements Predicate<T> {

    public ModelProperty() {
    }

    public ModelProperty(Predicate<T> predicate) {
    }

    public boolean test(T value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/model/data/ModelProperty.test:(Ljava/lang/Object;)Z");
    }
}
