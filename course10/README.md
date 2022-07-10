# course-backend

## 第十章 資料庫應用 3 SQL 子查詢

### SQL 子查詢

- [SQL 語法教學](https://www.1keydata.com/tw/sql/sql.html)

如果我們想知道有過交易資料的顧客資料，要怎麼寫 SQL?

這樣會先把 sales_order 跟 customer 資料表 join 起來

    SELECT customer_id, customer.name, customer.address FROM `sales_order` join customer on customer_id = customer.id

這樣是 0.0005 秒
再加上 distinct

    SELECT DISTINCT customer_id, customer.name, customer.address FROM `sales_order` join customer on customer_id = customer.id

這樣是 0.0015 秒

換個方向

    SELECT customer_id, name, address FROM customer join sales_order on customer_id = customer.id

這樣是 0.0022 秒

SELECT distinct customer_id, name, address FROM customer join sales_order on customer_id = customer.id

這樣是 0.0015 秒

使用子查詢

- [子查詢](https://www.1keydata.com/tw/sql/sql-subquery.html)

  select \* from customer where id in (select customer_id from sales_order)

這樣是 0.0005 秒

當資料量不大的時候，可能沒甚麼差別，但是當資料量大的時候就會有差別了，要試一下不同的組合，看看甚麼樣的組合執行時間較短。

- [create view](https://www.1keydata.com/tw/sql/sql-create-view.html)

  create view sales_order_customer as SELECT sales_order.id, order_time, customer_id, customer.name, customer.address FROM `sales_order` join customer on customer_id = customer.id

- [union](https://www.1keydata.com/tw/sql/sqlunion.html)

### REST

上次還沒有提到怎麼回傳一個完整的 SalesOrder，不過，應該不難。
首先，先讀取 sales_order，接下來，根據 sales_order 去讀取對應的 sales_order_item。

不過，當訂單數量很大的時候，這樣的做法就很沒有效率。

對消費者而言，可能要取得的是他自己的 SalesOrder

對管理者而言，可能要取得的是不同狀態的訂單，或最近幾筆的訂單，才不會一次要讀所有的訂單。

### 作業 在訂單中加入一個狀態(status)，0:未處理，1:已處理
取得的是不同狀態的訂單
http://localhost:8080/order/status/0
http://localhost:8080/order/status/1
