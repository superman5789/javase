package demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁，锁定的部分只能有一个线程操作
 */
public class LockTest {

	final static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {

		ExecutorService pool = MyThreadPool.getInstance().getPool();
		for (int i = 0; i < 5; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						write();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	private static void write() throws Exception {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "正在执行写操作");
			Thread.sleep(2000);
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + "执行完写操作");
		}
	}

}