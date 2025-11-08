package in.arc.urlShortner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidationExceptionMessage {
    private String field;
    private String message;
}
