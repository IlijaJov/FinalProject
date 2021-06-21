package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

//	●	get metode za sve potrebne elemente
//	○	sve elemente za izmenu osnovnih informacija kao i 
//	○	sve elemente potrebne za rad sa profilnom slikom

	
	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
		super(driver, wait, js, actions);
		// TODO Auto-generated constructor stub
	}
	
	public WebElement firstName() {
		return driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement lastName() {
		return driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement email() {
		return driver.findElement(By.name("user_email"));
	}

	public WebElement address() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement phoneNo() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement zipCode() {
		return driver.findElement(By.name("user_phone"));
	}
	
	public Select country() {
		Select country = new Select (driver.findElement(By.id("user_country_id")));
		return country;
	}
	
	public Select state() {
		Select state = new Select (driver.findElement(By.id("user_state_id")));
		return state;
	}
	
	public Select city() {
		Select city = new Select (driver.findElement(By.id("user_city")));
		return city;
	}
	
	public WebElement currentPassword() {
		return driver.findElement(By.name("current_password"));
	}
	
	public WebElement newPassword() {
		return driver.findElement(By.name("new_password"));
	}
	
	public WebElement confirmPassword() {
		return driver.findElement(By.name("conf_new_password"));
	}
	
	public WebElement saveInfoBtn() {
		return driver.findElement(By.xpath("//div[@class='row']//input[@name='btn_submit']"));
	}
	
	public WebElement savePasswordBtn() {
		return driver.findElement(By.xpath("//form[@name='changePwdFrm']//input[@name='btn_submit']"));
	}
	
//	○	da bi se na stranici pojavio element input type="file" potrebno je da se prvo izvrši JavaScript kod koji vrši 
//	akciju klik na Upload dugme 
//○	Skripta: arguments[0].click();
//●	metodu koja otprema profilnu sliku - kao parametar se prosleđuje putanja do fajla, tj. u ovom slučaju do slike
	public void changeImg(String imgPath) throws InterruptedException {
		WebElement addFile = driver.findElement(By.xpath("//a[@title='Uplaod']"));
		js.executeScript("arguments[0].click();", addFile);
		Thread.sleep(500);
		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys(imgPath);
	}
	
	//●	metodu koja briše profilnu sliku
	//○	klikom na Remove dugme 
	//○	Preporuka: izvršite JavaScript kod arguments[0].click(); nad tim dugmetom
	
	public void deleteImg() {
		WebElement dlt = driver.findElement(By.xpath("//a[@class='remove']"));
		js.executeScript("arguments[0].click();", dlt);
	}
	
	//●	metodu koja menja sve osnovne informacije korisnika - kao parametri se prosleđuju sve potrebne informacije
	public void changeInfo(String firstName, String lastName, String address, String phoneNo,
							String zipCode, String country, String state, String city) throws InterruptedException {
		this.firstName().clear();
		this.firstName().sendKeys(firstName);
		Thread.sleep(100);
		this.lastName().clear();
		this.lastName().sendKeys(lastName);
		Thread.sleep(100);
		this.address().clear();
		this.address().sendKeys(address);
		Thread.sleep(100);
		this.phoneNo().clear();
		this.phoneNo().sendKeys(phoneNo);
		Thread.sleep(100);
		this.zipCode().clear();
		this.zipCode().sendKeys(zipCode);
		Thread.sleep(100);
		this.country().selectByVisibleText(country);
		Thread.sleep(1000);
		this.state().selectByVisibleText(state);
		Thread.sleep(1000);
		this.city().selectByVisibleText(city);
		Thread.sleep(1000);
		saveInfoBtn().click();
		
	}
	
}
