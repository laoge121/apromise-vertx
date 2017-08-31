package com.laoge.apromise.vertx.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

import java.util.Arrays;
import java.util.List;

/**
 * future 执行
 * Created by yuhou on 2017/8/24.
 */
public class VertxFutureService extends AbstractVerticle {

    public void start() {

        Future<String> stringFuture = Future.future();
        stringFuture.completer();
        stringFuture.setHandler(as -> {
            if (as.succeeded()) {

            } else {

            }
        });

        Future<List> listFuture = Future.future();
        stringFuture.completer();


        //所有成功都成功 一个失败就失败
        CompositeFuture.all(Arrays.asList(stringFuture, listFuture)).setHandler(asr -> {
            if (asr.succeeded()) {

            } else {

            }
        });

        //一个成功就返回  所有失败就失败
        CompositeFuture.any(Arrays.asList(stringFuture, listFuture)).setHandler(asr -> {
            if (asr.succeeded()) {

            } else {

            }
        });


        //全部成功才成功 至少一个失败才算失败
        CompositeFuture.join(Arrays.asList(stringFuture, listFuture)).setHandler(asr -> {
            if (asr.succeeded()) {

            } else {

            }
        });


        //测试顺序合并
        FileSystem fileSystem = vertx.fileSystem();
        Future<Void> statusFuture = Future.future();

        Future<Void> fut1 = Future.future();
        fileSystem.createFile("/foo", fut1.completer());

        fut1.compose(v -> {
            Future<Void> fut2 = Future.future();
            fileSystem.writeFile("/foo", Buffer.buffer(), fut2.completer());
            return fut2;
        }).compose(v -> {
            fileSystem.move("/foo", "/bar", statusFuture.completer());
        }, statusFuture);
    }
}
