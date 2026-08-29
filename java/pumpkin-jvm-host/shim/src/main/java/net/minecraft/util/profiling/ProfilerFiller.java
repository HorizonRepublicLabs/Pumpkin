package net.minecraft.util.profiling;

import java.util.function.Supplier;
import net.minecraft.util.profiling.metrics.MetricCategory;
import dev.pumpkin.shim.Unimplemented;

public interface ProfilerFiller {

    void startTick();

    void endTick();

    void push(String name);

    void push(Supplier<String> name);

    void pop();

    void popPush(String name);

    void popPush(Supplier<String> name);

    void markForCharting(MetricCategory category);

    void incrementCounter(String name, int amount);

    void incrementCounter(Supplier<String> name, int amount);

    class CombinedProfileFiller implements ProfilerFiller {

        public CombinedProfileFiller(ProfilerFiller first, ProfilerFiller second) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.<init>:(Lnet/minecraft/util/profiling/ProfilerFiller;Lnet/minecraft/util/profiling/ProfilerFiller;)V");
        }

        public void startTick() {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.startTick:()V");
        }

        public void endTick() {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.endTick:()V");
        }

        public void push(String name) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.push:(Ljava/lang/String;)V");
        }

        public void push(Supplier<String> name) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.push:(Ljava/util/function/Supplier;)V");
        }

        public void markForCharting(MetricCategory category) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.markForCharting:(Lnet/minecraft/util/profiling/metrics/MetricCategory;)V");
        }

        public void pop() {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.pop:()V");
        }

        public void popPush(String name) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.popPush:(Ljava/lang/String;)V");
        }

        public void popPush(Supplier<String> name) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.popPush:(Ljava/util/function/Supplier;)V");
        }

        public void incrementCounter(String name, int amount) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.incrementCounter:(Ljava/lang/String;I)V");
        }

        public void incrementCounter(Supplier<String> name, int amount) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.incrementCounter:(Ljava/util/function/Supplier;I)V");
        }

        public void addZoneText(String text) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.addZoneText:(Ljava/lang/String;)V");
        }

        public void addZoneValue(long value) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.addZoneValue:(J)V");
        }

        public void setZoneColor(int color) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/ProfilerFiller$CombinedProfileFiller.setZoneColor:(I)V");
        }

        protected CombinedProfileFiller() {
        }
    }
}
