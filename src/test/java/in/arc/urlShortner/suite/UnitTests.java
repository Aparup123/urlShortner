package in.arc.urlShortner.suite;

import in.arc.urlShortner.controller.HomeController;
import in.arc.urlShortner.controller.UrlControllerTest;
import in.arc.urlShortner.controller.UrlRouterTest;
import in.arc.urlShortner.repository.UrlRepositoryTest;
import in.arc.urlShortner.repository.UserRepositoryTest;
import in.arc.urlShortner.service.UrlServiceTest;
import in.arc.urlShortner.util.Crc32HasherUtilTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        UrlRepositoryTest.class,
        UserRepositoryTest.class,
        UrlServiceTest.class,
        HomeController.class,
        UrlControllerTest.class,
        UrlRouterTest.class,
        Crc32HasherUtilTest.class
})
public class UnitTests {
}
