package kibana;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//import managers.FileReaderManager;

public class KibanaTest {

	public class CreatingLogs
	{
		ExtentHtmlReporter htmlReporter;
		ExtentReports extent;
		ExtentTest test;
		String timeStamp;
		String chromeDriverLocation;
		String geckDriverLocation;
		String fileWithPath;
		WebDriver driver;

		@BeforeTest
		public void config()
		{
			timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/reports/"+"KibanaValidation_"+timeStamp+".html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			chromeDriverLocation = System.getProperty("user.dir")+ "\\drivers\\chromedriver.exe";
			geckDriverLocation = "FunctionalTest/drivers/geckodriver";
		}

		@Test
		@org.testng.annotations.Parameters("kibanaURL")
		public void kibanaScreenValidation(String kibanaURL) throws Exception
		{

			/*System.setProperty("webdriver.chrome.driver", driverLocation);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-features=VizDisplayCompositor");
			driver = new ChromeDriver(options);
			*/
			
			System.setProperty("webdriver.gecko.driver",geckDriverLocation);
			driver = new FirefoxDriver();
			Thread.sleep(5000);
			
			test = extent.createTest("KibanaValidationTest");
			//fileWithPath = Util.takeSnapShot(driver);

			driver.get(kibanaURL);

			WebDriverWait wait = new WebDriverWait(driver, 240);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='logo kibana']")));
			driver.manage().window().maximize();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src=\"/plugins/kibana/assets/app_security.svg\"]")));

			if(driver.findElement(By.xpath("//li[@class='logo kibana']")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "kibana text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "kibana text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

			if(driver.findElement(By.xpath("//div[contains(text(),'Discover')]")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "Discover text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "Discover text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

			if(driver.findElement(By.xpath("//div[contains(text(),'Dashboard')]")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "Dashboard text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "Dashboard text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

			if(driver.findElement(By.xpath("//div[contains(text(),'Visualize')]")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "Visualize text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "Visualize text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

			if(driver.findElement(By.xpath("//div[contains(text(),'Timelion')]")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "Timelion text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "Timelion text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

			if(driver.findElement(By.xpath("//div[contains(text(),'Dev Tools')]")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "Dev Tools text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "Dev Tools text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

			if(driver.findElement(By.xpath("//div[contains(text(),'Management')]")).isDisplayed()) {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.PASS, "Management text label is visible "+test.addScreenCaptureFromPath(fileWithPath));	
			}
			else {
				fileWithPath = Util.takeSnapShot(driver);
				test.log(Status.FAIL, "Management text label is visible "+test.addScreenCaptureFromPath(fileWithPath));
			}

		}

		@AfterTest
		public void tearDown()
		{
			driver.quit();
			extent.flush();
		}
	}

}
