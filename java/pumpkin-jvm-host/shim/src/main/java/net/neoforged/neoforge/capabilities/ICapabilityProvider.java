package net.neoforged.neoforge.capabilities;

public interface ICapabilityProvider<O, C extends Object, T> {

    T getCapability(O object, C context);
}
