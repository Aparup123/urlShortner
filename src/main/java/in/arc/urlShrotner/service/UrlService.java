package in.arc.urlShrotner.service;

import in.arc.urlShrotner.model.dto.UrlCreationResponse;
import in.arc.urlShrotner.model.entity.Url;
import in.arc.urlShrotner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    private final String COMPANY_DOMAIN="http://localhost:8080/arc/";

    public UrlCreationResponse createShortUrl(String trueUrl){
        Url url=new Url();
        url.setTrueUrl(trueUrl);
        Url savedUrl=urlRepository.save(url);
        String shortUrl=COMPANY_DOMAIN+savedUrl.getId().toString();
        savedUrl.setShortUrl(shortUrl);
        urlRepository.save(savedUrl);
        return new UrlCreationResponse(url.getShortUrl(), url.getTrueUrl());
    }

    public String getTrueUrlFromShortUrl(String urlId){
        Url url=urlRepository.findById(UUID.fromString(urlId)).orElseThrow(() -> new RuntimeException("URL Not Registered!"));
        return url.getTrueUrl();
    }
}
