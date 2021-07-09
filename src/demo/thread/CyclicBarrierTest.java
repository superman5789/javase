package demo.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * CyclicBarrier
 * 加计数方式
 * 计数达到指定值时释放所有等待线程
 * 计数达到指定值时，计数置为0重新开始
 * 调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞
 * 可重复利用
 */
public class CyclicBarrierTest {

	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

	public static void main(String[] args) {
		ExecutorService pool = MyThreadPool.getInstance().getPool();

		for (int i = 0; i < 11; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(5) * 1000);
						System.out.println(Thread.currentThread().getName() + "准备完毕");
						// 适合在子线程中拦截
						cyclicBarrier.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("集合完毕了。。。。。。");
				}
			});
		}
		System.out.println("我是主线程");
	}
}