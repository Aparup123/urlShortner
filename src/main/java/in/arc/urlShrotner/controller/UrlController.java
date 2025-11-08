package in.arc.urlShrotner.controller;

import in.arc.urlShrotner.model.dto.UrlCreationRequest;
import in.arc.urlShrotner.model.dto.UrlCreationResponse;
import in.arc.urlShrotner.service.UrlService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
@Slf4j
public class UrlController {
    @Autowired
    UrlService urlService;
    @PostMapping("/create-short-url")
    public ResponseEntity<UrlCreationResponse> createShortUrl(@Valid @RequestBody UrlCreationRequest urlCreationRequest){
        String url=urlCreationRequest.getUrl();
        log.info("URL: {}", url);
        return ResponseEntity.ok(urlService.createShortUrl(url));
    }
}
