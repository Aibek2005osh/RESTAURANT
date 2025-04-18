package java16.repo;

import java16.entitys.Cheque;
import java16.entitys.User;
import java16.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChequeRepo extends JpaRepository<Cheque, Long> {
   default Cheque findByChequeId(Long chequeId) {
       return findById(chequeId).orElseThrow(()->new NotFoundException("Cheque not found with id: " + chequeId));
   }


    List<Cheque> findByUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);
    @Query("SELECT c FROM Cheque c WHERE c.user.restaurant.id = :restaurantId " +
           "AND c.createdAt BETWEEN :start AND :end")
    List<Cheque> findByUserRestaurantIdAndCreatedAtBetween(
            @Param("restaurantId") Long restaurantId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}