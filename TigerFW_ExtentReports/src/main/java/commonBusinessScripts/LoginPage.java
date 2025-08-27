package commonBusinessScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibraries.ConfigFiles;
/**
 * 
 * @author Fayaz
 *
 */
public class LoginPage extends ConfigFiles
{
	
	@FindBy(xpath = "//input[@name='user_name']")
	private WebElement userName;
	
	@FindBy(xpath = "//input[@name='user_password']")
	private WebElement password;
	
	@FindBy(id="submitButton")
	private WebElement LoginBtn;
	
	
	public void LoginToVtiger(String un,String pwd)
	{
//		userName.sendKeys(un);
//		password.sendKeys(pwd);
		driverScripts.enterText(userName, un, "username");
		driverScripts.enterText(password, pwd, "password");
//		LoginBtn.click();
		driverScripts.click(LoginBtn, "Login Button");
	}
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
}
