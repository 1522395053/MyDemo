package com.ssyh.mydemo.test.thread;

import android.os.SystemClock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyNotifyAll {


    public static void main(String[] args) {
        new Storage();
    }
}

class Account {

    //账号
    private String accountNo;
    //余额
    private double balance;

    public Account() {

    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}

class Producer extends Thread{
    private Storage storage;
    private int count;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        super.run();
        synchronized (storage.object){
            while (true){
                if (storage.hasData){
                    try {
                        storage.object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                storage.hasData = true;

                System.out.println("=====生产");

                storage.object.notify();
            }

        }


    }
}

class Consumer extends Thread{
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }
    @Override
    public void run() {
        super.run();
        synchronized (storage.object){
            while (true){
                //如果没有数据
                if (!storage.hasData){
                    try {
                        storage.object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



                //走到这里说明有数据，消费掉
                storage.hasData  = false;

                System.out.println("============消费");

                storage.object.notify();
            }



        }

    }
}


class Storage {
    public Object object = new Object();
    public boolean hasData;
    public Producer producer = new Producer(this);
    public Consumer consumer = new Consumer(this);
    public ExecutorService executorService = Executors.newCachedThreadPool();


    public Storage() {
        executorService.execute(producer);
        executorService.execute(consumer);
    }
}




