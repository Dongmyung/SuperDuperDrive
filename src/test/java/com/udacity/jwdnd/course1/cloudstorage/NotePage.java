package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {

    private WebDriver webDriver;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(className  = "edit-note-button")
    private List<WebElement> editNoteButtons;

    @FindBy(className = "delete-note-button")
    private List<WebElement> deleteNoteButtons;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;

    @FindBy(id = "notes-table-body")
    private WebElement noteTableBody;

    public  NotePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickNoteTab() {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", notesTab);
    }

    public void addNote(String title, String description) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", addNoteButton);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + title + "';", noteTitle);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + description + "';", noteDescription);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", noteSubmit);
    }

    public void editNote(int idx, String title, String description) {
//        this.editNoteButtons.get(idx).click();
        ((JavascriptExecutor) webDriver).executeScript("arguments[" + idx + "].click();", editNoteButtons);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + title + "';", noteTitle);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].value='" + description + "';", noteDescription);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", noteSubmit);
    }

    public void deleteNote(int idx) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[" + idx + "].click();", deleteNoteButtons);
//        this.deleteNoteButtons.get(idx).click();
    }

    public boolean hasNotes() {
        List<WebElement> noteTitles = noteTableBody.findElements(By.id("td-note-title"));
        System.out.println("test:" + noteTitles.size());
        return noteTitles.size() != 0;
    }
}
