package steps;

import io.cucumber.java.en.*;
import pages.app.HomePage;
import pages.app.LoginPage;
import pages.app.TutorialPage;
import utilities.CommonAppDriver;

public class AssignedRouteSteps {

    LoginPage loginPage;
    HomePage homePage;
    TutorialPage tutorialPage;

    @Given("The driver has opened the Jitsu app")
    public void openApp() throws Exception {
        var driver = CommonAppDriver.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        tutorialPage = new TutorialPage(driver);
    }

    @When("The driver logs in with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @When("The driver navigates to the Tutorials screen")
    public void goToTutorials() {
        homePage.goToTutorials();
    }

    @Then("The driver should see tutorial sections: Assigned Route, Direct Booking, Ticket Booking")
    public void verifySections() {
        tutorialPage.verifyTutorialSections();
    }

    @When("The driver selects the {string} tutorial")
    public void selectTutorial(String name) {
        tutorialPage.selectTutorial(name);
    }

    @Then("The Assigned Route tutorial should start")
    public void verifyStarted() {
        tutorialPage.verifyTutorialStarted();
    }

    @And("If a tutorial is already active, the driver quits it")
    public void quitIfExists() {
        tutorialPage.quitActiveTutorialIfExists();
    }
}
