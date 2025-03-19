package java16.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RestaurantResponse {

    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private double service;
}