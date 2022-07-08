# course-backend
## 第七章 資料庫基礎 2 基礎CRUD 2
### 使用JDBC新增資料

可以透過sql的insert新增單筆資料

    String sql = "insert into customer (name, address, weight) values(?, ?, ?)";
    PreparedStatement stmt = conn.prepareStatement(sql);

設定preparedStatement的參數

    stmt.setString(1, customer.getName());
    stmt.setString(2, customer.getAddress());
    stmt.setInt(3, customer.getWeight());

執行sql statement，result為「1」，新增成功，result為「0」，新增就沒有成功。

    int result = stmt.executeUpdate();

在CustomerController裡呼叫

    @PostMapping(value = "/customer")
    public void processFormCreate(@RequestBody Customer customer) throws SQLException {
        dao.createCustomer(customer);
    }

可利用postman，新增一個request，選擇POST，url: http://localhost:8080/customer/
Body輸入: (形式為raw / JSON (appication/json))

    { "name":"ruby", "address":"Taipei", "weight":50 }

### 使用JDBC修改資料
可以透過sql的update修改單筆資料

    String sql = "update customer set name=?, address=?, weight=? where id =?";
    PreparedStatement stmt = conn.prepareStatement(sql);

設定preparedStatement的參數

    stmt.setString(1, customer.getName());
    stmt.setString(2, customer.getAddress());
    stmt.setInt(3, customer.getWeight());
    stmt.setLong(4, customer.getId());

執行sql statement，result為「1」，新增成功，result為「0」，新增就沒有成功。

    result = stmt.executeUpdate();

在CustomerController裡呼叫

    @PutMapping(value = "/customer")
    public void processFormUpdate(@RequestBody Customer customer) throws SQLException {
      dao.updateCustomer(customer);
    }

可利用postman，新增一個request，選擇PUT，url: http://localhost:8080/customer/
Body輸入: (形式為raw / JSON (appication/json))

    { id:2 , "name":"ruby", "address":"Taipei", weight:50 }

使用JDBC刪除資料
可以透過sql的delete刪除資料

    String sql = "delete from customer where id =?";
    PreparedStatement stmt = conn.prepareStatement(sql);

設定preparedStatement的參數

    stmt.setLong(1, id);

執行sql statement，result為「1」，新增成功，result為「0」，新增就沒有成功。如果刪除多筆資料，會回傳被刪除的筆數。

    result = stmt.executeUpdate();

在CustomerController裡呼叫

    @DeleteMapping(value = "/customer/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
      dao.delete(id);
    }

可利用postman，新增一個request，選擇DELETE，url: http://localhost:8080/customer/2

如何處理錯誤? 試試看上面的程式，會產生錯誤嗎? 會有哪些錯誤? 如何處理? 請參考前幾週的處理方式。

### 作業:1.完成delete 2.試著完成employee的CRUD