# course-backend

## 第十二章 前後端整合 1

### 讀取

我們這一章要來介紹如何讀取 spring 的 REST API。
目前我們前端需要取得產品的列表，不過，後端目前沒有對應的 API。我們先在 spring 寫一個 product 的 REST API。

@Autowired
ProductDAO dao;

    @GetMapping(value = "/product")
    public List<Product> retrieveProducts() throws SQLException, Exception {
      return dao.getList();
    }

並且產生對應的 DAO 及 implementation，請參考 CustomerDAO.java 及 CustomerDAOImpl.java

    @Repository
    public interface ProductDAO {
      public List<Product> getList() throws Exception;

    }

接下來，先透過 postman 測試 API 是否正確。

不過，卻無法在 react 裡看到內容，在 terminal 裡看不到錯誤，在 spring 也看不到錯誤。利用 postman 測試，也是正常的。發生了甚麼事?

原因是這是執行時的問題，所以，要在瀏覽器的「開發人員工具」裡去找錯誤訊息。打開「開發人員工具」會看到

Access to XMLHttpRequest at 'http://localhost:8080/product' from origin 'http://localhost:3000' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.

執行時，會看不到結果，是因為 cross-origin requests (CORS)被擋了，因為 spring 是跑在 8080 port 而 react 跑在 3000 port，spring 因為資訊安全的關係，拒絕了 react 的要求。一般解決的方法有兩種，第一種是在 react 端設定 proxy，讓 react 假裝是在 8080 上執行，另一種做法，是在 spring 上進行 CORS 的設定。

    import org.springframework.web.bind.annotation.CrossOrigin;

    @RestController
    @CrossOrigin
    public class ProductController {

加上@CrossOrigin 之後，react 就可以順利讀取這個 API 了。

### 新增

接下來，我們來提供 Post 的 API。

    @PostMapping(value = "/product")
    public void processFormCreate(@RequestBody Product product) throws Exception {
      dao.insert(product);
    }

並且產生對應的 DAO 及 implementation，請參考 CustomerDAO.java 及 CustomerDAOImpl.java

    public int insert(Product product) throws Exception;

接下來，先透過 postman 測試 API 是否正確。如果正確，我們就可以修改一下 react 的對應程式。

### 刪除

接下來，我們來提供 Delete 的 API。

    @DeleteMapping(value = "/product/{id}")
    public void delete(@PathVariable("id") int id) throws Exception {

      int result = dao.delete(id);
      if (result == 0) {
        throw new SQLException("id: " + id + " 並不存在");
      }
    }

並且產生對應的 DAO 及 implementation，請參考 CustomerDAO.java 及 CustomerDAOImpl.java

    public int delete(int id) throws Exception;

接下來透過 postman 先測試一下。如果正確，我們就可以修改一下 react 的對應程式。

有些產品不能刪除，因為我們將 FK 的刪除及修改行為設定為 restrict，當該產品已經有訂單時，該產品就無法刪除。

### 作業 我們根據 product 來完成 customer 的 讀取、新增、刪除
