package com.aor.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface IListSorter {
    public List<Integer> sort();
}

/**
 * An utility class to sort list of numbers.
 */
public class ListSorter implements IListSorter{
    private final List<Integer> list;

    public ListSorter(List<Integer> list) {
        this.list = list;
    }

    /**
     * Really stupid way to sort a list.
     * @return A sorted version of the list.
     */
    @Override
    public List<Integer> sort() {

        List<Integer> sorted = new ArrayList<>();
        sorted.addAll(list);

        Collections.sort(sorted);

        return sorted;
    }
}
