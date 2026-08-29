package net.minecraft.advancements;

import com.mojang.serialization.DataResult;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.network.FriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public record AdvancementRequirements(List<List<String>> requirements) {

    public AdvancementRequirements(FriendlyByteBuf input) {
        this((List<List<String>>) null);
    }

    public void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.size:()I");
    }

    public boolean test(Predicate<String> predicate) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.test:(Ljava/util/function/Predicate;)Z");
    }

    public int count(Predicate<String> predicate) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.count:(Ljava/util/function/Predicate;)I");
    }

    public DataResult<AdvancementRequirements> validate(Set<String> expectedCriteria) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.validate:(Ljava/util/Set;)Lcom/mojang/serialization/DataResult;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.isEmpty:()Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRequirements.toString:()Ljava/lang/String;");
    }

    public interface Strategy {

        AdvancementRequirements create(Collection<String> criteria);
    }
}
