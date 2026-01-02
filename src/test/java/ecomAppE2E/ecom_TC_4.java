
package ecomAppE2E;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ecom_TC_4 extends E2EBaseTest {

	@Test
	public void testCartTotal() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
		driver.hideKeyboard();
		driver.findElement(By.className("android.widget.Button")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		// WebElement screenTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
				"text", "Cart"));

		List<WebElement> addedProductPrices = driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		// int count = addedProductPrices.size();

		double totalAmount = 0.0;

		for (WebElement webElement : addedProductPrices) {
			String amountString = webElement.getText();
			Double productPrice = returnFormattedPrice(amountString);
			totalAmount += productPrice;
		}

		String displayedTotalAmtStr = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
				.getText();
		Double displayedTotalAmt = returnFormattedPrice(displayedTotalAmtStr);

		Assert.assertEquals(totalAmount, displayedTotalAmt);

		WebElement tAndC = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		gestureLongClick(tAndC);

		driver.findElement(AppiumBy.id("android:id/button1")).click();

		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

		Thread.sleep(3000);
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */