package tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;



public abstract class BasicTest {
	
	protected WebDriver driver;
	
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;
	
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	protected Actions actions;
	protected String baseURL = "http://demo.yo-meals.com/";
	protected String username = "customer@dummyid.com";
	protected String password = "12345678a";
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30, 100);
		
		loginPage = new LoginPage(driver, null, null, null);
		locationPopupPage = new LocationPopupPage(driver, wait, js, actions);
		notificationSystemPage = new NotificationSystemPage(driver, wait, js, actions);
		profilePage = new ProfilePage(driver, wait, js, actions);
		authPage = new AuthPage(driver, wait, js, actions);
		mealPage = new MealPage(driver, wait, js, actions);
		cartSummaryPage = new CartSummaryPage(driver, wait, js, actions);
		searchResultPage = new SearchResultPage(driver, wait, js, actions);
	}
	
	@AfterMethod
	public void cleanup(ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus()==ITestResult.FAILURE) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();  
		String dt = dtf.format(now); 
		
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr, new File("./screenshots/" + dt + ".jpg"));
		}
		Thread.sleep(3000);
		driver.quit();
	}

}
