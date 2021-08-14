package com.geek;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @version 1.0
 * @program: week02HttpClient
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/15 1:14 AM
 */
public class HttpClientHomework {

    public static void main(String[] args) {
        String url = "https://www.bilibili.com";
        String response = HttpClientHomework.connection(url);

        System.out.println("response: " + response);
    }

    private static String connection(String url) {
        String response = "";
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response1 = httpclient.execute(httpGet)) {

            HttpEntity entity1 = response1.getEntity();
            response = EntityUtils.toString(entity1, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
