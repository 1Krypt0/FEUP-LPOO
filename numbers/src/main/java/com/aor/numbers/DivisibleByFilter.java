package com.aor.numbers;

public class DivisibleByFilter implements GenericListFilter {

    private final int filter;

    public DivisibleByFilter(int filter) {
        this.filter = filter;
    }

    @Override
    public boolean accept(Integer number) {
        return number % filter == 0;
    }
}
