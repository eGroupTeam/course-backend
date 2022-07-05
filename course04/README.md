# course-backend
## 第四章 系統開發基礎架構 1 Spring Controller
### Spring MVC架構簡介
基本上去執行DemoApplication是沒有太多的意義，因為，並沒有定義任何的網頁或任何的服務，Spring是個MVC的架構，也就是會把程式碼切割為Model、View以及Controller。

早期，java是透過執行每個程式(稱為servlet)就會產生對應的網頁，這樣的作法，雖然可以開發網頁，但是，回傳內容就必須將HTML跟回應內容夾在一起，當系統越來越大的時候就變得很複雜，很難開發。後來，開始流行將程式夾在html裡(如:PHP)，就有servlet+JSP的概念出現了，將html(view)與java為主的servlet切割。後來，系統越來越大，就跟php一樣，開始有一些框架的出現。當框架出現時，首先就是提供最佳實務並且簡化設定，這些都是長期的累積前人的智慧，一步一步的演進到今天的規模。在Java，最多人使用的就是spring，現在spring也支援其他的語言:Kotlin及Groovy。[手寫Servlet 到 Spring MVC 的簡化之路](https://kknews.cc/zh-tw/code/kmkykgv.html)

在spring的專案中，所有的程式碼都在src檔案夾下，src下有main以及test，main檔案夾裡就是我們要執行的程式，test檔案夾裡就是可執行的測試程式，main底下的檔案夾，會因為是JAR或WAR，檔案的位置及名稱會有所不同，因為我們使用JAR，所以，會看到java以及resources檔案夾，resources檔案夾下有application.properties，未來會把一些設定寫在這個檔案裡，static檔案夾放html及照片等靜態檔案，template則是放其他的前端檔案。

[Differences Between JAR and WAR Packaging](https://www.baeldung.com/java-jar-war-packaging)

在java下會是以程式碼的結構(java.com.example.demo)來儲存我們的檔案，一個新的spring專案，只會看到一個檔案:DemoApplication.java，在此檔案裡有java最基本的內容，再加上Spring的一些設定，讓伺服器知道這是個Spring專案，而且是使用Spring Boot來進行專案的設定。

    package com.example.demo;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class DemoApplication {

        public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }

    }

當我們加了@SpringBootApplication，Spring會自動scan這個這個package以下的目錄，所以，其他的檔案要放在com.example.demo下，或這在這個之下的目錄，如: com.example.demo.controller，(如: [Structuring Your Code](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)的建議)。

注意:如果把controller放到錯誤的資料夾，就不會被scan，當然就不會視為controller。所以，如果寫了controller，卻無法執行，通常問題就出在這裡。

「@」在java中稱為Annotation，Annotation是java 5之後才有的語法，雖然很多人將Annotation翻譯為註解，但是，Annotation跟Comment是完全不一樣的，Comment是純粹給人看的，而Annotation卻是給系統看的。當我們加了@SpringBootApplication，Spring boot會進行很多動作，例如:讀取一些設定檔，也會自動scan這個這個package以下的目錄，處理其他的Annotation (詳參: [Spring Framework Annotations](https://springframework.guru/spring-framework-annotations/))。後面會看到更多的Annotation。在沒有Annotation之前，這些動作都必須靠很多xml的設定檔，現在這些動作都簡化了。

### Spring controller
現在，在Spring裡，是利用Controller來定義所有的網路服務，網路服務有兩個部分:URI (URL)以及對應的HTTP action (如: Get, Post, Put, Patch, Delete)。

要定義一個Controller，首先，在DemoApplication.java的同一個目錄下，新增一個controller的資料夾，在資料夾中新增一個MainController.java。

一個最基本的Spring Controller就是一個類別，首先，會定義package，現在開始請養成習慣把同樣類型的類別都放在同一個package，例如，將controller都放在controller的package裡，並將檔案放在對應的路徑 (src/main/java/com/example/demo/controller)裡。

    package com.example.demo.controller;

然後，import等一下需要的兩個類別。

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;

接下來，會利用java的Annotation:「@」告訴Spring boot定義這個類別是個Controller，spring boot會藉著這個annotation產生應該有的設定。

    @Controller

一般而言，在@Controller裡會利用Mapping來設定被呼叫的方法及對應的URI。常用的HTTP 方法有get、post、put、patch、delete。

### HTTP Get

我們先指定index這個方法的為一個HTTP Get服務，並指定路徑為「/」 ，在Spring 4.3之後，可利用GetMapping來也就是指定這個Controller是HTTP GET以及相對的URI，(在Spring 4.3之前，是使用@RequestMapping，詳參: [Spring @RequestMapping New Shortcut Annotations](https://www.baeldung.com/spring-new-requestmapping-shortcuts))。

    @GetMapping(value = "/")

@ResponseBody，就是將指定內容(HTML)回傳給瀏覽器。

    @ResponseBody

下面的範例，就是告訴web server，當瀏覽器要求"/"時，就顯示Hi。
src/main/java/com/example/demo/controller/MainController.java

    package com.example.demo.controller;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ResponseBody;
    @Controller
    public class MainController{
        @GetMapping(value = "/")
        @ResponseBody
        public String index() {
            return "<h1>Hi!</h1>";
        }
    }

試試看，啟動一下我們的伺服器，看能不能看到Hi!

如果看到:
Ambiguous handler methods mapped for '/'

表示有兩個mapping都指向"/"。

接下來，怎麼像php一樣可以取得get參數? 基本上有三種方法:
第一種方式是利用HttpServletRequest，利用getParameter來存取對應的變數。其實，controller底層還是使用傳統的servlet，所以，還是可以使用servlet的語法。

    import javax.servlet.http.HttpServletRequest;

    @GetMapping("/request")
    @ResponseBody
    public String request(HttpServletRequest request) {
       String name = request.getParameter("id");
       return name;
    }

試試看:
http://localhost:8080/request?id=ben

第二種方式是利用@RequestParam，可以設定這個參數不是必要的，也可以設定當參數不存在時的預設值。如果要達到同樣的目的，第一種寫法就要寫不少程式才行。

    import org.springframework.web.bind.annotation.RequestParam;

    @GetMapping("/request")
    @ResponseBody
    public String request(@RequestParam(value="id", required=false, defaultValue="world") String name) {
       
      return name;
    }

第三種方式是利用@ModelAttribute。

    import org.springframework.web.bind.annotation.ModelAttribute;

    @GetMapping("/request")
    @ResponseBody
    public String request(@ModelAttribute("id") String name) {
       
      return name;
    }

注意，如果沒有@ResponseBody，spring會去啟動一個名字跟name內容一樣的html網頁，因為並沒有那個網頁，就會一直看到

Whitelabel Error Page

在terminal裡會看到:

javax.servlet.ServletException: Circular view path [request]: would dispatch back to the current handler URL [/request] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)

### HTTP Post

使用@ModelAttribute的另一個好處是可以接受一個物件，主要是用在POST，spring會去對應類別的屬性去產生物件。對應規則是依據Java Beans的命名規則，要有對應的getter及setter (不是透過建構式)。只要命名不一致，就不會對應過去，但也不會有錯誤訊息，所以，只要沒收到資料，應該就是命名不一致的問題。因為是對應一個類別，可以在類別裡去預設內容，所以，不需要像@RequestParam，去設定預設值。

    import org.springframework.web.bind.annotation.PostMapping;

    @PostMapping("/request")
    @ResponseBody
    public String postRequest(@ModelAttribute Customer customer) {
       
      return customer.name;
    }

首先，我們要產生一個Customer.java，因為class只是儲存內容，所以，稱為entity。

    package com.example.demo.entity;

    public class Customer {
      private String name;
      public void setName(String name){
        this.name = name;
      }
      public String getName(){
        return name;
      }  
    }



接下來，我們寫一個html

    <meta charset="utf-8">
    <form action="/request" method="post">
      姓名:<input type="text" name="name"/>
      <input type = "submit" value="送出"/> 
    </form>

試試看:
http://localhost:8080/customer.html

補充一下，同一個方法也可以對應多個uri，例如:

    @GetMapping({"/","/hello"})

### Maven
Spring專案可以使用Maven或Gradle來管理package，Maven或Gradle功能大同小異，主要差別在設定檔的格式，Maven設定檔使用的是XML的格式，而Gradle是以 Groovy 語言為格式。

在一個Maven專案中，最重要的就是pom.xml，POM就是Project Object Model，裡面儲存的就是我們專案裡使用到的package(程式庫)。

spring就是透過maven來控制我們所需要的package，目前，我們用到的package就是web

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

### 作業 試試看在Customer.java增加age的屬性，並且修改html，讓使用者可以輸入age，也在網頁上顯示age