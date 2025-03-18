package java16.repo;

import java16.entitys.StopList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


public interface StopListRepo extends JpaRepository<StopList, Long> {
    @Query("SELECT s FROM StopList s WHERE s.menuitem.id = :menuitemId")
    StopList findStopListByMenuitemId(@Param("menuitemId") Long menuitemId);
}
