package demo.jdk8;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String[] args) {
        testDouble();

    }

    //BigDecimal计算
    private static void testBigDecimal() {
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.ZERO);
        list.add(BigDecimal.ONE);
        list.add(BigDecimal.TEN);

        BigDecimal add = list.stream().reduce(BigDecimal::add).get();
        System.out.println(add);
    }

    //double计算
    private static void testDouble() {
        List<Double> list = new ArrayList<>();
        list.add(1D);
        list.add(2D);
        list.add(3D);

        Double add = list.stream().reduce(0D, Double::sum);
        System.out.println(add);
    }

    // 集合统计
    private static void summaryStatistics() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(3);
        Collections.sort(list, (i1, i2) -> i1.compareTo(i2));
        list.forEach(System.out::println);
        //只输出大于3的
        System.out.println("只输出大于3的");
        list.stream().filter(n -> n > 3).forEach(System.out::println);
        //乘以2
        System.out.println("乘以2");
        List<Integer> collect = list.stream().map(i -> i * 2).collect(Collectors.toList());
        collect.forEach(System.out::println);
        //大于3的有几个
        long count = list.stream().filter(i -> i > 3).count();
        System.out.println("大于3的有" + count + "个");
        //大于3的拼接成字符串
        String joinList = list.stream().filter(i -> i > 3).map(i -> i.toString()).collect(Collectors.joining(","));
        System.out.println(joinList);
        //统计
        IntSummaryStatistics summaryStatistics = list.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("list统计平均值为：" + summaryStatistics.getAverage());
        System.out.println("list统计max值为：" + summaryStatistics.getMax());
        System.out.println("list统计min值为：" + summaryStatistics.getMin());
        System.out.println("list统计总和为：" + summaryStatistics.getSum());

        System.out.println("生成10个随机数");
        List<Object> random = new Random().ints().limit(10).mapToObj(i -> i).collect(Collectors.<Object>toList());
        random.forEach(System.out::println);
    }
}
