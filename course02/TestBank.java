public class TestBank {
    public static void main(String[] args) {
        Bank myBank = new Bank(2);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);//應該不會被加進去
        System.out.println("--Bank1--");
        System.out.println("Accounts:");
        System.out.println("Before calculating interests:");
        myBank.displayAllAccounts();
        System.out.println("After calculating interests:");
        myBank.calculateInterest();
        myBank.displayAllAccounts();

        Bank altBank = new Bank(3);
        altBank.addAccount(0);
        altBank.addAccount(5000000);
        altBank.addAccount(1101);
        System.out.println("\n\n--Bank2--");
        System.out.println("Accounts:");
        System.out.println("Before calculating interests:");
        altBank.displayAllAccounts();
        System.out.println("After calculating interests:");
        altBank.calculateInterest();
        altBank.displayAllAccounts();

    }
}
