package com.laoge.apromise.vertx.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;

/**
 * Created by yuhou on 2017/8/22.
 */
public class VertxHelloVerticle extends AbstractVerticle {

    /**
     * 采用的 多反应器模式 nodjs采用单一反应器模式(一个监听线程处理请求)
     * 在event loop 中 是不能执行阻塞操作的 避免引用 请求处理
     */
    public void start() {

        new VertxOptions().setWorkerPoolSize(Runtime.getRuntime().availableProcessors());

        WorkerExecutor workerExecutor = vertx.createSharedWorkerExecutor("my-work-pool");


        vertx.createHttpServer().requestHandler(httpServerRequest -> {

            //异步执行操作 a,b,c ;a异步执行的方法,b 设置线程是否并发执行 false并发执行 默认true顺序执行 ,c 回调方法
            vertx.executeBlocking(future -> {
                future.complete("aaa");
            }, false, res -> {
                System.out.println(res.result());
            });

            workerExecutor.executeBlocking(future -> {

                // 调用一些需要耗费显著执行时间返回结果的阻塞式API
                future.complete("cccc");

            }, res -> {

                System.out.println(res.result());

            });


            httpServerRequest.response().putHeader("content-type", "text/plain").end("hello word!");
        }).listen(8080);
    }
}
