package com.ssyh.mydemo.test.thread;

import android.content.Context;

import androidx.annotation.Nullable;

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

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        super.run();
        synchronized (this){
            if (storage.hasData){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (storage.consumer){
            storage.hasData = true;
            System.out.println("=====生产");
            storage.consumer.notifyAll();
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
        synchronized (this){
            if (!storage.hasData){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        synchronized (storage.producer){
            storage.hasData  = false;
            System.out.println("============消费");
            storage.producer.notifyAll();
        }
    }
}


class Storage {
    public boolean hasData;
    public Producer producer = new Producer(this);
    public Consumer consumer = new Consumer(this);
    public ExecutorService executorService = Executors.newCachedThreadPool();


    public Storage() {
        this.producer = producer;
        this.consumer = consumer;

        executorService.execute(producer);
        executorService.execute(consumer);
    }
}




