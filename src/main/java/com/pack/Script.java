package com.pack;

import java.io.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Script {

    static FirefoxOptions addSpecifications(FirefoxOptions options) {

        //TO avoid AT detection
        options.addArguments("--disable-blink-features=AutomationControlled"); // -chrome
        options.addPreference("network.proxy.type", 1);
        options.addPreference("network.proxy.http", "localhost");
        options.addPreference("network.proxy.http_port", "3128");
        options.addPreference("dom.webdriver.enabled", true);

        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--start-fullscreen");
        options.addArguments("--single-process");
        options.addArguments("--incognito");
        options.addPreference("useAutomationExtension", false);
        options.addPreference("enable-automation", true);
        options.addArguments("disable-infobars");

        //- control direct auto download 
        //options.addPreference("browser.download.improvements_to_download_panel", false);
        options.addPreference("browser.download.useDownloadDir", false);
        options.addPreference("download.prompt.for.download", true);

        //- this works for chrome
        //options.addPreference("download.restrictions",3);

        //- avoid pop ups and auto download after countdown
        options.addPreference("javascript.enabled", false);
        options.addPreference("devtools.chrome.enabled", true);

        return options;
    }


    public static void main(String[] args) throws IOException {

    	 //broken
      	 //String URL = "https://www.zoho.com/unknown";

    	 //detects AT
    	 //String URL= "https://www.zoho.com/forms/";

    	 //-auto downloads 
    	 //String URL= "https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.3.0/selenium-java-4.3.0.zip";

    	 //-pop ups
    	 String URL = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiC6qC7ifX4AhUq-jgGHamXBR8QFnoECA8QAQ&url=https%3A%2F%2Fneilpatel.com%2Fblog%2Fwebsite-popup-examples%2F&usg=AOvVaw3scgAVh9PLdPbVhYCBlBaR";


    System.setProperty("webdriver.gecko.driver",
        "C:\\Automation_Tools\\Drivers\\geckodriver.exe");
	
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");

    // func to add required spects
    options = addSpecifications(options);
      
        WebDriver driver = new FirefoxDriver(options);

		//JavascriptExecutor to run js in client side		
        JavascriptExecutor js = (JavascriptExecutor)driver;
       	        
        int ResponseCode = -1;
        String ContentType = "";

    try {
     
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        ResponseCode = connection.getResponseCode();
        ContentType = connection.getHeaderField("Content-Type");

    } catch (ConnectException e) {

        System.out.println("errr while getting response on url");

    }

    System.out.println(ResponseCode);
    System.out.println(ContentType);

    switch (ResponseCode) {
        case -1: System.out.println("Error in connecting, check Domain properly.");
            return;
        case 200: {
            if (!ContentType.contains("text/html")) {
                System.out.println("Link is download link");
                return;
            }
        }
            break;
        default:
            System.out.println("Link is broken/improper, Response code - " + ResponseCode);
            return;
    }

    //- get valid url using driver
    driver.get(URL);

    driver.manage().window().maximize();
    driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);

    System.out.println("page loaded");

        //js.executeScript("alert('Welcome to 2');");
        //js.executeScript("console.log('2scds32');");
	   
	    String data = driver.getPageSource();

    System.out.println(data.length());
       
		Path path
        = Paths.get("C:\\Users\\donga\\eclipse-workspace\\Seleniumm\\Result\\domContent.html");

    byte[] arr = data.getBytes();

    try {

        Files.write(path, arr);
        System.out.print("Dom fetched");
    }
    catch (IOException ex) {
        System.out.print("Invalid Path");
    }

    driver.quit();

}

    //-	WebDriver driver= new FirefoxDriver();	
    //- JavascriptExecutor js = (JavascriptExecutor)driver;		

    //Launching the Site.		
    //- driver.get("http://www.google.com");			

    //  WebElement button =driver.findElement(By.name("btnLogin"));			

    //Login		
    //	        driver.findElement(By.name("uid")).sendKeys("mngr34926");					
    //	        driver.findElement(By.name("password")).sendKeys("amUpenu");					

    //Perform Click on LOGIN button using JavascriptExecutor		
    //js.executeScript("arguments[0].click();", button);

    //To generate Alert window using JavascriptExecutor. Display the alert message 			
    //js.executeScript("alert('Welcome to Guru99');");
    //--js.executeScript("alert('Welcome to 2');");
    //js.executeScript("alert('Welcome to 222');");
    //js.executeScript("console.log(2)");
    //ChromeOptions options = new ChromeOptions();
    //options.addArguments("--headless");
    //options.setBinary("C:\\Automation_Tools\\Drivers\\chromedriver.exe");
    //WebDriver driver = new ChromeDriver(options);
    //driver.get("https://www.zoho.com");
    //        
    //String src = driver.getPageSource();
    //System.out.println(src);


     //        System.setProperty("webdriver.chrome.driver", "C:\\Automation_Tools\\Drivers\\chromedriver.exe");
     //        ChromeOptions options = new ChromeOptions();
     //        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");  //chrome binary location specified here
     //        options.addArguments("start-maximized");
     //       options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
     //     options.setExperimentalOption("useAutomationExtension", false);
     //   WebDriver driver = new ChromeDriver(options);
     // driver.get("https://www.google.com/");
     

}






