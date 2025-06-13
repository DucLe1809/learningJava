package org.example;
import java.util.ArrayList;
import java.util.List;

public class CameraSystem implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    @Override
    public void addObserver(org.example.Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(org.example.Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    public void setAction(String newAction) {
        state = newAction;
        notifyObservers();
    }

}
