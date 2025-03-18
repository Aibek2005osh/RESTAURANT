package java16.service.impl;

import java16.dto.response.StopListResponse;
import java16.entitys.Menuitem;
import java16.entitys.StopList;
import java16.repo.MenuitemRepo;
import java16.repo.StopListRepo;
import java16.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {


    private final StopListRepo stopListRepo;
    private final MenuitemRepo menuitemRepo;

    @Override
    public ResponseEntity<String> addToStopList(Long menuitemId, String reason) {

        try {


            Menuitem menuitemById = menuitemRepo.findMenultemById(menuitemId);

            if (menuitemById.getStopList() != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Menuitem da stopList bar");

            }
            StopList stopList = new StopList();
            stopList.setMenuitem(menuitemById);
            stopList.setReason(reason);
            stopListRepo.save(stopList);
            menuitemById.setStopList(stopList);

            menuitemRepo.save(menuitemById);

            return ResponseEntity.status(HttpStatus.CREATED).body("success");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<String> deleteFromStopList(Long menuitemId) {

        try {
            Menuitem menultemById = menuitemRepo.findMenultemById(menuitemId);
            StopList stopList = menultemById.getStopList();
            if (stopList == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Menuitem da stopList jok");
            }
            menultemById.setStopList(null);
            menuitemRepo.save(menultemById);
            menuitemRepo.delete(menultemById);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success deleteted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<List<StopListResponse>> getAllStopList() {

        try {
            List<StopList> stopLists = stopListRepo.findAll();
            if (stopLists.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            List<StopListResponse> responseList = new ArrayList<>();
            for (StopListResponse stopListResponse : responseList) {
                StopListResponse.builder()
                        .reason(stopListResponse.getReason())
                        .date(stopListResponse.getDate())
                        .menuitemName(stopListResponse.getMenuitemName())
                        .isVegeterian(stopListResponse.getIsVegeterian())
                        .restaurantId(stopListResponse.getRestaurantId())
                        .build();
                responseList.add(stopListResponse);

            }

            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }

    @Override
    public ResponseEntity<StopListResponse> getStopListByMenuitemId(Long menuitemId) {
try {


    StopList stopList = stopListRepo.findStopListByMenuitemId(menuitemId);
    if (stopList == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    Menuitem menuitem = stopList.getMenuitem();

    StopListResponse response = StopListResponse.builder()
            .reason(stopList.getReason())
            .date(stopList.getDate())
            .menuitemName(menuitem.getName())
            .isVegeterian(menuitem.getIsVegeterian())
            .menuitemPrice(menuitem.getPrice())
            .restaurantId(menuitem.getRestaurant() == null ? null : menuitem.getRestaurant().getId())
            .build();
    return ResponseEntity.ok(response);
} catch (Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
}

    }
}
