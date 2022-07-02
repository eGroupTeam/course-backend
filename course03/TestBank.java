public class TestBank {
    public static void main(String[] args) {
        Bank myBank = new Bank(3);
        myBank.addAccount(10000);
        myBank.addAccount(20000, Bank.AccountType.regular);
        myBank.addAccount(30000, Bank.AccountType.saving);
        myBank.displayAllAccounts();
        myBank.calculateInterest();
        myBank.displayAllAccounts();

    }
}
