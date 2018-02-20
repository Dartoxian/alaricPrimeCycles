package com.dartoxia.alaric.primes.filters;

import java.util.function.Predicate;

public class LessThan1Million implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s.length() < 7;
    }
}
