# course-backend
## 第五章 系統開發基礎架構 2 Spring RestController
### RestController
REST controller是透過REST，以JSON格式與前端溝通。採用REST及JSON的好處是，前後端的開發平台就完全獨立，後端可以是Spring、PHP、.net或者python，前端也是一樣可以是react，也可以是手機App，甚至可以透過REST及JSON與其他的伺服器交換資料。現在很多的open data的平台都是採用這樣的方式交換資料。

現在利用spring boot開發REST已經非常簡單了，先新增一個EmployeeController.java (Rest請詳參:[Spring REST](https://sites.google.com/im.fju.edu.tw/web/spring-framework/spring-rest))，內容和一般的Controller很像，只是將controller設定為RestController，回傳值就會自動轉為JSON格式。

    @RestController

目前的資料以ArrayList變數的方式(employeeList)儲存，未來會介紹如何利用REST存取資料庫的內容。這裡使用到ArrayList (詳參: [Java ArrayList](https://www.w3schools.com/java/java_arraylist.asp))，並使用到java 5.0之後的語法: 泛型(Generic)，也就是定義ArrayList裡的每個物件都會是個Employee類別所產生的物件。

    private ArrayList<Employee> employeeList = new ArrayList<>();

另外，RestController預設回傳JSON文件，所以，不需要@ResponseBody

其他的部分大同小異

### HTTP Get

    package com.example.demo.controller;
    import java.util.ArrayList;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;
    import com.example.demo.entity.Employee;

    @RestController
    public class EmployeeController {
      private ArrayList<Employee> employeeList = new ArrayList<>();
      public EmployeeController(){
        employeeList.add(new Employee("Mary", "IT"));
        employeeList.add(new Employee("Ruby", "IT"));
        employeeList.add(new Employee("Ben", "IT"));
      }

      @GetMapping("/employee")
      public ArrayList<Employee> get() {
        return employeeList;
      }

    }

Employee.java

    package com.example.demo.entity;
    public class Employee{
        private String name;
        private String department;

        public Employee(String name, String department ) {
          this.name = name;
          this.department = department;
        }

        public String getName() {
          return name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public String getDepartment() {
          return department;
        }

        public void setDepartment(String department) {
          this.department = department;
        }
    }

REST取得單一物件的方法跟一般的方式不太一樣，不是利用get變數取得，而是利用「/」來處理，這樣的好處是每個物件都有一個獨立的URL
http://localhost:8080/employee/2

所以，就要使用PathVariable()來取得內容

    @GetMapping("/employee/{id}")
    public Employee retrieveOneEmployee(@PathVariable("id") int id){
      return employeeList.get(id);
    }

### HTTP Post

RestController利用@RequestBody來收post送來的資料

    @PostMapping(value = "/employee")
    public void addOneEmployee(@RequestBody Employee employee) {
      employeeList.add(employee);
    }

### HTTP Put
因為必須知道要修改哪筆資料，所以，使用PathVariable()來取得id，並利用@RequestBody來收put送來的資料

    @PutMapping("/employee/{id}")
    public void editOneEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
      employeeList.add(id,employee);
    }

或者:

    @PutMapping("/employee/{id}")
    public void editOneEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
      employeeList.set(id,employee);
    }

### HTTP Delete
因為必須知道要修改哪筆資料，所以，使用PathVariable()來取得id

    @DeleteMapping("/employee/{id}")
    public void removeOneEmployee(@PathVariable("id") int id) {
      employeeList.remove(id);
    }

### 使用postman
首先，先[下載](https://www.postman.com/downloads/)[postman](https://www.postman.com/)，並且註冊帳號

先新增一個Collection
新增request
url:localhost:8080/employee
選擇method: GET

url:localhost:8080/employee
選擇method: POST
body: raw/JSON: 
    {
        "name": "Steve",
        "department": "ACCT"
    }

如果要測試一般的Controller
新增request
url:localhost:8080/request
選擇method: POST
body: form-data
key: name value: Judy

### 處理錯誤
[Error Handling for REST with Spring](https://www.baeldung.com/exception-handling-for-rest-with-spring)
Spring 5以上可以使用ResponseStatusException

    catch(IndexOutOfBoundsException exception){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在", exception);

    }

[Http Status Code](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)

要讓我們的message可以被看到，在application.proporties裡要加上:
server.error.include-message=always

### 作業 試著利用上個章節的Customer完成一個簡單的RestController
