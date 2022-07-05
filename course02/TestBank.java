public class TestBank {
    public static void main(String[] args) {
        Bank myBank = new Bank(2);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);//應該不會被加進去
        System.out.println("第一間銀行");
        System.out.println("帳戶有:");
        myBank.displayAllAccounts();
        System.out.println("增加利息後:");
        myBank.calculateInterest();
        myBank.displayAllAccounts();

        System.out.println("------------");
        System.out.println("第二間銀行");
        Bank ginnyBank = new Bank(5);
        ginnyBank.addAccount(100000);
        ginnyBank.addAccount(200000);
        ginnyBank.addAccount(300000);
        ginnyBank.addAccount(5000);
        ginnyBank.addAccount(600);
        System.out.println("帳戶有:");
        ginnyBank.displayAllAccounts();
        System.out.println("增加利息後:");
        ginnyBank.calculateInterest();
        ginnyBank.displayAllAccounts();

    }
}
