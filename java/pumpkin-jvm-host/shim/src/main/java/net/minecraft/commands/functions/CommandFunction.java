package net.minecraft.commands.functions;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.FunctionInstantiationException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;

public interface CommandFunction<T> {

    Identifier id();

    InstantiatedFunction<T> instantiate(CompoundTag arguments, CommandDispatcher<T> dispatcher) throws FunctionInstantiationException;
}
