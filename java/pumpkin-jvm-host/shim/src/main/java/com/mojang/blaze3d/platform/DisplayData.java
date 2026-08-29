package com.mojang.blaze3d.platform;

import java.util.OptionalInt;

public record DisplayData(int width, int height, OptionalInt fullscreenWidth, OptionalInt fullscreenHeight, boolean isFullscreen) {
}
