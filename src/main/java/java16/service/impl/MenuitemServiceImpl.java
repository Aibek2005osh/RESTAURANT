package java16.service.impl;

import java16.dto.request.MenuitemDTO;
import java16.dto.response.MenuitemResponse;
import java16.entitys.Menuitem;

import java16.entitys.Restaurant;
import java16.enums.VegetarianStatus;
import java16.exceptions.NotFoundException;
import java16.repo.MenuitemRepo;
import java16.repo.RestaurantRepo;
import java16.service.MenuitemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MenuitemServiceImpl implements MenuitemService {
    private final MenuitemRepo menuitemRepo;
    private final RestaurantRepo restaurantRepo;


    @Override
    public ResponseEntity<?> createMenultem(Long restaurantId, MenuitemDTO menuitemDTO) {

        Restaurant byRestId = restaurantRepo.findByRestId(
                restaurantId);

        Menuitem menuitem = new Menuitem();
        menuitem.setName(menuitemDTO.getName());
        menuitem.setImage(menuitemDTO.getImage());
        menuitem.setPrice(menuitemDTO.getPrice());
        menuitem.setDescription(menuitemDTO.getDescription());
        menuitem.setIsVegeterian(menuitemDTO.getIsVegeterian());
        menuitem.setRestaurant(byRestId);

        try {
            menuitemRepo.save(menuitem);
            return ResponseEntity.status(HttpStatus.CREATED).body("success Create Menuitem");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error server" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<List<MenuitemResponse>> getAllMenultem() {

        try {
            List<Menuitem> menuitems = menuitemRepo.findAll();

            List<MenuitemResponse> response = new ArrayList<>();

            for (Menuitem menuitem : menuitems) {
                MenuitemResponse menuitemResponse = MenuitemResponse.builder()
                        .name(menuitem.getName())
                        .image(menuitem.getImage())
                        .price(menuitem.getPrice())
                        .description(menuitem.getDescription())
                        .isVegeterian(menuitem.getIsVegeterian())
                        .restaurantId(menuitem.getRestaurant().getId())
                        .subcategoryId(menuitem.getSubcategory() != null ? menuitem.getSubcategory().getId() : null)
                        .build();
                response.add(menuitemResponse);
            }

            if (response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build();
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error server", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @Override
    public ResponseEntity<MenuitemResponse> findMenuitemById(Long id) {
        try {
            Menuitem menultemById = menuitemRepo.findMenultemById(id);

            MenuitemResponse response = MenuitemResponse.builder()
                    .name(menultemById.getName())
                    .image(menultemById.getImage())
                    .price(menultemById.getPrice())
                    .description(menultemById.getDescription())
                    .isVegeterian(menultemById.getIsVegeterian())
                    .restaurantId(menultemById.getRestaurant().getId())
                    .subcategoryId(menultemById.getSubcategory() != null ? menultemById.getSubcategory().getId() : null)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error server", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<?> deleteMenuById(Long menuitemId) {
        Menuitem menultemById = menuitemRepo.findMenultemById(menuitemId);

        try {
            menuitemRepo.delete(menultemById);
            return ResponseEntity.status(HttpStatus.OK).
                    body("success Delete Menuitem");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error server" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateMenuitem(Long menuitemId, MenuitemDTO menuitemDTO) {

        Menuitem menultemById = menuitemRepo.findMenultemById(menuitemId);
        Menuitem menuitem = new Menuitem();
        menuitem.setName(menuitemDTO.getName());
        menuitem.setImage(menuitemDTO.getImage());
        menuitem.setPrice(menuitemDTO.getPrice());
        menuitem.setDescription(menuitemDTO.getDescription());
        menuitem.setIsVegeterian(menuitemDTO.getIsVegeterian());

        try {
            menuitemRepo.save(menuitem);
            return ResponseEntity.status(HttpStatus.CREATED).body("success Update Menuitem");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error server" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<List<?>> filterVegetarian(VegetarianStatus vegetarian) {

        try {

            List<Menuitem> menuitems = menuitemRepo.findMenultemIsVegetarian(vegetarian);
            if (menuitems.isEmpty()) {
                log.warn("not found vegetarian with menultem ");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            List<MenuitemResponse> response = new ArrayList<>();
            for (Menuitem menuitem : menuitems) {
                MenuitemResponse menuitemResponse = MenuitemResponse.builder()
                        .name(menuitem.getName())
                        .image(menuitem.getImage())
                        .price(menuitem.getPrice())
                        .description(menuitem.getDescription())
                        .isVegeterian(menuitem.getIsVegeterian())
                        .restaurantId(menuitem.getRestaurant() != null ? menuitem.getRestaurant().getId() : null)
                        .subcategoryId(menuitem.getSubcategory() != null ? menuitem.getSubcategory().getId() : null)
                        .build();
                response.add(menuitemResponse);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<MenuitemResponse>> globalSearch(String query) {
        try {
            List<Menuitem> menuitems = menuitemRepo.globalSearchMenuitem(query);
            if (menuitems.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            List<MenuitemResponse> response = new ArrayList<>();
            for (Menuitem menuitem : menuitems) {
                MenuitemResponse menuitemResponse = MenuitemResponse.builder()
                        .name(menuitem.getName())
                        .image(menuitem.getImage())
                        .price(menuitem.getPrice())
                        .description(menuitem.getDescription())
                        .isVegeterian(menuitem.getIsVegeterian())
                        .restaurantId(menuitem.getRestaurant() != null ? menuitem.getRestaurant().getId() : null)
                        .subcategoryId(menuitem.getSubcategory() != null ? menuitem.getSubcategory().getId() : null)
                        .build();
                response.add(menuitemResponse);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Override
    public ResponseEntity<List<?>> filterPrice(BigDecimal price) {
try {
        List<Menuitem> menuitems = menuitemRepo.filterPrice(price);
        List<MenuitemResponse> response = new ArrayList<>();
        for (Menuitem menuitem : menuitems) {
            MenuitemResponse menuitemResponse = MenuitemResponse.builder()
                    .name(menuitem.getName())
                    .image(menuitem.getImage())
                    .price(menuitem.getPrice())
                    .description(menuitem.getDescription())
                    .isVegeterian(menuitem.getIsVegeterian())
                    .restaurantId(menuitem.getRestaurant() != null ? menuitem.getRestaurant().getId() : null)
                    .subcategoryId(menuitem.getSubcategory() != null ? menuitem.getSubcategory().getId() : null)
                    .build();
            response.add(menuitemResponse);
        }
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    }

    @Override
    public ResponseEntity<List<MenuitemResponse>> sortByPrice(String sortPrice) {

        try {
            List<Menuitem> menuitems = new ArrayList<>();
            if ("desc".equals(sortPrice)) {
                menuitems = menuitemRepo.findAllByPriceDesc();
            }else if ("asc".equals(sortPrice)) {
                menuitems = menuitemRepo.findAllByPriceAsc();
            }
           else {
               new NotFoundException("sort price not found");
            }

           if (menuitems.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

           }
           List<MenuitemResponse> response = new ArrayList<>();
           for (Menuitem menuitem : menuitems) {
               MenuitemResponse menuitemResponse = MenuitemResponse.builder()
                       .name(menuitem.getName())
                       .image(menuitem.getImage())
                       .price(menuitem.getPrice())
                       .description(menuitem.getDescription())
                       .isVegeterian(menuitem.getIsVegeterian())
                       .restaurantId(menuitem.getRestaurant() != null ? menuitem.getRestaurant().getId() : null)
                       .subcategoryId(menuitem.getSubcategory() != null ? menuitem.getSubcategory().getId() : null)
                       .build();
               response.add(menuitemResponse   );
           }
           return ResponseEntity.ok(response);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

