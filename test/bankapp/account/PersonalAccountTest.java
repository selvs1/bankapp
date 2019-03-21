package bankapp.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import bankapp.bank.BankException;

public class PersonalAccountTest {

    private static final int NR = 1;
    private static final String PIN = "12345";
    private static final double BALANCE = 2000.0;
    private static final double AMOUNT = 1000.0;
    private static final double DELTA = 0.01;

    private Account account;

    @Before
    public void init() {
        account = new PersonalAccount(NR, PIN, BALANCE);
    }

    @Test
    public void testNr() {
        assertEquals(NR, account.getNr());
    }

    @Test
    public void testPIN() throws BankException {
        account.checkPin(PIN);
    }

    @Test(expected = BankException.class)
    public void testInvalidPIN() throws BankException {
        account.checkPin("");
    }

    @Test
    public void testBalance() {
        assertEquals(BALANCE, account.getBalance(), DELTA);
    }

    @Test
    public void testDeposit() throws BankException {
        account.deposit(AMOUNT);
        assertEquals(BALANCE + AMOUNT, account.getBalance(), DELTA);
    }

    @Test
    public void testDepositInvalidAmount() throws BankException {
        try {
            account.deposit(-AMOUNT);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertEquals(BALANCE, account.getBalance(), DELTA);
        }
    }

    @Test
    public void testWithdraw() throws BankException {
        account.withdraw(AMOUNT);
        assertEquals(BALANCE - AMOUNT, account.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawInvalidAmount() {
        try {
            account.withdraw(-AMOUNT);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertEquals(BALANCE, account.getBalance(), DELTA);
        }
    }

    @Test
    public void testOverdraw() throws BankException {
        account.withdraw(2 * BALANCE);
        assertEquals(-BALANCE, account.getBalance(), DELTA);
    }
}