package GDS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class DragDropGestureDemo extends BaseTest{
	
	@Test
	public void testDragDropGesture() throws InterruptedException{
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		gestureDragDrop(source, 650, 581);
		
		Thread.sleep(3000);
		
		String successMsg = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(successMsg, "Dropped!");
	}
}
