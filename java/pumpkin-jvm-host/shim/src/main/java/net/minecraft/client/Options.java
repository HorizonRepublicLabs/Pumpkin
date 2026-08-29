package net.minecraft.client;

import java.io.File;
import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public class Options {

    public boolean advancedItemTooltips;

    public Options(Minecraft minecraft, File workingDirectory) {
        throw Unimplemented.forMember("net/minecraft/client/Options.<init>:(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V");
    }

    public void save() {
        throw Unimplemented.forMember("net/minecraft/client/Options.save:()V");
    }

    public boolean useNativeTransport() {
        throw Unimplemented.forMember("net/minecraft/client/Options.useNativeTransport:()Z");
    }

    public interface FieldAccess extends Options.OptionAccess {

        int process(String name, int value);

        boolean process(String name, boolean value);

        String process(String name, String value);

        float process(String name, float value);

        <T> T process(String name, T value, Function<String, T> reader, Function<T, String> writer);
    }

    private interface OptionAccess {

        <T> void process(String name, OptionInstance<T> option);
    }

    public Options() {
    }
}
