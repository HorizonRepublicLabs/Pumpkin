package net.minecraft.network.chat;

import com.mojang.serialization.MapCodec;
import java.util.BitSet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public class FilterMask {

    private FilterMask(BitSet mask, FilterMask.Type type) {
    }

    private FilterMask(BitSet mask) {
    }

    public FilterMask(int length) {
    }

    private FilterMask.Type type() {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.type:()Lnet/minecraft/network/chat/FilterMask$Type;");
    }

    public static FilterMask read(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/chat/FilterMask;");
    }

    public static void write(FriendlyByteBuf output, FilterMask mask) {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.write:(Lnet/minecraft/network/FriendlyByteBuf;Lnet/minecraft/network/chat/FilterMask;)V");
    }

    public String apply(String text) {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.apply:(Ljava/lang/String;)Ljava/lang/String;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.isEmpty:()Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask.hashCode:()I");
    }

    private enum Type implements StringRepresentable {

        PASS_THROUGH, FULLY_FILTERED, PARTIALLY_FILTERED;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask$Type.getSerializedName:()Ljava/lang/String;");
        }

        private MapCodec<FilterMask> codec() {
            throw Unimplemented.forMember("net/minecraft/network/chat/FilterMask$Type.codec:()Lcom/mojang/serialization/MapCodec;");
        }
    }

    public FilterMask() {
    }
}
