package com.dartoxia.alaric.primes.data;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class PrimeDirectoryLoader {

    public static Set<String> loadPrimeDirectory(String directory, Collection<Predicate<String>> filters) {
        File dir = new File(directory);
        List<File> files = Lists.newArrayList(dir.listFiles());

        Set<String> result = Collections.synchronizedSet(Sets.newHashSet());
        files.parallelStream().forEach(file -> result.addAll(PrimeFileLoader.read(file, filters)));

        return ImmutableSet.copyOf(result);
    }
}
