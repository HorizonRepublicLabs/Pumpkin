package com.mojang.blaze3d.platform;

import dev.pumpkin.shim.Unimplemented;

public class MonitorManager implements AutoCloseable {

    public MonitorManager() {
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/MonitorManager.close:()V");
    }
}
