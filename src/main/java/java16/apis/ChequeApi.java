package java16.apis;

import java16.dto.request.ChequeDTO;
import java16.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/cheque")
@RequiredArgsConstructor































































public class ChequeApi {

    private final ChequeService chequeService;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WAITER')")
    @PostMapping("/createCheque")

    public ResponseEntity<?> createCheque(@RequestBody ChequeDTO chequeDTO) {
        return chequeService.createCheque(chequeDTO);
    }

    @GetMapping("/{chequeId}")
    public ResponseEntity<?> getChequeById(@PathVariable Long chequeId) {
        return chequeService.getChequeById(chequeId);
    }

    @GetMapping("/getAllCheques")
    public ResponseEntity<?> getAllCheques() {
        return chequeService.getAllCheques();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateCheque/{chequeId}")
    public ResponseEntity<?> updateCheque(@PathVariable Long chequeId, @RequestBody ChequeDTO chequeDTO) {
        return chequeService.updateCheque(chequeId, chequeDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteCheque/{chequeId}")
    public ResponseEntity<?> deleteCheque(@PathVariable Long chequeId) {
        return chequeService.deleteCheque(chequeId);
    }

    @GetMapping("/dailyTotalForWaiter/{waiterId}")
    public ResponseEntity<?> getDailyTotalForWaiter(
            @PathVariable Long waiterId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return chequeService.getDailyTotalForWaiter(waiterId, date);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getRestaurantDayAvg/{restaurantId}")
    public ResponseEntity<?> getRestaurantDayAvg(
            @PathVariable Long restaurantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return chequeService.getRestaurantDayAvg(restaurantId, date);
    }
}