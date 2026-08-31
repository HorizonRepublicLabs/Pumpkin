package net.minecraft.world.attribute;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class EnvironmentAttributeProbe {

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeProbe.reset:()V");
    }

    public void tick(Level level, Vec3 position) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeProbe.tick:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public <Value> Value getValue(EnvironmentAttribute<Value> attribute, float partialTicks) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeProbe.getValue:(Lnet/minecraft/world/attribute/EnvironmentAttribute;F)Ljava/lang/Object;");
    }

    private class ValueProbe<Value> {

        public ValueProbe(EnvironmentAttribute<Value> attribute) {
        }

        public boolean tick() {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeProbe$ValueProbe.tick:()Z");
        }

        public Value get(EnvironmentAttribute<Value> attribute, float partialTicks) {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeProbe$ValueProbe.get:(Lnet/minecraft/world/attribute/EnvironmentAttribute;F)Ljava/lang/Object;");
        }

        protected ValueProbe() {
        }
    }

    public EnvironmentAttributeProbe() {
    }
}
