package net.minecraft.world.level.biome;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.DensityFunction;
import dev.pumpkin.shim.Unimplemented;

public class Climate {

    interface DistanceMetric<T> {

        long distance(Climate.RTree.Node<T> node, long[] target);
    }

    public record Parameter(long min, long max) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$Parameter.toString:()Ljava/lang/String;");
        }
    }

    public static class ParameterList<T> {

        public ParameterList(List<Pair<Climate.ParameterPoint, T>> values) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$ParameterList.<init>:(Ljava/util/List;)V");
        }

        protected ParameterList() {
        }
    }

    public record ParameterPoint(Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter depth, Climate.Parameter weirdness, long offset) {
    }

    protected static final class RTree<T> {

        private RTree(Climate.RTree.Node<T> root) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree.<init>:(Lnet/minecraft/world/level/biome/Climate$RTree$Node;)V");
        }

        public static <T> Climate.RTree<T> create(List<Pair<Climate.ParameterPoint, T>> values) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree.create:(Ljava/util/List;)Lnet/minecraft/world/level/biome/Climate$RTree;");
        }

        private static <T> Climate.RTree.Node<T> build(int dimensions, List<? extends Climate.RTree.Node<T>> children) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree.build:(ILjava/util/List;)Lnet/minecraft/world/level/biome/Climate$RTree$Node;");
        }

        private static final class Leaf<T> extends Climate.RTree.Node<T> {

            private Leaf(Climate.ParameterPoint parameterPoint, T value) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$Leaf.<init>:(Lnet/minecraft/world/level/biome/Climate$ParameterPoint;Ljava/lang/Object;)V");
            }

            protected Climate.RTree.Leaf<T> search(long[] target, Climate.RTree.Leaf<T> candidate, Climate.DistanceMetric<T> distanceMetric) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$Leaf.search:([JLnet/minecraft/world/level/biome/Climate$RTree$Leaf;Lnet/minecraft/world/level/biome/Climate$DistanceMetric;)Lnet/minecraft/world/level/biome/Climate$RTree$Leaf;");
            }

            protected Leaf() {
            }
        }

        abstract static class Node<T> {

            protected Node(List<Climate.Parameter> parameterSpace) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$Node.<init>:(Ljava/util/List;)V");
            }

            protected abstract Climate.RTree.Leaf<T> search(final long[] target, final Climate.RTree.Leaf<T> candidate, final Climate.DistanceMetric<T> distanceMetric);

            public String toString() {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$Node.toString:()Ljava/lang/String;");
            }

            protected Node() {
            }
        }

        private static final class SubTree<T> extends Climate.RTree.Node<T> {

            public SubTree(List<? extends Climate.RTree.Node<T>> children) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$SubTree.<init>:(Ljava/util/List;)V");
            }

            public SubTree(List<Climate.Parameter> parameterSpace, List<? extends Climate.RTree.Node<T>> children) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$SubTree.<init>:(Ljava/util/List;Ljava/util/List;)V");
            }

            protected Climate.RTree.Leaf<T> search(long[] target, Climate.RTree.Leaf<T> candidate, Climate.DistanceMetric<T> distanceMetric) {
                throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$RTree$SubTree.search:([JLnet/minecraft/world/level/biome/Climate$RTree$Leaf;Lnet/minecraft/world/level/biome/Climate$DistanceMetric;)Lnet/minecraft/world/level/biome/Climate$RTree$Leaf;");
            }

            protected SubTree() {
            }
        }

        protected RTree() {
        }
    }

    public record Sampler(DensityFunction temperature, DensityFunction humidity, DensityFunction continentalness, DensityFunction erosion, DensityFunction depth, DensityFunction weirdness, List<Climate.ParameterPoint> spawnTarget) {
    }

    private static class SpawnFinder {

        private SpawnFinder(List<Climate.ParameterPoint> targetClimates, Climate.Sampler sampler) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate$SpawnFinder.<init>:(Ljava/util/List;Lnet/minecraft/world/level/biome/Climate$Sampler;)V");
        }

        private record Result(BlockPos location, long fitness) {
        }

        protected SpawnFinder() {
        }
    }

    public record TargetPoint(long temperature, long humidity, long continentalness, long erosion, long depth, long weirdness) {
    }

    protected Climate() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Climate");
        }
    }
}
