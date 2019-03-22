package bankapp.bank;

import bankapp.account.Account;
import bankapp.account.PersonalAccount;
import bankapp.account.SavingsAccount;
import bankapp.account.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankImpl implements Bank {

    private int lastAccountNr = 0;
    // private ArrayList<Account> accounts = new ArrayList<>();

    private Map<Integer, Account> accounts = new HashMap();


    public BankImpl() {


    }


    public void closeAccount(int nr, String pin) throws BankException {
        System.out.println(getAccounts());
        Account account = findAccount(nr);
        account.checkPin(pin);
        accounts.remove(nr);
    }

    public void deposit(int nr, double amount) throws BankException {
        Account account = findAccount(nr);
        account.deposit(amount);


/*
        if (amount <= 0) return false;
        if (account == null) return false;
        return account.deposit(amount);
*/
    }

    private Account findAccount(int nr) throws BankException {
/*        for (Account account : accounts) {
            if (account.getNr() == nr)
                return account;
        }*/

        if (accounts.get(nr) != null)
            return accounts.get(nr);

        throw new BankException("findAccount: account does not exist");
    }

    public List<Account> getAccounts() {

        List<Account> list = new ArrayList<>(accounts.values()); //TODO: Was macht der Ausdruck in der Klammer? Wie findet man das?
        //accounts.values() --> gibt ein Set zurück, die obige ausdruck, nimmt den Set und wandelt in eien ArrayList um

        AccountComparator sortForMe = new AccountComparator();

/*
        Fischli
        accounts.sort(new AccountComparator());
*/
        list.sort(sortForMe);

        return list;

    }

    public double getBalance(int nr, String pin) throws BankException {
        Account account = findAccount(nr);
        account.checkPin(pin);
        return account.getBalance();

    }

    public int openAccount(AccountType accountType, String pin, double balance) {
        // Lohnkonto eröffnen
        if (accountType == AccountType.PERSONAL)
            accounts.put(lastAccountNr, new PersonalAccount(lastAccountNr, pin, balance));
        //accounts.add(new PersonalAccount(lastAccountNr, pin, balance));

        // Sparkonto eröffnen
        if (accountType == AccountType.SAVINGS)
            accounts.put(lastAccountNr, new SavingsAccount(lastAccountNr, pin, balance));
        //accounts.add(new SavingsAccount(lastAccountNr, pin, balance));
        int accountNr = lastAccountNr;
        lastAccountNr++;
        return accountNr;

    }

    public void withdraw(int nr, String pin, double amount) throws BankException {
        Account account = findAccount(nr);
        account.checkPin(pin);
        account.withdraw(amount);

/*
        if (amount <= 0) return false;
        if (account == null) return false;
        if (account.checkPin(pin))
            return account.withdraw(amount);
        return false;
*/

    }


    public List<Transaction> getTransactions(int nr, String pin) throws BankException {
        Account account = findAccount(nr);
        account.checkPin(pin);
        return account.getTransactions();
    }

}
