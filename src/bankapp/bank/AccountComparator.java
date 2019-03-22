package bankapp.bank;


import bankapp.account.Account;

import java.util.Comparator;

public class AccountComparator<E> implements Comparator<E> {

    public AccountComparator() {

    }

    public int compare(Object account1, Object account2) {
        Account value1 = (Account) account1;
        Account value2 = (Account) account2;

        return Double.compare(value1.getBalance(), value2.getBalance());

/*
        Implementiert von Herrn Fischli
        return value1 == value2 ? 0 : (value1 < value2 ? -1 : 1);
*/
    }

}
