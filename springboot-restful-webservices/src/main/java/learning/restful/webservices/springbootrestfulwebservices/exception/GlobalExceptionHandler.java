package learning.restful.webservices.springbootrestfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorDetail> handleEmailAlreadyExistException(EmailAlreadyExistException ex, WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_ALREADY_EXIST"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGlobalException(Exception ex, WebRequest webRequest){
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error)->{
            String feildName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(feildName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
