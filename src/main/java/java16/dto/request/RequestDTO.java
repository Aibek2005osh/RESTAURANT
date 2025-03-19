package java16.dto.request;

import jakarta.validation.constraints.*;
import java16.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestDTO {

    @NotBlank(message = "Email талап кылынат")
    private String email;

    @NotBlank(message = "Аты талап кылынат")
    private String firstName;

    @NotBlank(message = "Фамилиясы талап кылынат")
    private String lastName;

    @NotNull(message = "Туулган күнү талап кылынат")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Телефон номери талап кылынат")
    @Pattern(regexp = "^\\+996[0-9]{9}$", message = "Телефон номери +996 менен башталышы керек")
    private String phoneNumber;

    @NotBlank(message = "Пароль талап кылынат")
    @Size(min = 5, message = "Пароль 4 символдон көп болушу керек")
    private String password;

    @NotNull(message = "Иш стажы талап кылынат")
    private Integer experience;

    @NotNull(message = "Роль талап кылынат")
    private Role role;

    @NotNull(message = "Ресторан ID талап кылынат")
    private Long restaurantId;
}