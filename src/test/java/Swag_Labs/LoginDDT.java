package Swag_Labs;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
    @Test
    public void login_ddt(){
        WebDriver driver;
        String baseURL = "http://www.saucedemo.com";

        WebDriverManager.chromedriver().setup();

        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv/";

        try (CSVReader reader = new CSVReader(new FileReader(csvDir))) {
            String[] nextline;
            while ((nextline = reader.readNext()) != null) {
                String username = nextline[0];
                String password = nextline[1];
                String status = nextline[2];

                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.get(baseURL);

                //form
                driver.findElement(By.id("user-name")).sendKeys(username);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//input[@type='submit']")).click();

                //assertion
                if (status.equals("success")) {
                    driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]"));
                    String dashboardProducts = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
                    Assert.assertEquals(dashboardProducts, "Products");
                } else {
                    String loginError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
                    Assert.assertEquals(loginError,"Epic sadface: Username and password do not match any user in this service");
                }
                driver.close();
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
