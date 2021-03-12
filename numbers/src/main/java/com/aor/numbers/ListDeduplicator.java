package com.aor.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface GenericListDeduplicator {
    public List<Integer> deduplicate(List<Integer> list, GenericListSorter sorter);
}

/**
 * An utility class that removes duplicate numbers
 * from a list.
 */
public class ListDeduplicator implements GenericListDeduplicator {

    /**
     * Removes duplicate numbers from a list.
     * @return A list having the same numbers as the original
     * but without duplicates. The order of the numbers might
     * change.
     */
    public List<Integer> deduplicate(List<Integer> list, GenericListSorter sorter) {

        List<Integer> sorted = sorter.sort(list);
        List<Integer> unique = new ArrayList<>();

        Integer last = null;

        for (Integer number : sorted)
            if (!number.equals(last)) {
                last = number;
                unique.add(number);
            }

        return unique;
    }
}
