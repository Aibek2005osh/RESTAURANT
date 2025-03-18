package java16.service;

import java16.dto.request.MenuitemDTO;
import java16.dto.response.MenuitemResponse;
import java16.enums.VegetarianStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface MenuitemService {


    ResponseEntity<?> createMenultem(Long restaurantId ,MenuitemDTO menuitemDTO);

    ResponseEntity<List<MenuitemResponse>> getAllMenultem();

    ResponseEntity<MenuitemResponse> findMenuitemById(Long id);

    ResponseEntity<?> deleteMenuById(Long menuitemId);

    ResponseEntity<?> updateMenuitem(Long menuitemId, MenuitemDTO menuitemDTO);

    ResponseEntity<List<?>> filterVegetarian(VegetarianStatus vegetarian);

    ResponseEntity<List<MenuitemResponse>> globalSearch(String query);

    ResponseEntity<List<?>> filterPrice(BigDecimal price);


    ResponseEntity<List<MenuitemResponse>> sortByPrice(String sortPrice);
}
