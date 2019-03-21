package bankapp.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class BankTest {

    private static final String PIN = "12345";
    private static final double BALANCE = 5000.0;
    private static final double AMOUNT = 1000.0;
    private static final double DELTA = 0.01;

    private Bank bank;
    private int accountNr;

    @Before
    public void init() throws Exception {
        bank = new BankImpl();
        if (Math.random() < 0.5)
            accountNr = bank.openAccount(AccountType.PERSONAL, PIN, BALANCE);
        else
            accountNr = bank.openAccount(AccountType.SAVINGS, PIN, BALANCE);
    }

    @Test
    public void testOpen() {
        assertNotNull(accountNr);
    }

    @Test
    public void testBalance() throws BankException {
        assertEquals(BALANCE, bank.getBalance(accountNr, PIN), DELTA);
    }

    @Test(expected = BankException.class)
    public void testBalanceInvalidNr() throws BankException {
        assertNull(bank.getBalance(-1, PIN));
    }

    @Test(expected = BankException.class)
    public void testBalanceInvalidPIN() throws BankException {
        assertNull(bank.getBalance(accountNr, ""));
    }

    @Test
    public void testDeposit() throws BankException {
        bank.deposit(accountNr, AMOUNT);
        assertEquals(BALANCE + AMOUNT, bank.getBalance(accountNr, PIN), DELTA);
    }

    @Test
    public void testDepositInvalidNr() throws BankException {
        try {
            bank.deposit(-1, AMOUNT);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertEquals(BALANCE, bank.getBalance(accountNr, PIN), DELTA);
        }
    }

    @Test
    public void testWithdraw() throws BankException {
        bank.withdraw(accountNr, PIN, AMOUNT);
        assertEquals(BALANCE - AMOUNT, bank.getBalance(accountNr, PIN), DELTA);
    }

    @Test
    public void testWithdrawInvalidNr() throws BankException {
        try {
            bank.withdraw(-1, PIN, AMOUNT);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertEquals(BALANCE, bank.getBalance(accountNr, PIN), DELTA);
        }
    }

    @Test
    public void testWithdrawInvalidPIN() throws BankException {
        try {
            bank.withdraw(accountNr, "", AMOUNT);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertEquals(BALANCE, bank.getBalance(accountNr, PIN), DELTA);
        }
    }

    @Test
    public void testWithdrawInvalidAmount() throws BankException {
        try {
            bank.withdraw(accountNr, PIN, -AMOUNT);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertEquals(BALANCE, bank.getBalance(accountNr, PIN), DELTA);
        }
    }

    @Test
    public void testClose() throws BankException {
        bank.closeAccount(accountNr, PIN);
        try {
            bank.getBalance(accountNr, PIN);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
        }
    }

    @Test
    public void testCloseInvalidNr() throws BankException {
        try {
            bank.closeAccount(-1, PIN);
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertNotNull(bank.getBalance(accountNr, PIN));
        }
    }

    @Test
    public void testCloseInvalidPIN() throws BankException {
        try {
            bank.closeAccount(accountNr, "");
            fail("Exception expected: " + BankException.class.getName());
        } catch (BankException ex) {
            assertNotNull(bank.getBalance(accountNr, PIN));
        }
    }
}