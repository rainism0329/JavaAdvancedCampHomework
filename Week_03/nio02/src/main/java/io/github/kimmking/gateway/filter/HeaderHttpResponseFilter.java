package io.github.kimmking.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

import java.time.LocalDateTime;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("kk", "java-1-nio");
//        response.content().clear();
        response.headers().set("timeStamp", LocalDateTime.now());
    }
}
