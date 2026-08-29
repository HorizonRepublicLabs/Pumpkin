package net.minecraft.util;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import dev.pumpkin.shim.Unimplemented;

public interface ProblemReporter {

    ProblemReporter forChild(ProblemReporter.PathElement path);

    void report(ProblemReporter.Problem problem);

    class Collector implements ProblemReporter {

        public Collector() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.<init>:()V");
        }

        public Collector(ProblemReporter.PathElement root) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.<init>:(Lnet/minecraft/util/ProblemReporter$PathElement;)V");
        }

        private Collector(ProblemReporter.Collector parent, ProblemReporter.PathElement path) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.<init>:(Lnet/minecraft/util/ProblemReporter$Collector;Lnet/minecraft/util/ProblemReporter$PathElement;)V");
        }

        public ProblemReporter forChild(ProblemReporter.PathElement path) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.forChild:(Lnet/minecraft/util/ProblemReporter$PathElement;)Lnet/minecraft/util/ProblemReporter;");
        }

        public void report(ProblemReporter.Problem problem) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.report:(Lnet/minecraft/util/ProblemReporter$Problem;)V");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.isEmpty:()Z");
        }

        public void forEach(BiConsumer<String, ProblemReporter.Problem> output) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector.forEach:(Ljava/util/function/BiConsumer;)V");
        }

        private record Entry(ProblemReporter.Collector source, ProblemReporter.Problem problem) {
        }

        private record ProblemTreeNode(ProblemReporter.PathElement element, List<ProblemReporter.Problem> problems, Map<ProblemReporter.PathElement, ProblemReporter.Collector.ProblemTreeNode> children) {

            public ProblemTreeNode(ProblemReporter.PathElement pathElement) {
                this((ProblemReporter.PathElement) null, (List<ProblemReporter.Problem>) null, (Map<ProblemReporter.PathElement, ProblemReporter.Collector.ProblemTreeNode>) null);
                throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector$ProblemTreeNode.<init>:(Lnet/minecraft/util/ProblemReporter$PathElement;)V");
            }

            public ProblemReporter.Collector.ProblemTreeNode child(ProblemReporter.PathElement id) {
                throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$Collector$ProblemTreeNode.child:(Lnet/minecraft/util/ProblemReporter$PathElement;)Lnet/minecraft/util/ProblemReporter$Collector$ProblemTreeNode;");
            }
        }
    }

    record ElementReferencePathElement(ResourceKey<?> id) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$ElementReferencePathElement.get:()Ljava/lang/String;");
        }
    }

    record FieldPathElement(String name) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$FieldPathElement.get:()Ljava/lang/String;");
        }
    }

    record IndexedFieldPathElement(String name, int index) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$IndexedFieldPathElement.get:()Ljava/lang/String;");
        }
    }

    record IndexedPathElement(int index) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$IndexedPathElement.get:()Ljava/lang/String;");
        }
    }

    record MapEntryPathElement(String name, String key) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$MapEntryPathElement.get:()Ljava/lang/String;");
        }
    }

    interface PathElement {

        String get();
    }

    interface Problem {

        String description();
    }

    record RootElementPathElement(ResourceKey<?> id) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$RootElementPathElement.get:()Ljava/lang/String;");
        }
    }

    record RootFieldPathElement(String name) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$RootFieldPathElement.get:()Ljava/lang/String;");
        }
    }

    class ScopedCollector extends ProblemReporter.Collector implements AutoCloseable {

        public ScopedCollector(Logger logger) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$ScopedCollector.<init>:(Lorg/slf4j/Logger;)V");
        }

        public ScopedCollector(ProblemReporter.PathElement root, Logger logger) {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$ScopedCollector.<init>:(Lnet/minecraft/util/ProblemReporter$PathElement;Lorg/slf4j/Logger;)V");
        }

        public void close() {
            throw Unimplemented.forMember("net/minecraft/util/ProblemReporter$ScopedCollector.close:()V");
        }

        protected ScopedCollector() {
        }
    }
}
