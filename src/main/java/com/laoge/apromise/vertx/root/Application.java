package com.laoge.apromise.vertx.root;

import com.laoge.apromise.vertx.service.VertxHelloVerticle;
import io.vertx.core.Vertx;

/**
 * 启动入口
 * Created by yuhou on 2017/8/22.
 */
public class Application {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(VertxHelloVerticle.class.getName());
    }
}
