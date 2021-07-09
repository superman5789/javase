package demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读写锁：读读并行/读写互斥/写写互斥
 */
public class ReadWriteLockTest {

	final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	final static ReadLock readLock = lock.readLock();
	final static WriteLock writeLock = lock.writeLock();

	public static void main(String[] args) {
		ExecutorService pool = MyThreadPool.getInstance().getPool();
		for (int i = 0; i < 15; i++) {
			final int index = i;
			pool.execute(new Runnable() {
				public void run() {
					try {
						if (index % 2 == 0){
							read();
						}else{
							write();
						}
//						read();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

	private static void read() throws Exception {
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "正在读");
			Thread.sleep(5000);
		} finally {
			System.out.println(Thread.currentThread().getName() + "读完");
			readLock.unlock();
		}
	}

	private static void write() throws Exception {
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "正在执行写操作");
			Thread.sleep(2000);
		} finally {
			writeLock.unlock();
			System.out.println(Thread.currentThread().getName() + "执行完写操作");
		}
	}
}