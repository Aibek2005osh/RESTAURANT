package java16.service;

import java16.dto.request.RestaurantDTO;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {
    ResponseEntity<?> saveRestaurant(RestaurantDTO restaurantDTO);
}
