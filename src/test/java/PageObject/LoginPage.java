package PageObject;

import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    //заголовок формы "Вход"
    private By loginPageHeader = By.xpath(".//h2[text()='Вход']");

    //поле "Email"
    private By loginField = By.xpath("//label[text()='Email']/parent::div/input[@name='name']");

    //поле "Пароль"
    private By passwordField = By.xpath(".//input[@name='Пароль']");

    //кнопка "Войти"
    private By loginButton = By.xpath(".//button[text()='Войти']");

    //ссылка "Зарегистрироваться"
    private By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");

    //ссылка "Восставновить пароль"
    private By passwordRecoverLink = By.xpath(".//a[text()='Восстановить пароль']");

    public void enterEmail(String email){
        driver.findElement(loginField).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void clickRegisterButton(){
        driver.findElement(registerLink).click();
    }

    public void clickPasswordRecoverLink(){
        driver.findElement(passwordRecoverLink).click();
    }

    public LoginPage (WebDriver driver){
        this.driver = driver;
    }

    public By getLoginPageHeader() {
        return loginPageHeader;
    }

    public boolean loginPageHeaderIsDisplayed() {
       return driver.findElement(loginPageHeader).isDisplayed();
    }

}
