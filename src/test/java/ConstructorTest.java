import PageObject.HomePageBurgers;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorTest {
    private WebDriver driver;

    //Чтобы запустить тесты в FireFox нужно в setDriver() поменять ChromeDriver() на FireFoxDriver()
    @Before
    public void setDriver() {
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void teardown() {
        driver.quit();
    }

    //Тест на выбор списка соусов
    @Test
    @DisplayName("Select sauces")
    public void transitionToSauces() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(homePageBurgers.getLoader()));
        homePageBurgers.clickSaucesButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getSaucesSelectedButton()));
        Assert.assertTrue("Sauces button is not selected", homePageBurgers.isSaucesButtonSelected());
    }

    //Тест на выбор списка начинок. В Firefox не проходит, перескакивает с начинок на соусы, не могу понять почему, если можно комментарий, пожалуйста
    @Test
    @DisplayName("Select fillings")
    public void transitionToFillings() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(homePageBurgers.getLoader()));
        homePageBurgers.clickFillingsButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getFillingsSelectedButton()));
        Assert.assertTrue("Fillings button is not selected", homePageBurgers.isFillingsButtonSelected());
    }

    //Тест на выбор списка булок, так как они выбраны по умолчанию - переходит а другой список и обратно
    @Test
    @DisplayName("Select fillings then buns")
    public void transitionFromFillingsToBuns() {
        HomePageBurgers homePageBurgers = new HomePageBurgers(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(homePageBurgers.getLoader()));
        homePageBurgers.clickFillingsButton();
        homePageBurgers.clickBunsButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getBunsSelectedButton()));
        Assert.assertTrue("Fillings button is not selected", homePageBurgers.isBunsButtonSelected());
    }
}

