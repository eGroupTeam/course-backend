# course-backend
## 第六章 資料庫基礎 1 讀取資料
### MySQL
比較常用的關聯式資料庫有SQL server、MySQL、MariaDB、Oracle database。雖然都支援SQL，但在SQL語法上還是有些差異 (詳參: [MySQL to SQL Server Coding Differences](https://www.mssqltips.com/sqlservertutorial/2204/mysql-to-sql-server-coding-differences/) )。

MySQL是一個開源的資料庫，原本是由MySQL AB開發，2008年被Sun收購，2009年Oracle收購Sun時，就變成Oracle的產品了，MySQL最新版本是8.0.29(2022/07/15) (下載: [MySQL Community Downloads](https://dev.mysql.com/downloads/))(詳參:[MySQL](https://en.wikipedia.org/wiki/MySQL))。

MariaDB是MySQL的一個分支，在2013年由MySQL的創始人Ulf Michael Widenius與其他人一起成立了MaribDB Foundation來主導MariaDB的開發。在MariaDB 5.5之前，MariaDB的版本都與MySQL同步，後來MariaDB就跳到 10.0，並且，MariaDB就與MySQL版本不再同步。目前最新的穩定版本是10.8.3 (2022/07/05更新)(下載: [Downloads](https://mariadb.org/download/?t=mariadb&p=mariadb&r=10.10.0)) (詳參: [MariaDB](https://en.wikipedia.org/wiki/MariaDB))。 

一般而言，可以直接下載MySQL或Maria DB，也可以下載一些安裝包(如:XAMPP、AppServ)，這些安裝包將這些軟體(Apache及MySQL)包裝好，可以一次安裝好，不必一個一個的安裝。使用安裝包的時候要注意，每個安裝包所內含的軟體是不太一樣的，目前最大的差異在於使用MySQL或MariaDB，例如，XAMPP內含MariaDB，AppServ內含MySQL，WampServer則是MySQL及MariaDB，另外，還要注意的是支援的作業系統，AppServ及WampServer支援windows，XAMPP號稱跨平台，所以，支援Windows、Mac、Linux版本。

常用的管理介面有phpmyadmin及MySQL WorkBench，在web程式設計課程中會採用phpmyadmin，因為phpmyadmin是個web介面，而且是用php開發，一定要有apache伺服器才能執行，所以，phpmyadmin通常會包含在Appserv及XAMPP裡。

另外，如果希望使用獨立的管理介面，可以使用[MySQL WorkBench](https://dev.mysql.com/downloads/workbench/)，MariaDB10.0以後的版本跟MySQL不相容，所以，如果使用MariaDB10.0以後的版本，MySQL WorkBench就可能會有相容性的問題了。MySQL Workbench的使用說明: (詳參: [MySQL Workbench Manual](https://dev.mysql.com/doc/workbench/en/))

在這裡就不說明如何安裝或使用MySQL的管理介面了。

請要先新增一個practice的資料庫(schema)，編碼請選擇utf8_general_ci。然後，利用SQL產生以下的資料表:

    CREATE TABLE practice.customer (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    address VARCHAR(45) NOT NULL,
    weight INT NOT NULL,
    PRIMARY KEY (`id`));

記得要輸入資料

### JDBC
JDBC是Java內建的關聯式資料庫的API，如:Connection、SQLException。Spring Framework支援JDBC的相關元件，提供額外的類別，如:Autowired、Repository。因為JDBC是關聯式資料庫通用的API，所以，可以透過JDBC連接不同的關聯式資料庫，例如，MySQL或SQL server。不過，因為不同關聯式資料庫有些SQL語法上的差異，還是要注意，更換資料庫的時候，不一定100%相容。

更動pom.xml，引用JDBC及MySQL的相關packages，在VSCode裡，可以利用spring Initializr來編輯，在pom.xml裡按右鍵，選擇"Edit Starter"，就可以挑所需要的外掛，"JDBC API"及"MSQL Driver"。這樣的好處是，不會放錯地方。


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>


設定參數 (application.properties)，以使用mySQL為例，其中，username及password請放資料庫的username及password。
src\main\resources\application.properties:

    spring.datasource.url=jdbc:mysql://localhost/practice
    spring.datasource.username=(your username)
    spring.datasource.password=(your password)
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

如果連不上資料庫，也有可能是port錯誤，請確定資料使用哪個port，因為預設的port是3336，如果你的mysql是在8889，就要改成

    spring.datasource.url=jdbc:mysql://localhost:8889/practice
    spring.datasource.username=(your username)
    spring.datasource.password=(your password)
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

過去，是類似跟我們在web程式設計所教的一樣，要寫一個java class來設定所有的參數，讓其他的程式使用，現在，大部分的框架都提供替代方案，可以利用設定檔來處理，好處是，如果不同機器上的設定不同，可以只要替換掉設定檔，而不必記得要改哪些程式的內容，這對多人同時開發系統的情境而言，是相當重要的，也可以不把application.properties放到github，避免覆蓋掉其他人的設定。

**注意，這是spring boot的用法，如果是使用spring，設定方式不太一樣，在參考網路文章時，請注意這個差異 **

首先，先對應資料表的欄位，更改一下Customer.java。

    package com.example.demo.entity;
    public class Customer {
      private Long id;
      private String name;
      private String address;
      private int weight;
    
    }

可以在VS Code中安裝Java Code Generator，就可以自動產生對應的constructor、getters、setters。

因為資料庫的開發方式可能會改變，為了不讓將來的改變影響整個系統，通常會增加一個interface (CustomerDAO) ，在Controller裡就使用CustomerDAO而不是CustomerDAOImpl，到時候，如果CustomerDAOImpl必須更換時，就不需要改Controller的內容了。將interface放在dao裡，對應的impl，就放在dao的impl檔案夾裡。

    package com.example.demo.dao;
    import java.util.List;
    import com.example.demo.entity.Customer;
    public interface CustomerDAO {
      public List<Customer> findAll();
    }

[Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)

使用JDBC讀取資料

因為資料庫的開發方式可能會改變，為了不讓將來的改變影響整個系統，通常會增加一個interface (CustomerDAO) ，在Controller裡就使用CustomerDAO而不是CustomerDAODB，到時候，如果CustomerDAODB必須更換時，就不需要改Controller的內容了。為了保持彈性，一般會實作CustomerDAO  interface，而不直接連接CustomerDAOImpl。

另外，在Spring Framework有一些stereotype (如: @Controller)，使用資料庫時，我們會將使用@Repository，這樣才能利用@Autowired來使用這個類別。

    @Repository
    public class CustomerDAOImpl implements CustomerDAO {

jdbc是java內建的資料庫package。首先，透過@Autowired，讓dataSource去讀取application.properties的內容 (這是spring的用法不是標準的jdbc):

    @Autowired
    private DataSource dataSource;

以下的語法就跟JDBC語法是一樣的，接下來利用dataSource產生Connection:

    Connection conn = dataSource.getConnection();

有些文件會使用DriverManager或Class.forName，那都是比較早期的用法。
接下來，我們利用PreparedStatement來定義sql statement:

    String sql = "select id, name, address, weight from customer";
    PreparedStatement stmt = conn.prepareStatement(sql);

再來，執行sql statement，取得結果:

    ResultSet rs = stmt.executeQuery();

從ResultSet裡讀取每一筆資料:

    while (rs.next()){
      customers.add(getCustomer(rs));
    }

讀取每個欄位是利用getLong()、getString()、getInt()，根據對應欄位使用不同的方法:

      Long id = rs.getLong("id");
      String name = rs.getString("name");
      int weight = rs.getInt("weight");

利用getCustomer將讀取的資料轉成Customer物件，再把物件新增到customers。

    while (rs.next()){
      customers.add(getCustomer(rs));
    }

getCustomer利用getLong()、getString()、getInt()產生Customer物件

  public Customer getCustomer(ResultSet rs) throws SQLException{
      return new Customer(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getString("address"),
        rs.getInt("weight"));
  }


在CustomerController裡呼叫findAll()

    dao.findAll();

CustomerController.java

    package com.example.demo.controller;
    import java.sql.SQLException;
    import java.util.List;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.GetMapping;
    import com.example.demo.dao.CustomerDAO;
    import com.example.demo.entity.Customer;
    @RestController
    public class CustomerController {
      @Autowired
      CustomerDAO dao;
      @GetMapping(value = "/customer")
        public List<Customer> retrieveCustomers() throws SQLException{
          return dao.findAll();
        }
    }

使用JDBC讀取單筆資料
可以透過sql的where讀取單筆資料，在sql中利用「?」來接受參數

      String sql = "select id, name, address, weight from customer where id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);

設定preparedStatement的參數，第一個參數是「1」，代表第一個「?」(**注意** 不是從0開始)。第二個參數，是參數所對應的變數。

      stmt.setLong(1, id);

再來，執行sql statement，取得結果:

      ResultSet rs = stmt.executeQuery();

從ResultSet裡讀取資料，如果讀取得到內容，利用getCustomer將讀取的資料轉成Customer物件:

      if (rs.next()) {
        customer = getCustomer(rs);
      }

在CustomerController裡呼叫

    @GetMapping(value = {"/customer/{id}"})
    public Customer retrieveOneCustomer(@PathVariable("id") Long id) throws SQLException{
        return dao.findOne(id);
    }

src\main\java\com\example\demo\dao\CustomerDAO.java
也要加上findOne():

    package com.example.demo.dao;
    import java.util.List;
    import com.example.demo.entity.Customer;
    public interface CustomerDAO {
        public List<Customer> findAll();
        public Customer findOne(Long id);
    }

如果我們要取得單筆資料，就是REST通常會傳遞值

http://localhost:8080/customer/2

得到的JSON內容:

{"id":2, "name":"Ben","address": "Taipei","weight":50}

### 作業:試著新增一個employee資料表，並且修改讓上週的RestController可以從資料表讀取資料