package java16.dto.response;

import java16.entitys.Menuitem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ChequeResponse {
    private String waiterFullName;
    private List<Menuitem> items;
    private double averagePrice;
    private double servicePercentage;
    private double grandTotal;
}