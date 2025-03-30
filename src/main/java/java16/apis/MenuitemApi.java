package java16.apis;

import java16.dto.request.MenuitemDTO;
import java16.dto.response.MenuitemResponse;
import java16.enums.VegetarianStatus;
import java16.service.MenuitemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/menuitem")
@RequiredArgsConstructor
public class MenuitemApi {

    public final MenuitemService menuitemService;

    @PreAuthorize("hasAuthority('CHEF')or hasAuthority('ADMIN')")
    @PostMapping("/createMenuitem/{restaurantId}")

    public ResponseEntity<?> saveMenuitem(@PathVariable Long restaurantId ,@RequestBody MenuitemDTO  menuitemDTO){
        return menuitemService.createMenultem(restaurantId,menuitemDTO);
    }

    @GetMapping("/getAllMenuitem")
    public ResponseEntity<List<MenuitemResponse>> getAllMenuitem(){
        return menuitemService.getAllMenultem();
    }


    @GetMapping("/findByMenuitemId/{menuitemId}")
    public ResponseEntity<MenuitemResponse> getMenuitemById(@PathVariable Long menuitemId){
        return menuitemService.findMenuitemById(menuitemId);

    }

    @DeleteMapping("/deleteMenuitem/{menuitemId}")
    public ResponseEntity<?> deleteMenuitem(@PathVariable Long menuitemId){
      return   menuitemService.deleteMenuById(menuitemId);
    }

    @PutMapping("/updateMenuitem/{menuitemId}")
    public ResponseEntity<?> updateMenuitem(@PathVariable Long menuitemId,@RequestBody MenuitemDTO menuitemDTO){
        return menuitemService.updateMenuitem(menuitemId,menuitemDTO);
    }

    @GetMapping("/filter/vegetarian")
    public ResponseEntity<List<?>> filterVegetarian(@RequestParam VegetarianStatus vegetarian){
        return menuitemService.filterVegetarian(vegetarian);
    }

    @GetMapping("/globalSearch")
    public ResponseEntity<List<MenuitemResponse>> getGlobalSearch(@RequestParam String query){
      return   menuitemService.globalSearch(query);
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<?>> filterPrice(@RequestParam BigDecimal price){
     return menuitemService.filterPrice(price);

    }
    @GetMapping("/sort/price")
    public ResponseEntity<List<MenuitemResponse>> sortByPrice(@RequestParam String sortPrice) {
        return menuitemService.sortByPrice(sortPrice);
    }
}
