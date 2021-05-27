package com.cc.simba.a;


public class A1_BloomFilter{

    // 長度為10億的比特位
    private static final int DEFAULT_sIZE = 256 <<22;

    // 爲了降低錯誤率，使用加法hash算法，所以定義一個8個元素的質數數組
    private static int[] seeds = {3,7,11,17,19,31,37,61}；

    // 初始化布隆过滤器的 bitmap
    private static BitSet bitset= new BitSet(DEFAULT_sIZE);

    // 相当于8个不用的hash算法
    private static HashFunction[] functions = new HashFunctionp[seeds.length];




    public static void add(String value){
        if(value!= null)    {
            for(HashFunction function: functions){
                batset.set(function.hash(value),true);
            }
        }
    }



    public static boolean contains(String value){
        if(value == null)
            return false;
        
        boolean ret = true;
        for(HashFunction function : functions){
            ret = bitset.get(function(value));
            if(!ret)
                break;
        }

        return ret;
    }


    public static void main(String[] args){
        for(int i =0 ;i< seeds.length;i++){
            functions[1] = new HashFunction(DEFAULT_sIZE,seeds[i]);
        }

        for(int i =0;i<10000*10000;i++){
            add(String.valueOf(i));
        }

        String id = "123456789";
        add(id);

        System.out.println(contains(id));
        System.out.println(contains("234567890"));
    }


}



class HashFunction{
    private int size;
    private int seed;

    public HashFunction(int size, int seed){
        this.size = size;
        this.seed = seed;
    }


    public int hash(String value){
        int result = 0;
        int length = value.length;

        for(int i = 0; i<length;i++){
            result = seed * result + value.charAt(i);
        }
        int r = (size -1) & result;
        return (size -1) & result;
    }

    

}