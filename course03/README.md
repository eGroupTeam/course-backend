# course-backend
## 第三章 程式語言基礎 2
### 繼承
就如同前一章提到的，繼承是重複利用程式碼的方法之一，那我們怎麼重複利用程式碼呢? 假設我們有一種特別的帳戶:Saving Account，在這種帳戶裡，餘額必須保持5000，其他的規格跟原本的Account一樣。那要怎麼處理? 我們首先會先產生一個SavingAccount.java，利用extends，來指定繼承Account。

這時候Account稱為superclass，SavingAccount就被稱為subclass。

    public class SavingAccount extends Account{
    }

### override
當我們要修改原本的規則，可以定義新的withdraw()，這時候，我們就覆蓋 (override)了Account裡的withdraw。簡單的說，就是優先使用這個方法。

由於Account跟SavingAccount是不同的類別，所以，也不能直接去取得balance，就必須靠getBalance()去取得。接下來利用super.withdraw()去透過Account裡的withdraw()去提款。

執行的時候就會看到:
Implicit super constructor Account() is undefined for default constructor. Must define an explicit constructor.
也就是SavingAccount必須要有自己的建構式

    public SavingAccount(long balance){
      super(balance);
    }

這時候，我們就會發現如果餘額小於5000就會無法領款了。

### 什麼是多型/同名異式(polymorphism)? 為何需要多型/同名異式?
當我們新增了新的SavingAccount之後，在Bank裡要做甚麼改變呢? Account陣列裡可以放Account也可以放SavingAccount，可以發現大部分的程式都不用更動，在呼叫時會因為儲存的類別而去呼叫對應的方法，這就是所謂的polymorphism。

polymorphism透過dynamic binding完成。

    private Account allAccounts[];

      if (count < maxAccount){        
        switch (type){
          case saving:
            allAccounts[count] = new SavingAccount(balance); 
            break;
          default: allAccounts[count] = new Account(balance);
        }  
        count ++;
      }

### 抽象類別
當一個類別裡的方法需要讓subclass去實作時，可以將方法宣告為抽象(abstract)方法，一個具有抽象方法的類別就是所謂抽象類別。
(參考Trip.java、HolidayTrip.java、RegularTrip.java)

    public abstract class Trip {
      public abstract double getCharge();
    }

如果沒有在class定義寫abstract，就會看到:
The type Trip must be an abstract class to define abstract methods

如果沒有在method定義寫abstract，就會看到:
This method requires a body instead of a semicolon

如果沒有寫getCharge();
The type HolidayTrip must implement the inherited abstract method 

### interface
當所有方法都是抽象方法時，這種特殊的類別就是interface。宣告為interface有個好處，在java裡只能繼承一個類別，卻可以實作多個interface。
(參考Rental.java、GPS.java)

    public interface Rental {
      public abstract double getCharge();
    }

如果implements寫成extends:
The type Rental cannot be the superclass of GPS; a superclass must be a class

### 作業 讓Trip實作Rental，並且根據TestTrip寫TestRental，讓GPS跟不同的Trip可以一起被租用

補充
[三個Java小程式做出簡易小遊戲](https://deeplink.tw/#/webinarContent/TvVD9SlbLzt8riAQzbna)
[物件導向補充](https://www.notion.so/tiffany26/6befb1a5da5b43ac87e1163aaf1be10f)


