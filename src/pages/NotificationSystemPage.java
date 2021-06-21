package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage{

//	Notification Sistem Page:

	public NotificationSystemPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
		this.wait = new WebDriverWait(driver, 30, 10);
	}
	
//	get metodu za element koji prikazuje poruku
//	//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]
	public WebElement notification() {
		return driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

//	metodu koja vraća poruku koja se nalazi u obaveštenju
	public String notificationMsg() {
		String msg = this.notification().getText();
		return msg;
	}
//	metodu koja čeka da obaveštenje nestane
//	čeka se da element //*[contains(@class, 'system_message')]
//	za atribut style dobije vrednost  "display: none;"
	
	public boolean notificationDissapeared() {
		return this.wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"),
								"style",  "display: none;"));
	}
}
