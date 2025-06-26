package org.example;

public class Account {
    String owner;
    String iban;
    double balance;

    private static int runningNumber = 0;
    // Constructor
    public Account(String name) {
        owner = name;
        balance = 0;

        generate_iban();

    }
    // Getter function
    /* public String getOwner(){
         return owner_;
    }
    public double getBalance() {
        return balance_;
    } */

    // Setter function
    public void setBalance(double amount) {
        balance = amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transferTo(Account otheraccount, double amount) {
        if (this.iban.equals(otheraccount.iban)) {
            return true;
        }
        if (!withdraw(amount)) {
            return false;
        }
        else {
            otheraccount.saveMoney(amount);
        }
        return true;
    }

    // Function to save money
    public void saveMoney(double amount) {
        balance += amount;
    }


    // Function to print all data about the account (owner : IBAN : balance)
    public void printInfo() {
        System.out.println(owner + " : " + iban + " : " + balance);
    }

    private void generate_iban() {
        ++runningNumber;
        String suffix = "";

        if (runningNumber < 10) {
            suffix += "0";
        }
        else if (runningNumber > 99) {
            System.out.println("Too many accounts");
        }

        suffix += Integer.toString(runningNumber);
        iban = "12311708" + suffix;
    }
}
