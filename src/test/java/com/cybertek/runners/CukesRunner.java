package com.cybertek.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt", // store failed scenario names into rerun .txt file
                "json:target/cucumber.json"//generate jason execution report to be used for html report
        },
        features = "src/test/resources/features",
        glue = "com/cybertek/step_definitions",
        dryRun =false,
        tags ="@librarian"
        //tags = "@smoke or @etsy" when there are multiple




)
public class CukesRunner {




}
