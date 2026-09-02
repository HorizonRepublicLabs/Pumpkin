package dev.pumpkin.jvmhost.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/** Makes SLF4J -- and so every mod log line -- resolve to {@link PumpkinLogger}. */
public final class PumpkinSlf4jProvider implements SLF4JServiceProvider {
    private final java.util.concurrent.ConcurrentHashMap<String, PumpkinLogger> loggers =
            new java.util.concurrent.ConcurrentHashMap<>();
    private final IMarkerFactory markerFactory = new BasicMarkerFactory();
    private final MDCAdapter mdcAdapter = new BasicMDCAdapter();

    @Override
    public ILoggerFactory getLoggerFactory() {
        return name -> loggers.computeIfAbsent(name, PumpkinLogger::new);
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return markerFactory;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }

    @Override
    public String getRequestedApiVersion() {
        return "2.0.99";
    }

    @Override
    public void initialize() {
    }
}
