package com.laoge.apromise.vertx.root;

import com.laoge.apromise.vertx.service.VertxHelloVerticle;
import io.vertx.core.AbstractVerticle;

/**
 * 启动方式2
 * Created by yuhou on 2017/8/22.
 */
public class ApplicationLauncher extends AbstractVerticle {

    public void start() {
        vertx.deployVerticle(VertxHelloVerticle.class.getName());
    }
}
