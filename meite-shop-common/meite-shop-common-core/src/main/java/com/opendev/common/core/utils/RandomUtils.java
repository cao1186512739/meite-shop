package com.opendev.common.core.utils;


public class RandomUtils {

    public static String getRandomNumber(Integer integer) {

        StringBuffer sb = new StringBuffer();
        int intFlag = (int) (Math.random() * 1000000);
        sb.append(intFlag);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomUtils.getRandomNumber(6));
    }
}
