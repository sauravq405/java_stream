package com.streams.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluationDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        //filtering a selecting names
        Stream<String> stream = names.stream().filter(x -> {
            System.out.println("Filtering names: " + x);
            return x.length() > 3;
        });
        System.out.println("Before terminal operation");
        List<String> result = stream.collect(Collectors.toList());
        System.out.println("After terminal operation");
        System.out.println(result);

        //Filtering names: will not be printed before "Before terminal operation"
        //which demonstrates the fact that intermediate operations are invoked only when terminal operations are invoked

    }
}
