package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    private WebDriver webDriver;

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + firstName + "';", inputFirstName);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + lastName + "';", inputLastName);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + username + "';", inputUsername);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + password + "';", inputPassword);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", submitButton);
    }
}
