package java16.service;

import java16.dto.response.MenuitemResponse;
import java16.dto.response.StopListResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StopListService {

    ResponseEntity<String> addToStopList(Long menuitemId, String reason);

    ResponseEntity<String> deleteFromStopList(Long menuitemId);

    ResponseEntity<List<StopListResponse>> getAllStopList();

    ResponseEntity<StopListResponse> getStopListByMenuitemId(Long menuitemId);
}