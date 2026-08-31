package net.neoforged.fml;

/**
 * Marker for FML extension points. Mods reference the type when registering display
 * tests and similar extensions; the registrations themselves land in
 * {@link ModContainer}, which accepts and drops them -- extension points steer
 * launcher and client UI concerns that a headless server has no seat for.
 */
public interface IExtensionPoint {
}
