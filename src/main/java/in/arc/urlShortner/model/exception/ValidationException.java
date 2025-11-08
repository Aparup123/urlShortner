package in.arc.urlShortner.model.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private final int status;
    ValidationException(String msg, int status){
        super(msg);
        this.status=status;
    }
}
