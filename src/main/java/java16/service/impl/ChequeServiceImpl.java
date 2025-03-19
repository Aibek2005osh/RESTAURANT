package java16.service.impl;

import java16.dto.request.ChequeDTO;
import java16.dto.response.ChequeResponse;
import java16.entitys.Cheque;
import java16.entitys.Menuitem;
import java16.entitys.User;
import java16.enums.Role;
import java16.exceptions.BadRequestException;
import java16.exceptions.ForbidenException;
import java16.exceptions.NotFoundException;
import java16.repo.ChequeRepo;
import java16.repo.MenuitemRepo;
import java16.repo.UserRepo;
import java16.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {

    private final ChequeRepo chequeRepo;
    private final UserRepo userRepo;
    private final MenuitemRepo menuitemRepo;

    @Override
    public ResponseEntity<?> createCheque(ChequeDTO chequeDTO) {
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals(Role.ADMIN) && !currentUser.getRole().equals(Role.WAITER)) {
            throw new ForbidenException("Чекти Админ же Официант гана түзө алат!");
        }

        User user = userRepo.findById(chequeDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("Колдонуучу табылган жок ID: " + chequeDTO.getUserId()));
        if (!user.getRole().equals(Role.WAITER) && !user.getRole().equals(Role.ADMIN)) {
            throw new BadRequestException("Чек Официант же Админ үчүн гана түзүлөт!");
        }

        Cheque cheque = new Cheque();
        cheque.setUser(user);
        cheque.setCreatedAt(LocalDateTime.now());

        List<Menuitem> menuitems = new ArrayList<>();
        for (Long menuitemId : chequeDTO.getMenuitemIds()) {
            Menuitem menuitem = menuitemRepo.findMenultemById(menuitemId);
            if (menuitem == null) {
                throw new NotFoundException("Меню элементи табылган жок ID: " + menuitemId);
            }
            menuitems.add(menuitem);
        }
        cheque.setMenuitems(menuitems);

         double totalPrice = menuitems.stream()
                .mapToDouble(m -> m.getPrice().doubleValue())
                .sum();
        cheque.setPriceAverage(totalPrice / menuitems.size());

        chequeRepo.save(cheque);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Чек ийгиликтүү түзүлдү ✅");
    }

    @Override
    public ResponseEntity<?> getChequeById(Long chequeId) {
        Cheque cheque = chequeRepo.findById(chequeId)
                .orElseThrow(() -> new NotFoundException("Чек табылган жок ID: " + chequeId));
        ChequeResponse response = buildChequeResponse(cheque, 10.0);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getAllCheques() {
        List<Cheque> cheques = chequeRepo.findAll();
        List<ChequeResponse> responses = new ArrayList<>();
        for (Cheque cheque : cheques) {
            responses.add(buildChequeResponse(cheque, 10.0));
        }
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<?> updateCheque(Long chequeId, ChequeDTO chequeDTO) {
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals(Role.ADMIN)) {
            throw new ForbidenException("Чекти Админ гана жаңырта алат!");
        }

        Cheque cheque = chequeRepo.findById(chequeId)
                .orElseThrow(() -> new NotFoundException("Чек табылган жок ID: " + chequeId));

        User user = userRepo.findById(chequeDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("Колдонуучу табылган жок ID: " + chequeDTO.getUserId()));
        if (!user.getRole().equals(Role.WAITER) && !user.getRole().equals(Role.ADMIN)) {
            throw new BadRequestException("Чек Официант же Админ үчүн гана жаңыртылат!");
        }
        cheque.setUser(user);

        List<Menuitem> menuitems = new ArrayList<>();
        for (Long menuitemId : chequeDTO.getMenuitemIds()) {
            Menuitem menuitem = menuitemRepo.findMenultemById(menuitemId);
            if (menuitem == null) {
                throw new NotFoundException("Меню элементи табылган жок ID: " + menuitemId);
            }
            menuitems.add(menuitem);
        }
        cheque.setMenuitems(menuitems);

        double totalPrice = menuitems.stream()
                .mapToDouble(m -> m.getPrice().doubleValue())
                .sum();
        cheque.setPriceAverage(totalPrice / menuitems.size());

        chequeRepo.save(cheque);
        return ResponseEntity.ok("Чек ийгиликтүү жаңыртылды 🤓");
    }

    @Override
    public ResponseEntity<?> deleteCheque(Long chequeId) {
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals(Role.ADMIN)) {
            throw new ForbidenException("Чекти Админ гана өчүрө алат!");
        }

        Cheque cheque = chequeRepo.findById(chequeId)
                .orElseThrow(() -> new NotFoundException("Чек табылган жок ID: " + chequeId));
        chequeRepo.delete(cheque);
        return ResponseEntity.ok("Чек ийгиликтүү өчүрүлдү ✖️");
    }

    @Override
    public ResponseEntity<?> getDailyTotalForWaiter(Long waiterId, LocalDate date) {
        User waiter = userRepo.findById(waiterId)
                .orElseThrow(() -> new NotFoundException("Официант табылган жок ID: " + waiterId));
        if (!waiter.getRole().equals(Role.WAITER)) {
            throw new BadRequestException("Бул колдонуучу официант эмес!");
        }

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<Cheque> cheques = chequeRepo.findByUserAndCreatedAtBetween(waiter, startOfDay, endOfDay);
        double dailyTotal = cheques.stream()
                .mapToDouble(cheque -> {
                    double totalPrice = cheque.getMenuitems().stream()
                            .mapToDouble(m -> m.getPrice().doubleValue())
                            .sum();
                    return totalPrice * (1 + 10.0 / 100);
                })
                .sum();

        return ResponseEntity.ok("Официант " + waiter.getFirstName() + " " + waiter.getLastName() +
                                 " үчүн " + date + " күнү жалпы сумма: " + dailyTotal);
    }

    @Override
    public ResponseEntity<?> getRestaurantDayAvg(Long restaurantId, LocalDate date) {
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals(Role.ADMIN)) {
            throw new ForbidenException("Бул маалыматты Админ гана көрө алат!");
        }

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<Cheque> cheques = chequeRepo.findByUserRestaurantIdAndCreatedAtBetween(restaurantId, startOfDay, endOfDay);
        double dailyAverage = cheques.stream()
                .mapToDouble(Cheque::getPriceAverage)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok("Ресторан ID " + restaurantId + " үчүн " + date +
                                 " күнү орточо сумма: " + dailyAverage);
    }

    private ChequeResponse buildChequeResponse(Cheque cheque, double servicePercentage) {
        double totalPrice = cheque.getMenuitems().stream()
                .mapToDouble(m -> m.getPrice().doubleValue())
                .sum();
        double grandTotal = totalPrice * (1 + servicePercentage / 100);

        return ChequeResponse.builder()
                .waiterFullName(cheque.getUser().getFirstName() + " " + cheque.getUser().getLastName())
                .items(cheque.getMenuitems())
                .averagePrice(cheque.getPriceAverage())
                .servicePercentage(servicePercentage)
                .grandTotal(grandTotal)
                .build();
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Колдонуучу табылган жок: " + email));
    }
}