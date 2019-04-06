package bankapp.account;

public class PersonalAccount extends Account {

    private static double INTEREST_RATE = 0.01;
    public PersonalAccount(int nr, String pin, double balance) {
        super(nr, pin, balance);

    }

    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }


}
