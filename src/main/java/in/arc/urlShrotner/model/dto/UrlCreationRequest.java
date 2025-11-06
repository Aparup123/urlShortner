package in.arc.urlShrotner.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UrlCreationRequest {
    @NotEmpty(message = "URL cannot be empty!")
    @NotNull(message="URL not given!")
    private String url;
}
