package com.dartoxia.alaric.primes.filters;

import java.util.function.Predicate;

/**
 * Any 5's will eventually cycle to be divisible by 5.
 */
public class NoFives implements Predicate<String> {
    @Override
    public boolean test(String aLong) {
        return !aLong.contains("5");
    }
}
