package com.laoge.apromise.vertx.service;

import io.vertx.core.AbstractVerticle;

/**
 * Created by yuhou on 2017/8/22.
 */
public class VertxHelloVerticle extends AbstractVerticle {

    public void start() {
        vertx.createHttpServer().requestHandler(httpServerRequest -> {
            httpServerRequest.response().putHeader("content-type", "text/plain").end("hello word!");
        }).listen(8080);
    }
}
