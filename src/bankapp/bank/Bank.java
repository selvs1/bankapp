package bankapp.bank;

import bankapp.account.Transaction;

import java.util.List;

public interface Bank {

    int openAccount(AccountType type, String pin, double balance);

    double getBalance(int nr, String pin)throws BankException;

    void deposit(int nr, double amount)throws BankException;

    void withdraw(int nr, String pin, double amount)throws BankException;

    void closeAccount(int nr, String pin)throws BankException;

    List<Transaction> getTransactions(int nr, String pin) throws BankException;

}