package GDS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LongPressGestureDemo extends BaseTest{
	
	@Test
	public void testLongClickGesture(){
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Custom Adapter\"]")).click();
		WebElement elePeopleNames = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));
		gestureLongClick(elePeopleNames);
		WebElement eleSampleMenu = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample menu\"]"));
		String menuText = eleSampleMenu.getText();
		Assert.assertEquals(menuText, "Sample menu");
		WebElement eleSampleAction = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]"));
		Assert.assertTrue(eleSampleAction.isDisplayed());
	}
}
