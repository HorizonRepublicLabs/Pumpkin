package net.minecraft.world.level.saveddata;

import dev.pumpkin.shim.Unimplemented;

public final class WeatherData extends SavedData {

    public WeatherData() {
    }

    public WeatherData(int clearWeatherTime, int rainTime, int thunderTime, boolean raining, boolean thundering) {
    }

    public void setThundering(boolean thundering) {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/WeatherData.setThundering:(Z)V");
    }

    public void setRaining(boolean raining) {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/WeatherData.setRaining:(Z)V");
    }
}
