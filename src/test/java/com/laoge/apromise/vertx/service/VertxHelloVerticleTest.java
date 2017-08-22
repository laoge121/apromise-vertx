package com.laoge.apromise.vertx.service;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 单元测试
 * Created by yuhou on 2017/8/22.
 */
@RunWith(VertxUnitRunner.class)
public class VertxHelloVerticleTest {

    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(VertxHelloVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient().getNow(8080, "localhost", "/", responseHandler -> {
                    responseHandler.bodyHandler(body -> {
                        context.assertTrue(body.toString().contains("hello"));
                        async.complete();
                    });
                }
        );


    }
}
