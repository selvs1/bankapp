package bankapp.bank;

import bankapp.account.Account;
import bankapp.account.PersonalAccount;
import bankapp.account.SavingsAccount;
import bankapp.account.Transaction;

import java.io.*;
import java.util.*;

public class BankImpl implements Bank {

    private int lastAccountNr = 0;
    private Map<Integer, Account> accounts = new HashMap();
    private static final String DATA_FILE = ".diniMuetter";

    public BankImpl() {
        loadData();

    }


    public void closeAccount(int nr, String pin) throws BankException {
        System.out.println(getAccounts());
        Account account = findAccount(nr);
        account.checkPin(pin);
        accounts.remove(nr);
        saveData();
    }

    public void deposit(int nr, double amount) throws BankException {
        Account account = findAccount(nr);
        account.deposit(amount);
        saveData();


/*
        if (amount <= 0) return false;
        if (account == null) return false;
        return account.deposit(amount);
*/
    }

    private Account findAccount(int nr) throws BankException {

        if (accounts.get(nr) != null)
            return accounts.get(nr);

        throw new BankException("findAccount: account does not exist");
    }

    public List<Account> getAccounts() {

        List<Account> list = new ArrayList<>(accounts.values());
        //accounts.values() --> gibt ein Set zurück, die obige ausdruck, nimmt den Set und wandelt in eien ArrayList um
        // Der Klammerausdruckt gibt ein Set zurück

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
        saveData();
        return accountNr;

    }

    public void withdraw(int nr, String pin, double amount) throws BankException {
        Account account = findAccount(nr);
        account.checkPin(pin);
        account.withdraw(amount);
        saveData();

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


    private void saveData() {
        // letzte Kontonummer
        // Bankkonten mittels Objektserialisierung
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(accounts);
            out.writeInt(lastAccountNr);
        } catch (IOException e) {
            System.out.println("saveData: Konnte nicht gesepeichert werden");
        }

    }


    private void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {

            FileInputStream fileIn = new FileInputStream(DATA_FILE);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Map<Integer, Account> konten = (HashMap) objectIn.readObject();
            int letzteAccountNummer = objectIn.readInt();

            //TODO: Test --> nach kann nach der Kontrolle enfernt werden
            System.out.println("The Object has been read from the file");
            System.out.println("lastAccountNumber = " + letzteAccountNummer);
            System.out.println("accounts " + konten);

            accounts = konten;
            lastAccountNr = letzteAccountNummer;


        } catch (IOException e) {
            System.out.println("loadData: konnte nicht geladen werden");
        } catch (ClassNotFoundException e) {
            System.out.println("sheiss exception");
        }

    }

}
