package net.neoforged.bus.api;

/** Listener ordering on the bus. Pumpkin's bus keeps registration order per priority. */
public enum EventPriority {
    HIGHEST, HIGH, NORMAL, LOW, LOWEST
}
