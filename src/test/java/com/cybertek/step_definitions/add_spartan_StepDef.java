package com.cybertek.step_definitions;

import com.cybertek.pages.AddSpartanPage;
import com.cybertek.pages.SpartanConfirmationPage;
import com.cybertek.pages.SpartanHomePage;
import com.cybertek.pages.SpartansDataTablePage;
import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class add_spartan_StepDef {
    Map<String, String> spartanMap = new HashMap<>();
    @Given("User is on spartan home page")
    public void user_is_on_spartan_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("spartan.url"));
    }
    @When("User goes to Web Data page")
    public void user_goes_to_web_data_page() {
        SpartanHomePage homePage = new SpartanHomePage();
        homePage.webDataLink.click();
    }
    @When("clicks on add spartan")
    public void clicks_on_add_spartan() {
        SpartansDataTablePage dataTablePage = new SpartansDataTablePage();
        dataTablePage.addSpartanBtn.click();
    }
    @When("enters following data and submits:")
    public void enters_following_data_and_submits(Map<String,String> spartanInfo) {
        spartanMap.putAll(spartanInfo); //
        AddSpartanPage    addSpartan = new AddSpartanPage();
        addSpartan.name.sendKeys(spartanInfo.get("name"));
      addSpartan.selectGender(spartanInfo.get("gender"));
      addSpartan.phone.sendKeys(spartanInfo.get("phone"));
      addSpartan.submitBtn.click();

    }
    @Then("success message should be displayed")
    public void success_message_should_be_displayed() {
        SpartanConfirmationPage confirmationPage = new SpartanConfirmationPage();
        Assert.assertEquals("Successfully Added new Data!", confirmationPage.alertMessage.getText());
    }
    @Then("data on confirmation page must be same")
    public void data_on_confirmation_page_must_be_same() {
        SpartanConfirmationPage spartanConfirmationPage = new SpartanConfirmationPage();
        Assert.assertEquals(spartanMap.get("name"),spartanConfirmationPage.name.getAttribute("value"));
        Assert.assertEquals(spartanMap.get("gender"),spartanConfirmationPage.gender.getAttribute("value"));
        Assert.assertEquals(spartanMap.get("phone"),spartanConfirmationPage.phone.getAttribute("value"));
        //element.getAttribute
    }
    @And("data in database must be match")
    public void dataInDatabaseMustBeMatch() {
        Map<String,Object> dbMap = DBUtils.getRowMap("Select * from spartans Where name = 'Zamazingo'");
        Assert.assertEquals(spartanMap.get("name"),dbMap.get("NAME"));
        Assert.assertEquals(spartanMap.get("gender"),dbMap.get("GENDER"));
        Assert.assertEquals(spartanMap.get("phone"),dbMap.get("PHONE"));

        //delete the record how do you prevent duplicate
       // DBUtils.executeQuery("DELETE FROM spartans WHERE name = 'Wooden Tester'");



    }
}
