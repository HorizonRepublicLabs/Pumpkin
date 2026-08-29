package net.minecraft.world.entity.player;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class StackedContents<T> {

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedContents.clear:()V");
    }

    public interface IngredientInfo<T> {

        boolean acceptsItem(T item);
    }

    public interface Output<T> {

        void accept(T item);
    }

    private class RecipePicker {

        public RecipePicker(List<? extends StackedContents.IngredientInfo<T>> ingredients) {
            throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedContents$RecipePicker.<init>:(Ljava/util/List;)V");
        }

        protected RecipePicker() {
        }
    }

    protected StackedContents() {
    }
}
