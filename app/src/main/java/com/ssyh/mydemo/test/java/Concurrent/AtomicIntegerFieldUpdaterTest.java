package com.ssyh.mydemo.test.java.Concurrent;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 如何把普通变量升级为原子变量？主要是AtomicIntegerFieldUpdater<T>类，参考如下代码：
 */
public class AtomicIntegerFieldUpdaterTest implements Runnable {

    static Goods phone;
    static Goods computer;

    AtomicIntegerFieldUpdater<Goods> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Goods.class,Goods.getPriceFiledName());

    @Override
    public void run() {
        for (int i = 0; i < 10009; i++) {
            phone.price++;
            atomicIntegerFieldUpdater.getAndIncrement(computer);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        phone = new Goods();
        computer = new Goods();
        AtomicIntegerFieldUpdaterTest atomicIntegerFieldUpdaterTest = new AtomicIntegerFieldUpdaterTest();
        Thread thread1 = new Thread(atomicIntegerFieldUpdaterTest);
        Thread thread2 = new Thread(atomicIntegerFieldUpdaterTest);
        thread1.start();
        thread2.start();
        //join()方法是为了让main主线程等待thread1、thread2两个子线程执行完毕
        thread1.join();
        thread2.join();
        System.out.println("CommonInteger price = " + phone.price);
        System.out.println("AtomicInteger price = " + computer.price);
    }

}


class Goods {
    //商品价格
    volatile int price;

    public static String getPriceFiledName(){

        return "price";
    }
}













