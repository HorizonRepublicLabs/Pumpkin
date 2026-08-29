package net.neoforged.fml;

import dev.pumpkin.shim.Unimplemented;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;

/**
 * Hand-written, not generated: FML is published as a separate artifact whose sources are
 * not in the decompiled tree. On the generator's "no source found" list; do not delete it
 * as un-regenerable.
 *
 * <p>Carries only the two {@code registerConfig} overloads the manifest records the mods
 * calling. Config handling is behaviour Pumpkin does not have yet, so both throw.
 */
public abstract class ModContainer {
    protected ModContainer() {
    }

    public void registerConfig(ModConfig.Type type, IConfigSpec spec) {
        throw Unimplemented.forMember(
                "net/neoforged/fml/ModContainer.registerConfig:"
                        + "(Lnet/neoforged/fml/config/ModConfig$Type;Lnet/neoforged/fml/config/IConfigSpec;)V");
    }

    public void registerConfig(ModConfig.Type type, IConfigSpec spec, String fileName) {
        throw Unimplemented.forMember(
                "net/neoforged/fml/ModContainer.registerConfig:"
                        + "(Lnet/neoforged/fml/config/ModConfig$Type;Lnet/neoforged/fml/config/IConfigSpec;"
                        + "Ljava/lang/String;)V");
    }
}
