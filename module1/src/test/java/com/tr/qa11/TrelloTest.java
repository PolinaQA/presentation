package com.tr.qa11;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class TrelloTest {
    //FirefoxDriver wd;
    ChromeDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        //wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void TrelloTest() {
        openSite();
        login("milova.p.s@gmail.com", "h773hj177");
        createNewBoard("FridayNight");
    }

    public void createNewBoard(String boardName) {
        wd.findElement(By.xpath("//div[@class='header-user']/a[1]/span")).click();
        wd.findElementByPartialLinkText("Create Board").click();
        wd.findElementByXPath(".//*[@id='boardNewTitle']").click();
        wd.findElementByXPath(".//*[@id='boardNewTitle']").sendKeys(boardName);
        wd.findElement(By.xpath("//div[5]/div/div[2]/div/div/div/form/input[3]")).click();
    }

    public void login(String mail, String password) {
        wd.findElementByXPath(".//*[@href='/login?returnUrl=%2F']").click();
        wd.findElement(By.id("user")).click();
        wd.findElement(By.id("user")).sendKeys(mail);
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).sendKeys(password);
        wd.findElement(By.id("login")).click();
    }

    public void openSite() {
        wd.get("https://trello.com/");
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
