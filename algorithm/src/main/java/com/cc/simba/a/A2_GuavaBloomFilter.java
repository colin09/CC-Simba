package com.cc.simba.a;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class A2_GuavaBloomFilter {

    public static void main(String[] args) {

        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 10000, 0.1);

        for (int i = 0; i < 10000; i++) {
            bloomFilter.put(i);
        }

        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        System.out.println(bloomFilter.mightContain(3));
        System.out.println(bloomFilter.mightContain(10001));

        //把布隆过滤器通过 bloomFilter.writeTo() 写入一个文件，放入OSS、S3这类对象存储中。
        // bloomFilter.writeTo(arg0);

    }

}


/**
 * Java 的 Redis 客户端比较多
 * 已知的 Redisson 和 lettuce 是可以使用布隆过滤器的，
 * 这里用 Redisson 
 * 
public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("user");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add("Tom");
        bloomFilter.add("Jack");
        System.out.println(bloomFilter.count());   //2
        System.out.println(bloomFilter.contains("Tom"));  //true
        System.out.println(bloomFilter.contains("Linda"));  //false
    }
 * 
 */




 /**
  * 为了解决布隆过滤器不能删除元素的问题，布谷鸟过滤器横空出世。
    论文《Cuckoo Filter：Better Than Bloom》
    将布谷鸟过滤器和布隆过滤器进行了深入的对比。相比布谷鸟过滤器而言布隆过滤器有以下不足：查询性能弱、空间利用效率低、不支持反向操作（删除）以及不支持计数。
  */



