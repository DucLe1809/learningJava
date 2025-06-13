package org.example;

interface Animal {
     void sound();
     void moving();
}

class Dog implements Animal
{
    public void sound()
    {
        System.out.println("The dog says: Woof Woof!");
    }
    public void moving() {
        System.out.println("The dog is running!");
    }
}

class Cat implements Animal {
    public void sound() {
        System.out.println("The cat says: Meow Meow!");
    }
    public void moving() {
        System.out.println("The cat is jumping!");
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.sound();
        myDog.moving();

        System.out.println();

        Cat myCat = new Cat();
        myCat.sound();
        myCat.moving();
    }
}