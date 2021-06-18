package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	
	public BasicPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js ) {
		this.driver = driver;
		this.wait = wait;
		this.js = js; 
	}
	
	public boolean elementExists(By by) {
		boolean exists = true;
		try {
			driver.findElement(by);
		} catch (Exception e) {
			exists = false;
		}
		return exists;
	}
}
