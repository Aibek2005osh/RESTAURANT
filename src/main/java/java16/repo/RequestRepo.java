package java16.repo;

import java16.entitys.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepo extends JpaRepository<Request, Long> {

    List<Request> findByRestaurantId(Long restaurantId);
}