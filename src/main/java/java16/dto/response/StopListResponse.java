package java16.dto.response;

import java16.enums.VegetarianStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StopListResponse {
    private String reason;
    private LocalDateTime date;
    private String menuitemName;
    private BigDecimal menuitemPrice;
    private VegetarianStatus isVegeterian;
    private Long restaurantId;
}