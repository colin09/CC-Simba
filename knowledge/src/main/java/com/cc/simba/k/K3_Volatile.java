package com.cc.simba.k;

public class K3_Volatile {
}



/*
volatile
    保证线程可见性
        MESI
        缓存一致性协议
    禁止指令重排序 （JVM ， CPU）
        DCL 单例
        double check lock

单例
    饿汉式 ： static 赋值，jvm初始化
    懒汉式 : 使用到的时候初始化 ， double-check 线程安全 ， volatile

 */
