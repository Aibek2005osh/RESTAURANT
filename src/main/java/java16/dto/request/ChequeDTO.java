package java16.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChequeDTO {
    @NotNull(message = "User not  null")
    private Long userId;

    @NotNull(message = "menuitems not null  ")
    private List<Long> menuitemIds;

    @NotNull()
    private double servicePercentage;
}