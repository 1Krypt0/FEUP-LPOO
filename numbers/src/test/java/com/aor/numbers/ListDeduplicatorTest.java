package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListDeduplicatorTest {

    @Test
    public void deduplicate() {

        class Stub implements GenericListSorter{
            @Override
            public List<Integer> sort(List<Integer> list) {
                Collections.sort(list);
                return list;
            }
        }

        List<Integer> list = Arrays.asList(1,2,4,2,5);
        List<Integer> expected = Arrays.asList(1,2,4,5);

        ListDeduplicator deduplicator = new ListDeduplicator();
        GenericListSorter sorter = new ListSorter();
        List<Integer> distinct = deduplicator.deduplicate(list,sorter);

        Assertions.assertEquals(expected, distinct);
    }

    @Test
    public void distinct_bug_8726() {
        List<Integer> list = Arrays.asList(1, 2, 4, 2);
        List<Integer> expected = Arrays.asList(1, 2, 4);

        class Stub implements GenericListSorter{
            @Override
            public List<Integer> sort(List<Integer> list) {
                Collections.sort(list);
                return list;
            }
        }

        GenericListDeduplicator deduplicator = new ListDeduplicator();
        GenericListSorter sorter = new ListSorter();
        List<Integer> distinct = deduplicator.deduplicate(list, sorter);

        Assertions.assertEquals(expected, distinct);
    }
}
