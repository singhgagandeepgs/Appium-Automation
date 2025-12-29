package GDS;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AppiumBasicsDemo extends BaseTest{
	
	@Test
	public void testSettingWiFiName(){
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		driver.findElement(AppiumBy.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		
		driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Gagans WiFi");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
	}
}
