package ecomAppE2E;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import GDS.BaseTest;
import io.appium.java_client.AppiumBy;

public class ecom_TC_2 extends BaseTest{
	
	@Test
	public void testErrorToast() throws InterruptedException {
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
		gestureScrollToElement("Argentina");
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Argentina\"]")).click();
		driver.findElement(By.className("android.widget.Button")).click();
		String validationToastMsg = driver.findElement(AppiumBy.xpath("//android.widget.Toast")).getAttribute("name");
		Assert.assertEquals(validationToastMsg, "Please enter your name");
	}
}

/*
 * ### Learning: Typical tag name for the toast elements on android apps is
 * "android.widget.Toast"
 */