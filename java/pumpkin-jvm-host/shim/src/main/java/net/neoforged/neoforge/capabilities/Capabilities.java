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

    // NeoForge's create(): the neoforge namespace.
    private static net.minecraft.resources.Identifier pumpkinName(String path) {
        return net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", path);
    }


    public static final class Energy {

        public static final BlockCapability<EnergyHandler, Direction> BLOCK =
                BlockCapability.createSided(pumpkinName("energy_handler"), EnergyHandler.class);

        public static final EntityCapability<EnergyHandler, Direction> ENTITY =
                EntityCapability.createSided(pumpkinName("energy_handler"), EnergyHandler.class);

        public static final ItemCapability<EnergyHandler, ItemAccess> ITEM =
                ItemCapability.create(pumpkinName("energy_handler"), EnergyHandler.class, ItemAccess.class);

        protected Energy() {
        }

        // Pumpkin divergence: no throwing initializer -- the tokens above are real.
    }

    public static final class Fluid {

        public static final BlockCapability<ResourceHandler<FluidResource>, Direction> BLOCK =
                BlockCapability.createSided(pumpkinName("fluid_handler"), ResourceHandler.asClass());

        public static final EntityCapability<ResourceHandler<FluidResource>, Direction> ENTITY =
                EntityCapability.createSided(pumpkinName("fluid_handler"), ResourceHandler.asClass());

        public static final ItemCapability<ResourceHandler<FluidResource>, ItemAccess> ITEM =
                ItemCapability.create(pumpkinName("fluid_handler"), ResourceHandler.asClass(), ItemAccess.class);

        protected Fluid() {
        }

        // Pumpkin divergence: no throwing initializer -- the tokens above are real.
    }

    public static final class Item {

        public static final BlockCapability<ResourceHandler<ItemResource>, Direction> BLOCK =
                BlockCapability.createSided(pumpkinName("item_handler"), ResourceHandler.asClass());

        public static final EntityCapability<ResourceHandler<ItemResource>, Void> ENTITY =
                EntityCapability.createVoid(pumpkinName("item_handler"), ResourceHandler.asClass());

        public static final ItemCapability<ResourceHandler<ItemResource>, ItemAccess> ITEM =
                ItemCapability.create(pumpkinName("item_handler"), ResourceHandler.asClass(), ItemAccess.class);

        protected Item() {
        }

        // Pumpkin divergence: no throwing initializer -- the tokens above are real.
    }

    private static Identifier create(String path) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/Capabilities.create:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }

    protected Capabilities() {
    }
}
