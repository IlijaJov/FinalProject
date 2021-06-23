package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {
//	●	get metodu za sve rezultate pretrage //*[@class='product-name']/a
//	●	metodu koja vraća nazive svih jela dobijenih pretragom
//	●	metodu koja vraća broj rezultata pretrage

	
	public SearchResultPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
	}
	
	public List<WebElement> results() {	
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));	
	}
	
	public List<String> mealNames(){
		List<String> meals = new ArrayList <String>();
		for (int i = 0; i < results().size(); i++) {
			meals.add(this.results().get(i).getText());
		}
		return meals;
	}
	
	public int numOfResults() {
		return this.mealNames().size();
	}

}
