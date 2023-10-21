import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    @Test //tag untuk menjalankan script di bawahnya
    public void openBrowser(){
        WebDriver driver;
        String baseURL = "http://www.saucedemo.com";

        WebDriverManager.chromedriver().setup();

        // aplly chrome driver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
    }
}
