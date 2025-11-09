package in.arc.urlShortner;

import in.arc.urlShortner.controller.UrlControllerTest;
import in.arc.urlShortner.controller.UrlRouterTest;
import in.arc.urlShortner.service.UrlServiceTest;
import in.arc.urlShortner.suite.UnitTests;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectClasses({
		UnitTests.class
})
class UrlShortnerApplicationTests {
}
