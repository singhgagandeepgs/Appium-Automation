package ecomAppE2E;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest2 extends MobileBrowserBaseTest {

	@Test
	public void testResponsiveWebApp() {
		driver.navigate().to("https://drivetest.ca/");
		
		driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		driver.findElement(By.cssSelector("button[aria-label='Drivers’ Licences']")).click();
		scrollDown();
		driver.findElement(By.cssSelector("a[title='Licence Info Changes']")).click();
		driver.findElement(By.cssSelector("svg.ionicon.s-ion-icon")).click();
		scrollDown();
		String text = driver.findElement(By.xpath("//a[@title='Find a drivetest centre']")).getText();
		Assert.assertEquals(text, "FIND A DRIVETEST CENTRE");
	}
	
	public void scrollDown() {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
	}
}