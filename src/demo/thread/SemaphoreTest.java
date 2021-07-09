package demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * 控制java的最大并发量，不建议采用，可以先缓存在其他地方，通过这个慢慢消费
 */
public class SemaphoreTest {
	private static final Semaphore semaphore = new Semaphore(3);

	public static void main(String[] args) {

		ExecutorService pool = MyThreadPool.getInstance().getPool();

		for (int i = 0; i < 10; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "正在执行");
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						System.out.println(Thread.currentThread().getName() + "执行完");
						semaphore.release();
					}
				}
			});
		}

	}
}