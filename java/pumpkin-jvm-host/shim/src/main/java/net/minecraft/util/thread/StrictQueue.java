package net.minecraft.util.thread;

import java.util.Queue;
import dev.pumpkin.shim.Unimplemented;

public interface StrictQueue<T extends Runnable> {

    Runnable pop();

    boolean push(final T t);

    boolean isEmpty();

    int size();

    final class FixedPriorityQueue implements StrictQueue<StrictQueue.RunnableWithPriority> {

        public FixedPriorityQueue(int size) {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$FixedPriorityQueue.<init>:(I)V");
        }

        public Runnable pop() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$FixedPriorityQueue.pop:()Ljava/lang/Runnable;");
        }

        public boolean push(StrictQueue.RunnableWithPriority task) {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$FixedPriorityQueue.push:(Lnet/minecraft/util/thread/StrictQueue$RunnableWithPriority;)Z");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$FixedPriorityQueue.isEmpty:()Z");
        }

        public int size() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$FixedPriorityQueue.size:()I");
        }

        protected FixedPriorityQueue() {
        }
    }

    final class QueueStrictQueue implements StrictQueue<Runnable> {

        public QueueStrictQueue(Queue<Runnable> queue) {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$QueueStrictQueue.<init>:(Ljava/util/Queue;)V");
        }

        public Runnable pop() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$QueueStrictQueue.pop:()Ljava/lang/Runnable;");
        }

        public boolean push(Runnable t) {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$QueueStrictQueue.push:(Ljava/lang/Runnable;)Z");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$QueueStrictQueue.isEmpty:()Z");
        }

        public int size() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$QueueStrictQueue.size:()I");
        }

        protected QueueStrictQueue() {
        }
    }

    record RunnableWithPriority(int priority, Runnable task) implements Runnable {

        public void run() {
            throw Unimplemented.forMember("net/minecraft/util/thread/StrictQueue$RunnableWithPriority.run:()V");
        }
    }
}
