package net.minecraft.client;

import dev.pumpkin.shim.Unimplemented;

public enum CameraType {

    FIRST_PERSON, THIRD_PERSON_BACK, THIRD_PERSON_FRONT;

    public boolean isFirstPerson() {
        throw Unimplemented.forMember("net/minecraft/client/CameraType.isFirstPerson:()Z");
    }
}
