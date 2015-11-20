package com.thoughtworks.wjlin;

import rx.Observable;
import rx.functions.Func1;

public class OperatorOfReactiveX {

    public static void mapDemo1() {
        Observable.just("Hello").map(s -> {
           return s + ", Wenjun";
        }).subscribe(s1 -> {
            System.out.println(s1);
        });
    }

    public static void mapDemo2() {
        Observable.just("Hello", "Bye").map(s -> {
            return s + ", Wenjun";
        }).subscribe(s1 -> {
            System.out.println(s1);
        });
    }

    public static void mapDemo3() {
        Observable.from(new String[]{"Hello", "Bye", "Welcome"}).map(s -> {
            return s + ", Wenjun";
        }).subscribe(s1 -> {
            System.out.println(s1);
        });
    }

    public static void mapDemo4() {
        Observable.from(new String[] {"Hello", "Bye"}).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(s1 -> {
            System.out.println(s1);
        });
    }

    public static void mapDemo5() {
        Observable.from(new String[] {"Hello", "Bye"}).map(s -> {
            return s.hashCode();
        }).subscribe(s1 -> {
            System.out.println(s1);
        });
    }

    public static void mapDemo6() {
        Observable.from(new Integer[] {1, 2, 3, 4, 5}).map(s -> {
            return s * 2;
        }).doOnNext(s -> {
            System.out.println(s);
        }).filter(s->{
            return s > 5;
        }).subscribe(s1 -> {
            System.out.println(s1);
        });
    }


}
