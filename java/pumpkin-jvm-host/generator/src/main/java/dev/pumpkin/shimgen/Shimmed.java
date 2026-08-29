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
     * Only these packages are shimmed; the mod's own classes and the JDK are not.
     * Callers pass an already-{@link #outerOf}'d name, matching {@code JarScanner}'s
     * and {@code MixinScanner}'s original convention exactly: this method does not
     * strip {@code $} itself.
     */
    static boolean isShimmed(String internalName) {
        return internalName.startsWith("net/minecraft/") || internalName.startsWith("net/neoforged/");
    }
}
