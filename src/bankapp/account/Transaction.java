package bankapp.account;

import java.util.Date;

public class Transaction {
    private double amount;
    private double balance;
    private Date valuta;


    public Transaction(double amount, double balance) {
        this.amount = amount;
        this.balance = balance;
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
        return String.format("LOG:    %1$td.%<tm.%<tY(%<ta) [%<tH:%<tM:%<tS]   <<<<Bewegung:   %2$10.2f>>>>   ##Kontostand:   %3$10.2f##", getValuta(), getAmount(), getBalance());
    }

// Main ist nur ein Test
    public static void main(String[] args) {

        Transaction test = new Transaction(-100, 900);
        System.out.println(test.toString());
        //System.out.println(test.getValuta());
    }
}
