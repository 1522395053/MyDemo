package com.ssyh.mydemo.test.mode.guanChaZhe;

import java.util.ArrayList;
import java.util.List;

public class AndroidKnowledge implements Observerable{

    private String knowledge;
    private List<Observer> observerList;

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public AndroidKnowledge() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update(knowledge);
        }
    }
}
