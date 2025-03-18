package java16.dto.response;

import java16.enums.VegetarianStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Builder @Data
public class MenuitemResponse {


    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private VegetarianStatus isVegeterian;
    private Long restaurantId;
    private Long subcategoryId;
}
