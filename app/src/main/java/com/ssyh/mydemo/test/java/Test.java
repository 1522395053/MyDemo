package com.ssyh.mydemo.test.java;

public class Test {
    public static void main(String[] args){
        System.out.println("-=-=-=-=-");
    }




    /**
     * 使用条件 arr 由小到大排好了顺序
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearch(int[] arr,int key){

        int left = 0;
        int right = arr.length - 1;

        int middleIndex = (left+right)/2;
        int middle = arr[middleIndex];

        while (middle != key){
            if (middle < key){
                right = middleIndex - 1;
            }else {
                left = middleIndex + 1;
            }



        }


        return -1;
    }
}
