package java16.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java16.enums.VegetarianStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuitemDTO {
    @NotBlank(message = " name not null")
    private String name;

    private String image;

    @NotNull(message = "Price not null")
    @Min(value = 0)
    private BigDecimal price;

    private String description;

    @NotNull(message = "Vegetarian  VEGETARIAN,\n" +
                       "    NON_VEGETARIAN,\n" +
                       "    UNKNOWN")
    private VegetarianStatus isVegeterian;
}