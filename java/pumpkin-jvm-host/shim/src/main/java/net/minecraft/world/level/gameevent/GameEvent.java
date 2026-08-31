package net.minecraft.world.level.gameevent;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record GameEvent(int notificationRadius) {

    public static final Holder.Reference<GameEvent> BLOCK_CHANGE = null;

    public static final Holder.Reference<GameEvent> BLOCK_DESTROY = null;

    public static final Holder.Reference<GameEvent> BLOCK_PLACE = null;

    public static final Holder.Reference<GameEvent> DRINK = null;

    public static final Holder.Reference<GameEvent> ENTITY_DAMAGE = null;

    public static final Holder.Reference<GameEvent> ENTITY_INTERACT = null;

    public static final Holder.Reference<GameEvent> ENTITY_PLACE = null;

    public static final Holder.Reference<GameEvent> EXPLODE = null;

    public static final Holder.Reference<GameEvent> FLUID_PICKUP = null;

    public static final Holder.Reference<GameEvent> FLUID_PLACE = null;

    public static final Holder.Reference<GameEvent> PRIME_FUSE = null;

    public static final Holder.Reference<GameEvent> SHEAR = null;

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
        }

        public int compareTo(GameEvent.ListenerInfo other) {
            throw Unimplemented.forMember("net/minecraft/world/level/gameevent/GameEvent$ListenerInfo.compareTo:(Lnet/minecraft/world/level/gameevent/GameEvent$ListenerInfo;)I");
        }

        public ListenerInfo() {
        }
    }
}
