package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    private By inputName = By.xpath(".//label[text()='Имя']/parent::div/input[@name='name']");

    private By inputEmail = By.xpath(".//label[text()='Email']/parent::div/input[@name='name']");

    private By inputPassword = By.xpath(".//label[text()='Пароль']/parent::div/input[@name='Пароль']");

    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private By loginLink = By.xpath(".//a[text()='Войти']");

    private By incorrectPasswordError = By.xpath(".//p[text()='Некорректный пароль']");

    public void enterName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    //Данный метод нажимает кнопку "Enter" в поле password, потому что в FireFox происходит перехват элемента и кнопка "Зарегистрироваться" не кликается
    public void clickEnterInPasswordField() {
        driver.findElement(inputPassword).sendKeys(Keys.ENTER);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public boolean incorrectPasswordErrorIsVisible() {
        return driver.findElement(incorrectPasswordError).isDisplayed();
    }

    public RegisterPage (WebDriver driver){
        this.driver = driver;
    }
}
