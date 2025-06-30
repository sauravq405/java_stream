package com.streams.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        //1. collect
        list.stream().skip(1).collect(Collectors.toList());
        list.stream().skip(1).toList(); //new version of java, this list is unmodifiable

        //2. for each
        list.stream().forEach(x -> System.out.println(x));

        //3. reduce
        //Combines elements to produce a single result
        //takes BinaryOperator or BiFunction
        Optional<Integer> opInt = list.stream().reduce((x, y) -> x + y);
        list.stream().reduce(Integer::sum);
        System.out.println(opInt.get());

        //4. count
        list.stream().count();

        //5. anyMatch, allMatch, noneMatch
        boolean anyMatch = list.stream().anyMatch(x -> x % 2 == 0);
        boolean allMatch = list.stream().allMatch(x -> x > 0);
        boolean noneMatch = list.stream().noneMatch(x -> x < 0);

        //6. findFirst, findAny
        Optional<Integer> findFirst = list.stream().findFirst();
        Optional<Integer> findAny = list.stream().findAny();

        //7. toArray
        Object[] array = Stream.of(1, 2, 3).toArray();

        //8. Min/Max
        Optional<Integer> max1 = Stream.of(2, 44, 305).max(Comparator.naturalOrder());
        System.out.println("max1: " + max1);
        Optional<Integer> max2 = Stream.of(2, 44, 305).max((x, y) -> x - y);
        System.out.println("max2: " + max2);

        Optional<Integer> max3 = Stream.of(2, 44, 305).max(Comparator.reverseOrder());
        System.out.println("max3: " + max3);
        Optional<Integer> max4 = Stream.of(2, 44, 305).max((x, y) -> y - x);
        System.out.println("max4: " + max4);

        //Examples:Filtering and Selecting names
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        names.stream().filter(x -> x.length() > 3).toList();

        //Example: Squaring and sorting numbers
        List<Integer> sortedNumbers = list.stream().map(x -> x * x).sorted().toList();

        //Example: Summing values
        list.stream().reduce(Integer::sum);

        //Example: Counting occurrence of a character 'l'
        String sentence = "Hello world";
        char[] sentenceCharArray = sentence.toCharArray();
        //Arrays.stream(sentenceCharArray); --> compilation fails because Arrays.stream doesn't support char[]
        sentence.chars().filter(x -> x == 'l').count();

        //9. forEachOrdered

        //Example
        Stream<String> nameStream = names.stream();
        nameStream.forEach(System.out::println);
        //nameStream.map(String::toUpperCase).toList(); //EXCEPTION THROWN : IllegalStateException
        // - stream has already been operated upon or closed

        List<Integer> randomNumbers = Arrays.asList(1, 5, 16, 23, 30);
        System.out.println("Using forEach with parallel stream");
        randomNumbers.parallelStream().forEach(System.out::println);
        System.out.println("Using forEachOrdered with parallel stream");
        //observer how parallelStream is used to reuse the stream
        randomNumbers.parallelStream().forEachOrdered(System.out::println);

        //stateful & stateless operation
        //check the class LazyEvaluationDemo
    }
}
