package net.neoforged.neoforge.common.util;

import java.util.List;
import java.util.function.Predicate;
import dev.pumpkin.shim.Unimplemented;

public class RecipeMatcher {

    // Pumpkin divergence: NeoForge's algorithm, reimplemented -- a backtracking perfect
    // matching of inputs to ingredient tests. Returns which test each input satisfies,
    // or null when no assignment covers every test, which is what "the recipe does not
    // match" means for shapeless-style machines.
    public static <T> int[] findMatches(List<T> inputs, List<? extends Predicate<T>> tests) {
        if (inputs.size() != tests.size()) {
            return null;
        }
        int size = tests.size();
        boolean[][] accepts = new boolean[size][size];
        for (int input = 0; input < size; input++) {
            for (int test = 0; test < size; test++) {
                accepts[input][test] = tests.get(test).test(inputs.get(input));
            }
        }
        int[] assigned = new int[size];
        java.util.Arrays.fill(assigned, -1);
        boolean[] used = new boolean[size];
        if (assign(accepts, assigned, used, 0, size)) {
            return assigned;
        }
        return null;
    }

    private static boolean assign(boolean[][] accepts, int[] assigned, boolean[] used,
            int input, int size) {
        if (input == size) {
            return true;
        }
        for (int test = 0; test < size; test++) {
            if (!used[test] && accepts[input][test]) {
                used[test] = true;
                assigned[input] = test;
                if (assign(accepts, assigned, used, input + 1, size)) {
                    return true;
                }
                used[test] = false;
                assigned[input] = -1;
            }
        }
        return false;
    }

    public RecipeMatcher() {
    }
}
