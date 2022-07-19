# course-backend

## 第十五章 google 登入

在 OAuth 下，系統可以有多種角色，第一種是去接受 OAuth 帳號(如:google 帳號的登入)，第二種是角色 Client，是透過 OAuth 去取得其他伺服器的資源，第三種角色是 Resource Server，就是接收 OAuth Client 的要求提供資源給 Client。

- [[筆記] 認識 OAuth 2.0：一次了解各角色、各類型流程的差異](https://medium.com/%E9%BA%A5%E5%85%8B%E7%9A%84%E5%8D%8A%E8%B7%AF%E5%87%BA%E5%AE%B6%E7%AD%86%E8%A8%98/%E7%AD%86%E8%A8%98-%E8%AA%8D%E8%AD%98-oauth-2-0-%E4%B8%80%E6%AC%A1%E4%BA%86%E8%A7%A3%E5%90%84%E8%A7%92%E8%89%B2-%E5%90%84%E9%A1%9E%E5%9E%8B%E6%B5%81%E7%A8%8B%E7%9A%84%E5%B7%AE%E7%95%B0-c42da83a6015)

我們在前端利用 NextAuth 去讓使用者去 google 登入，接下來就是扮演 Client 的角色，向 spring 要求資源。

作為 Resource Server，可以透過兩種方式進行驗證，我們採用的是 JWT (JSON Web Token)

#### 安裝

我們先來設定我們的 spring 專案，首先，可透過 Spring Initializr 安裝 OAuth2 Resource Server，這個套件會包含 Spring Security。Spring Security 是 spring 提供的套件，讓我們可以很容易的設定權限。

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
      </dependency>



    @Configuration
    public class SecurityConfig {

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authz -> authz
                .antMatchers(HttpMethod.GET, "/product/**").authenticated()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated())
            .cors().and()
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
      }
    }

application.properties 裡要新增:

    spring.security.oauth2.resourceserver.jwt.issuer-uri:https://accounts.google.com

這樣就好了~

我們可以在程式裡取得

      @GetMapping(value = "/product")
      public List<Product> retrieveProducts() throws SQLException, Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
          System.out.println("authenticated");
        }
        System.out.println(authentication.getName());
        return dao.getList();
      }

- [OAuth 2.0 Resource Server With Spring Security 5](https://www.baeldung.com/spring-security-oauth-resource-server)
- [OAuth 2.0 Resource Server](https://docs.spring.io/spring-security/reference/reactive/oauth2/resource-server/index.html)
- [OAuth 2.0 Resource Server Sample](https://github.com/spring-projects/spring-security-samples/tree/5.7.x/reactive/webflux/java/oauth2/resource-server)

- [Intro to Spring Security Expressions](https://www.baeldung.com/spring-security-expressions)

### 補充 spring 扮演要求登入的角色

#### google 設定

要使用 google 登入，首先，先建立專案，建立之後，要先設定 OAuth 同意畫面，設定為「外部使用者」，讓所有擁有 google 帳號的人都可以使用。接下來會填寫一些資料，只要留必要資料就可以了。

接下來，要建立憑證，選擇「OAuth 用戶端 ID」/「OAuth client ID」，應用程式類型，選擇「網頁」，並輸入應用程式名稱

接下來就會產生 id 及密碼，在 spring security 的設定中會用到。

- [建立及取得憑證](https://console.cloud.google.com/apis/credentials)

我們先來設定我們的 spring 專案，首先，可透過 Spring Initializr 安裝 OAuth2 Client 及 Spring Security。Spring Security 是 spring 提供的套件，讓我們可以很容易的設定權限，Spring Security 也可以透過 OAuth 去讓 OAuth 的服務提供者，如:google，取得登入者的身分。

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
      </dependency>

#### 設定 spring security

把剛剛產生的 Client ID 和 Client Secret 寫到 application.properties

spring.security.oauth2.client.registration.google.client-id=XXXX
spring.security.oauth2.client.registration.google.client-secret=XXXX

接下來在 demo 下新增一個 config 的檔案夾，並且新增一個
SecurityConfig.java

啟動 spring 之後，就會看到
錯誤代碼 400： redirect_uri_mismatch

那就是要去設定 redirect_uri

如果得到: redirect_uri_mismatch，那就是在設定憑證時，在「已授權的 JavaScript 來源」中沒有把放進去，問題來了，因為我們都是在 localhost 上執行，google 為了資訊安全，會擋掉 localhost。那怎麼辦呢?

這時候就要搬出一個神器: ngrok，免費版的 ngrok 雖然有很多限制，但是，對於開發測試而言，夠用了。

- [ngrok](https://ngrok.com/)
- [[Day-37] 使用 ngrok 讓外網連接你的 API](https://ithelp.ithome.com.tw/articles/10197345)

先下載 ngrok，並且登入帳號，取得 token，透過指令設定 token (XXX 就是 ngrok 提供的 token)

ngrok authtoken XXXX

然後，啟動 ngrok，要記得，我們是使用 port8080。

ngrok http 8080

先利用 ngrok 產生一個臨時的網址，回到[憑證管理](https://console.cloud.google.com/apis/credentials)

如果同時要使用 3000 及 8080，就需要利用 ngrok.yml 來設定了

    authtoken: XXXX
    tunnels:
      first:
        addr: 3000
        proto: http
      second:
        addr: 8080
        proto: http

啟動的時候，要使用 ngrok start --all

這時候去使用這個臨時的網址來打開我們的系統，注意，chrome 會一直告訴我們，這是個不安全的網頁，想想看，任何人都可以架一個網站，的確是不安全的。

接下來，記得要在 google 登錄 ngrok 產生的 url，記得，http 及 https 都要登錄

已授權的 JavaScript 來源:
https://b553-114-36-1-69.ngrok.io
http://616d-114-36-1-69.ngrok.io

已授權的重新導向 URI:

    https://616d-114-36-1-69.ngrok.io/login/oauth2/code/google
    https://616d-114-36-1-69.ngrok.io/login/oauth2/code/google

這樣子的話，當我們進入網站時，spring security 就會要求要登入了。

要讓我們的 next.js 可以順利取得，要在 security 中設定 cors (原本的設定無效)

    package com.example.demo.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SecurityConfig {

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated())
            // .httpBasic();
            .cors().and()
            .oauth2Login();
        // .httpBasic(withDefaults());
        return http.build();
      }

    }

這樣的話，試圖取得資料，就會被要求登入

http://616d-114-36-1-69.ngrok.io/product

- [Java Configuration without Spring Boot 2.x](https://docs.spring.io/spring-security/reference/servlet/oauth2/login/core.html)

- [建立及取得憑證](https://console.cloud.google.com/apis/credentials)

- [Spring Security + OAuth Login + Google Sign-In 架構與實例解析](https://waynestalk.com/spring-security-oauth2-google-signin-explained/)
  -- 注意一下，這篇文章的範例不是 java 語法
- [Spring Security without the WebSecurityConfigurerAdapter](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
  -- Spring Security 5.7.0 以後，寫法改變了
- [How to create a Google App for Social Connect](https://www.cozmoslabs.com/docs/profile-builder-2/add-ons/social-connect/create-google-app-social-connect/)

- [Adventures in Google Login OIDC / OAuth2 with React and Spring](https://medium.com/@johndbro1/adventures-in-google-login-oidc-oauth2-with-react-and-spring-370c1fd706c2)
  -- 注意，文中提到 react-google-login 已經不能使用了
- [Spring Security 5 – OAuth2 Login](https://www.baeldung.com/spring-security-5-oauth2-login)
