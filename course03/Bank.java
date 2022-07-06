
public class Bank {
  public enum AccountType {
    regular, saving
  }
  private Account allAccounts[];
  private int count=0;//目前帳戶數
  private int maxAccount;//最多只能有這麼多帳戶
  private static double interestRate = .006;

  public Bank(int maxAccount){
    this.maxAccount = maxAccount;
    allAccounts = new Account[maxAccount];
  }
  public void addAccount(long balance){
    //allAccounts[count] = new Account(balance);
    addAccount(balance, AccountType.regular);
  }

  /*
  public void addSavingAccount(long balance){
    allAccounts[count] = new SavingAccount(balance);
    //addAccount(balance, AccountType.regular);
  }
   */

  public void addAccount(long balance, AccountType type){
    //overloading
      
      if (count < maxAccount){        
        switch (type){
          case saving:
            allAccounts[count] = new SavingAccount(balance); 
            break;
          default: allAccounts[count] = new Account(balance);
        }  
        count ++;
      }
      
  }

  public void calculateInterest(){
    for (int i=0; i<count; i++){
      long balance= allAccounts[i].getBalance();
      long interest=Math.round(balance*interestRate);
      allAccounts[i].deposit(interest);

    }
      
  }

  public void displayAllAccounts(){
    for (int i=0; i<count; i++){
      System.out.println(allAccounts[i].getBalance());
    }

  }
  public static void main(String[] args) {
    Bank myBank = new Bank(3);
    myBank.addAccount(10000);
    myBank.addAccount(20000, AccountType.regular);
    myBank.addAccount(30000, AccountType.saving);
    myBank.displayAllAccounts();


  }
  
}
