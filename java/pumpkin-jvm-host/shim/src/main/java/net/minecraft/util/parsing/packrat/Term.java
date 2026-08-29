package net.minecraft.util.parsing.packrat;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public interface Term<S> {

    boolean parse(ParseState<S> state, Scope scope, Control control);

    static <S> Term<S> cut() {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term.cut:()Lnet/minecraft/util/parsing/packrat/Term;");
    }

    record Alternative<S>(Term<S>[] elements) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$Alternative.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    record LookAhead<S>(Term<S> term, boolean positive) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$LookAhead.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    record Marker<S, T>(Atom<T> name, T value) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$Marker.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    record Maybe<S>(Term<S> term) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$Maybe.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    record Repeated<S, T>(NamedRule<S, T> element, Atom<List<T>> listName, int minRepetitions) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$Repeated.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    record RepeatedWithSeparator<S, T>(NamedRule<S, T> element, Atom<List<T>> listName, Term<S> separator, int minRepetitions, boolean allowTrailingSeparator) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$RepeatedWithSeparator.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }

    record Sequence<S>(Term<S>[] elements) implements Term<S> {

        public boolean parse(ParseState<S> state, Scope scope, Control control) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Term$Sequence.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;Lnet/minecraft/util/parsing/packrat/Scope;Lnet/minecraft/util/parsing/packrat/Control;)Z");
        }
    }
}
