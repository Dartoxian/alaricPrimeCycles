package com.dartoxia.alaric.primes.filters;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.function.Predicate;

public class HasEveryEven implements Predicate<String> {
    private Set<Character> evenNumbers = ImmutableSet.<Character>builder()
            .add('0')
            .add('2')
            .add('4')
            .add('6')
            .add('8')
            .build();

    @Override
    public boolean test(String s) {
        Set<Character> sChars = Sets.newHashSet();
        for (Character c: s.toCharArray()) {
            sChars.add(c);
        }
        return Sets.difference(evenNumbers, sChars).isEmpty();
    }
}
