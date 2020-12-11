package com.ssyh.mydemo.test.mode.guanChaZhe;

public interface Observerable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
