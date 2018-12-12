package com.saucelabs.example.stepdefs;

import com.saucelabs.example.Util;
import com.saucelabs.example.pages.InventoryPage;
import com.saucelabs.example.pages.LoginPage;
import com.saucelabs.example.pages.PagesFactory;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageSteps implements En
{
    public LoginPageSteps()
    {
        Given("^The user is on the Home Page$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            Util.info(driver, ">>> The user is on the Home Page");

            LoginPage loginPage = pf.getLoginPage();
            loginPage.navigateTo(LoginPage.PAGE_URL);
        });

        And("^The user provides the username as \"([^\"]*)\" and password as \"([^\"]*)\"$", (String username, String password) -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            Util.info(driver, ">>> The user provides the username as \"username\" and password as \"password\"");

            LoginPage loginPage = pf.getLoginPage();
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            Util.sleep(1000);
        });

        And("^The user clicks the 'Login' button$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            Util.info(driver, ">>> The user clicks the 'Login' button");

            LoginPage loginPage = pf.getLoginPage();
            loginPage.clickLogin();
        });

        Then("^The user should login successfully and is brought to the inventory page$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            Util.info(driver, ">>> The user should login successfully and is brought to the inventory page");

            InventoryPage inventoryPage = pf.getInventoryPage();
            String currentUrl = PagesFactory.getInstance().getDriver().getCurrentUrl();
            Assert.assertEquals(currentUrl, inventoryPage.PAGE_URL);
        });

        Then("^The user should be shown a locked out message$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            Util.info(driver, ">>> The user should be shown a locked out message");

            LoginPage loginPage = pf.getLoginPage();
            Assert.assertTrue(loginPage.hasLockedOutError());
        });

        Then("^The user should be shown an invalid username/password message$", () -> {
            PagesFactory pf = PagesFactory.getInstance();
            WebDriver driver = pf.getDriver();
            Util.info(driver, ">>> The user should be shown an invalid username/password message");

            LoginPage loginPage = pf.getLoginPage();
            Assert.assertTrue(loginPage.hasUsernamePasswordError());
        });
    }
}
