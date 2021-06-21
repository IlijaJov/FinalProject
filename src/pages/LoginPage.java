package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
	}
	
//	Login Page:
//			get metode za sve potrebne elemente
	public WebElement getUsername() {
		return driver.findElement(By.name("username"));
	}
	
	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}
	
	public WebElement getLoginBtn() {
		return driver.findElement(By.name("btn_submit"));
	}
//	metodu koja prijavljuje korisnika na sistem - kao parametri se prosleđuju imejl i lozinka
	public void logIn(String username, String password) throws InterruptedException {
		this.getUsername().clear();
		Thread.sleep(500);
		this.getUsername().sendKeys(username);
		this.getPassword().clear();
		Thread.sleep(500);
		this.getPassword().sendKeys(password);	
		this.getLoginBtn().click();
	}
	
}
