package in.arc.urlShortner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import in.arc.urlShortner.model.dto.UrlCreationRequest;
import in.arc.urlShortner.model.dto.UrlCreationResponse;
import in.arc.urlShortner.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 👇 Mock the service so that Spring injects it into UrlController
    @MockitoBean
    private UrlService urlService;

    @Test
    void createShortUrlTest() throws Exception {
        // Arrange
        UrlCreationRequest urlCreationRequest = new UrlCreationRequest();
        urlCreationRequest.setUrl("http://www.youtube.com");
        UrlCreationResponse actualResponse = new UrlCreationResponse(
                "http://localhost:8080/uihq9u2eb",
                "http://www.youtube.com"
        );



        when(urlService.createShortUrl("http://www.youtube.com")).thenReturn(actualResponse);

        // Act+Assert
        UrlCreationResponse expectedResponse = new UrlCreationResponse(
                "http://localhost:8080/uihq9u2eb",
                "http://www.youtube.com"
        );

        mockMvc.perform(post("/api/v1/url/create-short-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlCreationRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse) ));
    }

    @Test
    void shouldReturnFieldValidationExceptionMessageGivenEmptyUrl() throws Exception {
        UrlCreationRequest request=new UrlCreationRequest();
        request.setUrl("");
        mockMvc.perform(post("/api/v1/url/create-short-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldReturnFieldValidationExceptionMessageGivenEmptyJsonRequestBody() throws Exception {
        class EmptyRequest{

        }
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        EmptyRequest request=new EmptyRequest();
        System.out.println("ObjectString: "+ objectMapper.writeValueAsString(request));
        mockMvc.perform(post("/api/v1/url/create-short-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
