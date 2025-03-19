package java16.apis;

import java16.dto.request.RequestDTO;
import java16.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestApi {

    private final RequestService requestService;

    @PostMapping("/send-request")
    public ResponseEntity<?> submitRequest(@RequestBody RequestDTO requestDTO) {
        return requestService.sendRequest(requestDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign/{requestId}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long requestId) {
        return requestService.assignRequest(requestId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/requests/{restaurantId}")
    public ResponseEntity<?> getPendingRequests(@PathVariable Long restaurantId) {
        return requestService.getRestaurantRequests(restaurantId);
    }
}