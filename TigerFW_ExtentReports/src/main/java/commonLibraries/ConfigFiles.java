package commonLibraries;

import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import commonBusinessScripts.HomePage;
import commonBusinessScripts.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @code This Class provides Standard Schedule of connect DB,launching browser,
 *       login,logout,close browser,DisConnect DB by Using TestNG
 *       Annotations @author Fayaz
 *
 */
public class ConfigFiles {

	/**
	 * commonLibraries object creation
	 */
	public static WebDriver driver;

	public DriverScripts driverScripts = new DriverScripts();

//	public DataBaseLib db =new DataBaseLib();

	public FileData data = new FileData();

	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public void ConnectDB() throws SQLException {

		/* Connect to DB */
//		db.connectToDB();
	}

	@Parameters("browser")
	@BeforeClass
	public void LaunchBroser(String browser) throws Throwable {
		// To CrossBrowser execution unComment @Parameters and add argument as String browser
		// comment below line
//		String browser = data.FetchDataFromPropertiesFile("browser");

//		Launch the browser
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
//			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			driver = new ChromeDriver(options);
			driverScripts.logInfo("Chrome Browser is Launched");
			tdriver.set(driver);
			driver.manage().deleteAllCookies();
			driverScripts.logInfo("Deleted All Cookies");
			driver.manage().window().fullscreen();
			driverScripts.logInfo("Window is in fullscreen mode");
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
//			System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver(options);
			driverScripts.logInfo("FireFox Browser is Launched");
			tdriver.set(driver);
			driver.manage().deleteAllCookies();
			driverScripts.logInfo("Deleted All Cookies");
			driver.manage().window().fullscreen();
			driverScripts.logInfo("Window is in fullscreen mode");
		}else {
            System.out.println("Invalid browser specified!");
        }

		// To perform Remote Execution (SELENIUM GRID)

//		  String nodeURL="http://172.20.10.2:19859/wd/hub"; 
//		  DesiredCapabilities cap = new DesiredCapabilities(); 
//		  cap.setBrowserName(browser);
//		  cap.setPlatform(Platform.WINDOWS); 
//		  driver =new RemoteWebDriver(new
//		  URL(nodeURL),cap);
		 

		driver.get(data.FetchDataFromPropertiesFile("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
		driver.manage().window().maximize();
	}

	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

//	@BeforeMethod
	public void loginApplication() {
		/* Login to Application */
		LoginPage LoginPage = new LoginPage(getDriver());
		try {
			LoginPage.LoginToVtiger(data.FetchDataFromPropertiesFile("username"),
					data.FetchDataFromPropertiesFile("password"));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void LogOutApplication() throws Throwable {
		/* Logout from Application */
		HomePage HomePage = new HomePage(getDriver());
		HomePage.MoveToAdminstrator();
		HomePage.ClickOnSignOut();
	}

//	@AfterClass
	public void CloseBrowser() throws Throwable {
		/* Close the browser */
		if (driver != null) {
            driver.quit();
        }
	}

	public void CloseDB() throws SQLException {
		/* DisConnect DB */
//		db.DisconnectToDB();
	}

}
