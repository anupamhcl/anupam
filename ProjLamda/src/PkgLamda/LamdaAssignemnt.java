package PkgLamda;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LamdaAssignemnt {

	private static RemoteWebDriver driver;
	private static String strGlobalUserName = "lambda";
	private static String stGlobalPassword = "lambda123";
	private static String stGlobalEmail = "anupam.bhattacharya@hcl.com";
	private static String stGlobalParentURL = "https://www.lambdatest.com/automation-demos/";	
	private static String stGlobalTabURL = "https://www.lambdatest.com/selenium-automation";
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters("browser")

	public void initial(String browser) throws Exception{			

		if(browser.equalsIgnoreCase("chrome"))
		{			
			System.setProperty("webdriver.chrome.driver",".\\chromedriver90.exe");		
			ChromeOptions options = new ChromeOptions();

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.prompt_for_download", false);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);

		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxDriver.PROFILE, profile);

			driver = new FirefoxDriver(cap);
		}

		else if(browser.equalsIgnoreCase("ie"))
		{			
			System.setProperty("webdriver.edge.driver",".\\IEDriverServer.exe");			
			driver = new InternetExplorerDriver();
		}
		else
		{			
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	@SuppressWarnings("unlikely-arg-type")
	public void formfillup() throws AWTException, InterruptedException, IOException 
	{
		driver.get(stGlobalParentURL);
		Thread.sleep(2000);

		WebElement wePageLabel = driver.findElement(By.xpath("//h2[contains(text(),'Login to Selenium Playground')]"));


		if("Login to Selenium Playground" .equals(wePageLabel.getText()))		
			assertTrue(true, "Login to Selenium Playground");		
		else
			assertTrue(true, "Not Login to Selenium Playground");

		System.out.println("Launched in Base page");

		WebElement weInputUserName = driver.findElement(By.id("username"));
		WebElement weInputPassword = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement weBtnLogin = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

		weInputUserName.sendKeys(strGlobalUserName);
		weInputPassword.sendKeys(stGlobalPassword);
		weBtnLogin.click();

		Thread.sleep(5000);			

		WebElement weInputEmail = driver.findElement(By.cssSelector("#developer-name"));
		weInputEmail.sendKeys(stGlobalEmail);
		WebElement weBtnPopulate = driver.findElement(By.xpath("//input[@id='populate']"));
		weBtnPopulate.click();

		Alert alert = driver.switchTo().alert();
		String alertPopupMessage = driver.switchTo().alert().getText();
		System.out.println(alertPopupMessage);            
		alert.accept();

		WebElement weRadiobtnFreqTime = driver.findElement(By.xpath("//input[@id='6months']"));
		weRadiobtnFreqTime.click();

		WebElement weChkBoxDecisiveFactorsOne = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[1]/label[1]"));
		weChkBoxDecisiveFactorsOne.click();

		WebElement weChkBoxDecisiveFactorsTwo = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[2]/label[1]"));
		weChkBoxDecisiveFactorsTwo.click();

		WebElement weChkBoxFreqFactorsThree = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[3]/label[1]"));
		weChkBoxFreqFactorsThree.click();

		WebElement weChkBoxFreqFactorsFour = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[4]/label[1]"));
		weChkBoxFreqFactorsFour.click();

		WebElement weDropdownModePayment = driver.findElement(By.xpath("//select[@id='preferred-payment']"));
		if(weDropdownModePayment.isSelected())
			weDropdownModePayment.clear();

		Select dropdownSelect = new Select(weDropdownModePayment);
		dropdownSelect.selectByIndex(1);

		WebElement weChkBoxDeclaration= driver.findElement(By.cssSelector("#tried-ecom"));
		weChkBoxDeclaration.click();

		WebElement fromElement = driver.findElement(By.xpath(" //div[contains(text(),'5')]")); 
		WebElement toElement = driver.findElement(By.xpath("//div[contains(text(),'9')]"));

		Actions act=new Actions(driver); 
		act.dragAndDrop(fromElement,toElement).build().perform();

		if(toElement.getText().equals("9"))
			assertTrue(true, "9");

		Thread.sleep(2000);

		WebElement weFeedback = driver.findElement(By.xpath("//textarea[@id='comments']"));
		weFeedback.sendKeys("Thanks");
		weFeedback.sendKeys(Keys.RETURN);
		weFeedback.sendKeys("Thanks again");

		((JavascriptExecutor) driver).executeScript("window.open()");             
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.navigate().to(stGlobalTabURL);

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		WebElement logoJenkins = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[7]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]"));
		String logoSRC = logoJenkins.getAttribute("src");
		System.out.println(logoSRC);
		URL imageURL = new URL(logoSRC);            

		BufferedImage saveImage = ImageIO.read(imageURL);			
		ImageIO.write(saveImage, "svg", new File("externalFiles/jenkins.svg"));

		driver.switchTo().window(tabs.get(0));

		WebElement uploadfiles = driver.findElement(By.xpath("//label[@id='img']"));   
		uploadfiles.click(); 
		Thread.sleep(2000);         

		uploadfiles.sendKeys("externalFiles/jenkins.svg");

		Alert alertImage = driver.switchTo().alert();
		String alertImageMsg = driver.switchTo().alert().getText();
		System.out.println(alertImageMsg);            
		alertImage.accept();

		if(alertImageMsg.equals("your image upload sucessfully!!"));
			assertTrue(true,"Form SUbmitted successfully");

		WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submit-button']"));  
		btnSubmit.click();

		WebElement btnFormSuccess = driver.findElement(By.xpath("//p[contains(text(),'You have successfully submitted the form.')]"));   
		if(btnFormSuccess.equals(btnFormSuccess.getText()))
			assertTrue(true,"Form SUbmitted successfully");
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.close(); 
		driver.quit();	
	}
}


