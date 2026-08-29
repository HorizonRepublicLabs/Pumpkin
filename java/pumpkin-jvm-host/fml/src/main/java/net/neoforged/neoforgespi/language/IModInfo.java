package net.neoforged.neoforgespi.language;

/**
 * Hand-written, not generated: {@code neoforgespi} is published as a separate NeoForge
 * artifact whose sources are not in the decompiled tree. On the generator's "no source
 * found" list; do not delete it as un-regenerable.
 *
 * <p>One mod's entry in a mod file. Only {@code getDisplayName} is declared, which is all
 * the manifest records the mods calling -- MysticalAgriculture puts the owning mod's name
 * in an item tooltip. An interface with no body to stub: there is nothing here to throw.
 */
public interface IModInfo {
    String getDisplayName();
}
