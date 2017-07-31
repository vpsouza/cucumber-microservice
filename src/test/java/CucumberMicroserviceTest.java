import cucumber.api.CucumberOptions;
import test.TestBase;

@CucumberOptions(plugin = {"pretty", "html:target/cucumber"}, tags = {"~@ignore"})
public class CucumberMicroserviceTest extends TestBase {

}
