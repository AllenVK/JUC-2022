package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Desc: 使用CAS思想，实现自旋锁
 *        自旋锁的优点：循环比较获取，没有wait阻塞
 *
 *        通过CAS操作完成自旋锁，A线程先调用 myLock 方法，并持有5秒，当B线程调用 myLock方法时，
 *        发现已有其他线程持有 myLock 方法(资源)的锁，B线程会自旋等待，直到A线程释放持有的 myLock 的锁，B线程方可抢到 myLock 的锁
 *
 * @Date: 2022-07-19 23:01
 * @Author: paul
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "--> come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "--> task over, unLock!");
    }

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        // 线程A
        new Thread(() -> {
            spinLockDemo.lock();
            // 暂停5秒
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        }, "A").start();

        // 先暂停500毫秒，保证线程A比线程B先启动去抢占资源
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程B
        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unLock();
        }, "B").start();

    }

}
