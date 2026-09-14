package net.neoforged.fml;

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

    /**
     * Accepts a config spec, and says once that nothing will read a file for it.
     *
     * <p>The values a mod defined already answer -- each holds the default the mod itself
     * declared, which is what NeoForge returns too when no file overrides it. What does not
     * exist is the file: an operator cannot change any of these yet. That is worth saying
     * out loud exactly once per mod, because it is invisible from the mod's side and a
     * server owner would otherwise discover it by editing a file that never gets read.
     *
     * <p>Throwing instead would stop both real mods in their constructors over settings they
     * are only declaring, never reading, at that point.
     */
    /**
     * The mod's id. Real body: the container is created for a named mod, and identity
     * questions have answers -- the same line FMLEnvironment draws.
     */
    public String getModId() {
        return toString();
    }

    /**
     * The mod's declared info. Answers the identity facts a display-info override reads
     * (id, description from the id); everything else throws by name.
     */
    public net.neoforged.neoforgespi.language.IModInfo getModInfo() {
        String modId = getModId();
        return dev.pumpkin.shim.Stubs.of(
                net.neoforged.neoforgespi.language.IModInfo.class,
                "net/neoforged/neoforgespi/language/IModInfo",
                java.util.Map.of("getModId", modId, "getDescription", modId,
                        "getDisplayName", modId,
                        // The version Pumpkin's own plugin listing reports for mods.
                        "getVersion",
                        new org.apache.maven.artifact.versioning.DefaultArtifactVersion("0.0.0")));
    }

    public void registerConfig(ModConfig.Type type, IConfigSpec spec) {
        // FML's own default name for a config the mod did not name itself.
        registerConfig(type, spec, getModId() + "-" + type.name().toLowerCase(java.util.Locale.ROOT)
                + ".toml");
    }

    public void registerConfig(ModConfig.Type type, IConfigSpec spec, String fileName) {
        pumpkinWarnOnce();
        pumpkinRecord(fileName, type.name());
    }

    private void pumpkinWarnOnce() {
        if (PUMPKIN_WARNED.add(toString())) {
            System.err.println("[pumpkin] " + this + " registered a config. Its values will "
                    + "answer with the defaults the mod declared; Pumpkin does not read or "
                    + "write config files yet, so editing one will have no effect.");
        }
    }

    /**
     * Tells the host a config file exists, so a modded client can be sent it.
     *
     * <p>Recorded, not read. A NeoForge client that has been told the connection is modded
     * loads its server-type configs only from what the server sends; a mod reading one that
     * never arrived throws "Cannot get config value before config is loaded" as the player
     * joins. The names are what the server needs to send them.
     *
     * <p>Reflective because this module sits *below* the host on the classpath -- host
     * depends on shim depends on fml -- and because the generator's tests load these
     * classes with no host present at all.
     */
    private static void pumpkinRecord(String fileName, String type) {
        try {
            if (PUMPKIN_RECORD == null) {
                PUMPKIN_RECORD = Class.forName("dev.pumpkin.jvmhost.PumpkinHost")
                        .getMethod("registerModConfig", String.class, String.class);
            }
            PUMPKIN_RECORD.invoke(null, fileName, type);
        } catch (ReflectiveOperationException | LinkageError ignored) {
            // No host: nothing can be sent to a client anyway.
        }
    }

    private static volatile java.lang.reflect.Method PUMPKIN_RECORD;

    private static final java.util.Set<String> PUMPKIN_WARNED =
            java.util.concurrent.ConcurrentHashMap.newKeySet();
}
