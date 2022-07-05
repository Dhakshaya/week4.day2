package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnWindowsHandle {
	
	public static void main(String[] args) {		
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("http://leaftaps.com/opentaps/control/main");
	//Username
	WebElement username = driver.findElement(By.id("username"));
	username.sendKeys("Demosalesmanager");
	//Password
	WebElement password = driver.findElement(By.id("password"));
	password.sendKeys("crmsfa");
	//Login
	WebElement loginbutton = driver.findElement(By.className("decorativeSubmit"));
	loginbutton.click();
	//Text Link
	WebElement linktext = driver.findElement(By.linkText("CRM/SFA"));
	linktext.click();
	//a[text()='Contacts']
	WebElement linkContact = driver.findElement(By.xpath("//a[@href='/crmsfa/control/contactsMain']"));
	linkContact.click();	
	WebElement mergeContact = driver.findElement(By.xpath("//a[@href='/crmsfa/control/mergeContactsForm']"));
	mergeContact.click();
	//Get the Merge Contact 
	String parentHandleMergeContact = driver.getWindowHandle();
	
	WebElement firstContact = driver.findElement(By.xpath("//form[@name='MergePartyForm']/table[1]/tbody[1]/tr/td[2]/a[1]/img[1]"));
	firstContact.click();
	//New window will get open	
	Set<String> allWindowsHandle = driver.getWindowHandles();
	//to access particular window change to List
	List<String> listfirstContact = new ArrayList<String>(allWindowsHandle);
	//move to first window
	driver.switchTo().window(listfirstContact.get(1));	
	driver.findElement(By.xpath("//*[@id=\'ext-gen247\']/div[1]/table/tbody/tr[1]/td[1]/div/a[1]")).click();
	
    driver.switchTo().window(parentHandleMergeContact);
  
    WebElement secondContact = driver.findElement(By.xpath("//form[@name='MergePartyForm']/table[1]/tbody[1]/tr[2]/td[2]/a[1]/img[1]"));
    secondContact.click();
    //switching to child window previously the window is closed so again handling the window
    Set<String> allWindowsHandles = driver.getWindowHandles();
    List<String> listSecondContact = new ArrayList<String>(allWindowsHandles);
    driver.switchTo().window(listSecondContact.get(1));	
  
	driver.findElement(By.xpath("//*[@id=\'ext-gen247\']/div[2]/table/tbody/tr[1]/td[1]/div/a[1]")).click();
	driver.switchTo().window(parentHandleMergeContact);
		
	WebElement mergeButton = driver.findElement(By.xpath("//form[@name='MergePartyForm']/table/tbody/tr[4]/td/a[1]"));
	mergeButton.click();
	//Accept the alert
	Alert alertForMerge = driver.switchTo().alert();
	String alertMergingContacts = alertForMerge.getText();
	System.err.println("Merging your contact: " +alertMergingContacts);
	alertForMerge.accept();
	String title = "View Contact | opentaps CRM";
	String verifyTitle = 	driver.getTitle();
	System.out.println("Title after Merge contact:" +verifyTitle);
	if(title.equals(verifyTitle)) {
		
	System.out.println("Contact Merged Successfully");	
	}
	else
	{
		System.out.println("check the Merge contact");
	}
	}

	

}

