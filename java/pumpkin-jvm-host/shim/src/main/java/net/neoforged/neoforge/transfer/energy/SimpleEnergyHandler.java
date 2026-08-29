package net.neoforged.neoforge.transfer.energy;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public class SimpleEnergyHandler implements EnergyHandler, ValueIOSerializable {

    protected int capacity;

    public SimpleEnergyHandler(int capacity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.<init>:(I)V");
    }

    public SimpleEnergyHandler(int capacity, int maxTransfer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.<init>:(II)V");
    }

    public SimpleEnergyHandler(int capacity, int maxInsert, int maxExtract) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.<init>:(III)V");
    }

    public SimpleEnergyHandler(int capacity, int maxInsert, int maxExtract, int energy) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.<init>:(IIII)V");
    }

    public void serialize(ValueOutput output) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.serialize:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void deserialize(ValueInput input) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.deserialize:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void set(int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.set:(I)V");
    }

    public long getAmountAsLong() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.getAmountAsLong:()J");
    }

    public long getCapacityAsLong() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.getCapacityAsLong:()J");
    }

    public int insert(int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.insert:(ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler.extract:(ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    private class EnergyJournal extends SnapshotJournal<Integer> {

        protected Integer createSnapshot() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler$EnergyJournal.createSnapshot:()Ljava/lang/Integer;");
        }

        protected void revertToSnapshot(Integer snapshot) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler$EnergyJournal.revertToSnapshot:(Ljava/lang/Integer;)V");
        }

        protected void onRootCommit(Integer originalState) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/SimpleEnergyHandler$EnergyJournal.onRootCommit:(Ljava/lang/Integer;)V");
        }

        protected EnergyJournal() {
        }
    }

    protected SimpleEnergyHandler() {
    }
}
