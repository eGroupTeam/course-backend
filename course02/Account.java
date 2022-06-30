public class Account {
  /*
    private static double interestRate = .006;
    public static void setInterestRate(double interestRate){
      Account.interestRate = interestRate;
    }
   */ 
  
    private long balance;
    
    public Account(long balance){
      this.balance = balance;
    }//建構式
    public void withdraw(long amount){
      if(amount > 0  && balance-amount >= 0){
        balance-=amount;
      }
      
    }
    public void deposit(long amount){
      if (amount > 0){
        balance+=amount;
      }
      
    }
  /*  
    public void calculateInterest(){
      balance+=(balance*interestRate);
    }
   */   
    public long getBalance(){
      return balance;
    }
    
  
    public static void main(String[] args) {
      
      Account aAccount = new Account(10000);
      Account bAccount = new Account(20000);
      bAccount.balance += 10000; //可以
      bAccount.deposit(10000);
      aAccount.deposit(-100);
      aAccount.withdraw(12000);
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
  