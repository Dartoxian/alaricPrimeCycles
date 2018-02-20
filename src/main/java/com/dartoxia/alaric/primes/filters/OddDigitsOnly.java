package com.dartoxia.alaric.primes.filters;

import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Any non odd digits will eventually cycle to be even
 */
public class OddDigitsOnly implements Predicate<String> {

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
        for (int i = 0; i < chars.length; i++) {
            if (!oddNumbers.contains(chars[i])) {
                return false;
            }
        }
        return true;
    }
}
