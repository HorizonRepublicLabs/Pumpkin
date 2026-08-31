package com.mojang.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mojang's slf4j facade, reimplemented rather than stubbed: the real class is four lines
 * over a StackWalker, ships in a separate artifact this classpath does not carry, and
 * every mod's static initializer calls it before doing anything else.
 */
public class LogUtils {
    private static final StackWalker STACK_WALKER =
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static Logger getLogger() {
        return LoggerFactory.getLogger(STACK_WALKER.getCallerClass());
    }
}
