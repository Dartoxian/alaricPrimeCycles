package com.dartoxia.alaric.primes.data;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Loads the primes from the 'primes/extract' folder in the root of the project
 *
 *
 */
public class PrimeFileLoader {

    private static final Logger log = LoggerFactory.getLogger(PrimeFileLoader.class);

    public static Set<String> read(File input, Collection<Predicate<String>> filters) {
        log.info("Reading "+input.getName());
        Set<String> result = Sets.newHashSet();
        try {
            List<String> lines = Files.readLines(input, Charsets.UTF_8);
            // The first line is a title, so always skip it.
            for (int i = 1; i < lines.size(); i++) {
                processLine(lines.get(i), filters, result);
            }
        } catch (IOException io) {
            io.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    private static void processLine(String line, Collection<Predicate<String>> filters, Set<String> resultAccumulator) {
        String[] numbers = line.split("\\s+");
        NUMBER_LOOP: for (int i = 0; i < numbers.length; i++) {
            if (! Strings.isNullOrEmpty(numbers[i])) {
                String number = numbers[i];
                for (Predicate<String> filter: filters) {
                    if (!filter.test(number)) {
                        continue NUMBER_LOOP;
                    }
                }
                resultAccumulator.add(number);
            }
        }
    }
}
