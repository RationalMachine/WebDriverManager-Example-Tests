# WebDriverManager-Example-Tests
Using the WebDriverManager made by @BoniGarcia to write a couple of random tests using the Java client library for Selenium

WebDriverManager - This is a library written by @BoniGarcia for Selenium WebDriver binary management. 

Normally we have to download the Webdriver binaries and then set the system property for JVM. Example code:

````
System.setProperty("webdriver.chrome.driver","the/absolute/path/to/chromedriver")
````

In order to use this library in a Maven project we use the following:

````
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>2.2.4</version>
</dependency>
````

Once Maven downloads all the required dependencies, it's just a matter of writing the following line in your code:

````
WebDriverManager.chromedriver().setup();
````

This repo holds test cases written in JUnit to test WebDriverManager library with instances of different browsers.

@BoniGarcia already has several test cases written in his own repo. This is just another repo inspired from his. 

