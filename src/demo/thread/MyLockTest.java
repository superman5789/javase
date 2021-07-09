package demo.thread;

import sun.applet.Main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁是采用循环的方式不断获取锁，避免了线程切换，缺点是消耗CPU
 */
public class MyLockTest {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " come in ");
        //直到获取到null才拿到锁
        while (!atomicReference.compareAndSet(null,thread)){}
        System.out.println(thread.getName() + " get the lock ");
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.set(null);
        System.out.println(thread.getName() + " release lock ");
    }

    public static void main(String[] args) {
        MyLockTest myLock = new MyLockTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myLock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    myLock.unlock();
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myLock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    myLock.unlock();
                }
            }
        },"t2").start();


    }
}
