package GDS;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class LSModeClipboardKeyEventsDemo extends BaseTest{
	
	@Test
	public void testMiscFeatures() throws InterruptedException{
		
		// App Package & App Activity
		// Directly launching the app on a specific screen aka activity
		// Windows Command: adb shell dumpsys window | find "mCurrentFocus"
		
		/*
		 * String appPackage = "io.appium.android.apis"; String appActivity =
		 * "io.appium.android.apis.preference.PreferenceDependencies";
		 * 
		 * Activity activity = new Activity(appPackage, appActivity);
		 * 
		 * driver.startActivity(activity); // Deprecated in latest Appium. Use below JS
		 * code instead.
		 */		
		((JavascriptExecutor) driver).executeScript("mobile: startActivity",
				ImmutableMap.of("intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
		
		//Commented out the 2 lines below as step-by-step navigation to the desired screen is not required now
		//Because above code will help us directly open the intended screen
		
		//driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		//driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
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
