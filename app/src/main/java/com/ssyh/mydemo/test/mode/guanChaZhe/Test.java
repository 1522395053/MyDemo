package com.ssyh.mydemo.test.mode.guanChaZhe;

public class Test {
    public static void main(String[] args){
        AndroidKnowledge androidKnowledge = new AndroidKnowledge();

        androidKnowledge.setKnowledge("Android 真有趣");
        Learner learnerXiaoming = new Learner("小明");
        Learner learnerZhangsan = new Learner("张三");
        androidKnowledge.addObserver(learnerXiaoming);
        androidKnowledge.addObserver(learnerZhangsan);

        androidKnowledge.notifyObserver();


        androidKnowledge.removeObserver(learnerZhangsan);
        androidKnowledge.setKnowledge("Android 真有趣,java 基础要扎实");
        androidKnowledge.notifyObserver();
    }
}
