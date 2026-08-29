package net.minecraft.util.parsing.packrat;

import dev.pumpkin.shim.Unimplemented;

public interface Rule<S, T> {

    T parse(ParseState<S> state);

    interface RuleAction<S, T> {

        T run(ParseState<S> state);
    }

    interface SimpleRuleAction<S, T> extends Rule.RuleAction<S, T> {

        T run(Scope ruleScope);

        default T run(ParseState<S> state) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Rule$SimpleRuleAction.run:(Lnet/minecraft/util/parsing/packrat/ParseState;)Ljava/lang/Object;");
        }
    }

    record WrappedTerm<S, T>(Rule.RuleAction<S, T> action, Term<S> child) implements Rule<S, T> {

        public T parse(ParseState<S> state) {
            throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Rule$WrappedTerm.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;)Ljava/lang/Object;");
        }
    }
}
