package ace.ucv.ro.orientationandorganizationapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends RuntimeException{

    public ActivityNotFoundException(String message) {
        super(message);
    }
}
