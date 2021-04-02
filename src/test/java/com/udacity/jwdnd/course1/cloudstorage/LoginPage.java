package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver webDriver;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + username + "';", inputUsername);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + password + "';", inputPassword);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", submitButton);
    }

}
