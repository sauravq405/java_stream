package com.streams.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        //Stream is a feature introduced in Java 8
        //helps us to process Collections in functional declarative manner
        //Simplify data processing
        //Embrace functional programming
        //Improve readability and maintainability
        //Enable easy parallelism (an alternative to Multi-threading)

        //How to use Streams?
        //1. Source
        //2. Intermediate Operations
        //3. Terminal Operations

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //Stream -> if I have to perform declarative operations on any collections, I would convert that into stream.
        //Stream -> sequence of elements that supports various operations.
        //Stream -> sequence of elements that supports functional or declarative programming
        Stream<Integer> stream = numbers.stream();
        //count even numbers in the list
        long countOfEvens = numbers.stream() //Source
                .filter(x -> x % 2 == 0) //Intermediate Operations
                .count(); //Terminal Operations

        //Creating Streams
        //1. From Collections
        List<Double> doubles = Arrays.asList(1.2, 3.5, 7.8);
        Stream<Double> doubleStream = doubles.stream();

        //2. From Arrays
        String[] wordArray = {"I", "Am", "Indian"};
        Stream<String> stringArrayStream = Arrays.stream(wordArray);

        //3. Using Stream.of()
        Stream<String> ofStream = Stream.of("A", "B", "C");

        //4. Infinite Stream
        Stream<Integer> infinite = Stream.generate(() -> 1);
        Stream<Integer> finite = Stream.generate(() -> 1).limit(100);
        Stream.iterate(1, x -> x + 1); //infinite stream
        //starting point: 1
        //next: 1 + 1
        //next: 2 + 1
        //next: 3 + 1
        //and so on...
        List<Integer> integerList = Stream.iterate(1, x -> x + 1).limit(100).collect(Collectors.toList());
        System.out.println(integerList);
    }
}
