package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseConfigSingleton configSingleton = DatabaseConfigSingleton.getInstance();
        configSingleton.loadFromFile("file.txt");

        System.out.println("Host: " + configSingleton.getHost());
        System.out.println("Port: " + configSingleton.getPort());

        DatabaseConfigStatic.loadFromFile("file.txt");
        System.out.println("Host: " + DatabaseConfigStatic.host);
        System.out.println("Port: " + DatabaseConfigStatic.port);
    }
}