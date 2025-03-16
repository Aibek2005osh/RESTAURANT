package java16.dto.request;



import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Builder @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CreateAdminUserDTO {


    @NotBlank(message = "Аты бош болбошу керек")
    private String firstName;

    @NotBlank(message = "Фамилиясы бош болбошу керек")
    private String lastName;

    @NotNull(message = "Туулган күнү бош болбошу керек")
    private LocalDate dateOfBirth;

    @Email(message = "Туура email киргизиңиз")
    @NotBlank(message = "Email бош болбошу керек")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email @gmail.com менен бүтүшү керек")
    private String email;

    @NotBlank(message = "Сырсөз бош болбошу керек")
    @Size(min = 5, message = "Сырсөз 5 символдон узун болушу керек")
    private String password;

    @NotBlank(message = "Телефон номери бош болбошу керек")
    @Pattern(regexp = "^\\+996\\d{9}$", message = "Телефон номери +996 менен башталып, 9 цифрадан турушу керек")
    private String phoneNumber;

    @NotBlank(message = "Роль бош болбошу керек")
    private String role;

    @NotNull(message = "Тажрыйба бош болбошу керек")
    @Min(value = 0, message = "Тажрыйба терс болбошу керек")
    private Integer experience;

    @NotNull(message = "Ресторан ID бош болбошу керек")
    private Long restaurantId;
}

