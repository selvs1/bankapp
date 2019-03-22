package bankapp.account;

import java.util.Date;

public class Transaction {
    private double amount;
    private double balance;
    private Date valuta;


    public Transaction(double amount, double balance) {
        this.amount = amount;
        this.balance = balance;
        //TODO: Kann das sein?? check
        valuta = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public Date getValuta() {
        return valuta;
    }

    @Override
    public String toString() {
        return String.format("Datum: %10b || Kontostand: %10f || Betrag: %10f", valuta.getTime(), balance, balance);
    }

}
