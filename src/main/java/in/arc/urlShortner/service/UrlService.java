package in.arc.urlShortner.service;

import in.arc.urlShortner.model.dto.UrlCreationResponse;
import in.arc.urlShortner.model.entity.Url;
import in.arc.urlShortner.repository.UrlRepository;
import in.arc.urlShortner.util.Crc32HasherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Value("${app.domain.base_url}")
    private String COMPANY_DOMAIN;

    @Value("${app.server.hash_collision_string}")
    private String HASH_COLLISION_STRING;

    public UrlCreationResponse createShortUrl(String trueUrl){
        Url url=new Url();
        String urlHash= Crc32HasherUtil.crc32Hash(trueUrl);
        String trueUrlWithCollisionString=trueUrl;
        // If Hash Already Exists
        while(urlRepository.findByUrlHash(urlHash)!=null){
            trueUrlWithCollisionString=trueUrl+HASH_COLLISION_STRING;
            urlHash=Crc32HasherUtil.crc32Hash(trueUrlWithCollisionString);
        }
        url.setTrueUrl(trueUrl);
        url.setUrlHash(urlHash);
        String shortUrl=COMPANY_DOMAIN+"/"+urlHash;
        url.setShortUrl(shortUrl);
        Url savedUrl=urlRepository.save(url);
        log.info("SHORT_URL: {}", shortUrl);
        return new UrlCreationResponse(savedUrl.getShortUrl(), savedUrl.getTrueUrl());
    }

    public String getTrueUrlFromUrlHash(String urlHash){
        try{
            Url url=urlRepository.findByUrlHash(urlHash);
            return url.getTrueUrl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
