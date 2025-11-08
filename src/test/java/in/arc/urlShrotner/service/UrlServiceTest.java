package in.arc.urlShrotner.service;

import in.arc.urlShrotner.model.dto.UrlCreationResponse;
import in.arc.urlShrotner.model.entity.Url;
import in.arc.urlShrotner.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    private final String TRUE_URL="https://www.youtube.com";
    private String COMPANY_DOMAIN;

    @Test
    public void UrlServiceTest_returnUrlCreationResponseWhenGivenTrueUrl(){
        ReflectionTestUtils.setField(urlService, "COMPANY_DOMAIN", "http://localhost:8080/");
        ReflectionTestUtils.setField(this, "COMPANY_DOMAIN", "http://localhost:8080/");
        Url url=new Url();
        url.setTrueUrl(TRUE_URL);
        Url savedUrl=new Url();
        savedUrl.setTrueUrl(TRUE_URL);
        savedUrl.setId(UUID.randomUUID());
        when(urlRepository.save(url)).thenReturn(savedUrl);
        String shortUrl=COMPANY_DOMAIN+savedUrl.getId().toString();

        UrlCreationResponse response=urlService.createShortUrl(TRUE_URL);
        assertEquals(TRUE_URL, response.getTrueUrl());
        assertEquals(shortUrl, response.getShortUrl());
    }

    @Test
    public void UrlServiceTest_getTrueUrlGivenShortUrl(){
        Url url=new Url();
        url.setShortUrl("shortUrl");
        url.setTrueUrl("trueUrl");
        url.setId(UUID.randomUUID());
        when(urlRepository.findById(url.getId())).thenReturn(Optional.of(url));
        String result=urlService.getTrueUrlFromShortUrl(url.getId().toString());
        assertEquals(result, url.getTrueUrl());
    }

    @Test
    public void UrlServiceTest_throwRuntimeExceptionGivenInvalidUrlId(){
        UUID invalidId=UUID.randomUUID();
        when(urlRepository.findById(invalidId)).thenReturn(null);
        assertThrows(RuntimeException.class, ()-> urlService.getTrueUrlFromShortUrl(invalidId.toString()));
    }
}