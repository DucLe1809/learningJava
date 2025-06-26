package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        Cat myCat = new Cat();

        test(myDog);
        test(myCat);
    }
    public static void test(Animal animal) {
        animal.sound();
        animal.moving();
    }
}