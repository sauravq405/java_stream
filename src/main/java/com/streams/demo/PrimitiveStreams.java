package com.streams.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

public class PrimitiveStreams {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(numbers);

        Integer[] wrapperNumbers = {1, 2, 3, 4, 5};
        Stream<Integer> stream = Arrays.stream(wrapperNumbers);

        //IntStream is an example of primitive stream

        //IntStream has many useful methods
        IntStream range = IntStream.range(1, 5);//1 is inclusive, 5 is exclusive
        List<Integer> intList = range.boxed().collect(Collectors.toList());
        System.out.println("intList: "+intList);

        IntStream rangeClosed = IntStream.rangeClosed(1, 5);//1 is inclusive, 5 is exclusive
        List<Integer> intListClosed = rangeClosed.boxed().collect(Collectors.toList());
        System.out.println("intListClosed: "+intListClosed);

        IntStream.of(1, 2, 3);

        DoubleStream doubles = new Random().doubles(5);
        List<Double> randomDouble = doubles.boxed().collect(Collectors.toList());
        System.out.println("randomDouble: "+randomDouble);

        IntStream ints = new Random().ints(5);
        List<Integer> randomInts = ints.boxed().collect(Collectors.toList());
        System.out.println("randomInts: "+randomInts);

        LongStream longs = new Random().longs(100);
        long longSum = longs.sum();
        System.out.println("longSum: "+longSum);

        System.out.println("Number of digits in "+longSum+" is: " + (int) (Math.log10(longSum)+1));

        //longs.summaryStatistics();

        LongStream newLongs = new Random().longs(5);
        IntStream intStreamFromMap = newLongs.mapToInt(x -> (int) x + 1);
        List<Integer> integerListFromMap = intStreamFromMap.boxed().collect(Collectors.toList());
        System.out.println("integerListFromMap: "+integerListFromMap);

    }
}
