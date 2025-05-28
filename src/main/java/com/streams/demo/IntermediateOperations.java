package com.streams.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperations {
    public static void main(String[] args) {
        //Intermediate operations transform one stream to another stream
        //They are lazy, meaning they are not invoked unless a terminal operation is invoked
        List<String> names = Arrays.asList("Akshit", "Vinit", "Harshit", "Shobhit", "Arnab");

        //1. filter
        Stream<String> filteredStream = names.stream().filter(x -> x.startsWith("A"));
        //no filtering at this point
        //it will be invoked when you do a terminal operation
        long count = names.stream().filter(x -> x.startsWith("A")).count();
        System.out.println("Number of Students having name starting with A: " + count);

        //2. map
        Stream<String> mappedStream = names.stream().map(x -> x.toUpperCase());
        Stream<String> mappedStreamWithMethodReference = names.stream().map(String::toUpperCase);

        //3. sorted
        Stream<String> sortedStream = names.stream().sorted();
        Stream<String> sortedStreamUsingComparator = names.stream().sorted((a, b) -> a.length() - b.length());

        //4. distinct
        names.stream().filter(x -> x.startsWith("A")).distinct().count();

        //5. limit
        long infinteCount = Stream.iterate(1, x -> x + 1).limit(100).count();
        System.out.println(infinteCount);

        //6. skip
        long infinteCountWithSkip = Stream.iterate(1, x -> x + 1).skip(10).limit(100).count();
        System.out.println(infinteCountWithSkip);//it will still print 100, but the starting point will be from 11

        //7. peek
        Stream.iterate(1, x -> x + 1).skip(10).limit(100).peek(System.out::println).count();
        //peek() is similar to forEach() but it is a intermediate operation

        //8. flatMap
        //Handle stream of streams
        //Handle collections, list, arrays where each element is a collection
        //Transform and flatten at the same time
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape")
        );
        System.out.println("listOfLists.get(1).get(1): "+listOfLists.get(1).get(1));
        List<String> singleList = listOfLists.stream().flatMap(x -> x.stream()).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("singleList: "+singleList);
        List<String> singleListSorted = listOfLists.stream().flatMap(x -> x.stream()).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println("singleListSorted: "+singleListSorted);
        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "flat map is useful"
        );
        List<String> commaSeparated = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("commaSeparated: "+commaSeparated);


    }
}
