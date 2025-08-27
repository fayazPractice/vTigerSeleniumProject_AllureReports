package commonLibraries;

import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

/**
 * @code This class provides all re-usable Scenarios of WebDriver @author Fayaz
 *
 */
public class DriverScripts {

	/**
	 * 1 To get Random Integer Values
	 * 
	 * @return i
	 */
	public int getRamDomNum() {
		Random r = new Random();
		int i = r.nextInt(1000);
		return i;
	}

	/**
	 * 2 wait for all element to load in DOM document
	 * 
	 * @param driver
	 */
	public void waitForPagetoLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		logInfo("waiting For Page to Load");
	}

	/**
	 * 3 wait for visibility of specific element in GUI
	 * 
	 * @param driver
	 * @param expEelement
	 */
	public void waitForElementVisibility(WebDriver driver, WebElement expEelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(expEelement));
		logInfo("Waiting For Element Visibility "+ cleanLocatorString(expEelement) );
	}

	/**
	 * 4 wait for page title to be available
	 * 
	 * @param driver
	 * @param pageTitle
	 */
	public void waitForPageTitleVisibility(WebDriver driver, String pageTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(pageTitle));
		logInfo("Waiting For Page Title Visibility");
	}

	/**
	 * 5 wait for any element
	 * 
	 * @param driver
	 * @param element
	 * @return
	 * @throws Throwable
	 */
	public boolean waitForAnyElement(WebDriver driver, WebElement element) throws Throwable {
		boolean flag = false;
		int count = 0;
		while (count < 30) {
			try {
				element.isDisplayed();
				flag = true;
				break;
			} catch (Throwable t) {
				count++;
				Thread.sleep(1000);
			}
		}
		logInfo("Waiting For Any Element"+cleanLocatorString(element));
		return flag;
	}

	/**
	 * 6 Use to wait and click an element
	 * 
	 * @param driver
	 * @param element
	 * @return flag
	 * @throws Throwable
	 */
	public boolean waitAndClickElement(WebDriver driver, WebElement element) throws Throwable {
		boolean flag = false;
		int count = 0;
		while (count < 30) {
			try {
				element.click();
				flag = true;
				break;
			} catch (Throwable t) {
				count++;
				Thread.sleep(1000);
			}
		}
		logInfo("Waiting And Click Element"+ cleanLocatorString(element));
		return flag;
	}

	/**
	 * 7 Used to Select value from Drop Down based on Parameter
	 * 
	 * @param WebElement
	 * @param EnterElement
	 */
	public void SelectDropDownByValue(WebElement WebElement, String enterElement) {
		Select s = new Select(WebElement);
		s.selectByValue(enterElement);
		logInfo("Select Drop Down By Value :- "+enterElement);
	}

	/**
	 * 8 Used to Select value from Drop Down based on Parameter
	 * 
	 * @param WebElement
	 * @param Index
	 */
	public void SelectDropDownByIndex(WebElement WebElement, int Index) {
		Select s = new Select(WebElement);
		s.selectByIndex(Index);
		logInfo("Select Drop Down By Index :- "+Index);
	}

	/**
	 * 9 Used to Switch to new window based on Parameter
	 * 
	 * @param driver
	 * @param pageTitle
	 */
	public void SwitchToSpecificWindow(WebDriver driver, String pageTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String winID = it.next();
			driver.switchTo().window(winID);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.contains(pageTitle)) {
				break;
			}
		}
		logInfo("Switching To Specific Window of page title :- "+pageTitle);
	}

	/**
	 * 10 Used to Accept Alert Message
	 * 
	 * @param driver
	 */
	public void alertOk(WebDriver driver) {
		driver.switchTo().alert().accept();
		logInfo("Switching Alert to accept (OK)");
	}

	/**
	 * 11 Used to Cancel Alert Message
	 * 
	 * @param driver
	 */
	public void alertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
		logInfo("Switching Alert to CANCEL");
	}

	/**
	 * 12 Used to move cursor to specific element(Mouse Over) based on Parameter
	 * 
	 * @param driver
	 * @param element
	 */
	public void moveMouseToElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		logInfo("Move Mouse To Element"+cleanLocatorString(element));
	}

	/**
	 * 13 Used to double click element based on Parameter
	 * 
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick().perform();
		logInfo("Perform double Click "+cleanLocatorString(element));
	}

	/**
	 * 14 Used to Right click element based on Parameter
	 * 
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
		logInfo("Perform Right Click "+cleanLocatorString(element));
	}

	/**
	 * 15 Used to Switch one Frame to another Frame based on parameter
	 * 
	 * @param drver
	 * @param attribute
	 */
	public void switchToFrame(WebDriver drver, String attribute) {
		drver.switchTo().frame(attribute);
		logInfo("switching To Frame with attribute "+ attribute);
	}

	/**
	 * 16 Used to Switch one Frame to another Frame based on parameter
	 * 
	 * @param drver
	 * @param index
	 */
	public void switchToFrame(WebDriver drver, int index) {
		drver.switchTo().frame(index);
		logInfo("switching To Frame with index "+ index);
	}

	/**
	 * 17 Used to Switch one Frame to another Frame based on parameter
	 * 
	 * @param drver
	 * @param element
	 */
	public void switchToFrame(WebDriver drver, WebElement element) {
		drver.switchTo().frame(element);
		logInfo("switching To Frame with index "+ cleanLocatorString(element));
	}

	/**
	 * 18 Used to Perform all JavascriptExecutor Actions based on parameter
	 * 
	 * @param driver
	 * @param javaScript
	 */
	public void executeJavaScript(WebDriver driver, String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript);
		logInfo("Execute Java Script query:- "+ javaScript);
	}

	private String cleanLocatorString(WebElement element) {
		String fullLocator = element.toString();
		// Look for the "->" delimiter and get the part after it
		int arrowIndex = fullLocator.indexOf("->");
		if (arrowIndex != -1) {
			return fullLocator.substring(arrowIndex + 2).trim();
		}
		return fullLocator; // Fallback if format is unexpected
	}

	public void click(WebElement locator, String elementName) {

		Allure.step("get Locator " + cleanLocatorString(locator));
		Allure.step("Clicking on the " + elementName);
		locator.click();
	}

	public void enterText(WebElement locator, String textToEnter, String elementName) {
		Allure.step("get Locator " + cleanLocatorString(locator));
		Allure.step("Entering text '" + textToEnter + "' into " + elementName);
		locator.sendKeys(textToEnter);
	}

	public void logInfo(String textToLog) {
		Allure.step(textToLog);
	}

	public String getElementText(WebElement locator, String elementName) {
		String text = locator.getText();
		Allure.step("get Locator " + cleanLocatorString(locator));
		Allure.step("Getting text from " + elementName + ": " + text);
		return text;
	}

}