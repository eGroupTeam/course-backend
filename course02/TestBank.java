public class TestBank {
    public static void main(String[] args) {
        Bank[] allBank = new Bank[2];
        allBank[0] = new Bank(2, "mybank");
        allBank[1] = new Bank(3, "shiunbank");

        allBank[0].addAccount(2000);
        allBank[0].addAccount(3000);

        allBank[1].addAccount(5000);
        allBank[1].addAccount(6000);
        allBank[1].addAccount(7000);

        for(int i = 0; i < allBank.length; i++){
            allBank[i].displayAllAccounts();
            allBank[i].calculateInterest();
        }
        System.out.println("\n=========After calculateInterest=========\n");
        for(int i = 0; i < allBank.length; i++){
            allBank[i].displayAllAccounts();
        }

        // Bank myBank = new Bank(2);
        // myBank.addAccount(10000);
        // myBank.addAccount(20000);
        // myBank.addAccount(30000);//應該不會被加進去
        // System.out.println("myBank: ");
        // myBank.displayAllAccounts();
        // myBank.calculateInterest();
        // System.out.println("After calculateInterest");
        // myBank.displayAllAccounts();

        // System.out.println();

        // Bank shiunBank = new Bank(2);
        // shiunBank.addAccount(66666);
        // shiunBank.addAccount(123456);
        // shiunBank.addAccount(54321);
        // System.out.println("shiunBank: ");
        // shiunBank.displayAllAccounts();
        // shiunBank.calculateInterest();
        // System.out.println("After calculateInterest");
        // shiunBank.displayAllAccounts();
        

        
    }
}
