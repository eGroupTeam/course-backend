# course-backend

## 第十三章 前後端整合 2

### 修改

    @PutMapping(value = "/product/{id}")
    public void processFormUpdate(@RequestBody Product product, @PathVariable("id") int id) throws Exception {
      if (product.getId() == 0) {// if not set, getId() will return 0
        product.setId(id);
      }

      int result = dao.update(product);
      if (result == 0) {
        throw new SQLException("id: " + id + " 並不存在");
      }
    }

並且產生對應的 DAO 及 implementation，請參考 CustomerDAO.java 及 CustomerDAOImpl.java

    public int update(Product product) throws Exception;

接下來透過 postman 先測試一下。如果正確，我們就可以修改一下 react 的對應程式。

### 作業 我們根據 product 來完成 customer 的 CRUD
