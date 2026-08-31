package net.neoforged.fml;

/** Which logical side code runs on. A dedicated server is always the server side. */
public enum LogicalSide {
    CLIENT, SERVER;

    public boolean isClient() {
        return this == CLIENT;
    }

    public boolean isServer() {
        return this == SERVER;
    }
}
