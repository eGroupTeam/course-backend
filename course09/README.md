# course-backend

## 第九章 資料庫應用 2 基礎 SQL2、資料庫關聯

### 基礎 SQL 2

- [SQL 語法教學](https://www.1keydata.com/tw/sql/sql.html)
- CONCAT

  SELECT concat(name, ' ', address) as title FROM customer

- SUBSTR
- TRIM
- Length
- Replace

### 資料庫關聯

到目前為止，我們只針對單一資料表進行 CRUD，接下來，我們要針對多資料表進行操作

請新增一些新的資料表

    CREATE TABLE practice.product ( id INT NOT NULL AUTO_INCREMENT , name VARCHAR(30) NOT NULL , price INT NOT NULL , PRIMARY KEY (id));

    CREATE TABLE practice.sales_order (
    id INT NOT NULL AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_time TIMESTAMP NOT NULL,
    PRIMARY KEY (id));

    ALTER TABLE sales_order ADD CONSTRAINT order_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE RESTRICT ON UPDATE RESTRICT;

    CREATE TABLE practice.sales_order_item ( id INT NOT NULL AUTO_INCREMENT , order_id INT NOT NULL , product_id INT NOT NULL , amount INT NOT NULL , price INT NOT NULL , PRIMARY KEY (id));

    ALTER TABLE sales_order_item ADD CONSTRAINT order_item_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE RESTRICT ON UPDATE RESTRICT;

    ALTER TABLE sales_order_item ADD CONSTRAINT order_item_order FOREIGN KEY (order_id) REFERENCES sales_order(id) ON DELETE RESTRICT ON UPDATE RESTRICT;

請先輸入 3 筆以上的資料到 product 資料表，並新增至少一筆資料到 sales_order，sales_order_item 包含至少一筆資料

#### 匯出、匯入

#### join

- [join](https://www.w3schools.com/sql/sql_join.asp)
- [join](https://www.1keydata.com/tw/sql/sqljoins.html)
- [outer join](https://www.1keydata.com/tw/sql/sqlouterjoin.html)
- [What's the Difference Between Having Multiple Tables in FROM and Using JOIN?](https://learnsql.com/blog/joins-vs-multiple-tables-in-from/)

先試試看如何去取得兩個資料表的內容

    select sales_order_item.id, name, sales_order_item.price, amount from product, sales_order_item where sales_order_item.product_id = product.id

    select sales_order_item.id, name, sales_order_item.price, amount from product join sales_order_item ON sales_order_item.product_id = product.id

如何取得產品的總銷量?

    select product_id, SUM(amount) from sales_order_item group by product_id

如果也要看到產品的名稱?

    select product_id, name, SUM(amount) as total from sales_order_item join product on product.id = sales_order_item.product_id group by product_id

寫對應的 rest 服務

這樣的結果會是?

    select product.id, name, count(amount) from product left join sales_order_item on product.id = sales_order_item.product_id group by product_id

如果看到:
Field dao in com.example.demo.controller.OrderController required a bean of type 'com.example.demo.dao.SalesOrderDAO' that could not be found.

記得要 implements

    public class SalesOrderDAOImpl implements SalesOrderDAO {

#### 新增資料到相關的資料表

想想看，如何寫一個 REST 服務去下一筆訂單?
url:localhost:8080/order
選擇 method: POST
body: raw/JSON:

    {
        "customerId": "1",
        "items":[
            {
                "productId": 1,
                "amount": 2
            },
            {
                "productId": 2,
                "amount": 1
            }
        ]
    }

看似簡單，要寫多少程式?
先試著新增 order，再試著新增 orderItem，再試著去讀 price

#### 設定時間:

    preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));

- [How to get current timestamps in Java](https://mkyong.com/java/how-to-get-current-timestamps-in-java/)

#### 如何取得自動產生的 key

當我們新增一筆資料到資料表(如:sales_order)，想取得資料表所產生的 key，再利用這個 key 去新增資料到另一個資料表(如:sales_order_item)的資料，就要使用 preparedStatement.getGeneratedKeys()

- [get generated key](https://www.javaguides.net/2019/07/how-to-retrieve-auto-generated-keys-in-jdbc.html)

在 preparedStatement 要傳參數去收到 key

    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

執行 executeUpdate()之後

    ResultSet key = stmt.getGeneratedKeys();

會取得 ResultSet，再去取得值

    int keyValue = 0;
      if (key.first()) {
      keyValue = key.getInt(1);
    }

#### create view

- [Create View](https://www.1keydata.com/tw/sql/sql-create-view.html)

作業: 如何取得某段時間內的所有產品總銷售量? (提示:銷售時間是在 SalesOrder 裡)
