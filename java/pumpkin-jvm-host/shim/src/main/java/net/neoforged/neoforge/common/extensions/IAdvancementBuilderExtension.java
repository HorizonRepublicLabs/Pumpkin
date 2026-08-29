package net.neoforged.neoforge.common.extensions;

import java.util.function.Consumer;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public interface IAdvancementBuilderExtension {

    default AdvancementHolder save(Consumer<AdvancementHolder> saver, Identifier id) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IAdvancementBuilderExtension.save:(Ljava/util/function/Consumer;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/advancements/AdvancementHolder;");
    }
}
