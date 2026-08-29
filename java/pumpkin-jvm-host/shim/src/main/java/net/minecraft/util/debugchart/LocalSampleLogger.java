package net.minecraft.util.debugchart;

import dev.pumpkin.shim.Unimplemented;

public class LocalSampleLogger extends AbstractSampleLogger implements SampleStorage {

    public LocalSampleLogger(int dimensions) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.<init>:(I)V");
    }

    public LocalSampleLogger(int dimensions, long[] defaults) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.<init>:(I[J)V");
    }

    protected void useSample() {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.useSample:()V");
    }

    public int capacity() {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.capacity:()I");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.size:()I");
    }

    public long get(int index) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.get:(I)J");
    }

    public long get(int index, int dimension) {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.get:(II)J");
    }

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/util/debugchart/LocalSampleLogger.reset:()V");
    }

    public LocalSampleLogger() {
    }
}
