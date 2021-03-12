package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListFiltererTest {
    @Test
    public void positiveFilter() {
        List<Integer> list = Arrays.asList(-1 , -3, 0, 1, 2, -5, 3);
        List<Integer> expected = Arrays.asList(1, 2, 3);

        GenericListFilter filter = new PositiveFilter();
        ListFilterer filterer = new ListFilterer(filter);

        List<Integer> filtered = filterer.filter(list);

        Assertions.assertEquals(expected, filtered);
    }

    @Test
    public void divisibleByFilter() {
        List<Integer> list = Arrays.asList(3, 5, 6, 9, 1, 0, 2, 2);
        List<Integer> expected = Arrays.asList(3, 6, 9, 0);

        GenericListFilter filter = new DivisibleByFilter(3);
        ListFilterer filterer = new ListFilterer(filter);

        List<Integer> filtered = filterer.filter(list);

        Assertions.assertEquals(expected, filtered);
    }
}
