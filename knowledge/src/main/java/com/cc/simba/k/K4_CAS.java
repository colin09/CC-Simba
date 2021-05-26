package com.cc.simba.k;

public class K4_CAS {
}

/*
CAS
    compare and set
    cas(v 当前值, expected 期望值, newValue 新值)
        if(v==e)  --> v = newVaule
        else :  try again or fail
        受 CUP 原语支持
    存在ABA问题
        加 version 控制
            A v1.0
            B v2.0
            A v3.0
        cas(* , version) 找带version类
    由 Unsafe 类执行 ，
        单例； UnSafe.getUnfafe()
 */