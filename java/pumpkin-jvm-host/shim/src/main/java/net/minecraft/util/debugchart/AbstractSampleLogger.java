package net.minecraft.util.debugchart;

import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractSampleLogger implements SampleLogger {

    protected AbstractSampleLogger(int dimensions, long[] defaults) {
    }

    public void logFullSample(long[] sample) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/AbstractSampleLogger.logFullSample:([J)V");
    }

    public void logSample(long sample) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/AbstractSampleLogger.logSample:(J)V");
    }

    public void logPartialSample(long sample, int dimension) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/AbstractSampleLogger.logPartialSample:(JI)V");
    }

    protected abstract void useSample();

    public AbstractSampleLogger() {
    }
}
