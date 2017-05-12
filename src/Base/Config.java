package Base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Config {

    public WebDriver driver;
    private Capabilities capabilities;

    public String nameUrl = "http://online.transportmonitoring.ru/";

    @BeforeTest
    public void checkStructure(){
        if(new File("onlineObjectsList.txt").exists()){
            new File("onlineObjectsList.txt").delete();
        }
    }

    @BeforeTest
    public void start(){

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

        driver.get(nameUrl);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void stop(){
        driver.quit();
    }

}
