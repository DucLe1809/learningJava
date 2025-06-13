package org.example;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void printAccounts(ArrayList<Account> accounts) {
        System.out.println("Printing all accounts:");
        for (Account account : accounts) {
            System.out.print("  ");
            account.printInfo();
        }
    }

    public static void main(String[] args) {

    ArrayList<Account> accounts = new ArrayList<>();
    System.out.println("1. Creating accounts");
    Account normalAccount = new Account("John");
    normalAccount.setBalance(500);
    normalAccount.saveMoney(200);

    CreditAccount creditAccount = new CreditAccount("Kaylee");
    creditAccount.setLimit(100);
    creditAccount.saveMoney(200);

    accounts.add(normalAccount);
    accounts.add(creditAccount);

    printAccounts(accounts);

    System.out.println("2. Transfer money from normal accounts to credit accounts");
    if (normalAccount.transferTo(creditAccount, 300)) {
        System.out.println("Transfer successful");
    }
    else {
        System.out.println("Transfer failed");
        }
    printAccounts(accounts);
    }
}