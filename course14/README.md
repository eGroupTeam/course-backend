# course-backend

## 第十四章 檔案上傳、發送電子郵件

### controller

先在前端的部分準備好上傳的介面，準備好之後，我們來產生一個接收檔案的 rest controller，在 spring 裡可以讓 controller 透過 MultipartFile 來接收上傳的檔案。

- [Spring Boot File Upload / Download Rest API Example](https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/)

  package com.example.demo.controller;

  import org.springframework.web.bind.annotation.CrossOrigin;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.RestController;
  import org.springframework.web.multipart.MultipartFile;

  @RestController
  @CrossOrigin
  public class FileUploadController {
  @PostMapping("/file")
  public String handleFileUpload(@RequestParam("file") MultipartFile file) {
  System.out.println(file.getOriginalFilename());
  // storageService.store(file);
  return "OK";
  }
  }

前端選擇好檔案並按下「送出」之後，會收到 500 的錯誤，看一下後端的錯誤。

org.springframework.web.multipart.MultipartException: Current request is not a multipart request。

問題在於前端送出時沒有處理好，請修改一下前端，使用 FormData 來傳送檔案。

接下來就可以看到可以收到檔案了! 在伺服器端，就可以看到上傳檔案的名稱。

#### spring 儲存檔案

收到檔案之後，接下來要將檔案儲存在伺服器上。跟連接資料庫一樣，我們都不直接串連實作的類別，我們先產生一個 StorageService，StorageService 是個 interface，真正的實作是 FileSystemStorageService，

service/StorageService.java:

    package com.example.demo.service;

    import org.springframework.web.multipart.MultipartFile;

    public interface StorageService {
      void store(MultipartFile file);
    }

在實作的類別裡，利用了 java 內建的 Path 及 Files，將收到的檔案放到指定的路徑(如果路徑是檔案名，就是以該名稱為檔案名)。
service/impl/StorageServiceImpl.java:

把服務跟 controller 串連起來:

這時候可以看到多了一個 test.jpg 了! 也可以透過 url 看到圖檔:
http://localhost:8080/test.jpg

要注意，有些 browser 會對檔案進行快取，所以，當使用同樣的檔名時，有可能不會馬上看到新的內容。

如果我們要把檔案儲存為原本的檔案名稱，我們就改一下，注意，因為我們的設定是 REPLACE_EXISTING，所以，如果檔案名稱一樣，會被覆蓋:

### 利用 Spring Mail 發送電子郵件

Spring Mail 是基於 JavaMail，可以利用 JavaMail 透過 SMTP 送出 email，也可以利用 pop3 或 imap 去 mail server 讀取 email。我們最常使用的就是利用 SMTP 送出 email，以下就只提供 SMTP 這部份的範例。

首先，需要安裝 Java mail sender。可以利用 Spring Initializr 新增，或者直接在 pom.xml 加入:

    	<dependency>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-mail</artifactId>
    	</dependency>

接下來，修改 application.properties，以下設定是以學校 email 為例，不同的 mail server 設定可能不同，輔大的 mail server 並沒有使用 TLS 或 SSL。

    spring.mail.host = mail.fju.edu.tw
    spring.mail.username = XXXXXX
    spring.mail.password = XXXXXX
    send.from.email= XXXXXX@mail.fju.edu.tw
    spring.mail.properties.mail.smtp.auth = true;
    #spring.mail.properties.mail.smtp.starttls.enable = false
    #spring.mail.properties.mail.smtp.ssl.enable = false
    #spring.mail.properties.mail.socketFactory.port=587
    #spring.mail.properties.mail.socketFactory.class=javax.net.ssl.SSLSocketFactory
    #spring.mail.properties.mail.socketFactory.fallback=false
    spring.mail.smtp.port= 25

就像檔案上傳一樣，先產生 MailService.java

    package com.example.demo.service;

    import org.springframework.mail.MailException;

    public interface MailService {
      public void prepareAndSend(String recipient, String message) throws MailException;

    }

在 impl 裡，寫 MailServiceImpl.java

    package com.example.demo.service.impl;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.mail.javamail.MimeMessageHelper;
    import org.springframework.mail.javamail.MimeMessagePreparator;
    import org.springframework.stereotype.Service;

    import com.example.demo.service.MailService;

    @Service
    public class MailServiceImpl implements MailService {
      private JavaMailSender mailSender;

      @Autowired
      public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
      }

      public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
          MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
          messageHelper.setFrom("053792@mail.fju.edu.tw");
          messageHelper.setTo(recipient);
          messageHelper.setSubject(message);
          messageHelper.setText(message);
        };
        mailSender.send(messagePreparator);
      }

    }

當我們完成檔案上傳時，也一併送出 email:

    @Autowired
    private MailService mailService;


    @PostMapping("/file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
      System.out.println(file.getOriginalFilename());
      storageService.store(file);
      mailService.prepareAndSend("053792@fju.edu.tw", "檔案已上傳");
      return "OK";
    }

處理一下錯誤:

    @PostMapping("/file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
      System.out.println(file.getOriginalFilename());
      try {
        storageService.store(file);
        mailService.prepareAndSend("053792@fju.edu.tw", "檔案已上傳");
      } catch (MailException e) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "email未能送出");
      }
      return "OK";

    }

- [Guide to Spring Email](https://www.baeldung.com/spring-email)

#### 作業 設定一下如何利用你自己的 email 帳號，在上傳檔案後，送出通知郵件
