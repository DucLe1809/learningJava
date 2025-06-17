package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Singleton
        DatabaseConfigSingleton configSingleton = DatabaseConfigSingleton.getInstance();
        configSingleton.loadFromFile("configs.txt");

        // Static
        DatabaseConfigStatic.loadFromFile("configs.txt");

        // Init
        System.out.println("Initial Result:");
        print(configSingleton.getHost(), DatabaseConfigStatic.host);

        // Make some changes to see the difference
        configSingleton.setHost("singleton.host");
        DatabaseConfigStatic.host = "static.host";

        // After modifications
        System.out.println("Later Result:");
        print(configSingleton.getHost(), DatabaseConfigStatic.host);

        DatabaseConfigSingleton anotherConfig = DatabaseConfigSingleton.getInstance();
        System.out.println("[Singleton] Host from anotherRef: " + anotherConfig.getHost());

    }
     public static void print(String host1, String host2) {
         System.out.println("[Singleton] Host: " + host1);
         System.out.println("[Static   ] Host: " + host2);
         System.out.println();
     }
}