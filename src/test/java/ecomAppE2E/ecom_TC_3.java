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

public class ecom_TC_3 extends BaseTest{
	
	@Test
	public void testAddToCart() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
		driver.hideKeyboard();
		/*
		 * driver.findElement(AppiumBy.id(
		 * "com.androidsample.generalstore:id/radioFemale")).click();
		 * driver.findElement(AppiumBy.className("android.widget.Spinner")).click();
		 * gestureScrollToElement("Argentina"); driver.findElement(By.
		 * xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Argentina\"]"
		 * )).click();
		 */
		driver.findElement(By.className("android.widget.Button")).click();
		
		gestureScrollToElement("Jordan 6 Rings");
		
		List<WebElement> visibleProductsList = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
		int numOfVisibleProducts = visibleProductsList.size();
		
		List<WebElement> addToCartButtons = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart"));
		
		for(int i=0; i<numOfVisibleProducts; i++) {
			String productName = visibleProductsList.get(i).getText();
			if(productName.equals("Jordan 6 Rings")) {
				addToCartButtons.get(i).click();
				// Either above stmt or below stmt: BOTH WORK
				//driver.findElement(By.xpath("(//android.widget.TextView[@text=\"ADD TO CART\"])["+(i+1)+"]")).click();
			}
		}
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		WebElement screenTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(screenTitle, "text", "Cart"));
		
		WebElement productOnCart = driver.findElement(By.id("com.androidsample.generalstore:id/productName"));
		String productTitleOnCart = productOnCart.getText();
		Assert.assertEquals(productTitleOnCart, "Jordan 6 Rings");
	}
}
