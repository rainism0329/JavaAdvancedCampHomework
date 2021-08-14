package com.geek;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * @version 1.0
 * @program: week02HttpClient
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/15 3:38 AM
 */
public class OkHttpHomework {
    public static void main(String[] args) {
        String url = "http://localhost:8801";
        String response = OkHttpHomework.connect(url);

        System.out.println(response);
    }

    private static String connect(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        String responseString = "";
        try (Response response = client.newCall(request).execute()) {
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }
}
