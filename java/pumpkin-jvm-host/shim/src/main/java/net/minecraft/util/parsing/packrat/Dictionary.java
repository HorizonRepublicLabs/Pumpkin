package net.minecraft.util.parsing.packrat;

import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class Dictionary<S> {

    private static class Entry<S, T> implements NamedRule<S, T>, Supplier<String> {

        private Entry(Atom<T> name) {
        }

        public Atom<T> name() {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Dictionary$Entry.name:()Lnet/minecraft/util/parsing/packrat/Atom;");
        }

        public Rule<S, T> value() {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Dictionary$Entry.value:()Lnet/minecraft/util/parsing/packrat/Rule;");
        }

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Dictionary$Entry.get:()Ljava/lang/String;");
        }

        protected Entry() {
        }
    }

    private record Reference<S, T>(Dictionary.Entry<S, T> ruleToParse, Atom<T> nameToStore) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Dictionary$Reference.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    public Dictionary() {
    }
}
