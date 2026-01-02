package ecomAppE2E;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MobileBrowserTest extends MobileBrowserBaseTest {

	@Test
	public void mobileBrowserTest() {
		driver.navigate().to("https://google.com");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		
		System.out.println(driver.getTitle());

		driver.findElement(By.name("q")).sendKeys("Gagandeep Singh");

		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}
}