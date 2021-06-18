package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasicPage {

	protected WebDriver driver;
	
	public BasicPage(WebDriver driver) {
		this.driver = driver;
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
