package com.aor.numbers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListFiltererTest {

    @Test
    public void filterPositive() {
        List<Integer> list = new ArrayList<>();

        list.add(-1);
        list.add(-7);
        list.add(4);
        list.add(0);
        list.add(3);
        list.add(-6);

        List<Integer> expected = new ArrayList<>();

        expected.add(4);
        expected.add(3);

        ListFilterer filterer = new ListFilterer(list);
        List<Integer> filtered = filterer.filter(new PositiveFilter());

        assertEquals(expected, filtered);
    }

    @Test
    public void filterDivisible() {
        List<Integer> list = new ArrayList<>();

        list.add(2);
        list.add(3);
        list.add(4);
        list.add(0);
        list.add(3);
        list.add(-6);

        List<Integer> expected = new ArrayList<>();

        expected.add(3);
        expected.add(0);
        expected.add(3);
        expected.add(-6);

        ListFilterer filterer = new ListFilterer(list);
        List<Integer> filtered = filterer.filter(new DivisibleByFilter(3));

        assertEquals(expected, filtered);
    }
}
