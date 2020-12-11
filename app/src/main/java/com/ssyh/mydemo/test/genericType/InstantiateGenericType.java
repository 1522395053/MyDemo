package com.ssyh.mydemo.test.genericType;

// generics/InstantiateGenericType.java

import java.util.function.Supplier;

class ClassAsFactory<T> implements Supplier<T> {
    Class<T> kind;

    ClassAsFactory(Class<T> kind) {
        this.kind = kind;
    }

    @Override
    public T get() {
        try {
            return kind.newInstance();
        } catch (InstantiationException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {
    @Override
    public String toString() {
        return "Employee";
    }
}

public class InstantiateGenericType {
    public static void main(String[] args) {

        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        System.out.println(fe.get());


        //这样可以编译，但对于 ClassAsFactory<Integer> 会失败，这是因为 Integer 没有无参构造函数。

        // 由于错误不是在编译时捕获的，因此语言创建者不赞成这种方法。他们建议使用显式工厂（Supplier）并约束类型，

        // 以便只有实现该工厂的类可以这样创建对象。这是创建工厂的两种不同方法：

        ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        try {
            System.out.println(fi.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
/* Output:
Employee
java.lang.InstantiationException: java.lang.Integer
*/
