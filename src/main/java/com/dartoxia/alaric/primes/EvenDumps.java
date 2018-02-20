package com.dartoxia.alaric.primes;

import com.dartoxia.alaric.primes.data.PrimeDirectoryLoader;
import com.dartoxia.alaric.primes.filters.GoodForEvens;
import com.dartoxia.alaric.primes.filters.HasEveryEven;
import com.dartoxia.alaric.primes.filters.LessThan1Million;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class EvenDumps {

    private static final Logger log = LoggerFactory.getLogger(PrimeCycles.class);

    public static void main(String... args) {
        String primePath = args[0];

        Collection<Predicate<String>> filters = ImmutableSet.<Predicate<String>>builder()
                .add(new LessThan1Million())
                //.add(new GoodForEvens())
                .add(new HasEveryEven())
                .build();

        Set<String> primes = PrimeDirectoryLoader.loadPrimeDirectory(primePath, filters);
        log.info("We have loaded " + primes.size() + " possible primes");

        List<String> primeList = Lists.newArrayList(primes);
        primeList.sort(Comparator.comparingLong(Long::parseLong));

        for (int i = 0; i < primeList.size(); i++) {
            System.out.print(primeList.get(i) + "\t");
            if (i % 10 == 9) {
                System.out.println();
            }
        }
    }
}