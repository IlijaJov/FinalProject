package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {
	
	@Test
	public void searchResultsTest() throws InterruptedException, IOException {
//		učitajte stranicu http://demo.yo-meals.com/meals
//		postavite lokaciju na "City Center - Albany"
		
		driver.navigate().to(baseURL + "meals");
		Thread.sleep(1000);
		locationPopupPage.closePopup();
		
		Thread.sleep(500);
		locationPopupPage.setLocation("City Center - Albany");
		
//		●	čitate podatke iz xlsx fajla > Meals Search Results Sheet
//		●	izvršite otvaranje svakog linka i postavljanje svake lokacije
//		●	i za svaku stranicu proverite rezultate pretrage 

		
		File file = new File("data/Data(1).xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meal Search Results");
		
		SoftAssert sa = new SoftAssert();
		
		for (int i = 1; i < 7; i++) {
			
			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			String mealURL = sheet.getRow(i).getCell(1).getStringCellValue();
			int numOfResults = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			
			locationPopupPage.setLocation(location);
			Thread.sleep(500);
			
			driver.navigate().to(mealURL);
			Thread.sleep(500);
			
			int numOfMeals = searchResultPage.numOfResults();
			Thread.sleep(1000);
			
//			verifikujte da je broj rezultata na stranici isti kao u fajlu
			sa.assertEquals(numOfMeals,numOfResults);
			
//		verifikujte da je svaki rezultat na stranici redom prikazan kao u fajlu
		for (int j = 0; j < searchResultPage.numOfResults(); j++) {
				
			String mealNameSheet = sheet.getRow(i).getCell(j + 3).getStringCellValue();
			Thread.sleep(500);
				
			String mealNamePage = searchResultPage.mealNames().get(j);
			Thread.sleep(500);
				
			sa.assertTrue(mealNamePage.contains(mealNameSheet), "Names don't match");
			Thread.sleep(500);
			}
			sa.assertAll();
		}

	}
	

}
