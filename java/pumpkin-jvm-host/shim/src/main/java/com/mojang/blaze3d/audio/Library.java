package com.mojang.blaze3d.audio;

import dev.pumpkin.shim.Unimplemented;

public class Library {

    public void cleanup() {
        throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library.cleanup:()V");
    }

    public Listener getListener() {
        throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library.getListener:()Lcom/mojang/blaze3d/audio/Listener;");
    }

    private interface ChannelPool {

        Channel acquire();

        boolean release(Channel channel);

        void cleanup();

        int getMaxCount();

        int getUsedCount();
    }

    private static class CountingChannelPool implements Library.ChannelPool {

        public CountingChannelPool(int limit) {
        }

        public Channel acquire() {
            throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library$CountingChannelPool.acquire:()Lcom/mojang/blaze3d/audio/Channel;");
        }

        public boolean release(Channel channel) {
            throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library$CountingChannelPool.release:(Lcom/mojang/blaze3d/audio/Channel;)Z");
        }

        public void cleanup() {
            throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library$CountingChannelPool.cleanup:()V");
        }

        public int getMaxCount() {
            throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library$CountingChannelPool.getMaxCount:()I");
        }

        public int getUsedCount() {
            throw Unimplemented.forMember("com/mojang/blaze3d/audio/Library$CountingChannelPool.getUsedCount:()I");
        }

        protected CountingChannelPool() {
        }
    }

    public enum Pool {

        STATIC, STREAMING
    }

    public Library() {
    }
}
