package ecomAppE2E;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ecom_TC_1 extends E2EBaseTest{
	
	@Test
	public void testFillForm() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
		gestureScrollToElement("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Argentina\"]")).click();
		driver.findElement(By.className("android.widget.Button")).click();
	}
}

/*
 * ### New Command learnt: 
 * driver.hideKeyboard();
 */