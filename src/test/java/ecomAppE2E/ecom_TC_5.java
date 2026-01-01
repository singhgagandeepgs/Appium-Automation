
package ecomAppE2E;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GDS.BaseTest;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ecom_TC_5 extends BaseTest {

	@Test
	public void testWebView() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
		driver.hideKeyboard();
		driver.findElement(By.className("android.widget.Button")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// Both the below wait mechanisms are correct and fail-safe. Will not cause StaleElementReferenceException
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/btnProceed")));
		
		/*
		 * wait.until(driver -> driver.findElement(
		 * By.id("com.androidsample.generalstore:id/toolbar_title")
		 * ).getText().equals("Cart") );
		 */

		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		// ✅ WAIT FOR WEBVIEW TO APPEAR
		wait.until(d -> driver.getContextHandles().size() > 1);
		//wait.until(d -> ((AndroidDriver) d).getContextHandles().size() > 1);

		
		Set<String> appContexts = driver.getContextHandles();
		String nativeContextName = "";
		String webviewContextName = "";
		
		for (String contextName : appContexts) {
			System.out.println(contextName);
			if(contextName.toLowerCase().contains("native")) {
				nativeContextName = contextName;
			}
			else if (contextName.toLowerCase().contains("webview")) {
				webviewContextName = contextName;
			}
		}
		
		//driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.context(webviewContextName);
		
		//wait.until(ExpectedConditions.titleIs("Google")); //Don't use it. It causes errors.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		
		driver.findElement(By.name("q")).sendKeys("Gagandeep Singh");
		
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//driver.context("NATIVE_APP");
		driver.context(nativeContextName);
		
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Gagandeep Singh");
	}
}