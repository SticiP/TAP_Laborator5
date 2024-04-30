package org.example.tap_laborator5;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {

    public static Set<Integer> concatenateSets(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersectSets(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }
}
