package GDS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SwipeGestureDemo extends BaseTest{
	
	@Test
	public void testSwipeGesture(){
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
		WebElement firstImage = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[1]"));
		WebElement secondImage = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[2]"));
		Assert.assertEquals(firstImage.getAttribute("focusable"), "true");
		Assert.assertEquals(secondImage.getAttribute("focusable"), "false");
		gestureSwipeOnAnElement(firstImage, "left");
		Assert.assertEquals(firstImage.getAttribute("focusable"), "false");
		Assert.assertEquals(secondImage.getAttribute("focusable"), "true");
	}
}
