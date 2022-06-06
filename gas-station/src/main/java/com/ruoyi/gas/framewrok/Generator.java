package com.ruoyi.gas.framewrok;

import java.util.LinkedList;
import java.util.List;

public abstract class Generator {
    private List<Observer> observers;

    public void addObserver(Observer observer) {
        if (observers == null) {
            observers = new LinkedList<>();
        }
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObserver() {
        if (observers == null || observers.size() == 0) return;
        for (Observer observer : observers){
            observer.update(this);
        }
    }
}

