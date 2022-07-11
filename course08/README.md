# course-backend
## 第八章 資料庫應用 1 基礎SQL

* [SQL語法教學](https://www.1keydata.com/tw/sql/sql.html)

Java 7之後支援try with resources語法，在try()裡放進必須close的資源，在使用後會close，萬一有問題，也會自動close。我們接下來全部會改用這樣的語法。

### like
找出有名字有特定字的客戶

    String sql = "select id, name, address, weight from customer where name like ?";

    stmt.setString(1, "%"+name+"%");

**注意 不能寫成:
    String sql = "select id, name, address, weight from customer where name like '%?%'";

### order by的處理
如果我們想讓使用者可以設定依體重排序，要怎麼做?

    String sql = "select id, name, address, weight from customer order by weight";

### between的處理
如果我們想查體重介於50到70公斤的客戶資料? 
如何設為變數?

    String sql = "select id, name, address, weight from customer where weight between ? and ?";

### distinct
如果我們要列出customer裡出現過的address

    String sql = "select distinct address from customer";

### group by
如果我們要知道同一個address的人數?

    String sql = "select address, COUNT(address) from customer group by address";

### having
如果我們要知道同一個address超過一個人的所有地址?

    String sql = "select address from customer group by address having COUNT(address)>2";

這樣的寫法有點累，每增加一個情境就要增加一個對應的controller，有比較聰明的寫法，有機會再補充。

### 作業:試著應用在employee
* 找出有名字有特定字的員工
* 依照department排序
* 增加一個薪資的欄位，並且可以讓管理者查某個薪資範圍內的員工
* 如果我們要列出employee裡出現過的department
* 如果我們要知道同一個department的人數
* 要知道只有一個人的department