package bankapp.bank;


import bankapp.account.Account;

import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {

    public AccountComparator() {

    }

    public int compare(Account account1, Account account2) {
       /* Account value1 = (Account) account1;
        Account value2 = (Account) account2;*/



        return Double.compare(account1.getBalance(), account2.getBalance());

/*
        Implementiert von Herrn Fischli
        return value1 == value2 ? 0 : (value1 < value2 ? -1 : 1);
*/
    }


}
