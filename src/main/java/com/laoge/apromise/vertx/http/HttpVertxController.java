package com.laoge.apromise.vertx.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Created by yuhou on 2017/8/24.
 */
public class HttpVertxController extends AbstractVerticle {


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(HttpVertxController.class.getName());
    }

    public void start() {

        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/").blockingHandler(routingContext -> {
            // 所有的请求都会调用这个处理器处理
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // 写入响应并结束处理
            response.end("Hello World from Vert.x-Web!");
        }, false);

        httpServer.requestHandler(router::accept).listen(8080);
    }
}
