package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    private By constructorButton = By.xpath(".//p[text()='Конструктор']");

    private By burgerLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private By hintLabel = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    private By logoutButton = By.xpath(".//button[text()='Выход']");

    private By loader = By.xpath(".//img[@alt='loading animation']");

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public boolean hintLabelIsVisible() {
        return driver.findElement(hintLabel).isDisplayed();
    }

    public void clickBurgerLogo() {
        driver.findElement(burgerLogo).click();
    }

    public By getHintLabel() {
       return hintLabel;
    }

    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    public By getLoader() {
        return loader;
    }

    public ProfilePage (WebDriver driver){
        this.driver = driver;
    }
}
