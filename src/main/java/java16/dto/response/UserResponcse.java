package java16.dto.response;

import lombok.*;

import java.time.LocalDate;
@Builder @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class UserResponcse {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBrith;

    private String email;

    private String password;

    private String phoneNumber;

    private int expirense;

}
