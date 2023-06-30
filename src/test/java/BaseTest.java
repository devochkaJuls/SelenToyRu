import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;


    @BeforeClass

    public void setUp(){
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();

        driver.get("https://www.toy.ru/");
        driver.manage().window().maximize();
        login();

    }

    public void login(){

        // профиль - авторизация
        WebElement profile = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/ul/li[6]/a"));
        profile.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement login = driver.findElement(By.xpath("/html/body/footer/div[5]/div[3]/div/input[1]"));
        login.sendKeys("julia.b.msk@mail.ru");
        WebElement password = driver.findElement(By.xpath("/html/body/footer/div[5]/div[3]/div/input[2]"));
        password.sendKeys("Julia123!");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement logInEnter = driver.findElement(By.xpath("/html/body/footer/div[5]/div[3]/div/input[3]"));
        logInEnter.click();

        // проверка для авторизации - на сайте ищем наличие текста и элемента с текстом Личный кабинет
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement personalMenu = driver.findElement(By.xpath("/html/body/div[3]/header/div[1]/div/div/div[2]/ul/li[6]/a"));
        Assert.assertTrue(personalMenu.getText().equals("Личный кабинет"), "You are not authorized!");

    }

    @AfterClass
    public void tearDown(){
        //driver.quit();
    }
}
