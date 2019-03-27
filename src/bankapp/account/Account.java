package bankapp.account;

import bankapp.bank.BankException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable {

    protected double balance;
    //Identification
    protected int nr;
    protected String pin;
    private List<Transaction> transactions = new ArrayList<>();


    public Account(int nr, String pin) {
        this(nr, pin, 0.0);
    }

    public Account(int nr, String pin, double balance) {
        this.nr = nr;
        this.pin = pin;
        this.balance = balance;
    }

    public void checkPin(String pin) throws BankException {
        if(!(this.pin.equals(pin)))
            throw new BankException("checkPin: pin doesn't match with account pin");
    }


    public void deposit(double amount) throws BankException{
        //TODO: geht auch amount > balance
        if (amount <= 0)
            throw new BankException("deposit: negativ or null amount");
        balance += amount;
        transactions.add(new Transaction(amount, balance));

    }

    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return account.nr == nr;
    }

    public double getBalance() {
        return balance;

    }

    public int getNr() {

        return nr;
    }

    public int hashCode() {
        return nr;

    }

    public String toString() {
        return getClass().getSimpleName() + " {nr=" + nr + ", balance=" + balance + "}";

    }

    public void withdraw(double amount) throws BankException {
        if (amount <= 0)
            throw new BankException("withdraw: negativ or null amount");
        balance -= amount;
        transactions.add(new Transaction(-1*amount, balance));

    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


}
