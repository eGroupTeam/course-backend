public class TestBank {
    public static void main(String[] args) {
        Bank myBank = new Bank(2);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);
        myBank.displayAllAccounts();
        myBank.calculateInterest();
        myBank.displayAllAccounts();

        Bank WeiBank = new Bank(3);
        WeiBank.addAccount(1234);
        WeiBank.addAccount(5647);
        WeiBank.addAccount(5555);
        WeiBank.displayAllAccounts();
        WeiBank.calculateInterest();
        WeiBank.displayAllAccounts();
            
            
        
        
    }
}
