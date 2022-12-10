package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    private By loginLink = By.xpath(".//a[text()='Войти']");

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public ForgotPasswordPage (WebDriver driver){
        this.driver = driver;
    }
}
