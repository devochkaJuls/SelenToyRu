import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

public class ToyRuTest extends BaseTest {


    @Test
    public void addBacketLegoGoods() {
        Actions actions = new Actions(driver);
        // тестируем главную страницу
        // вводим в поиск LEGO и проверяем первый товар на странице - в названии есть слово LEGO
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"title-search-input\"]"));
        searchInput.sendKeys("LEGO");
        searchInput.sendKeys(Keys.ENTER);


        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // находим все товары с кнопкой "В корзину"


        List<WebElement> cartButtons = driver.findElements(By.xpath("//*[text()='В корзину']"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);



        // проверяем меньше ли 30 ти товаров на странице? (на странице отобржается по 30 товаров)
        // если товаров больше, то ищем рандомный товар из 30, а если меньше то из длины листа кнопок
        int num = 30;
        if ((int) cartButtons.size() < 30){
            num = (int) cartButtons.size() - 1;
        }


            //три раза выбираем рандомный товар и добавляем в корзину
            for (int i = 0; i < 3; i++) {
                int randomGoods = (int) (Math.random() * num);
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                WebElement button = cartButtons.get(randomGoods);
                actions = new Actions(driver);
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                actions.moveToElement(button).click().build().perform();

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                cartButtons = driver.findElements(By.xpath("//*[text()='В корзину']"));
            }


        //переходим в корзину
        WebElement basket = driver.findElement(By.id("basket_count_price"));

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        actions.moveToElement(basket).click().build().perform();


        // знаем сумму в корзине
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       WebElement totalSum = driver.findElement(By.xpath("//*[@id=\"order_form_content\"]/div[1]/div/div/script[3]/text()"));


        System.out.println("СУММА ТОВАРОВ В КОРЗИНЕ : " + totalSum.getAttribute("totalvalue"));

    }



}
