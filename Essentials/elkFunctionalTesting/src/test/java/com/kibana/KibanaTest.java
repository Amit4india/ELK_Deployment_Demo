package com.kibana;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class KibanaTest {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
    String timeStamp;
    String chromeDriverLocation;
    String geckDriverLocation;
    String fileWithPath;
    WebDriver driver;


    @BeforeTest
    public void config() {
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/" + "KibanaValidation_" + timeStamp + ".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        /*chromeDriverLocation = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        geckDriverLocation = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";*/
    }

 /* @Test (priority = 0)
    public void PrestoHomePage() throws InterruptedException, MalformedURLException {
      System.out.println("Verifying the Login page");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\amit5india\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        driver.get("https://almsmart.demos.hclets.com/Presto/login-portal.jsp");
        String classname = driver.findElement(By.className("well")).getText();
      System.out.println("Getting Text from the Presto Login page for Jenkins Manual Verification >>>");
        System.out.println(classname);
        Assert.assertTrue(classname.contains("Welcome to Secure Login Portal"));
        System.out.println("Successfully verify the Presto Login page");
    }*/

    @Test(priority = 0)
    public void kibanTest() throws Exception {
        System.out.println("Verifying the Kibana Home Page");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //driver.get("https://almsmart.demos.hclets.com/Presto/about-us.jsp");
        String kibanaURL = System.getProperty("EndpointURL");
        System.out.println("kibanaURL" + kibanaURL);
        driver.get(kibanaURL);


        fileWithPath = Util.takeSnapShot(driver);
        WebDriverWait wait = new WebDriverWait(driver, 240);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='logo kibana']")));
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src=\"/plugins/kibana/assets/app_security.svg\"]")));


        if (driver.findElement(By.xpath("//li[@class='logo kibana']")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "kibana text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "kibana text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


        if (driver.findElement(By.xpath("//div[contains(text(),'Discover')]")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "Discover text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "Discover text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


        if (driver.findElement(By.xpath("//div[contains(text(),'Dashboard')]")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "Dashboard text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "Dashboard text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


        if (driver.findElement(By.xpath("//div[contains(text(),'Visualize')]")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "Visualize text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "Visualize text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


        if (driver.findElement(By.xpath("//div[contains(text(),'Timelion')]")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "Timelion text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "Timelion text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


        if (driver.findElement(By.xpath("//div[contains(text(),'Dev Tools')]")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "Dev Tools text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "Dev Tools text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


        if (driver.findElement(By.xpath("//div[contains(text(),'Management')]")).isDisplayed()) {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.PASS, "Management text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        } else {
            fileWithPath = Util.takeSnapShot(driver);
            test.log(Status.FAIL, "Management text label is visible " + test.addScreenCaptureFromPath(fileWithPath));
        }


    }


    @AfterTest
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}













        /*String classname = driver.findElement(By.className("well")).getText();
        System.out.println("Getting Text from the Presto About page for Jenkins Manual Verification");
        System.out.println(classname);
        Assert.assertTrue(classname.contains("Our Profile"));
        System.out.println("Successfully verified the Tab >About page");
    }
}*/

