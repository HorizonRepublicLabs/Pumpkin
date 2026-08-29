package net.minecraft.util.parsing.packrat;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public interface ErrorCollector<S> {

    void store(int cursor, SuggestionSupplier<S> suggestions, Object reason);

    default void store(int cursor, Object reason) {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/ErrorCollector.store:(ILjava/lang/Object;)V");
    }

    void finish(int finalCursor);

    class LongestOnly<S> implements ErrorCollector<S> {

        public void finish(int finalCursor) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/ErrorCollector$LongestOnly.finish:(I)V");
        }

        public void store(int cursor, SuggestionSupplier<S> suggestions, Object reason) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/ErrorCollector$LongestOnly.store:(ILnet/minecraft/util/parsing/packrat/SuggestionSupplier;Ljava/lang/Object;)V");
        }

        public List<ErrorEntry<S>> entries() {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/ErrorCollector$LongestOnly.entries:()Ljava/util/List;");
        }

        private static class MutableErrorEntry<S> {

            protected MutableErrorEntry() {
            }
        }

        protected LongestOnly() {
        }
    }

    class Nop<S> implements ErrorCollector<S> {

        public void store(int cursor, SuggestionSupplier<S> suggestions, Object reason) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/ErrorCollector$Nop.store:(ILnet/minecraft/util/parsing/packrat/SuggestionSupplier;Ljava/lang/Object;)V");
        }

        public void finish(int finalCursor) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/ErrorCollector$Nop.finish:(I)V");
        }

        protected Nop() {
        }
    }
}
