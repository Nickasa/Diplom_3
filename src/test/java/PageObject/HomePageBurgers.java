package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageBurgers {
    private WebDriver driver;

    //лого домашней страницы
    private By homePageLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    //кнопка личный кабинет
    private By accountButton = By.xpath(".//p[text()='Личный Кабинет']");

    //кнопка "Войти в аккаунт"
    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");

    //кнопка "Лента заказов"
    private By orderFeedButton = By.xpath(".//p[text()='Лента Заказов']");

    //кнопка "Булки"
    private By bunsButton = By.xpath(".//span[text()='Булки']");

    private By bunsButtonSelected = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");

    //кнопка "Соусы"
    private By saucesButton = By.xpath(".//span[text()='Соусы']");

    private By saucesButtonSelected = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");

    //кнопка "Начинки"
    private By fillingsButton = By.xpath(".//span[text()='Начинки']");

    private By fillingsButtonSelected = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    private By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");

    private By loader = By.xpath(".//img[@alt='loading animation']");

    public boolean createOrderButtonIsVisible(){
        return driver.findElement(createOrderButton).isDisplayed();
    }

    //кликнуть на кнопку "Личный кабинет"
    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }

    //кликнуть на кнопку "Войти в аккаунт"
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    //кликнуть на кнопку "Булки"
    public void clickBunsButton(){
        driver.findElement(bunsButton).click();
    }

    //Выделена ли кнопка "Булки"
    public boolean isBunsButtonSelected(){
        return driver.findElement(bunsButtonSelected).isDisplayed();
    }

    //кликнуть на кнопку "Соусы"
    public void clickSaucesButton(){
        driver.findElement(saucesButton).click();
    }

    //выделена ли кнопка "Соусы"
    public boolean isSaucesButtonSelected(){
        return driver.findElement(saucesButtonSelected).isDisplayed();
    }

    //кликнуть на кнопку "Начинки"
    public void clickFillingsButton(){
        driver.findElement(fillingsButton).click();
    }

    //выделена ли кнопка "Начинки"
    public boolean isFillingsButtonSelected(){
        return driver.findElement(fillingsButtonSelected).isDisplayed();
    }

    public By getBunsSelectedButton(){
        return bunsButtonSelected;
    }

    public By getFillingsSelectedButton(){
        return fillingsButtonSelected;
    }

    public By getSaucesSelectedButton(){
        return saucesButtonSelected;
    }

    public boolean constructorHeaderIsVisible(){
        return driver.findElement(constructorHeader).isDisplayed();
    }
    public By getCreateOrderButton(){
        return createOrderButton;
    }

    public By getLoader() {
        return loader;
    }

    public void waitUntilHomePageIsLoaded() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(homePageLogo));
    }

    public HomePageBurgers(WebDriver driver){
        this.driver = driver;
    }
}
