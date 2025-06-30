package com.streams.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectorsDemo {
    public static void main(String[] args) {
        //Collectors is a utility class
        //provides a set of methods to create common colectors

        //1. Collecting to a List
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        List<String> namesWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        //2. Collecting to a Set
        List<Integer> nums = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        Set<Integer> uniqueInt = nums.stream().collect(Collectors.toSet());

        //3. Collecting to a new Collection
        ArrayDeque<String> arrayDequeue = names.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));

        //4. Joining Strings
        String concatenatedStrings = names.stream().map(String::toUpperCase).collect(Collectors.joining());
        System.out.println("concatenatedStrings: "+concatenatedStrings);
        String concatenatedStringsWithSeparator = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println("concatenatedStringsWithSeparator: "+concatenatedStringsWithSeparator);

        //5. Summarizing Data
        //Generate Statistical summary Data (count, sum, average, min, max)
        IntSummaryStatistics statistics = nums.stream().collect(Collectors.summarizingInt(x -> x));
        System.out.println("Count: "+statistics.getCount());
        System.out.println("Sum: "+statistics.getSum());
        System.out.println("Average: "+statistics.getAverage());
        System.out.println("Min: "+statistics.getMin());
        System.out.println("Max: "+statistics.getMax());

        //6. Collecting Average
        Double collectedAverage = nums.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println("collectedAverage : "+collectedAverage);

        //7. CollectingCount
        Long collectedCount = nums.stream().collect(Collectors.counting());
        System.out.println("collectedCount : "+collectedCount);

        //8. Grouping
        List<String> words = Arrays.asList("hello", "world", "java", "stream", "collecting");
        Map<Integer, List<String>> collectMap = words.stream().collect(Collectors.groupingBy(x -> x.length()));
        System.out.println("collectMap : "+collectMap);
        //if you want to further process individual groups
        Map<Integer, String> collectAfterJoining = words.stream().collect(Collectors.groupingBy(String::length, Collectors.joining()));
        System.out.println("collectAfterJoining: "+collectAfterJoining);
        Map<Integer, Long> collectAfterCounting = words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("collectAfterCounting: "+collectAfterCounting);
    }
}
