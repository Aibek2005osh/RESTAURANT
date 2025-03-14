package java16.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder @Data
public class SimpleResponse {

    private String massage;

    private HttpStatus status;
}
