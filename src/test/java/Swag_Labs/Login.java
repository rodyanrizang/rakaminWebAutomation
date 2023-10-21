package Swag_Labs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    @Test
    public void success_login_case(){
        WebDriver driver;
        String baseURL = "http://www.saucedemo.com";

        WebDriverManager.chromedriver().setup();

        //aplly chrome driver setup, menampilkan halaman Swag_Labs.cucumber.stepDef.login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        //assert Swag_Labs.cucumber.stepDef.login page
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        //input username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //input password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //click Swag_Labs.cucumber.stepDef.login button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //assert Swag_Labs.cucumber.stepDef.login success
        String dashboardProducts = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(dashboardProducts, "Products");
        driver.close();
    }

    @Test
    public void failed_login_case() {
        WebDriver driver;
        String baseURL = "http://www.saucedemo.com";

        WebDriverManager.chromedriver().setup();

        //aplly chrome driver setup, menampilkan halaman Swag_Labs.cucumber.stepDef.login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseURL);
        //assert Swag_Labs.cucumber.stepDef.login page
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        //input wrong username
        driver.findElement(By.id("user-name")).sendKeys("super_user");
        //input wrong password
        driver.findElement(By.id("password")).sendKeys("secret_code");
        //click Swag_Labs.cucumber.stepDef.login button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //assert Swag_Labs.cucumber.stepDef.login failed
        String loginError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(loginError,"Epic sadface: Username and password do not match any user in this service");

        driver.close();
    }
}
