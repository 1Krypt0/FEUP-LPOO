package com.aor.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface GenericListSorter {
    public List<Integer> sort(List<Integer> list);
}

/**
 * An utility class to sort list of numbers.
 */
public class ListSorter implements GenericListSorter{
    /**
     * Really stupid way to sort a list.
     * @return A sorted version of the list.
     */
    public List<Integer> sort(List<Integer> list) {
        Collections.sort(list);
        return list;
    }
}
