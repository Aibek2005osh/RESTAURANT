package java16.service;

import java16.dto.request.RequestDTO;
import org.springframework.http.ResponseEntity;

public interface RequestService {

        ResponseEntity<?> sendRequest(RequestDTO requestDTO);


        ResponseEntity<?> assignRequest(Long requestId);


        ResponseEntity<?> getRestaurantRequests(Long restaurantId);

}