package com.thoughtworks.wjlin;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class Main {


    public static void main(String args[]) {
        Main.standObserver();
        Main.lambdaObserver();
        Main.simpleObserver();
        Main.observableWithAction();
        Main.observableWithLambdaAction();

        OperatorOfReactiveX.mapDemo1();
        OperatorOfReactiveX.mapDemo2();
        OperatorOfReactiveX.mapDemo3();
        OperatorOfReactiveX.mapDemo4();
        OperatorOfReactiveX.mapDemo5();
        OperatorOfReactiveX.mapDemo6();

        HttpRequestWithReactiveX.get();
        HttpRequestWithReactiveX.post();
    }

    private static void standObserver() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello,world!");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(subscriber);
    }

    private static void lambdaObserver() {
        Observable.just("lambdaObserver: Hello,world!").subscribe(s -> {
            System.out.println(s);
        });
    }

    private static void simpleObserver() {
        Observable<String> observable = Observable.just("SimpleObserver: Hello,world!");

        Action1<String> action1 =new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(action1);
    }

    private static void observableWithAction() {
        Integer[] data = new Integer[] {1, 2, 3, 4, 5, 6};

        Observable.from(data).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                System.out.println(i);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("Error occur");
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("This observable is finished");
            }
        });
    }

    private static void observableWithLambdaAction() {
        Integer[] data = new Integer[] {2, 4, 6, 8, 10, 12};

        Observable.from(data).subscribe(
                (integer) -> System.out.println(integer),
                (error) -> System.out.println("Error occur"),
                () -> System.out.println("The observableWithLambdaAction is finished")
        );
    }
}
