package net.minecraft.resources;

/** A namespaced id. Value type: never crosses the bridge. */
public record Identifier(String namespace, String path) {
    public static Identifier fromNamespaceAndPath(String namespace, String path) {
        return new Identifier(namespace, path);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
