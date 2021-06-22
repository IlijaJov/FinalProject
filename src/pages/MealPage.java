package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
	}
	
//	get metode za sve potrebne elemente

	public WebElement mealsBtn() {
		return driver.findElement(By.xpath("//*[contains(text(),'Meals')]"));
	}
	
	public WebElement addToCart() {
		return driver.findElement(By.xpath("//*[contains(text(),'Add To Cart')]"));
	}
	
	public WebElement quantityBtn() {
		return driver.findElement(By.name("product_qty"));
	}
	
//	metodu koja jelo dodaje u omiljena jela, klikom na dugme Favorite 
	public void addToFavorite() {
		driver.findElement(By.xpath("//a[@title='Favorite']")).click();
	}
//	metodu koja dodaje jelo u korpu - kao parametar se prosleđuje količina
	public void addMeal(int quantity) throws InterruptedException {
		Thread.sleep(500);
		this.quantityBtn().sendKeys(Keys.DELETE);
		Thread.sleep(500);
		this.quantityBtn().sendKeys(String.valueOf(quantity));
		Thread.sleep(500);
		this.addToCart().click();
	}

}
