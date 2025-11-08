package in.arc.urlShrotner.suite;

import in.arc.urlShrotner.controller.UrlControllerTest;
import in.arc.urlShrotner.controller.UrlRouterTest;
import in.arc.urlShrotner.service.UrlServiceTest;
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
