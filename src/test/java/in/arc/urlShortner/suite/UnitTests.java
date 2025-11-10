package in.arc.urlShortner.suite;

import in.arc.urlShortner.controller.HomeController;
import in.arc.urlShortner.controller.UrlControllerTest;
import in.arc.urlShortner.controller.UrlRouterTest;
import in.arc.urlShortner.repository.UrlRepositoryTest;
import in.arc.urlShortner.service.UrlServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UrlRepositoryTest.class,
        UrlServiceTest.class,
        HomeController.class,
        UrlControllerTest.class,
        UrlRouterTest.class,
})
public class UnitTests {
}
