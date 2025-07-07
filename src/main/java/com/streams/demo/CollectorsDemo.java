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
        TreeMap<Integer, Long> collectAsImplementationCollection = words.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        System.out.println("collectAsImplementationCollection: "+collectAsImplementationCollection);

        //9. Partitioning elements
        //Divides elements into two groups(true and false) based on predicates
        Map<Boolean, List<String>> partitionedByPredicate = words.stream().collect(Collectors.partitioningBy(x -> x.length() > 5));
        System.out.println("partitionedByPredicate: "+partitionedByPredicate);

        //10. Mapping and Collecting
        //Applies a mapping(or transformation) function before collecting
        List<String> mappedAndCollected = words.stream().collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.toList()));
        System.out.println("mappedAndCollected: "+mappedAndCollected);


        //EXAMPLES
        //Example 1: Collecting names by length
        List<String> list1 = Arrays.asList("Anna", "Bob", "Alexander", "Brian", "Alice");
        Map<Integer, List<String>> collectedNamesByLength = list1.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("collectedNamesByLength: "+collectedNamesByLength);

        //Example 1: Counting word occurrences
        String sentence = "hello world java hello world java hello";
        Map<String, Long> wordCountInSentence = Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println("wordCountInSentence: "+wordCountInSentence);

        //Example 3: Partitioning even and odd numbers
        Map<Boolean, List<Integer>> partitionedEvenOdd = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println("partitionedEvenOdd: "+partitionedEvenOdd);

        //Example 4: Summing values in a map
        Map<String, Integer> items = new HashMap<>();
        items.put("Apple", 20);
        items.put("Banana", 30);
        items.put("Orange", 40);
        Optional<Integer> sumByReduce = items.values().stream().reduce(Integer::sum);
        Integer sumByCollect = items.values().stream().collect(Collectors.summingInt(x -> x));
        System.out.println("sumByCollect: "+sumByCollect);

        //Example 5: Creating Map from Stream of elements
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
        Map<String, Integer> collectedMap = fruits.stream().collect(Collectors.toMap(x -> x.toUpperCase(), x -> x.length()));
        System.out.println("collectedMap: "+collectedMap);

        //Example 6: Count frequency of words (Merge function)
        List<String> moreFruits = Arrays.asList("Apple", "Banana", "Orange", "Apple", "Orange", "Banana");
        Map<String, Integer> mapMergeFunction = moreFruits.stream().collect(Collectors.toMap(k -> k, v -> 1, (x, y) -> x + y));
        System.out.println("mapMergeFunction: "+mapMergeFunction);


    }
}
