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

public class addProduct {
    WebDriver driver;
    String baseURL = "http://www.saucedemo.com/";

    @Given("user login page")
    public void user_login_page(){
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseURL);

        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }
    @When("user input username") //positive case
    public void user_input_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("user input password")
    public void user_input_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("user click login")
    public void user_click_login() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user should see products homepage")
    public void user_should_see_products_homepage() {
        String dashboardProducts = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(dashboardProducts, "Products");
    }

    @When("user click add to cart")
    public void user_click_add_to_cart() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @Then("product should added to cart")
    public void product_should_added_to_cart() {
        String dashboardProducts = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        Assert.assertEquals(dashboardProducts, "Remove");
        driver.close();
    }
}
