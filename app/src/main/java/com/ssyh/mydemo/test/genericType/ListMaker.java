package com.ssyh.mydemo.test.genericType;

// generics/ListMaker.java

import java.util.*;

public class ListMaker<T> {
    List<T> create() {
        return new ArrayList<>();//不加<>的话会有这个提示：Unchecked assignment: 'java.util.ArrayList' to 'java.util.List<T>'
    }

    public static void main(String[] args) {
        ListMaker<String> stringMaker = new ListMaker<>();
        List<String> stringList = stringMaker.create();
    }
}
