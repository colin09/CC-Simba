package com.cc.simba.k;

public class K2_Sync {

    private int number = 0;
    private final Object object = new Object();

    public void plus() {
        synchronized (object) {  // 锁 object
            number++;
            System.out.println(Thread.currentThread().getName() + " --> number: " + number);
        }
    }

    private void plus2() {
        synchronized (this) {    // 锁 class plus2 ??
            number++;
            System.out.println(Thread.currentThread().getName() + " --> number: " + number);
        }
    }

    private synchronized void plus3() {     // 等同于 synchronized(this)
        number++;
        System.out.println(Thread.currentThread().getName() + " --> number: " + number);
    }


    private static int count = 0;

    private static synchronized void plus4() {   // [static] 等同于 synchronized(K2_Sync.class)
        count++;
        System.out.println(Thread.currentThread().getName() + " --> number: " + count);
    }

    public static void plus5() {
        synchronized (K2_Sync.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + " --> number: " + count);
        }
    }
}


// synchronized  可重入锁，  子类重写锁方法 可以调用父类锁方法

// 线程异常 ，锁自动释放

/*
synchronized 底层实现
    1> markword 记录锁线程Id ，（偏向锁）
    2> 如果线程争用，升级为 自旋锁
    3> 自旋锁 10 次之后 ，升级为重量级锁 ——> os

    自旋锁：执行时间短，线程数少
    系统锁：执行时间长，线程数多

    synchronized(object)
        object 不能用 string , int , long , ,,,,
 */