package net.minecraft.server.packs.repository;

import java.util.List;
import java.util.function.Function;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public class Pack {

    public Pack(PackLocationInfo location, Pack.ResourcesSupplier resources, Pack.Metadata metadata, PackSelectionConfig selectionConfig) {
    }

    private Pack(PackLocationInfo location, Pack.ResourcesSupplier resources, Pack.Metadata metadata, PackSelectionConfig selectionConfig, List<Pack> children) {
    }

    public PackLocationInfo location() {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/Pack.location:()Lnet/minecraft/server/packs/PackLocationInfo;");
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/Pack.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public String getId() {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/Pack.getId:()Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/Pack.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/server/packs/repository/Pack.hashCode:()I");
    }

    public static record Metadata(Component description, PackCompatibility compatibility, FeatureFlagSet requestedFeatures, List<String> overlays, boolean isHidden) {

        public Metadata(Component description, PackCompatibility compatibility, FeatureFlagSet requestedFeatures, List<String> overlays) {
            this((Component) null, (PackCompatibility) null, (FeatureFlagSet) null, (List<String>) null, (boolean) false);
        }
    }

    public enum Position {

        TOP, BOTTOM;

        public <T> int insert(List<T> list, T value, Function<T, PackSelectionConfig> converter, boolean reverse) {
            throw Unimplemented.forMember("net/minecraft/server/packs/repository/Pack$Position.insert:(Ljava/util/List;Ljava/lang/Object;Ljava/util/function/Function;Z)I");
        }
    }

    public interface ResourcesSupplier {

        PackResources openPrimary(PackLocationInfo location);

        PackResources openFull(PackLocationInfo location, Pack.Metadata metadata);
    }

    public Pack() {
    }
}
