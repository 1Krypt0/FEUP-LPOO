package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

interface GenericListFilter {
    public boolean accept(Integer number);
}

public class ListFilterer {

    private final GenericListFilter filter;

    public ListFilterer(GenericListFilter filter) {
        this.filter = filter;
    }
    public List<Integer> filter(List<Integer> list) {
        List<Integer> res = new ArrayList<>();
        for (Integer integer : list) {
            if (filter.accept(integer)) res.add(integer);
        }
        return res;
    }
}
