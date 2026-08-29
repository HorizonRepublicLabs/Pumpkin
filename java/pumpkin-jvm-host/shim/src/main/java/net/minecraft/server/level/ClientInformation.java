package net.minecraft.server.level;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;
import dev.pumpkin.shim.Unimplemented;

public record ClientInformation(String language, int viewDistance, ChatVisiblity chatVisibility, boolean chatColors, int modelCustomisation, HumanoidArm mainHand, boolean textFilteringEnabled, boolean allowsListing, ParticleStatus particleStatus) {

    public ClientInformation(FriendlyByteBuf input) {
        this((String) null, (int) 0, (ChatVisiblity) null, (boolean) false, (int) 0, (HumanoidArm) null, (boolean) false, (boolean) false, (ParticleStatus) null);
    }

    public void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/server/level/ClientInformation.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }
}
