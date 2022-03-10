package com.cc.simba.a;

/**
 * 
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 
 */

 

public class LT_0002 {

    public int reverse(int x) {
        int dis = 0;
        int result = 0;
        while (x > 0) {

            if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE / 10)
                return 0;
            dis = x % 10;
            x /= 10;

            result = result * 10 + dis;
        }
        return result;
    }

}
