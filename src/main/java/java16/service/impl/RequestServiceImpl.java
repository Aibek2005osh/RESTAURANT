package java16.service.impl;

import java16.dto.request.RequestDTO;
import java16.entitys.Request;
import java16.entitys.Restaurant;
import java16.entitys.User;
import java16.enums.Role;
import java16.exceptions.BadRequestException;
import java16.exceptions.ForbidenException;
import java16.exceptions.NotFoundException;
import java16.repo.RequestRepo;
import java16.repo.RestaurantRepo;
import java16.repo.UserRepo;
import java16.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepo requestRepo;
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> sendRequest(RequestDTO requestDTO) {
        int age = Period.between(requestDTO.getDateOfBirth(), LocalDate.now()).getYears();
        if (requestDTO.getRole() == Role.CHEF) {
            if (age < 25 || age > 45) throw new BadRequestException("Повардын жашы 25-45 болушу керек");
            if (requestDTO.getExperience() < 2) throw new BadRequestException("Повардын стажы 2+ жыл");
        } else if (requestDTO.getRole() == Role.WAITER) {
            if (age < 18 || age > 30) throw new BadRequestException("Официанттын жашы 18-30 болушу керек");
            if (requestDTO.getExperience() < 1) throw new BadRequestException("Официанттын стажы 1+ жыл");
        }

        restaurantRepo.findByRestId(requestDTO.getRestaurantId()); // Ресторан бар экенин текшерүү

        Request request = new Request();
        request.setEmail(requestDTO.getEmail());
        request.setFirstName(requestDTO.getFirstName());
        request.setLastName(requestDTO.getLastName());
        request.setDateOfBirth(requestDTO.getDateOfBirth());
        request.setPhoneNumber(requestDTO.getPhoneNumber());
        request.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        request.setExperience(requestDTO.getExperience());
        request.setRole(requestDTO.getRole());
        request.setRestaurantId(requestDTO.getRestaurantId()); // Оңдолгон жер

        requestRepo.save(request);
        return ResponseEntity.ok("Заявка жиберилди");
    }

    @Override
    public ResponseEntity<?> assignRequest(Long requestId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals(Role.ADMIN)) throw new ForbidenException("Админ гана кабыл ала алат");

        Request request = requestRepo.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Заявка табылган жок: " + requestId));

        Restaurant restaurant = restaurantRepo.findByRestId(request.getRestaurantId());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBrith(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setExpirense(request.getExperience()); // "Expirense" ордуна "Experience" оңдолду
        user.setRole(request.getRole());
        user.setRestaurant(restaurant);

        userRepo.save(user);
        requestRepo.delete(request);
        return ResponseEntity.ok("Сотрудник кошулду");
    }

    @Override
    public ResponseEntity<?> getRestaurantRequests(Long restaurantId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals(Role.ADMIN)) throw new ForbidenException("Админ гана көрө алат");

        List<Request> requests = requestRepo.findByRestaurantId(restaurantId);
        if (requests.isEmpty()) throw new NotFoundException("Заявкалар жок: " + restaurantId);

        return ResponseEntity.ok(requests);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Колдонуучу табылган жок: " + email));
    }
}