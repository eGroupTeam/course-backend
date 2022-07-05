public class TestBank {
    public static void main(String[] args) {
        System.out.println("====MyBank====");
        Bank myBank = new Bank(2);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);//應該不會被加進去
        myBank.displayAllAccounts();
        myBank.calculateInterest();
        myBank.displayAllAccounts();
        System.out.println("====KevinBank====");
        Bank kevinBank = new Bank(3);
        kevinBank.addAccount(7777777);
        kevinBank.addAccount(3333333);
        kevinBank.addAccount(-200000);
        kevinBank.displayAllAccounts();
        kevinBank.calculateInterest();
        kevinBank.displayAllAccounts();
    }
}
