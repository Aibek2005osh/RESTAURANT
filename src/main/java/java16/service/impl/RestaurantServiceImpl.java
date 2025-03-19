package java16.service.impl;

import java16.dto.request.RestaurantDTO;
import java16.dto.response.RestaurantResponse;
import java16.entitys.Restaurant;
import java16.repo.RestaurantRepo;
import java16.service.RestaurantService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;

    @Override
    public ResponseEntity<?> saveRestaurant(RestaurantDTO restaurantDTO) {

        Restaurant restaurant = new Restaurant();

        if (restaurant.getNumberOfEmployees() > 15) {
            throw new NegativeArraySizeException("Кызматкерлердин саны 15тен ашпашы керек!");
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> deleteRestaurant(Long restaurantId) {

        try {

            Restaurant byRestId = restaurantRepo.findByRestId(restaurantId);
            restaurantRepo.delete(byRestId);
            return ResponseEntity.status(HttpStatus.OK).body("Restaurant delete successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error server" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> findByRestaurantId(Long restaurantId) {
        try {


            Restaurant byRestId = restaurantRepo.findByRestId(restaurantId);

            RestaurantResponse restaurantResponse = RestaurantResponse.builder()
                    .name(byRestId.getName())
                    .location(byRestId.getLocation())
                    .restType(byRestId.getRestType())
                    .service(byRestId.getService())
                    .numberOfEmployees(byRestId.getNumberOfEmployees())
                    .service(byRestId.getService())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(restaurantResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<List<?>> getAllRestaurant() {
        try {


            List<Restaurant> all = restaurantRepo.findAll();

            List<RestaurantResponse> restaurantResponseList = new ArrayList<>();
            for (Restaurant restaurant : all) {
                RestaurantResponse restaurantResponse = RestaurantResponse.builder()
                        .name(restaurant.getName())
                        .location(restaurant.getLocation())
                        .restType(restaurant.getRestType())
                        .service(restaurant.getService())
                        .numberOfEmployees(restaurant.getNumberOfEmployees())
                        .service(restaurant.getService()).build();
                restaurantResponseList.add(restaurantResponse);


            }

            return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseList);
        } catch (RuntimeException e) {
            log.error(" server error getAllRestaurant", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());

        }


    }

    @Override
    public ResponseEntity<?> updateRestaurant(Long restaurantId, RestaurantDTO restaurantDTO) {
        try {
            Restaurant byRestId = restaurantRepo.findByRestId(restaurantId);
            byRestId.setName(restaurantDTO.getName());
            byRestId.setLocation(restaurantDTO.getLocation());
            byRestId.setRestType(restaurantDTO.getRestType());
            byRestId.setService(restaurantDTO.getService());
            byRestId.setNumberOfEmployees(restaurantDTO.getNumberOfEmployees());
            restaurantRepo.save(byRestId);
            return ResponseEntity.status(HttpStatus.OK).body("Restaurant  update successfully  ");
        }
        catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error" + e.getMessage());
        }
    }
    }
