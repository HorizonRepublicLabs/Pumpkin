package net.minecraft.util;

import dev.pumpkin.shim.Unimplemented;

public class GsonHelper {

    private static class CountedAppendable implements Appendable {

        public CountedAppendable(int limit) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.<init>:(I)V");
        }

        public Appendable append(CharSequence csq) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.append:(Ljava/lang/CharSequence;)Ljava/lang/Appendable;");
        }

        public Appendable append(CharSequence csq, int start, int end) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.append:(Ljava/lang/CharSequence;II)Ljava/lang/Appendable;");
        }

        public Appendable append(char c) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.append:(C)Ljava/lang/Appendable;");
        }

        protected CountedAppendable() {
        }
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper");
        }
    }
}
