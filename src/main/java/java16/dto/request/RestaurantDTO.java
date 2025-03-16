package java16.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    @NotBlank(message = "not null")
    @Length(min = 5, max = 250,message = "length min 5 max 250 ! ")
    private String name;

    @NotBlank(message = "not null")
    private String location;

    @NotBlank(message = "not null")
    private String restType;

    @Min(value = 0) @Max(value = 15,message = "15ten otposh kerek")
    private int numberOfEmployees;

    @Min(value = 0, message = "Сервис % терс болбошу керек")
    private double service;
}
