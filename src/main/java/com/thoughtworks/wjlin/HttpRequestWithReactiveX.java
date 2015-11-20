package com.thoughtworks.wjlin;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestWithReactiveX {

    public static void get() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://www.baidu.com");

        HttpResponse response = null;
        BufferedReader responseBody = null;
        String result = "";

        try {
            response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();

            if(200 != statusCode) {
                throw new RuntimeException("Filed with HTTP error code :" + statusCode);
            } else {
                responseBody = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = responseBody.readLine()) != null) {
                    System.out.print("+");
                    result += line;
                }
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

        System.out.println(result);
    }

    public static void post() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://www.baidu.com");

        HttpResponse response = null;
        BufferedReader responseBody = null;
        String result = "";

        try {
            response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();

            if(200 != statusCode) {
                throw new RuntimeException("Filed with HTTP error code :" + statusCode);
            } else {
                responseBody = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = responseBody.readLine()) != null) {
                    System.out.print("+");
                    result += line;
                }
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

        System.out.println(result);
    }
}
