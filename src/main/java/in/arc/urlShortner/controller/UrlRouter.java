package in.arc.urlShortner.controller;


import in.arc.urlShortner.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class UrlRouter {
    @Autowired
    UrlService urlService;

    @GetMapping("/{id}")
    public void routeUrl(HttpServletResponse response, @PathVariable String id) throws IOException {
        response.sendRedirect(urlService.getTrueUrlFromShortUrl(id));
    }
}
