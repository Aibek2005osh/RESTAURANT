package java16.repo;

import jakarta.validation.constraints.NotNull;
import java16.entitys.Restaurant;
import java16.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository <Restaurant, Long> {


   default Restaurant   findByRestId(Long restaurantId){
       return findById(restaurantId).orElseThrow(()->new NotFoundException("restaurant tabylgan jok"));
   }
}
