package bankapp;


import bankapp.account.Account;
//import bankapp.atmgui.ATM;
import bankapp.atm.ATM;
import bankapp.bank.BankImpl;

public class Main {
    public static void main(String[] args){


        BankImpl ubs = new BankImpl();
        ATM atmBiel = new ATM(ubs);


        atmBiel.run();

        for (Account account : ubs.getAccounts()) {
            System.out.println(account);
        }

    }
}