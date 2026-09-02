package dev.pumpkin.jvmhost.log;

import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.LegacyAbstractLogger;
import org.slf4j.helpers.MessageFormatter;

/**
 * A logger that prints what a mod says.
 *
 * <p>Mods log through SLF4J, and with no provider on the classpath every one of those
 * lines went to the no-op logger -- including the warnings a mod raises when the host
 * answers something it did not expect. Those are exactly the lines worth reading, so
 * they go to stderr, where the JVM host's other output already goes and the Rust side
 * captures them.
 */
final class PumpkinLogger extends LegacyAbstractLogger {
    private static final long serialVersionUID = 1L;

    PumpkinLogger(String name) {
        this.name = name;
    }

    @Override
    protected String getFullyQualifiedCallerName() {
        return null;
    }

    @Override
    protected void handleNormalizedLoggingCall(Level level, Marker marker, String messagePattern,
            Object[] arguments, Throwable throwable) {
        String message = MessageFormatter.basicArrayFormat(messagePattern, arguments);
        System.err.println("[mod/" + level + "] " + name + ": " + message);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }

    // Debug and trace stay off: mods are chatty at those levels and the host's own
    // output would drown in them. Everything a mod considers noteworthy is kept.
    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }
}
