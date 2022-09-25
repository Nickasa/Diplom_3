import PageObject.HomePageBurgers;
import PageObject.LoginPage;
import PageObject.ProfilePage;
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

public class TransitionTest {
    private WebDriver driver;
    private UserClient userClient;
    private String email = "burger-test1@yandex.ru";
    private String password = "test12";
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

    //Тест на переход в личный кабинет через кнопку "Личный кабинет" на главной странице
    @Test
    @DisplayName("Transition to profile with login")
    public void transitionToProfileTest() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        homePageBurgers.clickAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(profilePage.getHintLabel()));

        Assert.assertTrue("Constructor header is not visible", profilePage.hintLabelIsVisible());
    }

    //Тест на переход в конструктор по клику на кнопку "Конструктор" в личном кабинете
    @Test
    @DisplayName("Transition to constructor from profile with constructor button")
    public void transitionToConstructorFromProfileViaConstructorButtonTest() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        homePageBurgers.clickAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(profilePage.getLoader()));

        profilePage.clickConstructorButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        Assert.assertTrue("Constructor header is not visible", homePageBurgers.constructorHeaderIsVisible());
    }

    //Тест на переход в конструктор по клику на логотип "Stellar Burgers" в личном кабинете
    @Test
    @DisplayName("Transition to constructor from profile with logo")
    public void transitionToConstructorFromProfileViaLogoTest() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        homePageBurgers.clickAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(profilePage.getLoader()));

        profilePage.clickBurgerLogo();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        Assert.assertTrue("Constructor header is not visible", homePageBurgers.constructorHeaderIsVisible());
    }

    //Тест на выход из личного кабинета
    @Test
    @DisplayName("Transition from profile to login via logout")
    public void logoutTest() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);

        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail(user.getEmail());

        loginPage.enterPassword(user.getPassword());

        loginPage.clickLoginButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));

        homePageBurgers.clickAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(profilePage.getHintLabel()));

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(profilePage.getLoader()));

        profilePage.clickLogoutButton();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginPageHeader()));

        Assert.assertTrue("Login page header is not visible", loginPage.loginPageHeaderIsDisplayed());
    }
}
