package com.thoughtworks.wjlin;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import rx.Observable;
import rx.Subscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestWithReactiveX {

    public static void demo() {
        new HttpRequestWithReactiveX()
                .get("http://www.baidu.com")
                .subscribe(s -> {
                   System.out.println(s.split("><"));
                });
    }

    public Observable<String> get(String url) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

                HttpResponse response = null;
                BufferedReader responseBody = null;
                String result = "";

                try {
                    response = httpClient.execute(get);
                    int statusCode = response.getStatusLine().getStatusCode();

                    if(200 != statusCode) {
                        subscriber.onError(new RuntimeException("Filed with HTTP error code :" + statusCode));
                    } else {
                        responseBody = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        String line = "";

                        while ((line = responseBody.readLine()) != null) {
                            result += line;
                        }

                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(responseBody != null) {
                        try {
                            responseBody.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                subscriber.onNext("");
            }
        });

        return observable;
    }

}
