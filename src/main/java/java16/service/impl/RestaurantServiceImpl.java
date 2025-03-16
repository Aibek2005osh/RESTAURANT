package java16.service.impl;

import java16.dto.request.RestaurantDTO;
import java16.entitys.Restaurant;
import java16.repo.RestaurantRepo;
import java16.service.RestaurantService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;

    @Override
    public ResponseEntity<?> saveRestaurant(RestaurantDTO restaurantDTO) {

        Restaurant restaurant = new Restaurant();

        if (restaurant.getNumberOfEmployees() >15 ){
            throw  new NegativeArraySizeException("Кызматкерлердин саны 15тен ашпашы керек!");
        }

        restaurant.setName(restaurantDTO.getName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setRestType(restaurantDTO.getRestType());
        restaurant.setService(restaurantDTO.getService());
        restaurant.setNumberOfEmployees(restaurantDTO.getNumberOfEmployees());

        try {
            restaurantRepo.save(restaurant);
           return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant save successfully");
        } catch (RuntimeException e) {
          return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
