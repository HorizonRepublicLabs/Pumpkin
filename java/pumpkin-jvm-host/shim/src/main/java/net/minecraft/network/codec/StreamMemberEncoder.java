package net.minecraft.network.codec;

public interface StreamMemberEncoder<O, T> {

    void encode(T value, O output);
}
