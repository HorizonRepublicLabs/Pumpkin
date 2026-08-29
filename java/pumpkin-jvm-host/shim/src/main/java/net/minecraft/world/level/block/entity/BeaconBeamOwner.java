package net.minecraft.world.level.block.entity;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public interface BeaconBeamOwner {

    List<BeaconBeamOwner.Section> getBeamSections();

    class Section {

        public Section(int color) {
        }

        public int getColor() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeaconBeamOwner$Section.getColor:()I");
        }

        public int getHeight() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BeaconBeamOwner$Section.getHeight:()I");
        }

        protected Section() {
        }
    }
}
