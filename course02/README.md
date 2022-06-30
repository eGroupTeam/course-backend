# course-backend
## 第二章 程式語言基礎 1
### 從main()談static method
(參考Hello.java)
每個class都會有一些屬性及方法，main()是個最特別的方法，執行Hello會自動呼叫main()，這是個static方法，在static方法裡只能呼叫static方法，不能呼叫一般的方法，因為一般的方法只會在產生物件後才存在。反過來，一般方法中可以呼叫static方法，因為static方法未產生物件前就存在了。 

### 物件是甚麼?
(參考Account.java)
可以想像類別就是個樣板，依照這個樣板產生出每個物件，每個物件會有一樣的屬性、及方法。但是，我們可以利用建構式來取得屬性的起始值，當我們呼叫物件的方法時，只有這個物件會受到影響。

### static方法及static屬性
(參考Account.java)
從Account.java可以看到balance在aAccount及bAccount中是獨立的，然而，interestRate卻是由aAccount及bAccount共享。

### 為什麼屬性要設為private? 甚麼是封裝? 為何需要封裝?
(參考Account.java)
當balance設為private時，就不能直接更動或取得balance，balance都由Account裡的deposit()、withdraw()來更動，取得也只能透過getBalance()，這時候就將balance的處理規則封裝在Account裡。

### 在類別裡可以包括其他類別嗎? (類別組合/Composition)
(參考Account.java、Bank.java、TestBank.java)
程式碼的重複利用方式有很多種，除了繼承之外，透過類別組合來重複利用程式，或者去將複雜的邏輯解構為不同的類別，是個很重要的概念，例如，在Account裡，主要是處理帳戶的邏輯，在Bank裡就處理管理帳戶的邏輯(如:新增)。另外，我們也將利息計算從Account移到Bank裡，以確保針對所有帳號計算利息。

在設計Bank的時候，我們也對Bank進行類別組合封裝，也就是使用Bank的時候，完全不用管Bank裡還有Account。換句話講就是把Account封裝在Bank裡，避免未來在異動Account的時候，將影響盡量限制在Account及Bank，盡量不影響其他使用Bank的程式。物件導向設計的精彩之處就在於寫程式時都要去思考未來的維護工作。

### 作業: 修改一下TestBank.java，新增一個Bank及一些帳號，並請針對所有Bank計算利息。