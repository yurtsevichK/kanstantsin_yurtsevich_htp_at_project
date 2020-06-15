package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests", "steps.gui", "steps.api" },
        features = {"src/main/resources/UserApiTest.feature"
        },
        //tags = {"@qa or @prod "},
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        strict = false
)
public class RunTest {
}
