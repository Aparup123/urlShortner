package in.arc.urlShortner.repository;


import in.arc.urlShortner.model.entity.Url;
import in.arc.urlShortner.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;


@Slf4j
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private EntityManager entityManager;

    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
    }


    // Create a user
    User user = User.builder()
            .username("david")
            .password("hello")
            .urls(new ArrayList<>())
            .build();

    @Test
    public void userStoredWhenSaved(){
        userRepository.save(user);
    }

    @Test
    @Transactional
    public void getAllUrlsCreatedByAnUser(){
        User savedUser = userRepository.save(user);
        Url url=Url.builder()
                .trueUrl("trueUrl")
                .shortUrl("shortUrl")
                .createdBy(savedUser)
                .build();
        Url savedUrl=urlRepository.save(url);
        entityManager.flush();
        entityManager.refresh(savedUser);
        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);
        Assertions.assertTrue(foundUser.getUrls().stream().map(u->u.getId()).toList().contains(savedUrl.getId()));
    }
}
