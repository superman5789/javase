package demo.thread;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, currentThread)) {
            //循环尝试获取
        }
    }

    public void unLock() {
        Thread currentThread = Thread.currentThread();
        if(atomicReference.get() != currentThread){
            System.out.println("error");
        }
        atomicReference.set(null);
    }
}
