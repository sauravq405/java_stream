package com.streams.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
        //A type of stream that enables parallel processing of elements
        //Allowing multiple threads to allow parts of the threads simultaneously
        //This can significantly improve performance for large datasets
        //Workload is distributed among different threads
        long startTime = System.currentTimeMillis(); //counts milliseconds till January 1, 1970 UTC till today
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000).toList();
        List<Integer> factorialList = list.stream().map(ParallelStream::factorial).toList();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with sequential stream is "+(endTime - startTime)+" ms");

        startTime = System.currentTimeMillis(); //counts milliseconds till January 1, 1970 UTC till today
        factorialList = list.parallelStream().map(ParallelStream::factorial).toList();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken with parallel stream is "+(endTime - startTime)+" ms");

        //ParallelStream is good for CPU Intensive tasks and large data sets, where tasks are independent
        //overhead for simple tasks or small datasets

        //Cumulative Sum
        //[1, 2, 3, 4, 5] => [1, 3, 6, 10, 15]
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //int sum = 0; --> this is not compiling inside lambda because it is not final
        //it requires to be final because it needs to ensure consistency
        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> cumulativeSum = numbers.parallelStream().map(x -> {
            //int i = x + sum;
            //sum = i;
            //return i;
            return sum.addAndGet(x);
        }).collect(Collectors.toList());
        numbers.parallelStream().map(sum::addAndGet).collect(Collectors.toList());//alternate
        System.out.println("Expected Cumulative Sum with parallel stream: [1, 3, 6, 10, 15]");
        System.out.println("Actual Cumulative Sum with parallel stream: "+cumulativeSum);
        //incorrect result due to non-independent tasks
        AtomicInteger sumTwo = new AtomicInteger(0);
        System.out.println("Actual Cumulative Sum with stream: "+numbers.stream().map(sumTwo::addAndGet).toList());

        //sequential stream
        //if after performing a parallel operation you want to perform a sequential operation
        //you can do using sequential
        AtomicInteger sumThree = new AtomicInteger(0);
        list.parallelStream().map(ParallelStream::factorial).sequential().map(sumThree::addAndGet).toList();


    }

    private static int factorial(int n){
        int result = 1;
        for (int i = 2; i <= n ; i++) {
            result *= i;
        }
        return result;
    }

    private static int factorialRecursion(int n){
        if (n == 0) return 1;
        return n * factorialRecursion(n - 1);
    }

}
