package com.ssyh.mydemo.test.genericType;

// generics/ArrayMaker.java
//出自on java 8(Java编程思想第五版)
//因为擦除，我发现了泛型最令人困惑的方面是可以表示没有任何意义的事物。例如：例如当前类
import java.lang.reflect.*;
import java.util.*;

public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker() {
    }
    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    public ArrayMaker(T t) {
        this.kind = (Class<T>) t.getClass();
    }


    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);//waring :Unchecked cast: 'java.lang.Object' to 'T[]'
    }

    public static void main(String[] args) {
        //ArrayMaker<String> stringMaker = new ArrayMaker<>("dd");
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringMaker.create(9);

        System.out.println(stringArray);
        System.out.println(Arrays.toString(stringArray));
    }
}
/* Output
[null,null,null,null,null,null,null,null,null]
*/
