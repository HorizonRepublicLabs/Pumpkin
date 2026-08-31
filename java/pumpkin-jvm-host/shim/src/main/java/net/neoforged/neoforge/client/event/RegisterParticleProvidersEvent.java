package net.neoforged.neoforge.client.event;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleResources;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterParticleProvidersEvent extends Event implements IModBusEvent {

    public RegisterParticleProvidersEvent(ParticleResources particleResources) {
    }

    public <T extends ParticleOptions> void registerSpecial(ParticleType<T> type, ParticleProvider<T> provider) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterParticleProvidersEvent.registerSpecial:(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/client/particle/ParticleProvider;)V");
    }

    public <T extends ParticleOptions> void registerSpriteSet(ParticleType<T> type, ParticleResources.SpriteParticleRegistration<T> registration) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RegisterParticleProvidersEvent.registerSpriteSet:(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/client/particle/ParticleResources$SpriteParticleRegistration;)V");
    }

    public RegisterParticleProvidersEvent() {
    }
}
