package io.github.kimmking.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
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
        String url = "http://localhost:8888/login";
        String response = HttpClientHomework.connection(url);

        System.out.println("response: " + response);
    }

    private static String connection(String url) {
        String response = "";
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.DEFAULT;
//                RequestConfig.custom()
//                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
//                .setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient httpclient = HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler(2,false)).disableAutomaticRetries().build();
             CloseableHttpResponse response1 = httpclient.execute(httpGet)) {

            HttpEntity entity1 = response1.getEntity();
            response = EntityUtils.toString(entity1, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
