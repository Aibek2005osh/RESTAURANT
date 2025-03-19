package java16.service;

import java16.dto.request.ChequeDTO;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

public interface ChequeService {
    ResponseEntity<?> createCheque(ChequeDTO chequeDTO);
    ResponseEntity<?> getChequeById(Long chequeId);
    ResponseEntity<?> getAllCheques();
    ResponseEntity<?> updateCheque(Long chequeId, ChequeDTO chequeDTO);
    ResponseEntity<?> deleteCheque(Long chequeId);
    ResponseEntity<?> getDailyTotalForWaiter(Long waiterId, LocalDate date);
    ResponseEntity<?> getRestaurantDayAvg(Long restaurantId, LocalDate date);
}