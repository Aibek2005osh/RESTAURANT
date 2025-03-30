package java16.apis;

import java16.dto.request.RestaurantDTO;
import java16.service.RestaurantService;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restaurant")
@RequiredArgsConstructor
public class RestaurantApi {

    private final RestaurantService restaurantService;

    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveRestaurant")
    public ResponseEntity<?>  saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.saveRestaurant(restaurantDTO);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteRestaurant/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long restaurantId) {
        return restaurantService.deleteRestaurant(restaurantId);
    }

    @GetMapping("/findByRestaurantId/{restaurantId}")
    public ResponseEntity<?> findByRestaurantId(@PathVariable Long restaurantId) {

        return restaurantService.findByRestaurantId(restaurantId);
    }

    @GetMapping("/getAllRestaurant")
    public ResponseEntity<List<?>> getAllRestaurant() {
       return restaurantService.getAllRestaurant();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateRestaurant/{restaurantId}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantId , restaurantDTO);
    }


}
