package demo.thread;


/**
 * 自旋锁：程序不断循环对比值是否与期望的相同，若不同则一直循环
 * 优点：没有线程切换的开销
 * 缺点：循环会导致CPU一直阻塞
 * 总结：冲突很小的时候建议使用自旋锁，减少线程切换；冲突大的话建议使用线程锁
 */
public class SpinLockTest {

    static int count = 0;
    static int lockCount = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            MyThreadPool.getInstance().getPool().execute(() -> {
                ++count;
            });
        }

        SpinLock spinLock = new SpinLock();
        for (int i = 0; i < 1000; i++) {
            MyThreadPool.getInstance().getPool().execute(() -> {
                spinLock.lock();
                ++lockCount;
                spinLock.unLock();
            });
        }

        Thread.sleep(2000);
        System.out.println(count);
        System.out.println(lockCount);
    }

}
