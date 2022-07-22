# course-backend

## 第一章 基本環境與開發規範

首先，先挑選並安裝你的整合開發環境(IDE)，可以使用 eclipse、IntelliJ 或 Visual Studio Code (VS Code)。
VS Code 的設定可參考:
[Getting Started with Java in VS Code](https://code.visualstudio.com/docs/java/java-tutorial)

簡單的說，要能在我們的 VS Code 裡執行 Java，必須先安裝 JDK (Java Development Kit)。

下載 Java 的時候要注意，Java Runtime Environment (JRE)是讓 Java 可以在我們的電腦上執行，Java SDK 才是開發用的。過去，Java SDK 分 SE (Standard Edition)及 EE (Enterprise Edition)，現在只剩下 SE。

- [Java Downloads](https://www.oracle.com/java/technologies/downloads/)

Java 最新的版本為 18 (2022/07/22)，以 Windows 而言，下載安裝檔案下來，直接進行安裝就可以使用了。如果無法執行，可以檢查一下環境變數設定，請參閱:

- [Java JDK 下載、安裝與環境變數設定教學-Windows 篇](https://www.kjnotes.com/devtools/35)
- [在 macOS 中安裝 Java](https://www.delftstack.com/zh-tw/howto/java/mac-java-install/)

由於 VS Code 可以用在不同的語言，所以，語言的相關功能必須透過下載 extension(擴充、外掛)，在 VS Code 裡可以安裝 Extension Pack for Java，這個安裝包包括了一些 Java 常用的外掛，接下來，安裝 Spring Boot Extension Pack。

- [Java extensions for Visual Studio Code](https://code.visualstudio.com/docs/java/extensions)

Java 之所以很流行一開始其實是因為 Java 編譯之後，會產生 byte code，byte code 是在 Java Virtual Machine (JVM)上執行，而不是直接在作業系統上執行，所以，可以達到程式碼能在各種平台上執行的效果。但是，一開始也有因為不是直接產生可以在作業系統上執行的機器碼，而會有執行上比較慢的問題，但是，在多年技術的改善後，執行速度已經不是問題了。

另外，java 在語言的設計上就已經考慮到不綁定特定的裝置，所以，很容易的就可以搬到各種作業系統，甚至可以用在 web 開發、手機開發，代價就是不像其他語言有內建的輸出輸入語法，輸出輸入都要透過比較麻煩。

Spring 是個 web 應用程式的框架，早期，Spring 是個 java 為主框架，現在，Spring 也開始支援其他語言，如:Kotlin 及 Groovy，這兩個語言跟 Java 很像，也都可以在 JVM 上執行，算是 Java 生態系中的語言。

安裝好了之後，產生一個空的 spring boot 專案。產生的方法有兩種，建議透過 Spring Boot Extension Pack 裡的 Spring Initializr。如果不是使用 VS Code，可以透過網頁版的[Spring Initializr](https://start.spring.io/)產生專案。

- [Spring Quickstart Guide](https://spring.io/quickstart)

產生的時候，專案(Project)的部分請選擇「Maven Project」，未來透過 Maven 進行整個專案的管理 (如:下載相關的套件)。

語言(Language)的部分請選擇「Java」。

Spring Boot 的版本，除非是要測試新版本的新功能，盡量選擇預設版本。

Project Metadata 的部分，就是整個專案的命名，一般而言，「Group」的部分會填寫公司的名稱，如:com.egroup，或預設的 com.example，當我們參與的專案很多的時候，就會以 Group 的名稱來區隔不同單位(公司)的專案。

「Artifact」的部分就會填寫專案的名稱，或預設的 demo，當我們在同一個公司參與的專案很多的時候，就會以 Artifact 的名稱來區隔不同的專案。

「Packaging」就選擇 JAR，如果想知道 JAR、WAR 差別，請參考:

- [淺談 SpringBoot 專案打成 war 和 jar 的區別](https://www.it145.com/9/48587.html)

接下來就選擇 Java 版本，可以選擇預設值，注意一下，我們目前安裝的 Java 是否有選擇的版本，如果沒有，就要安裝對應的 java 版本。

「Dependencies」的部分，先選擇「Spring Web」。未來還會需要其他的 Dependency，我們可以未來再安裝。

產生之後，VS Code 會詢問是否打開專案，請回答「Yes」，VS Code 會打開專案的 demo 檔案夾，未來要執行 Spring 專案，就是利用「File」，「Open Folder」，選擇「demo」資料夾，VS Code 就會認得這是個 Java 專案，就可以選擇「demo」，並執行這個專案，如果看到

The project was not built since its build path is incomplete. Cannot find the class file for java.lang.Object. Fix the build path then try building this project

通常就是選錯檔案夾了。

作業: 產生一個空的 spring boot 專案，並確認可以執行。
