package dev.pumpkin.dumper;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.google.gson.JsonObject;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfigs;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Every mod's synced config at its defaults, as TOML. A NeoForge client loads these from
 * the server alone and throws on the first read of one that never arrived, so the server
 * has to send each. A data run loads no config files, so they are built from the specs.
 */
final class SyncedConfigs {
    private SyncedConfigs() {
    }

    /** Mod id to config file name to its TOML; NeoForge's own is the server's to send. */
    static JsonObject dump() {
        var out = new JsonObject();
        for (ModConfig config : ModConfigs.getConfigSet(ModConfig.Type.SYNCED)) {
            if (config.getModId().equals("neoforge") || !(config.getSpec() instanceof ModConfigSpec spec)) {
                continue;
            }
            CommentedConfig toml = TomlFormat.newConfig();
            spec.correct(toml);
            var files = out.has(config.getModId()) ? out.getAsJsonObject(config.getModId()) : new JsonObject();
            files.addProperty(config.getFileName(), TomlFormat.instance().createWriter().writeToString(toml));
            out.add(config.getModId(), files);
        }
        return out;
    }
}
