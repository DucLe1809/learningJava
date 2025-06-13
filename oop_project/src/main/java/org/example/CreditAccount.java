package org.example;

public class CreditAccount extends Account {
    double credit_limit_;

    public CreditAccount(String owner) {
        super(owner);
        credit_limit_ = 0.0;
    }
    // Getter function
    /* public double getLimit() {
        return credit_limit_;
    }*/

    // Setter function

    public void setLimit(double limit) {
        credit_limit_ = limit;
    }

    public boolean withdraw(double amount) {
        if (balance - amount >= -credit_limit_) {
            this.setBalance(balance - amount);
            return true;
        }
        else {
            System.out.println("Exceeded credit limit");
            return false;
        }
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("  " + "::: " + "Credit limit : "  + credit_limit_);
    }
}
