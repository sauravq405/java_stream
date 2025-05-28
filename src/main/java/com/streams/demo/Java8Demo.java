package com.streams.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Java8Demo {
    public static void main(String[] args) {
        //Streams

        //Before java 8, code was more verbose

        //Java 8 -> minimal code, functional programming,
        // lambda expression, Streams, Date and Time API

        //lambda expression
        //lambda expression is an anonymous function
        //no name, no return type, no access modifier

        //functional interface
        //such interface which have single abstract method

        //functional interfaces can be represented with lambda expression

        //Thread t = new Thread(() -> System.out.println("hello"));
        //observe how lambda expression representation of run() method,
        //has been used to represent Runnable interface, which is a functional interface
        MathOperation addOperation = (a, b) -> a + b;
        MathOperation subtractOperation = (a, b) -> a - b;
        MathOperation productOperation = (a, b) -> a * b;
        MathOperation divisionOperation = (a, b) -> a / b;

        //observe above reference of interface is being used to hold lambda expression

        // @FunctionalInterface annotated over the interface MathOperation is not mandatory
        //It's kind of a compiler check which we give to ensure functional interface
        //if we violate "single abstract method" or add one more abstract method

        int result = addOperation.operate(1, 2);
        System.out.println("result = "+result);

        //Predicate --> Functional interface (Boolean valued function).
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println("Predicate isEven: "+isEven.test(4));
        //use predicate when you want to test a boolean condition
        Predicate<String> isStartsWithA = x -> x.toLowerCase().startsWith("a");
        System.out.println("Predicate isStartsWithA: "+isStartsWithA.test("Ankit"));
        Predicate<String> isStartsWithT = x -> x.toLowerCase().endsWith("t");
        System.out.println("Predicate isStartsWithT: "+isStartsWithT.test("Ankit"));
        Predicate<String> and = isStartsWithA.and(isStartsWithT);
        System.out.println("Predicate and: "+and.test("Ankit"));
        System.out.println("Predicate and: "+and.test("Akshay"));

        //Function --> Functional interface (Performs a task and returns a value)
        Function<Integer, Integer> doubleIt = x -> 2 * x;
        Function<Integer, Integer> trippleIt = x -> 3 * x;
        System.out.println("Function doubleIt: "+doubleIt.apply(5));
        System.out.println("Function trippleIt: "+trippleIt.apply(4));
        // and() -> first doubleIt will be applied, then trippleIt
        System.out.println("Function doubleIt & trippleIt: "+doubleIt.andThen(trippleIt).apply(100));
        // compose() -> first trippleIt will be applied, then doubleIt
        System.out.println("Function doubleIt compose trippleIt: "+doubleIt.compose(trippleIt).apply(100));
        Function<Integer, Integer> identityInt = Function.identity();
        Integer identityIntResult = identityInt.apply(5);
        System.out.println("Identity result: "+identityIntResult);

        //Consumer --> Functional interface (Performs a task and DOES NOT return a value)
        Consumer<Integer> print = x -> System.out.println("Consumer print: "+x);
        print.accept(25);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Consumer<List<Integer>> printList = x -> {
            for (int i: x){
                System.out.println("loop :"+i);
            }
        };
        printList.accept(list);

        //Supplier --> Functional interface (DOESN'T take any input BUT returns a value)
        Supplier<String> fromSupplier = () -> "Hello from supplier";
        System.out.println("Supplier: "+fromSupplier.get());

        //Combined example
        Predicate<Integer> predicate = x -> x%2 == 0;
        Function<Integer, Integer> function = x -> 2*x;
        Consumer<Integer> consumer = x -> System.out.println(x);
        Supplier<Integer> supplier = () -> 100;

        if(predicate.test(supplier.get())){
            consumer.accept(function.apply(supplier.get()));
        }

        //BiPredicate, BiConsumer, BiFunction
        BiPredicate<Integer, Integer> isSumEven = (x, y) -> (x + y) % 2 == 0;
        System.out.println("BiPredicate isSumEven: "+isSumEven.test(5,5));
        System.out.println("BiPredicate isSumEven: "+isSumEven.test(2,3));

        BiConsumer<String, Integer> appender = (x, y) -> {
            System.out.println(x+y);
        };
        appender.accept("BiConsumer appender: Hi ", 5);

        BiFunction<String, String, Integer> lengthFinder = (x, y) -> (x + y).length();
        System.out.println("BiFunction lengthFinder: "+lengthFinder.apply("Hello", "World"));

        Function<Integer, Integer> func = x -> 3 * x;
        //the above Function<> declaration can be represented as below:
        UnaryOperator<Integer> unar = x -> 3 * x;
        //UnaryOperator<Integer> represents that both input and output is Integer
        BinaryOperator<Integer> binar = (x, y) -> x + y;

        //Method Reference
        //use method without invoking
        //use in place of lambda expression
        List<String> students = Arrays.asList("Ram", "Shyam", "Ganesh");
        students.forEach(x -> System.out.println(x)); //forEach() takes a consumer
        students.forEach(System.out::println); //here :: is the method reference
        //here instead of invoking the method, method reference itself is given OR passing method itself as parameter to forEach

       //Constructor Reference
        List<String> phoneNames = Arrays.asList("A", "B", "C");
        List<MobilePhone> mobilePhonesList = phoneNames.stream().map(x -> new MobilePhone(x)).collect(Collectors.toList());
        List<MobilePhone> mobilePhones = phoneNames.stream()
                .map(MobilePhone::new) //Constructor reference
                .collect(Collectors.toList());


    }
}

class MobilePhone{
    String name;

    public MobilePhone() {
    }

    public MobilePhone(String name) {
        this.name = name;
    }
}

@FunctionalInterface
interface MathOperation{

    int operate(int a, int b);
}
