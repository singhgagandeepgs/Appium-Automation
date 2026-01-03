package ecomAppE2E;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ecom_TC_3 extends E2EBaseTest {

	@Test
	public void testAddToCart() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
		driver.hideKeyboard();

		driver.findElement(By.className("android.widget.Button")).click();

		gestureScrollToVisibleTextChatGPT("Jordan 6 Rings");

		addProductToCart("Jordan 6 Rings");

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		// WebElement screenTitle =
		// driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
				"text", "Cart"));

		WebElement productOnCart = driver.findElement(By.id("com.androidsample.generalstore:id/productName"));
		String productTitleOnCart = productOnCart.getText();
		Assert.assertEquals(productTitleOnCart, "Jordan 6 Rings");
	}
}

/*
 * ### Learning: New method under ExpectedConditions class "attributeContains"
 */