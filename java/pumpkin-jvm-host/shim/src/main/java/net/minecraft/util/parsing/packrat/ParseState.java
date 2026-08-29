package net.minecraft.util.parsing.packrat;

public interface ParseState<S> {

    Scope scope();

    ErrorCollector<S> errorCollector();

    <T> T parse(NamedRule<S, T> rule);

    S input();

    int mark();

    void restore(int mark);

    Control acquireControl();

    void releaseControl();

    ParseState<S> silent();
}
