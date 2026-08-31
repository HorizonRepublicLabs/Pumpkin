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
        return of(iface, owner, java.util.Map.of());
    }

    /**
     * An answer computed on every call, for values that cannot be shared across calls --
     * a {@link java.util.stream.Stream} is one-shot, so a stored one would break the
     * second caller.
     */
    public interface Dynamic {
        Object answer(Object[] args);
    }

    /**
     * A stub that answers a few methods and throws for the rest.
     *
     * <p>Some questions a stub genuinely can answer, and refusing to is worse than useless.
     * {@code BuiltInRegistries.ITEM} is handed to {@code DeferredRegister.create(Registry,
     * String)}, which needs only to ask the registry which registry it is -- a fact the stub
     * knows, because whoever built it said so. Throwing there would stop a mod over a
     * question that has an answer.
     *
     * <p>This is the same line {@code FMLEnvironment} draws: answer facts about Pumpkin,
     * throw for facts about Minecraft that Pumpkin does not have. Every method not named in
     * {@code answers} still throws.
     *
     * @param answers method name to the value it returns; overloads are not distinguished,
     *                which is sufficient for identity accessors and nothing more
     */
    public static <T> T of(Class<T> iface, String owner, java.util.Map<String, Object> answers) {
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
                default -> {
                    Object answer = answers.get(method.getName());
                    if (answer instanceof Dynamic dynamic) {
                        return dynamic.answer(args);
                    }
                    if (answer != null) {
                        return answer;
                    }
                    // A default method carries its own real implementation -- Codec.fieldOf
                    // builds a MapCodec out of `this`, and DFU composes through it at
                    // class-initialisation. Run it: the logic exists, and throwing here
                    // would stop a mod over code that is not missing. Only the abstract
                    // methods -- the ones with genuinely nothing behind them -- throw.
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(proxy, method, args);
                    }
                    throw Unimplemented.forMember(
                            owner + "." + method.getName() + ":" + descriptorOf(method));
                }
            }
        };
        return iface.cast(
                Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] {iface}, handler));
    }

    /**
     * A {@link com.mojang.serialization.Codec} that throws on any encode or decode.
     *
     * <p>For codec-typed statics the pruner stripped to {@code null}. The consumer is not a
     * mod calling a shim method but DataFixerUpper -- a real library -- dereferencing the
     * field while composing recipe codecs at class-initialisation. A null there is an NPE
     * deep in library code naming nothing; this survives composition and fails on first
     * actual serialisation, naming the field, and nothing serialises yet.
     */
    @SuppressWarnings("unchecked")
    public static <T> com.mojang.serialization.Codec<T> throwingCodec(String fieldKey) {
        return (com.mojang.serialization.Codec<T>) of(com.mojang.serialization.Codec.class, fieldKey);
    }

    /**
     * A {@link com.mojang.serialization.MapCodec} that throws on any encode or decode.
     *
     * <p>Same purpose as {@link #throwingCodec}, but {@code MapCodec} is an abstract class,
     * so no proxy: a minimal subclass whose three abstract methods throw. Concrete methods
     * like {@code forGetter} keep working, which is what lets a mod's
     * {@code RecordCodecBuilder} composition finish at class-initialisation.
     */
    public static <T> com.mojang.serialization.MapCodec<T> throwingMapCodec(String fieldKey) {
        return new com.mojang.serialization.MapCodec<>() {
            @Override
            public <O> java.util.stream.Stream<O> keys(com.mojang.serialization.DynamicOps<O> ops) {
                throw Unimplemented.forMember(fieldKey);
            }

            @Override
            public <O> com.mojang.serialization.DataResult<T> decode(
                    com.mojang.serialization.DynamicOps<O> ops,
                    com.mojang.serialization.MapLike<O> input) {
                throw Unimplemented.forMember(fieldKey);
            }

            @Override
            public <O> com.mojang.serialization.RecordBuilder<O> encode(T input,
                    com.mojang.serialization.DynamicOps<O> ops,
                    com.mojang.serialization.RecordBuilder<O> prefix) {
                throw Unimplemented.forMember(fieldKey);
            }

            @Override
            public String toString() {
                return "throwing " + fieldKey;
            }
        };
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
