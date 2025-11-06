package in.arc.urlShrotner.model.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private final int status;
    ValidationException(String msg, int status){
        super(msg);
        this.status=status;
    }
}
