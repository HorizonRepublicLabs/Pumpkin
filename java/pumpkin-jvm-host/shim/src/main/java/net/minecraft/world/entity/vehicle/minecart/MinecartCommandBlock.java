package net.minecraft.world.entity.vehicle.minecart;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BaseCommandBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class MinecartCommandBlock extends AbstractMinecart {

    public MinecartCommandBlock(EntityType<? extends MinecartCommandBlock> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    protected Item getDropItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.getDropItem:()Lnet/minecraft/world/item/Item;");
    }

    public ItemStack getPickResult() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.getPickResult:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public BlockState getDefaultDisplayBlockState() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.getDefaultDisplayBlockState:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void activateMinecart(ServerLevel level, int xt, int yt, int zt, boolean state) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.activateMinecart:(Lnet/minecraft/server/level/ServerLevel;IIIZ)V");
    }

    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.interact:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/InteractionResult;");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    private class MinecartCommandBase extends BaseCommandBlock {

        public void onUpdated(ServerLevel level) {
            throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock$MinecartCommandBase.onUpdated:(Lnet/minecraft/server/level/ServerLevel;)V");
        }

        public CommandSourceStack createCommandSourceStack(ServerLevel level, CommandSource source) {
            throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock$MinecartCommandBase.createCommandSourceStack:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/commands/CommandSource;)Lnet/minecraft/commands/CommandSourceStack;");
        }

        public boolean isValid() {
            throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock$MinecartCommandBase.isValid:()Z");
        }

        protected MinecartCommandBase() {
        }
    }

    public MinecartCommandBlock() {
    }
}
