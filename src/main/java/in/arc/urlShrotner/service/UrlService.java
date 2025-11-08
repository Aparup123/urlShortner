package in.arc.urlShrotner.service;

import in.arc.urlShrotner.model.dto.UrlCreationResponse;
import in.arc.urlShrotner.model.entity.Url;
import in.arc.urlShrotner.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Slf4j
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Value("${app.domain.base_url}")
    private String COMPANY_DOMAIN;

    public UrlCreationResponse createShortUrl(String trueUrl){
        Url url=new Url();
        url.setTrueUrl(trueUrl);
        Url savedUrl=urlRepository.save(url);

        String shortUrl=COMPANY_DOMAIN+savedUrl.getId().toString();
        log.info("SHORT_URL: {}", shortUrl);
        savedUrl.setShortUrl(shortUrl);
        urlRepository.save(savedUrl);
        return new UrlCreationResponse(savedUrl.getShortUrl(), savedUrl.getTrueUrl());
    }

    public String getTrueUrlFromShortUrl(String urlId){
        Url url=urlRepository.findById(UUID.fromString(urlId)).orElseThrow(() -> new RuntimeException("URL Not Registered!"));
        return url.getTrueUrl();
    }
}
