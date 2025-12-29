package GDS;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ScrollGestureDemo extends BaseTest{
	
	@Test
	public void testScrollGesture(){
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		
		/*
		 * Using Google Engine's AndroidUIAutomator Use when you know where to scroll
		 * i.e., until any specific element androidUIAutomator executes the script
		 * passed as an argument wherein object is created for UiScrollable class Object
		 * of UiSelector is passed as an argument to UiScrollable object creation.
		 * Method named "scrollIntoView" is invoked using the object of UiScrollable
		 * class And, the visible text of the element is passed as an argument to the
		 * scrollIntoView method.
		 */
		
		gestureScrollToElement("WebView");
		
		
		/*
		 * Appium's Scroll Gesture using various coordinates Use when: 1. No prior idea
		 * of the element till which to scroll 2. Just scroll a little bit 3. Keep
		 * scrolling to the end of the page/screen, in case any such validation is to be
		 * performed.
		 */
		
		//gestureScrollToEnd();
		
	}
}
