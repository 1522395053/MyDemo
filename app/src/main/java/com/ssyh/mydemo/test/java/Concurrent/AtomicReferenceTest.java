package com.ssyh.mydemo.test.java.Concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {
        m4();
    }

    public static void m1(){
        //1、使用 null 初始值创建新的 AtomicReference。
        AtomicReference<SimpleObject> atomicReference = new AtomicReference<>();
        atomicReference.set(new SimpleObject("test1" , 10));
        SimpleObject simpleObject = atomicReference.get();
        System.out.println("simpleObject  Value: " + simpleObject.toString());
    }
    public static void m2(){
        //2、使用给定的初始值创建新的 AtomicReference。
        AtomicReference<SimpleObject> atomicReference1 = new AtomicReference<>(new SimpleObject("test2",20));
        SimpleObject simpleObject1 = atomicReference1.get();
        System.out.println("simpleObject  Value: " + simpleObject1.toString());
    }
    public static void m3(){
        //3、如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        SimpleObject test = new SimpleObject("test3" , 30);
        AtomicReference<SimpleObject> atomicReference2 = new AtomicReference<>(test);
        test = new SimpleObject("test4", 40);
        //判断引用是否相等
        Boolean bool = atomicReference2.compareAndSet(test, test /*new SimpleObject("test4", 40)*/);
        SimpleObject simpleObject = atomicReference2.get();
        System.out.println("simpleObject  compareAndSet: " + bool);
        System.out.println("simpleObject  Value: " + simpleObject);
    }
    public static void m4(){
        //4、以原子方式设置为给定值，并返回旧值，先获取当前对象，在设置新的对象
        SimpleObject test1 = new SimpleObject("test5" , 50);
        AtomicReference<SimpleObject> atomicReference3 = new AtomicReference<>(test1);
        SimpleObject simpleObject2 = atomicReference3.getAndSet(new SimpleObject("test6",50));
        SimpleObject simpleObject3 = atomicReference3.get();
        System.out.println("simpleObject  Value: " + simpleObject2.toString());
        System.out.println("simpleObject  Value: " + simpleObject3.toString());
    }
}


class SimpleObject {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "SimpleObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SimpleObject(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
