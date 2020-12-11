package com.ssyh.mydemo.test.mode.guanChaZhe;

public class Learner implements Observer{
    private String name;

    public Learner(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name+" 收到通知："+message);
    }
}
