package com.Immerstec.actiondriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Immerstec.base.BasePage;

import io.netty.handler.timeout.TimeoutException;

public class Action extends BasePage {

	// عند عمل كلك على العنصر
	public void click(WebElement ele) {
		try {
			Actions act = new Actions(driver);
			act.click(ele).build().perform();
			System.out.println("Successfully click element");
		} catch (Exception e) {
			System.out.println("Not Successfully click element");

		}
	}

	// عند اخذ قيمة النص لعنصر معين
	public String getText(WebElement ele) {
		try {
			String text = ele.getText();
			System.out.println(" Successfully get Text");
			return text;

		} catch (Exception e) {
			System.out.println(" Not Successfully get Text");
			return null;
		}
	}

	// عند وضع نص معين داخل حقل معين
	public boolean sendText(WebElement ele, String text) {
		try {
			ele.clear();
			ele.sendKeys(text);
			System.out.println(" Successfully entered text");
			return true;

		} catch (Exception e) {

			System.out.println(" Successfully entered text");
			return false;
		}

	}

	// عند التاكد من وجود عنصر او عرض عنصر معين بالصفحة
	public boolean findElement(WebElement ele) {
		try {
			if (ele.isDisplayed()) {
				System.out.println("Successfully Found element");
				return true;
			}

		} catch (Exception e) {
			System.out.println(" Not Successfully Found element");
			return false;

		}

		System.out.println(" Not Successfully Found locate element");
		return false;
	}

	// عند التاكد اذا كان العنصر محدد ام لا
	public boolean isSelected(WebElement ele) {
		try {
			if (ele.isSelected()) {
				System.out.println("The element is Selected");
				return true;

			}
		} catch (Exception e) {
			System.out.println("The element not Selected");
			return false;
		}

		System.out.println(" Not Successfully locate element");
		return false;
	}

	// عند التاكد اذا كان العنصر فعال ام لا
	public boolean isEnabled(WebElement ele) {
		try {
			if (ele.isEnabled()) {
				System.out.println("The element is Enabled");
				return true;

			}
		} catch (Exception e) {
			System.out.println("The element not Enabled");
			return false;
		}

		System.out.println(" Not Successfully locate element or not displayed");
		return false;
	}

	// عند الاختيار من القائمة المنسدلة عنصر معين من خلال القيمة
	public boolean selectByValueWithDiv(String value, WebElement ele) {
		try {
			ele.sendKeys(value);
			System.out.println("Select value from the DropDown");
			return true;
		}

		catch (Exception e) {
			System.out.println("Not Selected value from the DropDown");
			return false;
		}
	}

	// عند الاختيار من القائمة المنسدلة عنصر معين من خلال الرقم او ال id
	public boolean selectByIndex(WebElement ele, int index) {
		try {
			Select s = new Select(ele);
			s.selectByIndex(index);
			System.out.println("Option selected by Index");
			return true;
		} catch (Exception e) {
			System.out.println("Option Not selected by Index");
			return false;
		}
	}

	// عند الاختيار من القائمة المنسدلة عنصر معين من خلال القيمة
	public boolean selectByValue(WebElement ele, String value) {
		try {
			Select s = new Select(ele);
			s.selectByValue(value);
			System.out.println("Option selected by Value");
			return true;
		} catch (Exception e) {
			System.out.println("Option Not selected by Value");
			return false;
		}

	}

	// عند حدوث مشكلة بالكلك العادية نستخدم الكلك من جافا سكربت
	public boolean JSClick(WebDriver driver, WebElement ele) throws Throwable {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			System.out.println("Click Action is performed");
			return true;
		} catch (Exception e) {
			System.out.println("Click Action is not performed");
			throw e;
		}
	}

	// تمرير الصفحة عند مستوى عنصر محدد
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	// الانتقال الى اطار معين داخل الصفحة من خلال الرقم او ال id
	public boolean switchToFrameById(WebDriver driver, String idValue) {
		try {
			driver.switchTo().frame(idValue);
			System.out.println("Frame with Id \"" + idValue + "\" is selected");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			return false;
		}
	}

	// الانتقال الى اطار معين داخل الصفحة من خلال الاسم او ال name
	public boolean switchToFrameByName(WebDriver driver, String nameValue) {
		try {
			driver.switchTo().frame(nameValue);
			System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			return true;
		} catch (Exception e) {
			System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			return false;
		}
	}

	// عند فتح نافذة جديدة يجب الانتقال اليها من خلال العنوان
	public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
		try {
			Set<String> windowList = driver.getWindowHandles();
			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count - 1]);

			boolean flag = driver.getTitle().contains(windowTitle);

			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not selected");
			}

			return flag;
		} catch (Exception e) {
			System.out.println("An error occurred while switching windows: " + e.getMessage());
			return false;
		}
	}

	// عند الذهاب الى نافذة جديدة
	public boolean switchToNewWindow(WebDriver driver) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			String[] handles = windowHandles.toArray(new String[0]);
			driver.switchTo().window(handles[1]);
			System.out.println("Window is Navigated with title");
			return true;
		} catch (Exception e) {
			System.out.println("The Window with title is not selected");
			return false;
		}
	}

	// الانتقال الى النافذة القديمة التي تسبق النافذة االحالية
	public boolean switchToOldWindow(WebDriver driver) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			String firstWindowHandle = windowHandles.iterator().next();
			driver.switchTo().window(firstWindowHandle);
			System.out.println("Focus navigated to the window with title");
			return true;
		} catch (Exception e) {
			System.out.println("The Window with title: is not Selected");
			return false;
		}
	}

	// عند ظهور مسج معين الضغط على ok
	public boolean AlertAccept(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("The alert was handled successfully");
			return true;
		} catch (NoAlertPresentException ex) {
			System.out.println("There was no alert to handle");
			return false;
		}
	}

	// عند ظهور مسج معين الضغط على cancel
	public boolean AlertCancel(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			System.out.println("The alert was handled successfully");
			return true;
		} catch (NoAlertPresentException ex) {
			System.out.println("There was no alert to handle");
			return false;
		}
	}

	// عند ظهور مسج معين ارسال نص معين
	public boolean AlertText(WebDriver driver, String text, Duration timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			if (alert != null) {
				alert.sendKeys(text);
				alert.accept();
				System.out.println("Alert handled successfully");
				return true;
			}
		} catch (TimeoutException ex) {
			System.out.println("Timeout: No alert to handle");
		} catch (NoAlertPresentException ex) {
			System.out.println("No alert to handle");
		}

		return false;
	}

	// التوجه الى لنك معين
	public boolean NavigateUrl(WebDriver driver, String url) {
		try {
			driver.navigate().to(url);
			System.out.println("Successfully launched \"" + url + "\"");
			return true;
		} catch (Exception e) {
			System.out.println("Failed to launch \"" + url + "\"");
			return false;
		}
	}

	// اخذ العنوان لصفحة
	public String getTitle(WebDriver driver) {
		String text = driver.getTitle();
		System.out.println("Title of the page is: \"" + text + "\"");
		return text;
	}

	// اخذ اللنك لصفحة الحالية
	public String getCurrentURL(WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL is: \"" + currentURL + "\"");
		return currentURL;
	}

	// الانتظار حسب الوقت الذي يحتاجه
	@SuppressWarnings("deprecation")
	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	// الانتظار حسب الوقت الذي يحتاجه بشرط معين
	public void explicitWait(WebDriver driver, WebElement element, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// لازالة الستايل عن الصفحة كاملة
	public void removeCSSStyle() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.querySelector('html').style.zoom = 'unset';");
	}

	// الانتظار حسب الوقت الذي يحتاجه بشرط معين لمجموعة عناصر
	public void explicitWaitForElements(WebDriver driver, List<WebElement> elements, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	// بحدد نص معين منم القائمة المنسدلة عند رؤيته
	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			System.out.println("Option selected by VisibleText");
			return true;
		} catch (Exception e) {
			System.out.println("Option not selected by VisibleText");
			return false;
		}
	}

	// وضع مؤشر الماوس على عنصر معين
	public boolean mouseHoverByJavaScript(WebElement ele) {
		try {
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent('mouseover', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, ele);
			System.out.println("MouseOver Action is performed");
			return true;
		} catch (Exception e) {
			System.out.println("MouseOver Action is not performed");
			return false;
		}
	}

	// عند الضغط مرتين على الماوس
	public void doubleClick(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).doubleClick().perform();
	}

	// عند الانتقال الى الاطار الافتراضي
	public boolean switchToDefaultFrame(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// وضع مؤشر الماوس على عنصر معين
	public void mouseOverElement(WebDriver driver, WebElement element) {
		try {
			new Actions(driver).moveToElement(element).build().perform();
			System.out.println("MouseOver Action is performed on");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MouseOver action is not performed on");
		}
	}

	// سحب و افلات العنصر
	public boolean dragAndDrop(WebDriver driver, WebElement source, int x, int y) {
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();

			System.out.println("Draggable Action is performed on \"" + source + "\"");
			return true;
		} catch (Exception e) {
			System.out.println("Draggable action is not performed on \"" + source + "\"");
			return false;
		}
	}

	// سحب و افلات العنصر
	public boolean dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			System.out.println("DragAndDrop Action is performed");
			return true;
		} catch (Exception e) {
			System.out.println("DragAndDrop Action is not performed");
			return false;
		}
	}

	// تحريك المؤشر
	public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
		try {
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform(); // 150,0
			Thread.sleep(5000);
			System.out.println("Slider Action is performed");
			return true;
		} catch (Exception e) {
			System.out.println("Slider Action is not performed");
			return false;
		}
	}

	// ضغط على الماوس كلك يمين على العنصر
	public boolean rightClick(WebDriver driver, WebElement ele) {
		try {
			Actions actions = new Actions(driver);
			actions.contextClick(ele).perform();
			System.out.println("RightClick Action is performed");
			return true;
		} catch (Exception e) {
			System.out.println("RightClick Action is not performed");
			return false;
		}
	}

	// الوقت الذي يحتاجه لتحميل الصفحة
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);

	}

	// اخذ لقطة شاشة بمكان معين داخل الكود
	public String captureScreenshot(WebDriver driver, String filename) {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/ScreenShots/" + filename + "_" + timestamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename
				+ "_" + timestamp + ".png";
		return newImageString;
	}

	// اخذ التاريخ والوقت
	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

}