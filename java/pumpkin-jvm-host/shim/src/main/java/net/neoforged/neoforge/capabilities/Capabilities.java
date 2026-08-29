package net.neoforged.neoforge.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.item.ItemResource;
import dev.pumpkin.shim.Unimplemented;

public final class Capabilities {

    public static final class Energy {

        public static final BlockCapability<EnergyHandler, Direction> BLOCK = null;

        public static final ItemCapability<EnergyHandler, ItemAccess> ITEM = null;

        protected Energy() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/Capabilities$Energy");
            }
        }
    }

    public static final class Fluid {

        public static final ItemCapability<ResourceHandler<FluidResource>, ItemAccess> ITEM = null;

        protected Fluid() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/Capabilities$Fluid");
            }
        }
    }

    public static final class Item {

        public static final BlockCapability<ResourceHandler<ItemResource>, Direction> BLOCK = null;

        protected Item() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/Capabilities$Item");
            }
        }
    }

    private static Identifier create(String path) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/Capabilities.create:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }

    protected Capabilities() {
    }
}
