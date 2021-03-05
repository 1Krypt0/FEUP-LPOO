package com.aor.numbers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class ListDeduplicatorTest {

    static class Stub implements IListSorter{

        private List<Integer> list;

        public Stub(List<Integer> list) {
            this.list = list;
            Collections.sort(this.list);
        }

        @Override
        public List<Integer> sort() {
            return list;
        }
    }

    @Test
    public void deduplicate() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(5);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(new Stub(list));

        assertEquals(expected, distinct);
    }

    @Test
    public void deduplicater() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate();

        assertEquals(expected,distinct);
    }
}