package net.minecraft.core;

import java.util.Optional;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.Identifier;

public class RegistrySynchronization {

    public record PackedRegistryEntry(Identifier id, Optional<Tag> data) {
    }

    public RegistrySynchronization() {
    }
}
