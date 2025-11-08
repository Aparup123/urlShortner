package in.arc.urlShortner.util;

import in.arc.urlShortner.model.dto.ExceptionResponse;
import in.arc.urlShortner.model.dto.FieldValidationExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<FieldValidationExceptionMessage> fieldValidationExceptionMessageList = new ArrayList<>();
        for(FieldError fieldError : e.getFieldErrors()){
            fieldValidationExceptionMessageList.add(
                    new FieldValidationExceptionMessage(
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    )
            );
        }
        return new ResponseEntity<>(new ExceptionResponse(400, fieldValidationExceptionMessageList), HttpStatusCode.valueOf(400));
    }
}
