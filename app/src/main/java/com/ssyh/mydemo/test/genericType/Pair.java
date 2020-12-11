package com.ssyh.mydemo.test.genericType;

import com.ssyh.mydemo.test.entity.User;

import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class Pair<T> {
    private T first;
    private T second;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }










    public static void main(String[] args){
        //为什么这样又可以获取 参数化类型数组了？？？
        Pair<String>[] eArr = getEArr(new Pair<String>());

        Pair<String> stringPair = new Pair<>();
        stringPair.setSecond("second-1");
        stringPair.setFirst("first-1");
        eArr[0] = stringPair;

        //eArr[1] = new Pair<Integer>();

        String second = eArr[0].getSecond();
        String first = eArr[0].getFirst();
        System.out.println(second);
        System.out.println(first);


    }

    public static void genericArrayTest(){
        //等式右边的加上 <String> 编译不通过，去掉 <String> 就可以通过
//        Pair<String>[] pairs = new Pair[10];
        Pair<String>[] pairTStringArr = new Pair/*<String>*/[10];

        List<String>[] listTStringArr = new ArrayList/*<String>*/[10];

        Object[] objectsArr = listTStringArr;

        objectsArr[0] = new User();

        Pair<String> stringPair = new Pair<>();
        stringPair.setFirst("first-1");
        stringPair.setSecond("second-1");
        pairTStringArr[0] = stringPair;

        String first = pairTStringArr[0].getFirst();
        String second = pairTStringArr[0].getSecond();
        System.out.println(first);
        System.out.println(second);
    }

    public static <E> Pair<E>[] getEArr(Pair<E> pair){
        User user = new User();
        Class<? extends User> aClass = user.getClass();

        Pair<E>[] eArr = (Pair<E>[]) Array.newInstance(pair.getClass(), 10);
        return eArr;
    }


}
