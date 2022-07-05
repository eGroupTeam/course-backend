public class SavingAccount extends Account{
  
  public SavingAccount(long balance){
    super(balance);
  }
  public void withdraw(long amount){
    if(amount > 0  && getBalance()-amount >= 5000){
      super.withdraw(amount);
    }
        
  }
  public static void main(String[] args) {
      
    SavingAccount aAccount = new SavingAccount(10000);
    SavingAccount bAccount = new SavingAccount(20000);
    //bAccount.balance += 10000; //不可以
    bAccount.deposit(10000);
    aAccount.deposit(-100);
    aAccount.withdraw(6000); //不可以
    System.out.println(aAccount.getBalance());
    System.out.println(bAccount.getBalance());
    /*
    Account.setInterestRate(.005);
    aAccount.calculateInterest();
    bAccount.calculateInterest();
    System.out.println(aAccount.getBalance());
    System.out.println(bAccount.getBalance());
      */ 
  }
}
