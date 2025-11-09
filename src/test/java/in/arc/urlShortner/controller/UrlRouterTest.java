package in.arc.urlShortner.controller;

import in.arc.urlShortner.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
@WebMvcTest(UrlRouter.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UrlRouterTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @Test
    void routeUrlTest() throws Exception {
        when(urlService.getTrueUrlFromShortUrl("shortUrl")).thenReturn("http://trueUrl");

        mockMvc.perform(get("/shortUrl"))
                .andExpect(redirectedUrl("http://trueUrl"));
    }
}