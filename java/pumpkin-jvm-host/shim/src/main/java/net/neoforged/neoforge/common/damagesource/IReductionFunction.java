package net.neoforged.neoforge.common.damagesource;

public interface IReductionFunction {

    float modify(DamageContainer container, float reductionIn);
}
