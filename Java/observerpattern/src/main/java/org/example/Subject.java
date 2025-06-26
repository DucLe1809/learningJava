package org.example;

import java.util.Observer;

public interface Subject {
    void addObserver(org.example.Observer observer);
    void removeObserver(org.example.Observer observer);
    void notifyObservers();
}
