package driver_factory;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class YahooDriver_Script {
	public static WebDriver driver;
	public static Properties config;

	@BeforeMethod
	public void setup() throws Throwable {
		config = new Properties();
		config.load(new FileInputStream("D:\\selinium_practice\\Yahoo_Reg\\PropertyFiles\\Login.properties"));
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(config.getProperty("Url"));
		Thread.sleep(2500);

	}

	@Test
	public void verifyaccount() throws Throwable {
		driver.findElement(By.id(config.getProperty("objClick"))).click();
		Thread.sleep(2500);
		driver.findElement(By.xpath(config.getProperty("objfirstname"))).sendKeys(config.getProperty("fname"));
		driver.findElement(By.xpath(config.getProperty("objlastname"))).sendKeys(config.getProperty("lname"));
		driver.findElement(By.xpath(config.getProperty("objemailid"))).sendKeys(config.getProperty("email"));
		driver.findElement(By.xpath(config.getProperty("objpassword"))).sendKeys(config.getProperty("pass"));
		driver.findElement(By.xpath(config.getProperty("objyear"))).sendKeys(config.getProperty("year"));
		driver.findElement(By.xpath(config.getProperty("objsubmit"))).click();
		String expected = "reg&done";
		String actual = driver.getCurrentUrl();
		if (actual.toLowerCase().contains(expected.toLowerCase())) {
			System.out.println("Sucessfull login" + actual + "  " + expected);
		} else {
			System.out.println(" login Failed" + actual + "  " + expected);
		}
		Thread.sleep(2500);
	}

	@Test
	public void verifymodile() throws Throwable {
		driver.findElement(By.id(config.getProperty("objClick"))).click();
		Thread.sleep(2500);
		driver.findElement(By.xpath(config.getProperty("objfirstname"))).sendKeys(config.getProperty("fname"));
		driver.findElement(By.xpath(config.getProperty("objlastname"))).sendKeys(config.getProperty("lname"));
		driver.findElement(By.xpath(config.getProperty("objemailid"))).sendKeys(config.getProperty("email"));
		driver.findElement(By.xpath(config.getProperty("objpassword"))).sendKeys(config.getProperty("pass"));
		driver.findElement(By.xpath(config.getProperty("objyear"))).sendKeys(config.getProperty("year"));
		driver.findElement(By.xpath(config.getProperty("objsubmit"))).click();
		Thread.sleep(2500);
		driver.findElement(By.xpath(config.getProperty("objmodile"))).sendKeys(config.getProperty("modilenumber"));
		driver.findElement(By.xpath(config.getProperty("objsendcode"))).click();
		Thread.sleep(2500);
		String expe = "authCredentialsType";
		String act = driver.getCurrentUrl();
		if (act.toLowerCase().contains(expe.toLowerCase())) {
			System.out.println("SMS Sent" + act + "   " + expe);
		} else {
			System.out.println("SMS Failed" + act + "   " + expe);

		}
		Thread.sleep(2500);

	}

	@Test
	public void verifyterms_conditons() throws Throwable {
		String parent = driver.getWindowHandle();
		driver.findElement(By.className(config.getProperty("objterms"))).click();
		driver.findElement(By.xpath(config.getProperty("objprivacy"))).click();
		Set<String> allwins = driver.getWindowHandles();
		System.out.println(allwins);
		for (String each : allwins) {
			if (!parent.equals(allwins)) {
				String pagetitle = driver.switchTo().window(each).getTitle();
				System.out.println(pagetitle);
				Thread.sleep(2500);
				driver.close();

			}

		}

	}

	@AfterMethod
	public void teardown() throws Throwable {
		Thread.sleep(1200);
		driver.close();

	}

}
