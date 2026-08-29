package dev.pumpkin.shim;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Instances of shimmed interfaces whose every method throws {@link Unimplemented}.
 *
 * <p>Exists because of what a holder class does without one. A holder's field initializers
 * call code the shim does not have, so the generator strips them, assigns {@code null}, and
 * adds a static initializer that throws — which stops the mod at class-initialisation with a
 * key naming only {@code net/minecraft/core/registries/BuiltInRegistries} and no member. That
 * is true but useless: it says the holder is empty, not which of its six registries the mod
 * wanted or what it tried to do with it.
 *
 * <p>A stub instance moves the failure to the point of use. {@code BuiltInRegistries.ITEM}
 * then hands back something that survives being passed around and fails only when called,
 * with {@code net/minecraft/core/DefaultedRegistry.getKey:(...)} — a manifest key naming
 * exactly what to implement. The mod also gets further, so one boot reveals several missing
 * members instead of one.
 *
 * <p>It is not a plausible default. Nothing here returns a convincing zero; every call still
 * stops the mod. The change is only in how precisely it says why.
 */
public final class Stubs {
    private Stubs() {
    }

    /**
     * A stub implementing {@code iface}, throwing {@link Unimplemented} on every call.
     *
     * @param iface  the shimmed interface to implement; must be an interface
     * @param owner  its internal name, e.g. {@code net/minecraft/core/Registry}, used to
     *               build the member key so the failure joins against the committed manifest
     */
    public static <T> T of(Class<T> iface, String owner) {
        if (!iface.isInterface()) {
            throw new IllegalArgumentException(iface.getName() + " is not an interface");
        }
        InvocationHandler handler = (proxy, method, args) -> {
            // equals, hashCode and toString are answered rather than thrown: a stub that
            // cannot be compared or printed breaks collections and debuggers without telling
            // anyone anything about the shim. They are facts about the proxy, not about
            // Minecraft, which is the same distinction FMLEnvironment draws.
            switch (method.getName()) {
                case "equals" -> {
                    return args != null && args.length == 1 && proxy == args[0];
                }
                case "hashCode" -> {
                    return System.identityHashCode(proxy);
                }
                case "toString" -> {
                    return "stub " + owner;
                }
                default -> throw Unimplemented.forMember(
                        owner + "." + method.getName() + ":" + descriptorOf(method));
            }
        };
        return iface.cast(
                Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] {iface}, handler));
    }

    /** The JVM descriptor for a method, so the key matches the manifest's spelling exactly. */
    private static String descriptorOf(Method method) {
        StringBuilder descriptor = new StringBuilder("(");
        for (Class<?> parameter : method.getParameterTypes()) {
            descriptor.append(typeDescriptor(parameter));
        }
        return descriptor.append(')').append(typeDescriptor(method.getReturnType())).toString();
    }

    private static String typeDescriptor(Class<?> type) {
        if (type.isArray()) {
            return "[" + typeDescriptor(type.getComponentType());
        }
        if (!type.isPrimitive()) {
            return "L" + type.getName().replace('.', '/') + ";";
        }
        return switch (type.getName()) {
            case "void" -> "V";
            case "boolean" -> "Z";
            case "byte" -> "B";
            case "char" -> "C";
            case "short" -> "S";
            case "int" -> "I";
            case "long" -> "J";
            case "float" -> "F";
            default -> "D";
        };
    }
}
