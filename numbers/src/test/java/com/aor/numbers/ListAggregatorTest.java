package com.aor.numbers;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {

    public ListAggregator prep() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);

        ListAggregator aggregator = new ListAggregator(list);

        return aggregator;
    }

    @Test
    public void sum() {

        ListAggregator aggregator = prep();

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public void max() {

        ListAggregator aggregator = prep();

        int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void bugReport7263() {
        //1st Step - Arrange
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-4);
        list.add(-5);

        ListAggregator aggregator = new ListAggregator(list);

        //2nd Step - Act
        int max = aggregator.max();

        //3rd Step - Assert
        assertEquals(-1, max);
    }

    @Test
    public void min() {

        ListAggregator aggregator = prep();

        int min = aggregator.min();

        assertEquals(1, min);

    }

    static class Stub implements IListDeduplicator{

        private List<Integer> list;

        public Stub(List<Integer> list) {
            this.list = list;
            this.list = this.deduplicate();
        }

        @Override
        public List<Integer> deduplicate() {
            return new ArrayList<>(new HashSet<>(list));
        }
    }

    @Test
    public void distinct() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);

        ListAggregator aggregator = new ListAggregator(list);

        IListDeduplicator deduplicator = Mockito.mock(IListDeduplicator.class);


        int distinct = aggregator.distinct(new Stub(list));

        assertEquals(4, distinct);
    }

    @Test
    public void bugReport8726() {
        //1st Step - Arrange
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        ListAggregator aggregator = new ListAggregator(list);

        //2nd Step - Act
        int distinct = aggregator.distinct(new Stub(list));

        //3rd Step - Assert
        assertEquals(3, distinct);
    }
}