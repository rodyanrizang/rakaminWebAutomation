package Swag_Labs.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseURL = "http://www.saucedemo.com/";

    @Given("user website login page")
    public void user_website_login_page(){
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseURL);

        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("user input registered username") //positive case
    public void user_input_registered_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("user input registered password")
    public void user_input_registered_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("user click login button")
    public void user_click_login_button() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user should see products page")
    public void user_should_see_products_page() {
        String dashboardProducts = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(dashboardProducts, "Products");
        driver.close();
    }

    @When("user input unregistered username") //negative case
    public void user_input_unregistered_username() {
        driver.findElement(By.id("user-name")).sendKeys("super_user");
    }

    @And("user input unregistered password")
    public void user_input_unregistered_password() {
        driver.findElement(By.id("password")).sendKeys("secret_code");
    }

    @Then("user should see error alerts")
    public void user_should_see_error_alerts() {
        String loginError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(loginError,"Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}
