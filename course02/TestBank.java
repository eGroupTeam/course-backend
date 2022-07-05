public class TestBank {
    public static void main(String[] args) {
        System.out.println("Ben的bank");
        
        Bank myBank = new Bank(2,0.06);
        myBank.addAccount(10000);
        myBank.addAccount(20000);
        myBank.addAccount(30000);//應該不會被加進去
        System.out.println("----------計算前---------");
        myBank.displayAllAccounts();
        myBank.calculateInterest();
        System.out.println("----------計算後---------");
        myBank.displayAllAccounts();
        System.out.println();
        System.out.println("dihung的bank");
        Bank dihungBank = new Bank(5 , 0.05);

        dihungBank.addAccount(12500);
        dihungBank.addAccount(23000);
        dihungBank.addAccount(35000);
        dihungBank.addAccount(79090);
        dihungBank.addAccount(45000);
        System.out.println("----------計算前---------");
        dihungBank.displayAllAccounts();
        dihungBank.calculateInterest();
        System.out.println("----------計算後---------");
        dihungBank.displayAllAccounts();

    }
}
