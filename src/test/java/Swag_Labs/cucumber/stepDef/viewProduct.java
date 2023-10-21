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

public class viewProduct {
    WebDriver driver;
    String baseURL = "http://www.saucedemo.com/";

    @Given("user login page to website")
    public void user_login_page_to_website(){
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseURL);

        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }
    @When("user input valid username") //positive case
    public void user_input_valid_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("user input valid password")
    public void user_input_valid_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("user click button login")
    public void user_click_button_login() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user should see homepage")
    public void user_should_see_homepage() {
        String dashboardProducts = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(dashboardProducts, "Products");
    }

    @When("user click product name")
    public void user_click_product_name() {
        driver.findElement(By.className("inventory_item_name")).click();
    }

    @Then("should see product detail page")
    public void should_see_product_detail_page() {
        String dashboardProducts = driver.findElement(By.xpath("//button[@id='back-to-products']")).getText();
        Assert.assertEquals(dashboardProducts, "Back to products");
        driver.close();
    }
}
