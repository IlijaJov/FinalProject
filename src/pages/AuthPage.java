package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
	}
	
//	get metode za sve potrebne elemente sa stranice   
	
	public WebElement account() {
		return driver.findElement(By.xpath("//a[@class='after-arrow user-trigger-js']"));
	}

	public WebElement myAccBtn() {
		return driver.findElement(By.xpath("//*[contains(text(),'My Account')]"));
	}
	
	public WebElement logoutBtn() {
		return driver.findElement(By.xpath("//*[contains(text(),'Logout')]"));
	}
	
//	metodu koja odjavljuje korisnika sa sistema
	public void logout() {
		account().click();
		wait.until(ExpectedConditions.elementToBeClickable(logoutBtn())).click();	
	}
	

}
