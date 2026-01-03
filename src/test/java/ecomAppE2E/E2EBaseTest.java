package ecomAppE2E;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class E2EBaseTest {

	AndroidDriver driver;
	AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		// service.start();

		URL appiumServer = new URI("http://127.0.0.1:4723/").toURL();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("GagansEmulator");
		options.setApp("F:\\appium-workspace\\AppiumLearning\\src\\test\\java\\resources\\General-Store.apk");

		// New statement added myself (Not in tutorial) to handle webview Chrome compatibility error
		options.setCapability("appium:chromedriverAutodownload", true);
		options.setCapability("appium:autoWebview", false);
		options.setCapability("appium:ensureWebviewsHavePages", true);

		driver = new AndroidDriver(appiumServer, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}
	}

	public void gestureLongClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public void gestureScrollDownOnceChatGPT() {
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 400,
				"width", 800, "height", 1200, "direction", "down", "percent", 0.5));
	}

	public void gestureScrollToEnd() {
		boolean canScrollMore;

		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}

	public void gestureScrollToVisibleText(String visibleText) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + visibleText + "\"));"));
	}

	public void gestureScrollToVisibleTextChatGPT(String visibleText) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())" + ".setMaxSearchSwipes(5)"
				+ ".scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\"))"));
	}

	public WebElement scrollUntilElementVisibleChatGPT(By locator) {

		int maxScrolls = 8;

		for (int i = 0; i < maxScrolls; i++) {
			try {
				WebElement element = driver.findElement(locator);
				if (element.isDisplayed()) {
					return element;
				}
			} catch (NoSuchElementException e) {
				// element not yet visible
			}

			gestureScrollDownOnceChatGPT();

			try {
				Thread.sleep(400); // allow RecyclerView to settle
			} catch (InterruptedException ignored) {
			}
		}

		throw new NoSuchElementException("Element not found after scrolling");
	}

	public void gestureSwipeOnAnElement(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.25));
	}

	public void gestureDragDrop(WebElement element, int dropCordX, int dropCordY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "endX", dropCordX, "endY", dropCordY));
	}

	public double returnFormattedPrice(String amountString) {
		String removedDollar = amountString.replace("$", "").trim();
		Double productPrice = Double.parseDouble(removedDollar);
		return productPrice;
	}

	public void addProductToCart(String productName) {
		WebElement productContainer = driver.findElement(By.xpath(
				"//android.widget.TextView[@text='" + productName + "']/ancestor::android.widget.RelativeLayout[1]"));
		WebElement addToCart = productContainer
				.findElement(By.xpath(".//android.widget.TextView[@text='ADD TO CART']"));
		addToCart.click();
	}
}
