package com.mb.main.system.test;

import java.util.Random;

public class Test {
    /** 
     * 通过模除取给定区间的随机数 
     * @param min 
     * @param max 
     * @return 
     */  
    public int nextIntF(int min, int max) {  
        Random random = new Random();  
        int tmp = Math.abs(random.nextInt());  
        tmp = tmp % (max - min + 1) + min;  
        return tmp;  
    }  
    /** 
     * 通过随机结果补位 
     * @return 
     */  
    public int nextIntS() {  
        Random random = new Random();  
        int x = random.nextInt(899999);  
        return x + 100000;  
    }  
    /** 
     * 通过随机数乘法运算间接实现补位 
     * @return 
     */  
    public int nextIntT() {  
        int n = 0;  
        while (n < 1000) {  
            n = (int) (Math.random() * 10000);  
        }  
        return n;  
    }  
    /** 
     * 小数放大补整继续放大取整 
     * @return rtnC 
     */  
    public int nextIntFor() {  
        return (int)((Math.random() * 9 + 1) * 100000);  
    }  
    /** 
     * 取給定位數隨機數(限制2~8位) 
     * @param radix 
     * @return rtnValue 
     */  
    public int getRandomValue(int radix) {  
        return ((int) ((Math.random() + 1) * (radix < 2 ? 100 : radix > 9 ? 100 : Math.pow(10D, radix))));  
    }  
    /** 
     * 测试入口 
     * @param args 参数列表 
     */  
    public static void main(String[] args) {  
    	Test rntInst = new Test();  
        for (int i = 0; i < 9999; i++) {  
//            System.out.println(rntInst.nextIntF(100, 200));  
//            System.out.println(rntInst.nextIntS());  
            System.out.println(rntInst.nextIntT());  
//            System.out.println(rntInst.nextIntFor());  
//            System.out.println(rntInst.nextIntFor());  
//            Long batchNo = Long.parseLong(String.valueOf(rntInst.getRandomValue(3)));  
//            System.out.println(i + ": " + batchNo);  
        }  
    }
}
