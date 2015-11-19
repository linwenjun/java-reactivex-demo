package com.thoughtworks.wjlin;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class Main {


    public static void main(String args[]) {
        Main.standObserver();
        Main.lambdaObserver();
        Main.simpleObserver();

        OperatorOfReactiveX.mapDemo1();
        OperatorOfReactiveX.mapDemo2();
        OperatorOfReactiveX.mapDemo3();
        OperatorOfReactiveX.mapDemo4();
        OperatorOfReactiveX.mapDemo5();
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
}
