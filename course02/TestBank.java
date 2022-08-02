public class TestBank {
    public static void main(String[] args) {
        Bank myBank = new Bank(2);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);//應該不會被加進去
        myBank.displayAllAccounts();
        myBank.calculateInterest();
        myBank.displayAllAccounts();

        Bank myBank2 = new Bank(3);
        myBank2.addAccount(10001);
        myBank2.addAccount(10002);
        myBank2.addAccount(10003);
        myBank2.displayAllAccounts();
        myBank2.calculateInterest();
        myBank2.displayAllAccounts();

    }
}
