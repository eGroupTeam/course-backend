public class TestBank {
    public static void main(String[] args) {
        // Bank myBank = new Bank(2);
        // myBank.addAccount(10000);
        // myBank.addAccount(20000);
        // myBank.addAccount(30000);//應該不會被加進去
        // myBank.displayAllAccounts();
        // myBank.calculateInterest();
        // myBank.displayAllAccounts();

        Bank myBank1Bank = new Bank(3);
        myBank1Bank.addAccount(20000);
        myBank1Bank.addAccount(30000);
        myBank1Bank.addAccount(40000);//應該不會被加進去
        System.out.println("**************");
        myBank1Bank.displayAllAccounts();
        System.out.println("**************");
        myBank1Bank.calculateInterest();
        System.out.println("**************");
        myBank1Bank.displayAllAccounts();
        System.out.println("**************");



    }
}
