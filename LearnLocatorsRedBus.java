package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnLocatorsRedBus {
	public static void main(String[] args) {
		
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.redbus.in/");
	Actions a = new Actions(driver);
    WebElement from = driver.findElement(By.xpath("//div/input[@id='src']"));
    from.sendKeys("Madiwala, Bangalore");
    WebElement fromDropdown = driver.findElement(By.xpath("//*[@id=\'search\']/div/div[1]/div/ul/li"));
    a.moveToElement(fromDropdown);
    fromDropdown.click();
    WebElement dest = driver.findElement(By.xpath("//div/input[@id='dest']"));
    dest.sendKeys("Koyambedu, Chennai");
    WebElement destDropdown = driver.findElement(By.xpath("//*[@id=\"search\"]/div/div[2]/div/ul/li[1]"));
    a.moveToElement(destDropdown);
    destDropdown.click();
    WebElement pickCalendar = driver.findElement(By.xpath("//div/input[@id='onward_cal']"));
    pickCalendar.click();
    
    a.moveToElement(pickCalendar);
    WebElement selectdate = driver.findElement(By.xpath("//*[@id=\'rb-calendar_onward_cal\']/table/tbody/tr[4]/td[5]"));
    selectdate.click();
    
	WebElement elementPresent = driver.findElement(By.xpath("/html/body/section/div[2]/main/section/div/div[2]/section/div/button"));
	if(elementPresent.isDisplayed())
	{
		System.out.println("displaying");
		elementPresent.click();
		System.out.println("displaying");
	}
	else
	{
		System.out.println("unable to click");
	}
	
	
}
}
