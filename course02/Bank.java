public class Bank {
    private Account allAccounts[];
    private int count=0;//目前帳戶數
    private int maxAccount;//最多只能有這麼多帳戶
    private String bankname;
    private static double interestRate = .006;

    public Bank(int maxAccount, String bankname) {
        this.maxAccount = maxAccount;
        this.bankname = bankname;
        allAccounts = new Account[maxAccount];
    }
    

    public void addAccount(long balance){
        if (count < maxAccount){
            allAccounts[count] = new Account(balance);
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
            System.out.println(bankname + ", Account " + i + ": " + allAccounts[i].getBalance());
        }

    }
    public static void main(String[] args) {
        // Bank myBank = new Bank(2, "mybank");
        // myBank.addAccount(100);
        // myBank.addAccount(200);
        // myBank.addAccount(300);//應該不會被加進去
        // myBank.displayAllAccounts();


    }
  
}
