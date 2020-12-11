package com.ssyh.mydemo.test.genericType;

// generics/FilledList.java

import java.util.*;
import java.util.function.*;
//import onjava.*;
//即使编译器无法得知 add() 中的 T 的任何信息，但它仍可以在编译期确保你放入 FilledList 中的对象是 T 类型,这就是类型检查？？？
class Test{
    public Test() {
        System.out.println("init Test");
    }
}

public class FilledList<T> extends ArrayList<T> {
    FilledList(Supplier<T> gen, int size) {

        //Suppliers.fill(this, gen, size);//暂时找不到这个类，注释掉了
    }

    public FilledList(T t, int size) {
        for (int i = 0; i < size; i++) {
            this.add(t);
        }
    }

    public static void main(String[] args) {
        List<String> list = new FilledList<>("Hello", 4);
        System.out.println(list);

        List<Integer> integers = new FilledList<>(1, 4);
        System.out.println(integers);
        // Supplier version:
        List<Integer> ilist = new FilledList<>(() -> 47, 4);
        System.out.println(ilist);

        Supplier<Test> supplier = Test::new;

        Test test = supplier.get();
        System.out.println(test);

    }
}
/* Output:
[Hello,Hello,Hello,Hello]
[47,47,47,47]
*/

