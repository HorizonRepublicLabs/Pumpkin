package net.minecraft.server.packs.metadata.pack;

import java.util.Optional;
import net.minecraft.util.InclusiveRange;
import dev.pumpkin.shim.Unimplemented;

public record PackFormat(int major, int minor) implements Comparable<PackFormat> {

    public int compareTo(PackFormat other) {
        throw Unimplemented.forMember("net/minecraft/server/packs/metadata/pack/PackFormat.compareTo:(Lnet/minecraft/server/packs/metadata/pack/PackFormat;)I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/server/packs/metadata/pack/PackFormat.toString:()Ljava/lang/String;");
    }

    public record IntermediaryFormat(Optional<PackFormat> min, Optional<PackFormat> max, Optional<Integer> format, Optional<InclusiveRange<Integer>> supported) {
    }

    public interface IntermediaryFormatHolder {

        PackFormat.IntermediaryFormat format();
    }
}
