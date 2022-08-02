public class TestAccount {
    public static void main(String[] args) {
      
        Account aAccount = new Account(10000);
        Account bAccount = new Account(20000);
        //bAccount.balance += 10000;
        //Error: The field Account.balance is not visible
        bAccount.deposit(10000);
        aAccount.deposit(-100);
        aAccount.withdraw(12000);
        System.out.println(aAccount.getBalance());
        System.out.println(bAccount.getBalance());
        /* 
        Account.setInterestRate(.005);
        aAccount.calculateInterest();
        bAccount.calculateInterest();
        */
        System.out.println(aAccount.getBalance());
        System.out.println(bAccount.getBalance());
         
      }
}
