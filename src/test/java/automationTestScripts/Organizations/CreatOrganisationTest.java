package automationTestScripts.Organizations;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonBusinessScripts.CreatingNewOrganization;
import commonBusinessScripts.HomePage;
import commonBusinessScripts.OrganizationInformation;
import commonBusinessScripts.Organizations;
import commonLibraries.ConfigFiles;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * @author Fayaz
 * @category Organizations
 * @code This code is written to create Organization based on parameters
 * @param Organization Name
 * @param Industry
 * @param Type
 */
public class CreatOrganisationTest extends ConfigFiles
{
	
	
//	@Test(groups={"regression"})
//	Comment below code line to start Group Execution

	@Test(priority = 1, description = "verifying Create Organisation test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Create Organisation test on Login Page")
	@Story("Story Name: To check Create Organisation")
	public void CreateOrganisation() throws Throwable
	{
		/*
		 * All POM Classes Objects Created
		 */
		HomePage HomePage = new HomePage(driver);
		Organizations Oganisations = new Organizations(driver);
		ConfigFiles configFiles =new ConfigFiles();
		CreatingNewOrganization CreatingNewOrganization = new CreatingNewOrganization(driver);
		OrganizationInformation OrganizationInformation =new OrganizationInformation(driver);
		
		configFiles.loginApplication();
		/* Step 1: Navigate to Organization link */
		HomePage.ClickOrganisationsLink();
		/* Step 2: Click on Create Organization Button */
		Oganisations.ClickCreateOrgBtn();
		/* Step 3: Enter AccountName in textBox*/
		CreatingNewOrganization.getAccountname().sendKeys(data.FetchDataFromExcelFile("Organizations", 1, 3),"_"+driverScripts.getRamDomNum());
		/* Step 4: Select Industry from DropDown */
		driverScripts.SelectDropDownByValue(CreatingNewOrganization.getIndustry(), data.FetchDataFromExcelFile("Organizations", 2, 3));
		/* Step 5: Select AccountType from DropDown */
//		driverScripts.SelectDropDownByValue(CreatingNewOrganization.getAccounttype(), data.FetchDataFromExcelFile("Organizations", 3, 3));
		/* Step 6: Click on Save Button */
		CreatingNewOrganization.ClickOnSaveBtn();
		/* Step 7: Validate Organization should be created */
		Assert.assertTrue(OrganizationInformation.getOrgSuccMsg().contains("Wipros"));
		configFiles.LogOutApplication();
		configFiles.CloseBrowser();
	}
	
}
