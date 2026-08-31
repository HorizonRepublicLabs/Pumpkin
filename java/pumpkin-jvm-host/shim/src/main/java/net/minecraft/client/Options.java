package net.minecraft.client;

import java.io.File;
import java.util.function.Function;
import net.minecraft.sounds.SoundSource;
import dev.pumpkin.shim.Unimplemented;

public class Options {

    public boolean advancedItemTooltips;

    private final OptionInstance<Boolean> showSubtitles = null;

    public final KeyMapping keyPickItem = null;

    private final OptionInstance<Double> gamma = null;

    public final float getSoundSourceVolume(SoundSource source) {
        throw Unimplemented.forMember("net/minecraft/client/Options.getSoundSourceVolume:(Lnet/minecraft/sounds/SoundSource;)F");
    }

    public OptionInstance<Boolean> showSubtitles() {
        throw Unimplemented.forMember("net/minecraft/client/Options.showSubtitles:()Lnet/minecraft/client/OptionInstance;");
    }

    public OptionInstance<Double> gamma() {
        throw Unimplemented.forMember("net/minecraft/client/Options.gamma:()Lnet/minecraft/client/OptionInstance;");
    }

    public Options(Minecraft minecraft, File workingDirectory) {
    }

    public int getBackgroundColor(float defaultOpacity) {
        throw Unimplemented.forMember("net/minecraft/client/Options.getBackgroundColor:(F)I");
    }

    public int getBackgroundColor(int defaultColor) {
        throw Unimplemented.forMember("net/minecraft/client/Options.getBackgroundColor:(I)I");
    }

    public void save() {
        throw Unimplemented.forMember("net/minecraft/client/Options.save:()V");
    }

    public boolean useNativeTransport() {
        throw Unimplemented.forMember("net/minecraft/client/Options.useNativeTransport:()Z");
    }

    public CameraType getCameraType() {
        throw Unimplemented.forMember("net/minecraft/client/Options.getCameraType:()Lnet/minecraft/client/CameraType;");
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
