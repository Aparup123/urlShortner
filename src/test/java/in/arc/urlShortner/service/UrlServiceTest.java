package in.arc.urlShortner.service;
import in.arc.urlShortner.model.dto.UrlCreationResponse;
import in.arc.urlShortner.model.entity.Url;
import in.arc.urlShortner.repository.UrlRepository;
import in.arc.urlShortner.util.Crc32HasherUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    private final String TRUE_URL="https://www.youtube.com";
    private String COMPANY_DOMAIN;

    private String HASH_COLLISION_STRING;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(urlService, "COMPANY_DOMAIN", "http://localhost:8080");
        ReflectionTestUtils.setField(this, "COMPANY_DOMAIN", "http://localhost:8080");
        ReflectionTestUtils.setField(urlService, "HASH_COLLISION_STRING", "ahsdjdhasudhasdhhaiudhw");
        ReflectionTestUtils.setField(this, "HASH_COLLISION_STRING", "ahsdjdhasudhasdhhaiudhw");
    }

    @Test
    public void UrlServiceTest_getTrueUrlGivenUrlHash(){
        Url url=new Url();
        String trueUrl="http://www.google.com";
        String urlHash=Crc32HasherUtil.crc32Hash(trueUrl);
        url.setTrueUrl(trueUrl);
        url.setUrlHash(urlHash);
        url.setId(UUID.randomUUID());
        when(urlRepository.findByUrlHash(urlHash)).thenReturn(url);
        String result=urlService.getTrueUrlFromUrlHash(urlHash);
        assertEquals(trueUrl,result);
    }

    @Test
    public void UrlServiceTest_throwRuntimeExceptionGivenInvalidUrlId(){
        String invalidUrlHash="2e2124df";
        when(urlRepository.findByUrlHash(invalidUrlHash)).thenReturn(null);
        assertThrows(RuntimeException.class, ()-> urlService.getTrueUrlFromUrlHash(invalidUrlHash));
    }

    @Test
    public void getUrlCreationResponseGivenTrueUrl(){
        String trueUrl="http://www.youtube.com";
        when(urlRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UrlCreationResponse urlCreationResponse=urlService.createShortUrl(trueUrl);
        String expectedShortUrl=COMPANY_DOMAIN+"/"+ Crc32HasherUtil.crc32Hash(trueUrl);
        assertEquals(expectedShortUrl, urlCreationResponse.getShortUrl());
    }

    @Test
    public void addHashCollisionStringToTrueUrlGivenTrueUrlHashAlreadyExists(){
        String trueUrl="http://www.youtube.com";
        String trueUrlHash=Crc32HasherUtil.crc32Hash(trueUrl);
        Url existingUrl=Url.builder().trueUrl(trueUrl).urlHash(trueUrlHash).build();
        when(urlRepository.findByUrlHash(trueUrlHash)).thenReturn(existingUrl);
        String newUrlHash=Crc32HasherUtil.crc32Hash(trueUrl+HASH_COLLISION_STRING);
        when(urlRepository.findByUrlHash(newUrlHash)).thenReturn(null);
        when(urlRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        UrlCreationResponse urlCreationResponse=urlService.createShortUrl(trueUrl);
        String expectedShortUrl=COMPANY_DOMAIN+"/"+ newUrlHash;
        assertEquals(expectedShortUrl, urlCreationResponse.getShortUrl());
    }
}
