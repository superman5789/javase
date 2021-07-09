package demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * 获取异步执行的结果，如果没有结果可用，此方法会阻塞直到异步计算完成
 */
public class FutureTaskTest {

	public static void main(String[] args) throws Exception {
		ExecutorService pool = MyThreadPool.getInstance().getPool();

		// 相当于有返回值的runnable
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("异步线程的耗时计算开始....");
				Thread.sleep(3000);
				return 1 + 98 + 1;
			}
		};

		FutureTask<Integer> futureTask = new FutureTask<>(callable);

		System.out.println("主线程继续...");
		pool.submit(futureTask);
		System.out.println("3秒后去计算结果为：" + futureTask.get());

	}
}