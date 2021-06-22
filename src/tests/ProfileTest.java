package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

//U okviru edit profile testa potrebno je izvršiti sledeće korake:
//●	učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
//●	ugasite lokacioni iskačući dijalog
//●	prijavite se na aplikaciju preko demo naloga
//●	verifikujte da je prikazana poruka sa tekstom "Login Successfull"
//●	učitajte stranicu http://demo.yo-meals.com/member/profile
//●	zamenite sve osnovne informacije korisnika
//●	verifikujte da je prikazana poruka sa tekstom "Setup Successful"
//●	odjavite se sa sajta
//●	verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"

	@Test
	public void editProfileTest() throws InterruptedException, IOException {
		driver.navigate().to(baseURL + "guest-user/login-form");
		Thread.sleep(1000);
		locationPopupPage.closePopup();
		loginPage.logIn(username, password);

		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Login Successfull"),
												"The Login Successfull message is not visible.");
		
		driver.navigate().to(baseURL + "member/profile"); 
		
		profilePage.changeInfo("John", "Doe", "Street 11", "123456789", "6845", "United States", "Colorado", "Boulder");
		
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Setup Successful"),
												"The Setup Successfull message is not visible."); 
		
		authPage.logout();
	
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Logout Successfull"),
												"The Logout Successfull message is not visible.");
	}

	@Test
	public void profileImgTest() throws InterruptedException, IOException {
//		učitajte stranicu http://demo.yo-meals.com/guest-user/login-form
//		ugasite lokacioni iskačući dijalog
		driver.navigate().to(baseURL + "guest-user/login-form");
		Thread.sleep(1000);
		locationPopupPage.closePopup();
		
//		prijavite se na aplikaciju preko demo naloga
//		verifikujte da je prikazana poruka sa tekstom "Login Successfull"
		loginPage.logIn(username, password);
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Login Successfull"),
												"The Login Successfull message is not visible.");
		
//		učitajte stranicu http://demo.yo-meals.com/member/profile
//		otpremite profilnu sliku
//		sliku iz images foldera
//		s obzirom na to da se za otpremanje šalje apsolutna putanja do slike, a mi koristimo relativnu,
//		moramo da pribavimo putanju na sledeći način
//		String imgPath = new File("imagеs/slika.png").getCanonicalPath();
//		verifikujte da je prikazana poruka sa tekstom "Profile Image Uploaded Successfully"
		driver.navigate().to(baseURL + "member/profile"); 
		
		String imgPath = new File("img/test.png").getCanonicalPath();
		profilePage.changeImg(imgPath);
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Profile Image Uploaded Successfully"),
												"The Profile Image Uploaded Successfully message is not visible.");
		
//		sačekajte da nestane obaveštenje
		notificationSystemPage.notificationDissapeared();

//		verifikujte da je prikazana poruka sa tekstom "Profile Image Deleted Successfully"
		profilePage.deleteImg();
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Profile Image Deleted Successfully"),
												"The Profile Image Deleted Successfully message is not visible.");
//		sačekajte da nestane obaveštenje
		notificationSystemPage.notificationDissapeared();
		
//		odjavite se sa sajta
//		verifikujte da je prikazana poruka sa tekstom "Logout Successfull!"
		authPage.logout();
		Assert.assertTrue(notificationSystemPage.notificationMsg().contains("Logout Successfull!"),
												"The Logout Successfull! message is not visible.");	
	}
	

}
