import PageObject.LoginPage;
import PageObject.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
    private UserClient userClient;
    private String email = "burger-test1@yandex.ru";
    private String password = "test12";
    private String token = "";
    private String rawToken = "";

    //Чтобы запустить тесты в FireFox нужно в setDriver() поменять ChromeDriver() на FireFoxDriver()
    @Before
    public void setDriver() {
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.get("https://stellarburgers.nomoreparties.site/register");
        userClient = new UserClient();
    }

    @After
    public void teardown() {
        ValidatableResponse response = userClient.login(new UserCredentials(email, password));
        int responseCode = response.extract().statusCode();
        if (responseCode == 200) {
            rawToken = response.extract().path("accessToken");
            token = rawToken.replaceFirst("Bearer ", "");
            userClient.delete(token);
        }
            driver.quit();
    }

    @Test
    @DisplayName("Registration test")
    public void registrationTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName("burger-test");
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickEnterInPasswordField();
        registerPage.clickRegisterButton();
        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginPageHeader()));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        assertEquals("Wrong url", currentUrl, expectedUrl);
    }

    @Test
    @DisplayName("Registration test with short password")
    public void registrationWithShortPasswordTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName("burger-test");
        registerPage.enterEmail(email);
        registerPage.enterPassword("test1");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.incorrectPasswordErrorIsVisible());
    }

}
