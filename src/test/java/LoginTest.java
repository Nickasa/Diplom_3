import PageObject.ForgotPasswordPage;
import PageObject.HomePageBurgers;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String token;

    //Чтобы запустить тесты в FireFox нужно в setDriver() поменять ChromeDriver() на FireFoxDriver()
    @Before
    public void setDriver() {
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.get("https://stellarburgers.nomoreparties.site/");
        userClient = new UserClient();

        user = UserGenerator.getDefault();

        ValidatableResponse response = userClient.create(user);

        String rawToken = response.extract().path("accessToken");

        token = rawToken.replaceFirst("Bearer ", "");

    }

    @After
    public void teardown() {
        userClient.delete(token);
        driver.quit();
    }

    //Тест на вход через кнопку "Войти" на главной странице
    @Test
    @DisplayName("Login with login button")
    public void loginWithLoginButton() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

    //Тест на вход через кнопку "Личный кабинет" на главной странице
    @Test
    @DisplayName("Login with account button")
    public void loginWithAccountButton() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Login with registration button")
    public void loginWithRegistrationButton() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.clickLoginLink();

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Login with recover password button")
    public void loginWithForgotPasswordButton() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickPasswordRecoverLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        forgotPasswordPage.clickLoginLink();

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

}
