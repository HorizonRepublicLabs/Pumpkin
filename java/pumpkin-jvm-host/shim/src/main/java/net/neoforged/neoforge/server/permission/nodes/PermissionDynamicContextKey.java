package net.neoforged.neoforge.server.permission.nodes;

import java.util.function.Function;

public record PermissionDynamicContextKey<T>(Class<T> typeToken, String name, Function<T, String> serializer) {
}
