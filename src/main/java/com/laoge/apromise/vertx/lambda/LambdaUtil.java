package com.laoge.apromise.vertx.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yuhou on 2017/8/23.
 */
public class LambdaUtil {

    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(100, 200, 300, 400, 500, 600, 200, 300);
        lists.stream().map((data) -> data + data * 0.12).forEach(d -> System.out.println(d));


        System.out.println(lists.stream().map((data) -> (data + data * 0.12)).reduce((sum, cost) -> sum + cost).get());

        System.out.println(lists.stream().filter(x -> (x > 500 && x < 1000)).collect(Collectors.toList()));


        System.out.println("》>>>>>"+lists.stream().filter(x -> (x < 500)).reduce(1, (a1, a2) -> a1 + a2));

        System.out.println(lists.stream().map(data -> data + "").collect(Collectors.joining(",")));

        System.out.println(lists.stream().map(i -> i * i).distinct().collect(Collectors.toList()));

        IntSummaryStatistics stats = lists.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>>>>");
            }
        }).start();

        new Thread(() -> {
            System.out.println("<<><>><><>");
            System.out.println("<<<<");
        }).start();

    }
}
