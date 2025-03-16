package java16.exceptions.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder  @Data
public class ExceptionResponse {

    private HttpStatus status;
    private String message;
    private String className;
}
