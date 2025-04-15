package java16.repo;

import java16.entitys.Menuitem;
import java16.enums.VegetarianStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface MenuitemRepo extends JpaRepository<Menuitem, Long> {


    default Menuitem findMenultemById(Long id) {
        return findById(id).orElseThrow(()-> new RuntimeException("Menuitem not found"));
    }

    @Query("select m from Menuitem  m where m.isVegeterian =:vegetarian " )
    List<Menuitem> findMenultemIsVegetarian(VegetarianStatus vegetarian);

    @Query("SELECT m FROM Menuitem m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(m.description) LIKE LOWER(CONCAT('%', :query, '%')) " +
       "OR LOWER(m.image) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Menuitem> globalSearchMenuitem(String query);

    @Query("select m from Menuitem  m where m.price =:price order by m.price ")
    List<Menuitem> filterPrice(BigDecimal price);

    @Query("SELECT m FROM Menuitem m ORDER BY m.price DESC")
    List<Menuitem> findAllByPriceDesc();

    @Query("SELECT m FROM Menuitem m ORDER BY m.price ASC")
    List<Menuitem> findAllByPriceAsc();
}
