package in.arc.urlShrotner.controller;

import in.arc.urlShrotner.model.entity.Url;
import in.arc.urlShrotner.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class UrlRouter {
    @Autowired
    UrlService urlService;

    @GetMapping("/arc/{id}")
    public void routeUrl(HttpServletResponse response, @PathVariable String id) throws IOException {
        response.sendRedirect(urlService.getTrueUrlFromShortUrl(id));
    }
}
