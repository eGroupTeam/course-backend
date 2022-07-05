public class TestBank {
    public static void main(String[] args) {

        Bank myBank = new Bank(2);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);//應該不會被加進去
        System.out.println("myBank: ");
        myBank.displayAllAccounts();
        myBank.calculateInterest();
        System.out.println("After calculateInterest");
        myBank.displayAllAccounts();

        System.out.println();

        Bank shiunBank = new Bank(2);
        shiunBank.addAccount(66666);
        shiunBank.addAccount(123456);
        shiunBank.addAccount(54321);
        System.out.println("shiunBank: ");
        shiunBank.displayAllAccounts();
        shiunBank.calculateInterest();
        System.out.println("After calculateInterest");
        shiunBank.displayAllAccounts();

        
    }
}
