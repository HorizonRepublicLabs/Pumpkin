package net.neoforged.neoforge.common;

import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public final class UsernameCache {

    protected UsernameCache() {
    }

    public static String getLastKnownUsername(UUID uuid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/UsernameCache.getLastKnownUsername:(Ljava/util/UUID;)Ljava/lang/String;");
    }

    protected static void save() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/UsernameCache.save:()V");
    }

    private static class SaveThread extends Thread {

        public SaveThread(String data) {
        }

        public void run() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/UsernameCache$SaveThread.run:()V");
        }

        protected SaveThread() {
        }
    }
}
