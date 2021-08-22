package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @version 1.0
 * @program: nio02
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/8/22 7:19 PM
 */
public class HttpRequestUriFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        System.out.println("url: " + uri);
        if (!uri.startsWith("/login")) {
            throw new RuntimeException("illegal uri:" + uri);
        }
        HttpHeaders headers = fullRequest.headers();
        if (headers == null) {
            headers = new DefaultHttpHeaders();
        }
        headers.set("filteredBy", this.getClass().getName());
    }
}
