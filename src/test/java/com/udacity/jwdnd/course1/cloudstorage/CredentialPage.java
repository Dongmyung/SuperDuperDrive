package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialPage {

    private WebDriver webDriver;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(className = "edit-credential-button")
    private List<WebElement> editCredentialButtons;

    @FindBy(className = "delete-credential-button")
    private List<WebElement> deleteCredentialButtons;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;


    public  CredentialPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickCredentialTab() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialTab);
    }

    public void addCredential(String url, String username, String password) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", addCredentialButton);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + url + "';", credentialUrl);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + username + "';", credentialUsername);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + password + "';", credentialPassword);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialSubmit);
    }

    public void editCredential(int idx, String url, String username, String password) {
        if (idx < 0 || idx > getCredentialSize()) return;
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", editCredentialButtons.get(idx));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + url + "';", credentialUrl);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + username + "';", credentialUsername);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + password + "';", credentialPassword);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", credentialSubmit);
    }

    public void deleteCredential(int idx) {
        if (idx < 0 || idx > getCredentialSize()) return;
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", deleteCredentialButtons.get(idx));
    }

    public int getCredentialSize() {
        List<WebElement> credentialUrls = credentialTable.findElements(By.className("td-credential-url"));
        return credentialUrls.size();
    }

    public boolean hasCredentials() {
        return getCredentialSize() != 0;
    }

    public String getUrl(int idx) {
        if (idx < 0 || idx >= getCredentialSize()) return null;
        List<WebElement> credentialUrls = credentialTable.findElements(By.className("td-credential-url"));
        return credentialUrls.get(idx).getText();
    }

    public String getUsername(int idx) {
        if (idx < 0 || idx >= getCredentialSize()) return null;
        List<WebElement> credentialUrls = credentialTable.findElements(By.className("td-credential-username"));
        return credentialUrls.get(idx).getText();
    }

    public String getPassword(int idx) {
        if (idx < 0 || idx >= getCredentialSize()) return null;
        List<WebElement> credentialUrls = credentialTable.findElements(By.className("td-credential-password"));
        return credentialUrls.get(idx).getText();
    }

}
