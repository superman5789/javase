package demo.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 *
 * CountDownLatch
 * 减计数方式
 * 计算为0时释放所有等待的线程
 * 计数为0时，无法重置
 * 调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响
 * 不可重复利用
 */
public class CountDownLatchTest {

	private static CountDownLatch countDownLatch = new CountDownLatch(5);

	public static void main(String[] args) throws Exception {
		ExecutorService pool = MyThreadPool.getInstance().getPool();

		System.out.println("至少来5个人才可以开饭。。。。");
		for (int i = 0; i < 10; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(5) * 1000);
						System.out.println(Thread.currentThread().getName() + "到了");
						countDownLatch.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		// 适合拦截主线程
		countDownLatch.await();
		System.out.println("人够了，开饭。。。");

	}
}