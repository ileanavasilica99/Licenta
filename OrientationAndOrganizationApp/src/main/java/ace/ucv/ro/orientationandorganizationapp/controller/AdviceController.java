package ace.ucv.ro.orientationandorganizationapp.controller;

import ace.ucv.ro.orientationandorganizationapp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClassroomNotFoundException.class)
    public ResponseEntity<Object> classroomNotFound(ClassroomNotFoundException exception) {
        return createResponseBody(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    public ResponseEntity<Object> activityNotFound(ActivityNotFoundException exception){
        return createResponseBody(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> courseNotFound(CourseNotFoundException exception){
        return createResponseBody(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<Object> imageNotFound(ImageNotFoundException exception){
        return createResponseBody(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<Object> noPermissionException(NoPermissionException exception){
        return createResponseBody(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UserNotFoundException exception){
        return createResponseBody(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> createResponseBody(String message, HttpStatus status) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("message", message);
        return new ResponseEntity<>(responseBody, status);
    }
}
