package java16.dto.request;

import lombok.Builder;
import lombok.Data;

@Data  @Builder
public class UserLoginDTO {
    private String email;

    private String password;
}
