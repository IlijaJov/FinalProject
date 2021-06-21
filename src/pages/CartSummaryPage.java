package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage {

	public CartSummaryPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
	}
	
//	●	get metodu za Clear All dugme

	public WebElement clearAllBtn() {
		return driver.findElement(By.xpath("//*[contains(text(),'Clear All')]"));
	}
//	●	metodu koja briše sve stavke iz korpe klikom na Clear All dugme
	public void clearAll() {
		clearAllBtn().click();
	}
	

}
