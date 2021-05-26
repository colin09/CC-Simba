package com.cc.simba.k;

/*
    线程实现
 */

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class K1_Thread {

    /*
     * 继承 Thread
     */
    static class T1 extends Thread {
        @Override
        public void run() {
            //super.run();
            for (int i = 0; i < 10; i++) {
                try {
                    //sleep(100);
                    TimeUnit.MICROSECONDS.sleep(100);
                    System.out.println(" Thread.run , " + i + " --> " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /*
        实现 Runnable
     */
    static class R2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                    System.out.println(" Runnable.run , " + i + " --> " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    static void theadyield() {
        new Thread(() -> {
            System.out.println("yield 1 , " + new Date().toString());
            Thread.yield(); // 线程回到指令队列
            System.out.println("yield 2 , " + new Date().toString());
        }).start();
    }

    static void threadJoin() {
        Thread t1 = new Thread(() -> {
            System.out.println("thread t1 : " + new Date().toString());
        });

        Thread t2 = new Thread(() -> {
            System.out.println("thread t2-1 : " + new Date().toString());
            try {
                t1.join();  // 等待t1 线程执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread t2-1 : " + new Date().toString());
        });
    }


    public static void main(String[] args) throws InterruptedException {
        // new T1().run(); //同步执行
        new T1().start();

        new Thread(new R2()).start();

        new Thread(() -> {
            System.out.println("Thread by lambda , --> " + Thread.currentThread().getName());
        }).start();

        for (int j = 0; j < 10; j++) {
            TimeUnit.MICROSECONDS.sleep(100);
            System.out.println("Thread main , --> " + Thread.currentThread().getName());
        }
    }
}

/* 线程状态 : new ---> runnable (ready,running) -- timedWaiting -- waiting -- blocked  ---> teminated .  */
/*

timedWaiting : sleep(x) , wait(x) , join(?) , parkNanos() , packUntil()

waiting: wait(x), join(?), LockSupport.park();

blocked : 同步等锁

*/