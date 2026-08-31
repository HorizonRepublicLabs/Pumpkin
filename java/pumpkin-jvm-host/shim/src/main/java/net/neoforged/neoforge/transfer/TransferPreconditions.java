package net.neoforged.neoforge.transfer;

import net.neoforged.neoforge.transfer.resource.Resource;

public class TransferPreconditions {

    protected TransferPreconditions() {
    }

    // Pumpkin divergence: NeoForge's own bodies -- argument validation, nothing else.
    public static void checkNonEmpty(Resource resource) {
        if (resource.isEmpty()) {
            throw new IllegalArgumentException("Resource may not be empty");
        }
    }

    public static void checkNonNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value may not be negative: " + value);
        }
    }

    public static void checkNonEmptyNonNegative(Resource resource, int value) {
        checkNonEmpty(resource);
        checkNonNegative(value);
    }
}
