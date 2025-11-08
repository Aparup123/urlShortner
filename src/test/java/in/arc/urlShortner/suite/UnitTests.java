package in.arc.urlShortner.suite;

import in.arc.urlShortner.controller.UrlControllerTest;
import in.arc.urlShortner.controller.UrlRouterTest;
import in.arc.urlShortner.service.UrlServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UrlServiceTest.class,
        UrlRouterTest.class,
        UrlControllerTest.class
})
public class UnitTests {
}
