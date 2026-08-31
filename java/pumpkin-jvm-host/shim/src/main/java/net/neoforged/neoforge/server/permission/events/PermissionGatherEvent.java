package net.neoforged.neoforge.server.permission.events;

import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import dev.pumpkin.shim.Unimplemented;

public abstract class PermissionGatherEvent extends Event {

    public static class Handler extends PermissionGatherEvent {

        public Handler() {
        }
    }

    public static class Nodes extends PermissionGatherEvent {

        public Nodes() {
        }

        public void addNodes(PermissionNode<?>... nodes) {
            throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/events/PermissionGatherEvent$Nodes.addNodes:([Lnet/neoforged/neoforge/server/permission/nodes/PermissionNode;)V");
        }

        public void addNodes(Iterable<PermissionNode<?>> nodes) {
            throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/events/PermissionGatherEvent$Nodes.addNodes:(Ljava/lang/Iterable;)V");
        }
    }

    public PermissionGatherEvent() {
    }
}
