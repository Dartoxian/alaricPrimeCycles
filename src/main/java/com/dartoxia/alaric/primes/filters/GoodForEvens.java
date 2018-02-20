package com.dartoxia.alaric.primes.filters;

import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.function.Predicate;

/**
 * These numbers are more than 80% Even digits!
 */
public class GoodForEvens implements Predicate<String> {

    private Set<Character> oddNumbers = ImmutableSet.<Character>builder()
            .add('1')
            .add('3')
            .add('5')
            .add('7')
            .add('9')
            .build();

    @Override
    public boolean test(String input) {
        char[] chars = input.toCharArray();
        double numEvens = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!oddNumbers.contains(chars[i])) {
                numEvens++;
            }
        }
        return numEvens / chars.length > 0.8d;
    }

}
