package java16.service;

import java16.dto.request.RestaurantDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantService {
    ResponseEntity<?> saveRestaurant(RestaurantDTO restaurantDTO);

    ResponseEntity<?> deleteRestaurant(Long restaurantId);

    ResponseEntity<?> findByRestaurantId(Long restaurantId);

    ResponseEntity<List<?>> getAllRestaurant();

    ResponseEntity<?> updateRestaurant(Long restaurantId, RestaurantDTO restaurantDTO);
}

