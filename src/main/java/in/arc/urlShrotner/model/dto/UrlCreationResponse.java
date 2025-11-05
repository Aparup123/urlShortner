package in.arc.urlShrotner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCreationResponse {
    private String shortUrl;
    private String trueUrl;
}
