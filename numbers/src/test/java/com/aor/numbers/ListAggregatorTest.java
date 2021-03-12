package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ListAggregatorTest {

    public List<Integer> prep() {
        return Arrays.asList(1, 2, 4, 2 ,5);
    }

    @Test
    public void sum() {

        ListAggregator aggregator = new ListAggregator();
        int sum = aggregator.sum(prep());

        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {

        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(prep());

        Assertions.assertEquals(5, max);
    }

    @Test
    public void  max_bug_7263() {
        List<Integer> list = Arrays.asList(-1, -4, -5);

        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(list);

        Assertions.assertEquals(-1, max);
    }

    @Test
    public void min() {

        ListAggregator aggregator = new ListAggregator();
        int min = aggregator.min(prep());

        Assertions.assertEquals(1, min);
    }

    @Test
    public void distinct() {

        class Stub implements GenericListDeduplicator {
            @Override
            public List<Integer> deduplicate(List<Integer> list, GenericListSorter sorter) {
                return new ArrayList<>(new HashSet<>(list));
            }
        }

        ListAggregator aggregator = new ListAggregator();
        ListDeduplicator deduplicator = new ListDeduplicator();
        GenericListDeduplicator deduplicator1 = new Stub();
        int distinct = aggregator.distinct(prep(), deduplicator);

        Assertions.assertEquals(4, distinct);
    }

    @Test
    public void distinct_bug_8726() {
        List<Integer> list = Arrays.asList(1, 2, 4, 2);

        class Stub implements GenericListDeduplicator {
            @Override
            public List<Integer> deduplicate(List<Integer> list, GenericListSorter sorter) {
                return new ArrayList<>(new HashSet<>(list));
            }
        }

        ListAggregator aggregator = new ListAggregator();
        ListDeduplicator deduplicator = new ListDeduplicator();
        GenericListDeduplicator deduplicator1 = new Stub();
        int distinct = aggregator.distinct(list, deduplicator1);

        Assertions.assertEquals(3, distinct);
    }
}
