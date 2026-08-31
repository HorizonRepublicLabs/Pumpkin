package net.neoforged.neoforge.common.world.chunk;

import net.minecraft.server.level.ServerLevel;

public interface LoadingValidationCallback {

    void validateTickets(ServerLevel level, TicketHelper ticketHelper);
}
