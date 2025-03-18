package java16.apis;

import java16.dto.response.StopListResponse;
import java16.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stoplist")
@RequiredArgsConstructor
public class StopListApi {

    private final StopListService stopListService;

    @PreAuthorize("hasAuthority('CHEF') or hasAuthority('ADMIN')")
    @PostMapping("/add/{menuitemId}")
    public ResponseEntity<String> addToStopList(@PathVariable Long menuitemId, @RequestParam String reason) {
        return stopListService.addToStopList(menuitemId, reason);
    }

    @PreAuthorize("hasAuthority('CHEF') or hasAuthority('ADMIN')")
    @DeleteMapping("/remove/{menuitemId}")
    public ResponseEntity<String> removeFromStopList(@PathVariable Long menuitemId) {
        return stopListService.deleteFromStopList(menuitemId);
    }

    @GetMapping
    public ResponseEntity<List<StopListResponse>> getAllStopListItems() {
        return stopListService.getAllStopList();
    }

    @GetMapping("/{menuitemId}")
    public ResponseEntity<?> findStopListByMenuitemId(@PathVariable Long menuitemId) {
        return stopListService.getStopListByMenuitemId(menuitemId);
    }
}


















