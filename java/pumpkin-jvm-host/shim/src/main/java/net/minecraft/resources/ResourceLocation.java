package net.minecraft.resources;

/** A namespaced id. Value type: never crosses the bridge. */
public record ResourceLocation(String namespace, String path) {
    public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
        return new ResourceLocation(namespace, path);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
