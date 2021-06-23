package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealsTest extends BasicTest {
	
	@Test
	public void addMealToCartTest() throws InterruptedException {

//		učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		ugasite lokacioni iskačući dijalog

		driver.navigate().to(baseURL + "meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		locationPopupPage.closePopup();
		
//		dodajte jelo u korpu, količina je proizvoljna
//		verifikujte da je prikazana poruka sa tekstom 
//		"The Following Errors Occurred:
//		Please Select Location"
		mealPage.addMeal(3);
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("The Following Errors Occurred"),
						"The The Following Errors Occurred: Please Select Location message is not visible.");
		
//		sačekajte da obaveštenje nestane
//		postavite lokaciju na "City Center - Albany"
		notificationSystemPage.notificationDissapeared();
		locationPopupPage.setLocation("City Center - Albany");
		
//		dodajte jelo u korpu, količina je proizvoljna
//		verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"
		mealPage.addMeal(4);
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Meal Added To Cart"),
												"The Meal Added To Cart message is not visible.");
	}
	
	
	@Test
	public void addMealToFavorites() throws InterruptedException {

//		●	učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		●	ugasite lokacioni iskačući dijalog		
		driver.navigate().to(baseURL + "meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		locationPopupPage.closePopup();
		
//		●	dodajte jelo u omiljena jela
//		●	verifikujte da je prikazana poruka sa tekstom "Please login first!"		
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Please login first"),
												"The Please login first message is not visible.");
		
//		●	učitajte stranicu za prijavu
//		●	prijavite se na aplikaciju preko demo naloga
		driver.navigate().to(baseURL + "guest-user/login-form");
		Thread.sleep(1000);
		
		loginPage.logIn(username, password);
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Login Successfull"),
												"The Login Successfull message is not visible.");

//		●	učitajte stranicu http://demo.yo-meals.com/meal/lobster-shrimp-chicken-quesadilla-combo
//		●	dodajte jelo u omiljena jela
//		●	verifikujte da je prikazana poruka sa tekstom "Product has been added to your favorites."
		driver.navigate().to(baseURL + "meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Product has been added to your favorites"),
												"The Product has been added to your favorites. message is not visible.");
	}
	
	@Test
	public void clearCartTest() throws InterruptedException, IOException {
	
//		učitajte stranicu http://demo.yo-meals.com/meals
		driver.navigate().to(baseURL + "meals");
		Thread.sleep(1000);
		locationPopupPage.closePopup();
		
//		●	postavite lokaciju na "City Center - Albany"
		locationPopupPage.setLocation("City Center - Albany");
		
//		●	čitate podatke iz xlsx fajla > Meals Sheet 
//		●	u korpu dodajte svaki proizvod sa određenom količinom
//		●	za svako dodavanje proizvioda verifikujte da je prikazana poruka sa tekstom "Meal Added To Cart"
//		○	koristite SoftAssert za ovu proveru
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		
		SoftAssert sa = new SoftAssert();
		
		for (int i = 1; i < 6; i++) {
			String mealURL = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();
			
			driver.navigate().to(mealURL);
			mealPage.addMeal(quantity);
			sa.assertTrue(notificationSystemPage.notificationMsg().contains("Meal Added To Cart"),
													"The Meal Added To Cart message is not visible.");
			
		}
		
//		●	obrišite sve stavke iz korpe
//		●	verifikujte da je prikazana poruka sa tekstom "All meals removed from Cart successfully"
		
		cartSummaryPage.clearAll();
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("All meals removed from Cart successfully"),
				"The All meals removed from Cart successfully message is not visible.");
		sa.assertAll();
	}
}
