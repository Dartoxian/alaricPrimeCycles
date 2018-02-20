package com.dartoxia.alaric.primes;

import com.dartoxia.alaric.primes.data.PrimeDirectoryLoader;
import com.dartoxia.alaric.primes.filters.NoFives;
import com.dartoxia.alaric.primes.filters.OddDigitsOnly;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

public class PrimeCycles {

    private static final Logger log = LoggerFactory.getLogger(PrimeCycles.class);

    public static void main(String... args) {
        String primePath = args[0];

        Collection<Predicate<String>> filters = ImmutableSet.<Predicate<String>>builder()
                .add(new NoFives())
                .add(new OddDigitsOnly())
                .build();

        Set<String> primes = PrimeDirectoryLoader.loadPrimeDirectory(primePath, filters);
        log.info("We have loaded " + primes.size() + " possible primes");

        detectCycles(primes);
    }

    public static void detectCycles(Set<String> primes) {
        Set<String> cyclingPrimes = Sets.newHashSet();

        NEXT_PRIME: for (String prime: primes) {
            if (cyclingPrimes.contains(prime)) {
                log.info("Already confirmed "+prime+" is a cycle, so skipping");
                continue;
            }
            Set<String> cycles = Sets.newHashSet();
            for (int i = 1; i < prime.length(); i++) {
                String cycle = prime.substring(i) + prime.substring(0, i);
                if (! primes.contains(cycle)) {
                    continue NEXT_PRIME;
                }
                cycles.add(cycle);
            }
            log.info(prime + " cycles through primes " + Joiner.on(", ").join(cycles));
            cyclingPrimes.addAll(cycles);
            cyclingPrimes.add(prime);
        }
    }
}
