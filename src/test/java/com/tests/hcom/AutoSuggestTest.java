package com.tests.hcom;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutoSuggestTest {
	
    private WebDriver driver;
	
	@BeforeTest
	public void start(){
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); //generates logs for chrome driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://uk.hotels.com/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	}
	
	@Test(priority=1)
	public void testAutoSuggest(){
		WebElement suggest= driver.findElement(By.id("qf-0q-destination"));
		suggest.clear();
		suggest.sendKeys("Las Vegas, Nevada, United States of America",Keys.TAB);
		//driver.findElement(By.xpath("/html/body/div[6]/table/tfoot/tr/td[2]/button")).click();
		WebElement FromDate= driver.findElement(By.id("qf-0q-localised-check-in"));
		FromDate.clear();
		FromDate.sendKeys("31/03/2017");
		WebElement ToDate= driver.findElement(By.id("qf-0q-localised-check-out"));
		ToDate.clear();
		ToDate.sendKeys("01/04/2017");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		Select select = new Select(driver.findElement(By.xpath("//*[@id='qf-0q-compact-occupancy']")));
		select.selectByIndex(0);
		driver.findElement(By.xpath("//*[@id='main-content']/main/div/div/div[1]/div/div[1]/div[1]/div/div/form/fieldset[5]/button")).click();
	}
	@Test(priority=2)
	public void testSearchResultsPage(){
		Actions action = new Actions(driver);
		//WebElement draggable= driver.findElement(By.xpath("//*[@id='filter-price']/div[2]/div/div[2]/div[2]"));
		WebElement droppable= driver.findElement(By.xpath("//*[@id='filter-price']/div[2]/div/div[2]/div[3]"));
		//action.dragAndDrop(draggable, droppable).moveByOffset(500, 100).release().perform();
		action.dragAndDropBy(droppable, -40, 0).build().perform();
		
	}
	@Test(priority=3)
	public void testLogin(){
		
		driver.findElement(By.id("hdr-account")).click();
		driver.findElement(By.id("hdr-signin")).click();
		driver.findElement(By.name("email")).sendKeys("skunnathpuliyakode@expedia.com");
		driver.findElement(By.name("password")).sendKeys("sindhu123");
		driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/div/form/div[2]/button")).click();	
	}
	
	
	@AfterTest
	public void teardown(){
		driver.close();
		driver.quit();
		
	}
}
