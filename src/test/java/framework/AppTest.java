package framework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws MalformedURLException 
     */
    @Test
    public void shouldAnswerWithTrue() throws MalformedURLException
    {
    	File file = new File("src");
		File appFile = new File(file, "ApiDemos-debug.apk"); // app name
		
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Simulator"); // replace with "Android Device" to run on a device
		dCaps.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
		
		
		// uiautomator2 added due to problems with sending keys -> instead of desired text
		// the content of the clipboard was sent to EditText
		dCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dCaps);
		
		driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
		
		AssertJUnit.assertTrue(driver.findElementByXPath("//android.widget.TextView[@text='Preference']").isDisplayed());
		
		driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		
		AssertJUnit.assertTrue(driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").isDisplayed());
		
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		
		AssertJUnit.assertTrue(driver.findElementById("android:id/checkbox").isDisplayed());

		driver.findElementById("android:id/checkbox").click();

		AssertJUnit.assertTrue(driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").isDisplayed());
		
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		
		driver.findElementById("android:id/edit").sendKeys("Secure Network");
		
		AssertJUnit.assertTrue(driver.findElementById("android:id/edit").isDisplayed());
		
		AssertJUnit.assertTrue(driver.findElementById("android:id/edit").getText().equals("Secure Network"));
		
		AssertJUnit.assertTrue(driver.findElementsByClassName("android.widget.Button").get(1).isDisplayed());
		
		driver.findElementsByClassName("android.widget.Button").get(1).click();
		
    }
}
