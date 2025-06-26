package org.example;

public class Announcer implements Observer {
    private String action;
    @Override
    public void update(String action) {
        this.action = action;
        display();
    }

    public void display() {
        System.out.println("The subject has been recorded to " + this.action);
    }
}
