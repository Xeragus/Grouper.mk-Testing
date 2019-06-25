import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * Class for testing Grouper.mk functionalities
 * @author Boban Sugareski
 */

public class Grouper {
	
	private static WebDriver driver;
	private String url;
	
	public Grouper(String url) {
		System.setProperty("webdriver.chrome.driver",  "/home/gsixdev/Downloads/chromedriver");
		driver = new ChromeDriver();
		this.url = url;
	}
	
	public void startPage() throws InterruptedException {
		driver.get(url);
		Thread.sleep(1000);
	}

	public String wrongLogin(String email, String password) throws InterruptedException {
		driver.findElement(By.cssSelector("li:nth-child(1) .visible-sm")).click();
		Thread.sleep(1500);
	    driver.findElement(By.id("login-email-input")).sendKeys("random@gmail.com");
	    Thread.sleep(500);
	    driver.findElement(By.id("login-password-input")).sendKeys("randomhehehe");
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector(".login-btn-page")).click();
		Thread.sleep(1500);
		return driver.findElement(By.className("login-errors-page")).getText().trim();
	}
	
	public WebElement login(String email, String password) throws InterruptedException {
		driver.get("https://grouper.mk/users/login");
		driver.findElement(By.id("login-email-input")).sendKeys(email);
		driver.findElement(By.id("login-password-input")).sendKeys(password);
		driver.findElement(By.cssSelector(".login-btn-page")).click();
		Thread.sleep(3500);
		return driver.findElement(By.xpath("//a[contains(@href, '/pages/mobile-app')]"));
	}
	
	public WebElement logout() throws InterruptedException {
		driver.get("https://grouper.mk");
		driver.findElement(By.cssSelector(".pull-right > .dropdown-toggle > span")).click();
	    driver.findElement(By.id("navigation-logout-trigger")).click();
	    return driver.findElement(By.className("alert-success"));
	}
	
	public String register() throws InterruptedException {
		driver.get("https://grouper.mk/users/register");
		String userName = generateRandomString();
	    driver.findElement(By.id("first_name")).sendKeys(userName);
	    driver.findElement(By.id("last_name")).sendKeys(userName);
	    driver.findElement(By.id("register-email")).sendKeys(userName + "@gmail.com");
	    driver.findElement(By.id("gender")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("gender"));
	      dropdown.findElement(By.xpath("//option[. = 'Машки']")).click();
	    }
	    driver.findElement(By.id("gender")).click();
	    driver.findElement(By.id("register-password")).sendKeys("99-oe-11cde");
	    driver.findElement(By.id("register-password-confirmation")).sendKeys("99-oe-11cde");
	    driver.findElement(By.cssSelector(".register-btn-page")).click();
	    Thread.sleep(3000);
	    return driver.findElement(By.className("register-success-page")).getText().trim();	
	}
	
	public String testVariantRequired() throws InterruptedException {
		driver.get("https://grouper.mk/deal/nokjevanje-dvajca-ohrid-kosta-juli-avgust");
	    driver.findElement(By.id("buy-now-trigger")).click();
	    Thread.sleep(1000);
	    return driver.findElement(By.className("variant_options_validation")).getText().trim();	
	}
	
	public WebElement buyNow() throws InterruptedException {
		driver.get("https://grouper.mk/deal/last-minute-grcija-gerakini-halkidiki-atlantis");
		Thread.sleep(1500);
	    driver.findElement(By.id("buy-now-trigger")).click();
	    Thread.sleep(2500);
	    return driver.findElement(By.id("process-payment-trigger"));
	}
	
	public String generateRandomString() {
		Random rnd = new Random();
	    String firstname = "aspodaskd";
	    String lastname = "aspodakspaos";

	    String result;

	    result = Character.toString(firstname.charAt(0)); // First char
	    if (lastname.length() > 5)
	      result += lastname.substring(0, 5);
	    else
	      result += lastname;
	    result += Integer.toString(rnd.nextInt(99));
	 
	    return result;
	}
	
	public void closeDriver() {
		driver.close();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}