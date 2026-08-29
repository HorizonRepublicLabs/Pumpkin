package net.minecraft.world.level.gameevent;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record GameEvent(int notificationRadius) {

    public static final Holder.Reference<GameEvent> BLOCK_CHANGE = null;

    private static Holder.Reference<GameEvent> register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent.register:(Ljava/lang/String;)Lnet/minecraft/core/Holder$Reference;");
    }

    private static Holder.Reference<GameEvent> register(String name, int notificationRadius) {
        throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent.register:(Ljava/lang/String;I)Lnet/minecraft/core/Holder$Reference;");
    }

    public record Context(Entity sourceEntity, BlockState affectedState) {

        public static GameEvent.Context of(Entity sourceEntity) {
            throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent$Context.of:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/level/gameevent/GameEvent$Context;");
        }

        public static GameEvent.Context of(BlockState state) {
            throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent$Context.of:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/gameevent/GameEvent$Context;");
        }

        public static GameEvent.Context of(Entity sourceEntity, BlockState state) {
            throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent$Context.of:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/gameevent/GameEvent$Context;");
        }
    }

    public static final class ListenerInfo implements Comparable<GameEvent.ListenerInfo> {

        public ListenerInfo(Holder<GameEvent> gameEvent, Vec3 source, GameEvent.Context context, GameEventListener recipient, Vec3 recipientPos) {
            throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent$ListenerInfo.<init>:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/gameevent/GameEvent$Context;Lnet/minecraft/world/level/gameevent/GameEventListener;Lnet/minecraft/world/phys/Vec3;)V");
        }

        public int compareTo(GameEvent.ListenerInfo other) {
            throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent$ListenerInfo.compareTo:(Lnet/minecraft/world/level/gameevent/GameEvent$ListenerInfo;)I");
        }

        public ListenerInfo() {
        }
    }
}
