package net.minecraft.world.level.levelgen.structure.pools;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public class StructureTemplatePool {

    public StructureTemplatePool(Holder<StructureTemplatePool> fallback, List<Pair<StructurePoolElement, Integer>> templates) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool.<init>:(Lnet/minecraft/core/Holder;Ljava/util/List;)V");
    }

    public StructureTemplatePool(Holder<StructureTemplatePool> fallback, List<Pair<Function<StructureTemplatePool.Projection, ? extends StructurePoolElement>, Integer>> templates, StructureTemplatePool.Projection projection) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool.<init>:(Lnet/minecraft/core/Holder;Ljava/util/List;Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool$Projection;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool.size:()I");
    }

    public enum Projection implements StringRepresentable {

        TERRAIN_MATCHING, RIGID;

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool$Projection.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool$Projection.getSerializedName:()Ljava/lang/String;");
        }
    }

    public StructureTemplatePool() {
    }
}
