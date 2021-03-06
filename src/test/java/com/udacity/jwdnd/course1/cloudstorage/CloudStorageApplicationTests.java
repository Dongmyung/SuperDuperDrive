package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	String url, loginUrl, logoutUrl, signupUrl, homeUrl;
	String username, password;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.url = "http://localhost:" + this.port;
		this.loginUrl = url + "/login";
		this.logoutUrl = url + "/logout";
		this.signupUrl = url + "/signup";
		this.homeUrl = url + "/home";

		this.username = "testuser";
		this.password = "testpass";

		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	public void signupThenLogIn() {
		// Signup
		driver.get(signupUrl);
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Derek", "Ahn", username, password);

		// Login
		driver.get(loginUrl);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
	}


	@Test
	@Order(1)
	public void getLoginPage() {
		driver.get(loginUrl);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(2)
	public void goHomePageWithoutLoggedIn() {
		driver.get(homeUrl);
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	@Order(3)
	public void signupAndVerifyAccessible() {
		// Signup and Login
		signupThenLogIn();

		// Verify accessibility to Home Page
		driver.get(homeUrl);
		Assertions.assertEquals("Home", driver.getTitle());
		// Logout
		HomePage homePage = new HomePage(driver);
		homePage.logout();

		// Verify not accessibility to Home Page
		driver.get(homeUrl);
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	@Order(4)
	public void createAndVerifyNote() {
		// Signup and Login
		signupThenLogIn();

		// Create Note
		driver.get(homeUrl);
		NotePage notePage = new NotePage(driver);
		notePage.clickNoteTab();
		notePage.addNote("note title for test", "note description for test");
		notePage.clickNoteTab();
		Assertions.assertEquals(true, notePage.hasNotes());
	}

	@Test
	@Order(5)
	public void editAndVerifyNote() {
		// Signup and Login
		signupThenLogIn();

		// Edit Note
		driver.get(homeUrl);
		NotePage notePage = new NotePage(driver);
		notePage.clickNoteTab();
		notePage.addNote("note", "note description for test");
		notePage.addNote("note 2", "note description for test 2");
		notePage.clickNoteTab();
		notePage.editNote(1,"edit", "desc edit");
		notePage.clickNoteTab();
		Assertions.assertNotEquals("edit", notePage.getTitle(0));
		Assertions.assertEquals("edit", notePage.getTitle(1));
		Assertions.assertEquals("desc edit", notePage.getDescription(1));
	}

	@Test
	@Order(6)
	public void deleteAndVerifyNote() {
		// Signup and Login
		signupThenLogIn();

		// Delete Note
		driver.get(homeUrl);
		NotePage notePage = new NotePage(driver);
		notePage.clickNoteTab();
		notePage.addNote("note", "note description for test");
		notePage.addNote("note 2", "note description for test 2");
		notePage.clickNoteTab();
		int numNotes = notePage.getNoteSize();
		notePage.deleteNote(1);
		notePage.clickNoteTab();
		Assertions.assertEquals((numNotes - 1), notePage.getNoteSize());
	}


	@Test
	@Order(7)
	public void createAndVerifyCredential() {
		// Signup and Login
		signupThenLogIn();

		// Create Note
		driver.get(homeUrl);
		CredentialPage credentialPage = new CredentialPage(driver);
		credentialPage.clickCredentialTab();
		credentialPage.addCredential("www.udacity.com","testuser", "testpass");
		credentialPage.clickCredentialTab();
		Assertions.assertEquals(true, credentialPage.hasCredentials());
	}

	@Test
	@Order(8)
	public void editAndVerifyCredential() {
		// Signup and Login
		signupThenLogIn();

		// Edit Note
		driver.get(homeUrl);
		CredentialPage credentialPage = new CredentialPage(driver);
		credentialPage.clickCredentialTab();
		credentialPage.addCredential("www.udacity.com","testuser", "testpass");
		credentialPage.addCredential("www.google.com","testuser", "testpass2");
		credentialPage.clickCredentialTab();
		credentialPage.editCredential(1,"www.github.com", "derek", "testpass");
		credentialPage.clickCredentialTab();
		Assertions.assertEquals("www.github.com", credentialPage.getUrl(1));
		Assertions.assertEquals("derek", credentialPage.getUsername(1));
	}

	@Test
	@Order(9)
	public void deleteAndVerifyCredential() {
		// Signup and Login
		signupThenLogIn();

		// Delete Note
		driver.get(homeUrl);
		CredentialPage credentialPage = new CredentialPage(driver);
		credentialPage.clickCredentialTab();
		credentialPage.addCredential("www.udacity.com","testuser", "testpass");
		credentialPage.addCredential("www.google.com","testuser", "testpass2");
		credentialPage.clickCredentialTab();
		int numCredential = credentialPage.getCredentialSize();
		credentialPage.deleteCredential(1);
		credentialPage.clickCredentialTab();
		Assertions.assertEquals((numCredential - 1), credentialPage.getCredentialSize());

	}


}
