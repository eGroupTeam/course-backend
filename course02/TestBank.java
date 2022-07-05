public class TestBank {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Bank1:");
        Bank myBank = new Bank(2,.007);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.displayAllAccounts();
        System.out.println();
        System.out.println("利息計算後：");
        myBank.calculateInterest();
        myBank.displayAllAccounts();

        System.out.println();
        System.out.println("----------我是分隔線----------");
        System.out.println();
        System.out.println("Bank2:");

        Bank yourbank = new Bank(6,.01);
        yourbank.addAccount(17000);
        yourbank.addAccount(25000);
        yourbank.addAccount(32000);
        yourbank.addAccount(46000);
        yourbank.addAccount(55000);
        yourbank.displayAllAccounts();
        System.out.println();
        System.out.println("利息計算後：");
        yourbank.calculateInterest();
        yourbank.displayAllAccounts();
        
    }
}
