package com.ssyh.mydemo.test.genericType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Frob {}
class Fnorkle {}
class Quark<Q> {}
class Food<F extends Number> {}

class Particle<POSITION, MOMENTUM> {}

public class LostInformation {

    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<>();
        Food<Integer> food = new Food<>();
        Particle<Long, Double> p = new Particle<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));

        System.out.println(Arrays.toString(food.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(food.getClass().getTypeParameters()[0].getBounds()));//泛型边界

        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
        System.out.println("=======================================");
        System.out.println("=======================================");


    }

/* 输出：这些都是 用作参数占位符的标识符 ，并非有用的信息
因此，你可以知道如类型参数标识符和泛型边界这些信息，但无法得知实际的类型参数从而用来创建特定的实例
 * [E]
 * [K, V]
 * [Q]
 * [POSITION, MOMENTUM]
 */

}
