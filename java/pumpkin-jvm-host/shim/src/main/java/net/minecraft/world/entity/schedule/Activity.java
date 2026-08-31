package net.minecraft.world.entity.schedule;

import dev.pumpkin.shim.Unimplemented;

public class Activity {

    public Activity(String name) {
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/schedule/Activity.getName:()Ljava/lang/String;");
    }

    private static Activity register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/entity/schedule/Activity.register:(Ljava/lang/String;)Lnet/minecraft/world/entity/schedule/Activity;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/entity/schedule/Activity.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/entity/schedule/Activity.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/schedule/Activity.toString:()Ljava/lang/String;");
    }

    public Activity() {
    }
}
