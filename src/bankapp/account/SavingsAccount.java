package bankapp.account;

import bankapp.bank.BankException;

public class SavingsAccount extends Account {

    public static final double WITHDRAW_LIMIT = 1000;


    public SavingsAccount(int nr, String pin, double balance) {
        super(nr, pin, balance);
    }

    @Override
    public void withdraw(double amount) throws BankException {
        if (amount <= 0)
            throw new BankException("withdraw: negativ or null amount");
        if (amount > WITHDRAW_LIMIT)
            throw new BankException("withdraw: amount is limited");
        if (0 >= balance - amount)
            throw new BankException("withdraw: no negativ, this is not a credit account mf");

        super.withdraw(amount);

        /*
        * amout > balance --> insufficient
        *
        * amount > withdraw limit
        * */


    }
}
