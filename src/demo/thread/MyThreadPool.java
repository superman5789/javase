package demo.thread;

import java.util.concurrent.*;

/**
 * 单例线程池(虚拟机级线程安全)
 */
public class MyThreadPool {

    private ExecutorService pool;

    /**
     * 3.只会执行一次构造方法
     * 线程池默认线程数为corePoolSize = 6，当满了以后会放到队列里边，慢慢消费。
     * 如果队列也满了，则会创建线程maximumPoolSize = 8来消费，
     * 再满了执行拒绝策略再调用者主线程执行，也就是单线程同步执行了。
     * 消费完这些任务之后，keepAliveTime =1分钟之内没有新任务，则会销毁线程，保持线程池线程数为corePoolSize = 6
     */
    private MyThreadPool() {
        pool = new ThreadPoolExecutor(6,
                8,
                1,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(1024),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    // 2.静态变量被所有的对象所共享，在内存中只有一个副本
    private static class Sub {
        static MyThreadPool POOL = new MyThreadPool();
    }

    // 1. 获取实例对象
    public static MyThreadPool getInstance() {
        return Sub.POOL;
    }

    // 4.因为在静态内部类加载时被加载过一次，所以有值
    public ExecutorService getPool() {
        return pool;
    }

}