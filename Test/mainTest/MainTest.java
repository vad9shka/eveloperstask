package mainTest;

import Main.MainClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends MainClass{

    WebElement webElement;

    //набор локаторов
    private By loginPanelHeaderText = By.id("login-panel_header_hd-textEl");
    private By enterButton = By.id("button-1012-btnInnerEl");
    private By registrationButton = By.id("button-1013-btnInnerEl");
    private By demoButton = By.id("button-1014-btnInnerEl");
    private By loginNameLabel = By.id("loginuname-labelEl");
    private By passwordNameLabel = By.id("loginpassword-labelEl");

    @Test
    public void mainTest(){

        autorisation();
        objectOnline();
        int count = counter()-1;
        System.out.println("Objects online = " + count);
        Assert.assertNotEquals(0, count, "Objects offline");
        logout();

        //проверки на присутсвие локатора
        Assert.assertTrue(isElementPresent("login-panel"));

        Assert.assertTrue(isElementPresent("login-panel_header_hd-textEl"));

        Assert.assertTrue(isElementPresent("loginuname-labelEl"));

        Assert.assertTrue(isElementPresent("loginpassword-labelEl"));

        Assert.assertTrue(isElementPresent("loginuname-inputEl"));

        Assert.assertTrue(isElementPresent("loginpassword-inputEl"));

        Assert.assertTrue(isElementPresent("button-1012-btnInnerEl"));

        Assert.assertTrue(isElementPresent("button-1013-btnInnerEl"));

        Assert.assertTrue(isElementPresent("button-1014-btnInnerEl"));

        //проверка на совпадение текста в элементах
        webElement = driver.findElement(loginPanelHeaderText);
        String loginPanelHeader = webElement.getText();
        Assert.assertEquals("Вход в систему", loginPanelHeader, "Header is incorrect");

        webElement = driver.findElement(enterButton);
        String buttonEnter = webElement.getText();
        Assert.assertEquals("Войти", buttonEnter, "Button name is incorrect");

        webElement = driver.findElement(registrationButton);
        String buttonRegistr = webElement.getText();
        Assert.assertEquals("Зарегистрироваться", buttonRegistr, "Button name is incorrect");

        webElement = driver.findElement(demoButton);
        String buttonDemo = webElement.getText();
        Assert.assertEquals("Демо", buttonDemo, "Button name is incorrect");

        webElement = driver.findElement(loginNameLabel);
        String nameLoginField = webElement.getText();
        if(!nameLoginField.contains("Логин:")){
            Assert.fail("login field is incorrect");
        }

        webElement = driver.findElement(passwordNameLabel);
        String namePassField = webElement.getText();
        if(!namePassField.contains("Пароль:")){
            Assert.fail("password field is incorrect");
        }
    }
}
