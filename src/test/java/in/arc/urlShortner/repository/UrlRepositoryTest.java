package in.arc.urlShortner.repository;

import in.arc.urlShortner.model.entity.Url;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UrlRepositoryTest {
    @Autowired
    private UrlRepository urlRepository;

    @AfterEach
    void teardown(){
        urlRepository.deleteAll();
    }

    @Test
    void shouldSaveUrl(){
        // Arrange
        Url url=Url.builder().trueUrl("youtube.com").build();
        // Act
        Url savedUrl=urlRepository.save(url);
        // Assert
        assertEquals(url.getTrueUrl(), savedUrl.getTrueUrl());
    }

    @Test
    void shouldUpdateUrl(){
        // Arrange
        String BASE_URL="baseUrl";
        Url url=Url.builder().trueUrl("youtube.com").build();
        Url savedUrl=urlRepository.save(url);
        String shortUrl=BASE_URL+savedUrl.getId();
        savedUrl.setShortUrl(shortUrl);
        // Act
        urlRepository.save(savedUrl);
        Url updatedUrl=urlRepository.getReferenceById(savedUrl.getId());
        // Assert
        assertEquals(shortUrl, updatedUrl.getShortUrl());
    }

    @Test
    void shouldGetShortUrlGivenId(){
        // Arrange
        Url url=Url.builder().trueUrl("Youtube.com").shortUrl("shortUrl").build();
        Url savedUrl=urlRepository.save(url);

        // Act
        Url retrievedUrl=urlRepository.getReferenceById(savedUrl.getId());

        // Assert
        assertNotNull(retrievedUrl);
        assertEquals(url.getShortUrl(), retrievedUrl.getShortUrl());

    }

}