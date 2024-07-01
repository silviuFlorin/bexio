import org.bexio.features.spring.SpringConfig;
import org.bexio.features.spring.SpringTestNGStarterApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

@SpringBootTest(classes = {SpringConfig.class, SpringTestNGStarterApplication.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
  @AfterMethod(alwaysRun = true)
  public void actionsAfterTest() {}

  @BeforeClass(alwaysRun = true)
  @Override
  protected final void springTestContextPrepareTestInstance() throws Exception {
    super.springTestContextPrepareTestInstance();
  }
}
