package dev.pumpkin.shimgen;

/**
 * The naming conventions shared by everything that records references into a {@link
 * UsedSet}: which outer class a reference belongs to, and which packages are shimmed
 * at all.
 *
 * <p>Pulled out of {@link JarScanner}, {@link MixinScanner}, and {@link
 * SupertypeCloser}, which each used to carry their own copy of both methods. A pure
 * move, not a behavior change: the three copies were byte-for-byte identical.
 */
final class Shimmed {
    private Shimmed() {}

    /**
     * Strips {@code $} and everything after it, so a nested type's reference lands on
     * the outer class the generator actually emits a file for.
     */
    static String outerOf(String internalName) {
        int dollar = internalName.indexOf('$');
        return dollar < 0 ? internalName : internalName.substring(0, dollar);
    }

    /**
     * Only these packages are shimmed; the mod's own classes, third-party libraries and
     * the JDK are not. Callers pass an already-{@link #outerOf}'d name, matching {@code
     * JarScanner}'s and {@code MixinScanner}'s original convention exactly: this method
     * does not strip {@code $} itself.
     *
     * <p>The three {@code com/mojang} entries are not a special case for a library. They
     * are game code that happens to live under Mojang's own package: {@code blaze3d},
     * {@code math} and {@code realmsclient} are decompiled out of the game jar into the
     * same tree as {@code net/minecraft}, and are published nowhere a build could depend
     * on them. The rest of {@code com/mojang} -- {@code serialization}, {@code
     * datafixers}, {@code brigadier}, {@code authlib}, {@code logging} -- is the opposite:
     * real released artifacts, which the shim compiles against rather than reimplements.
     */
    static boolean isShimmed(String internalName) {
        return internalName.startsWith("net/minecraft/")
                || internalName.startsWith("net/neoforged/")
                || internalName.startsWith("com/mojang/blaze3d/")
                || internalName.startsWith("com/mojang/math/")
                || internalName.startsWith("com/mojang/realmsclient/");
    }
}
