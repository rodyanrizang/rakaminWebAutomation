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

public class sortProduct {
    WebDriver driver;
    String baseURL = "http://www.saucedemo.com/";

    @Given("user start login page")
    public void user_start_login_page(){
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseURL);

        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }
    @When("user input account username") //positive case
    public void user_input_account_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("user input account password")
    public void user_input_account_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("login button clicked by user")
    public void login_button_clicked_by_user() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("user should on products page")
    public void user_should_on_products_page() {
        String dashboardProducts = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(dashboardProducts, "Products");
    }

    @When("user click product sort icon")
    public void user_click_product_sort_icon() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
    }

    @And("user click the name z to a option")
    public void user_click_the_name_z_to_a_option() {
        driver.findElement(By.xpath("//option[@value='za']")).click();
    }

    @Then("user should see the products sorted")
    public void user_should_see_the_products_sorted() {
        String dashboardProducts = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(dashboardProducts, "Test.allTheThings() T-Shirt (Red)");
        driver.close();
    }
}
