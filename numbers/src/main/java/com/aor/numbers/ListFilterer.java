package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

interface IListFilter {
    public boolean accept(Integer number);
}


public class ListFilterer {

    private final List<Integer> list;

    public ListFilterer(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> filter(IListFilter filter) {
        List<Integer> res = new ArrayList<>();

        for (Integer i : list) {
            if (filter.accept(i))
                res.add(i);
        }
        return res;
    }
}
