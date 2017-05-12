package Main;

import Base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


//класс для описания методов теста
public class MainClass extends Config {

    private WebElement webElement;
    private int count = 0;

    //набор локаторов
    private By loginField = By.id("loginuname-inputEl");
    private By passwordField = By.id("loginpassword-inputEl");
    private By enterButton = By.id("button-1012-btnInnerEl");
    private By logOutButton = By.id("logoutButton");
    private By tableObjects = By.id("WatchTabGrid-body");
    private By tableLines = By.cssSelector(".x-grid-row");
    private By innerCellLine = By.cssSelector(".x-grid-cell");
    private By textCell = By.cssSelector(".x-grid-cell-inner");
    private By mask = By.cssSelector(".x-mask");

    //метод для добавления текста в файл
    public void saveTextToFile(String text){
        try {
            File textFile = new File("onlineObjectsList.txt");
            FileWriter writer = new FileWriter(textFile, true);
            writer.write(text);
            writer.append("\n");
            writer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //авторизация
    public void autorisation(){
        try {
            String login = "demo";
            String pass = "demo";

            webElement = driver.findElement(loginField);
            webElement.sendKeys(login);

            webElement = driver.findElement(passwordField);
            webElement.sendKeys(pass);

            webElement = driver.findElement(enterButton);
            webElement.click();
        }
        catch (WebDriverException e){
            e.printStackTrace();
            Assert.fail();
        }
        catch (Throwable e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    //разлогин
    public void logout(){
        try {
            webElement = driver.findElement(logOutButton);
            webElement.click();
        }
        catch (WebDriverException e){
            e.printStackTrace();
            Assert.fail();
        }
        catch (Throwable e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    //увеличение значения суммы записей
    public int counter(){
        count = count + 1;
        return count;
    }

    //выборка элементов объектов, которые находятся на связи
    public boolean listObj(){

        try {
            List<WebElement> listObjects = driver.findElement(tableObjects).findElements(tableLines);
            for (int i = 0; i < listObjects.size(); i++) {
                List<WebElement> listInnerObjects = listObjects.get(i).findElements(innerCellLine);
                for (int j = 0; j < listInnerObjects.size(); j++) {
                    String str = listInnerObjects.get(j).findElement(textCell).getText();
                    if (str.equals("Да")) {
                        counter();
                        String carName = listInnerObjects.get(0).getText();
                        saveTextToFile(carName);
                    }
                }
            }
        }
        catch (WebDriverException e){
            if(e.getMessage().contains("element is not attached to the page document")){
                return false;
            }
        }
        return true;
    }

    //удаление записей из файла
    public void fileClear(){
        try {
            FileWriter fstream1 = new FileWriter("onlineObjectsList.txt");
            BufferedWriter out1 = new BufferedWriter(fstream1);
            out1.write("");
            out1.close();
        }
        catch (Exception e){
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

    //вызов метода выявления объектов, которые находятся на связи
    public void objectOnline() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);

            List<WebElement> maskElements = driver.findElements(mask);
            wait.until(ExpectedConditions.invisibilityOfAllElements(maskElements));

//            new WebDriverWait(driver, 10).until(new Function<WebDriver, Boolean>() {
//                @Override
//                public Boolean apply(WebDriver driver) {
//                    return !driver.findElement(By.id("WatchTabGrid")).getAttribute("class").contains("x-masked");
//                }});

            boolean flag = false;
            while (!flag){
                if(count!=0){
                    count = 0;
                }
                if(new File("onlineObjectsList.txt").exists()){
                    fileClear();
                }
                flag = listObj();
            }
        } catch (WebDriverException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (Throwable e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    //проверка существования элемента на странице
    public boolean isElementPresent(String locator){
        try{
            webElement = driver.findElement(By.id(locator));
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

}
