package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
/*
create Runner class in runners folder -> it is like the main method for Cucumber.
This class tells us where feature files and and steps folder (step definitions) located.
We add plugins-> libraries that add extra functionality. We will have reporting plugin
features plugin that tells us where feature files located
glue plugin that tells us location of steps, where the code, implementation will be
tags is when we are telling which tag to run
dryRun -> it's like a quick check to see if your
feature files are written correctly without actually running steps definitions (code)
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber.html", "json:target/report.json"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@seller",
        dryRun = false
)
public class Runner {

}
