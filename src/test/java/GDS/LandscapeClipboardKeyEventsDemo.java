package GDS;

import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class LandscapeClipboardKeyEventsDemo extends BaseTest{
	
	@Test
	public void testMiscFeatures() throws InterruptedException{
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		
		// Hereafter, rotate the device to landscape mode
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);
		
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		
		// Clipboard Testing
		driver.setClipboardText("Gagans WiFi"); // Setting text on the clipboard
		
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.getClipboardText()); //Fetching clipboard text
		
		// Android Key Events such as, pressing back, home, enter, etc.
		
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Next Line Text");
		
		Thread.sleep(3000);
		
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		Thread.sleep(1000);
	}
}
