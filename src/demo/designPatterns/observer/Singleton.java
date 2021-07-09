package demo.designPatterns.observer;

/**
 * @Description: 单例模式，内部静态类
 * @Author zhangdongkang
 * @Date 2021/7/9
 */
public class Singleton {

    private Singleton() {
    }

    private static class ObjectHolder {
        private static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return ObjectHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        Singleton instance3 = Singleton.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);

        Singleton singleton = new Singleton();
        System.out.println(singleton);

    }

}
