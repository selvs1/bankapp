package bankapp.bank;

import java.io.Serializable;

public class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}
