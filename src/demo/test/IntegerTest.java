package demo.test;

// 当需要进行自动装箱时，如果数字在 -128 至 127 之间时，会直接使用缓存中的对象，而不是重新创建一个对象。
public class IntegerTest {
    public static void main(String[] args) {
        // 1）基本类型和包装类型
        int a = 100;
        Integer b = 100;
        System.out.println(a == b);

        // 2）两个包装类型
        Integer c = 100;
        Integer d = 100;
        System.out.println(c == d);

        // 3）
        c = 200;
        d = 200;
        System.out.println(c == d);
    }
}
