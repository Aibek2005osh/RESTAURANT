package java16.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private String firstName;

    private String lastName;

    private LocalDate dateOfBrith;

    private String email;

    private String password;

    private  String phoneNumber;
}
