package bankapp.atm;

import bankapp.bank.AccountType;
import bankapp.bank.Bank;
import bankapp.bank.BankException;
//import bankapp.bank.BankImpl;

import java.util.Scanner;

public class ATM {

    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    private Scanner scanner = new Scanner(System.in);

    public void run() {
        try {


            while (true) {
                System.out.println("  A  TTTTT M   M");
                System.out.println(" AAA   T   MM MM");
                System.out.println("A   A  T   M M M");
                System.out.println();
                System.out.println("A  Open Account");
                System.out.println("B  Get Balance");
                System.out.println("C  Deposit");
                System.out.println("D  Withdraw");
                System.out.println("E  Close Account");
                System.out.println("X  Exit");
                System.out.println();
                System.out.print("> ");
                String choice = scanner.nextLine().toUpperCase();
                switch (choice) {
                    case "A":
                        openAccount();
                        break;
                    case "B":
                        getStatement();
                        //getBalance();
                        break;
                    case "C":
                        deposit();
                        break;
                    case "D":
                        withdraw();
                        break;
                    case "E":
                        closeAccount();
                        break;
                    case "X":
                        // Entfernen listAccounts();
                        //System.exit(0);
                        return;
                    default:
                        System.out.println("Invalid input");
                }
                System.out.println("Hit Enter to continue...");
                scanner.nextLine();
            }
        } catch (BankException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("run: Invalid amount");
        } catch (RuntimeException e) {
            System.out.println("run: unexpected error");
        }
    }

    private void openAccount() {

        try {
            System.out.println("Which account type do you wish? Enter \"0\" for PERSONAL ACCOUNT or \"1\" for SAVINGS ACCOUNT");
            AccountType accountType = null;
            int inputAccountType = Integer.parseInt(scanner.nextLine());

            switch (inputAccountType) {
                case 0:
                    accountType = AccountType.PERSONAL;
                    break;
                case 1:
                    accountType = AccountType.SAVINGS;
                    break;
                default:
                    System.out.println("Accounttype: invalid input");
                    break;
            }
            System.out.println("Define your new PIN");
            String pin = scanner.nextLine();
            int accountNumber;

            System.out.println("Do you want to deposite a amount? type something");
            String answer = scanner.nextLine();

            if (answer.isEmpty()) {
                accountNumber = bank.openAccount(accountType, pin, 0);
            } else {
                System.out.println("amout to deposite:");
                String balance = scanner.nextLine();
                accountNumber = bank.openAccount(accountType, pin, Double.parseDouble(balance));
            }
            System.out.println("Your account number:    " + accountNumber);

        } catch (RuntimeException e) {
            System.err.println("openAccount: unexpected error");
        }
    }

/*
    private void getBalance() throws BankException {
        try {

            System.out.println("Enter your account Number:");
            int accountNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter your PIN");
            String pin = scanner.nextLine();
            Double balance = bank.getBalance(accountNumber, pin);
            if (balance != null)
                System.out.format("Balance: %+.2f%n", balance);
            else
                System.out.println("An error occurred");
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }
*/

    private void getStatement() throws BankException {
        try {
            System.out.println("Enter your account Number:");
            int accountNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter your PIN");
            String pin = scanner.nextLine();
            bank.getTransactions(accountNumber, pin);
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposit() throws BankException {

        try {
            System.out.println("Enter your account Number:");
            int accountNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("How much do you want to deposit?");
            double toDeposit = Double.parseDouble(scanner.nextLine());
            bank.deposit(accountNumber, toDeposit);
           /* if ((!bank.deposit(accountNumber, toDeposit)))
                System.out.println("error");*/

        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    private void withdraw() throws BankException {

        try {


            System.out.println("Enter your account number");
            int accountNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter your pin");
            String pin = scanner.nextLine();

            System.out.println("How much do you want to deposit?");
            double amount = Double.parseDouble(scanner.nextLine());

            bank.withdraw(accountNumber, pin, amount);

/*
            if ((!bank.withdraw(accountNumber, pin, amount)))
                System.out.println("Withdraw - error");
*/
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeAccount() throws BankException {
        try {

            System.out.println("Enter your account number");
            int accountNumber = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter your pin");
            String pin = scanner.nextLine();

            bank.closeAccount(accountNumber, pin);

/*
            if (!(bank.closeAccount(accountNumber, pin)))
                System.out.println("close account - error");
*/

        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

/*
    zu löschen im Auftrag von Herrn Fischli
    private void listAccounts() {
        System.out.println(bank.getAccounts());
    }
*/
}