package net.minecraft.server.level;

import dev.pumpkin.shim.Unimplemented;

public class Ticket {

    public Ticket(TicketType type, int ticketLevel) {
    }

    private Ticket(TicketType type, int ticketLevel, long ticksLeft) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/server/level/Ticket.toString:()Ljava/lang/String;");
    }

    public TicketType getType() {
        throw Unimplemented.forMember("net/minecraft/server/level/Ticket.getType:()Lnet/minecraft/server/level/TicketType;");
    }

    public int getTicketLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/Ticket.getTicketLevel:()I");
    }

    public Ticket() {
    }
}
