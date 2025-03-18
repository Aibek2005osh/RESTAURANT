package java16.service.impl;

import jakarta.validation.constraints.*;
import java16.dto.request.CreateAdminUserDTO;
import java16.entitys.Restaurant;
import java16.entitys.User;
import java16.enums.Role;
import java16.exceptions.NoVacancyException;
import java16.repo.RestaurantRepo;
import java16.repo.UserRepo;
import java16.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
private final RestaurantRepo restaurantRepo;
    private final PasswordEncoder passwordEncoder;



    @Override
    public ResponseEntity<?> createUserByAdmin(CreateAdminUserDTO createAdminUserDTO) {
        try {
            String email = createAdminUserDTO.getEmail();
            if (email == null || email.isBlank()) {
                return ResponseEntity.badRequest().body("Email милдеттүү түрдө керек!");
            }
            if (userRepo.findByEmail(email).isPresent()) {
                return ResponseEntity.badRequest().body("Мындай email бар");
            }

            User user = new User();
            user.setFirstName(createAdminUserDTO.getFirstName());
            user.setLastName(createAdminUserDTO.getLastName());
            user.setDateOfBrith(createAdminUserDTO.getDateOfBirth());
            user.setEmail(email);

            String dtoPassword = createAdminUserDTO.getPassword();
            if (dtoPassword == null || dtoPassword.isBlank()) {
                return ResponseEntity.badRequest().body("Сырсөз милдеттүү түрдө керек!");
            }
            user.setPassword(passwordEncoder.encode(dtoPassword));

            user.setPhoneNumber(createAdminUserDTO.getPhoneNumber());

            String role = createAdminUserDTO.getRole();
            if (role == null || role.isBlank()) {
                return ResponseEntity.badRequest().body("Роль милдеттүү түрдө керек!");
            }
            try {
                user.setRole(Role.valueOf(role));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Роль туура эмес: " + role);
            }

            user.setExpirense(createAdminUserDTO.getExperience());

            Long restaurantId = createAdminUserDTO.getRestaurantId();
            if (restaurantId == null) {
                return ResponseEntity.badRequest().body("Ресторан ID милдеттүү түрдө керек!");
            }
            Restaurant restaurantRepoById = restaurantRepo.findByRestId(restaurantId);
            if (restaurantRepoById == null) {
                return ResponseEntity.badRequest().body("Ресторан табылган жок!");
            }
            if (restaurantRepoById.getNumberOfEmployees() >= 15) {
                throw new NoVacancyException("Вакансия жок! Бул ресторанда 15 кызматкер бар !✖️");
            }
            user.setRestaurant(restaurantRepoById);
            restaurantRepoById.setNumberOfEmployees(restaurantRepoById.getNumberOfEmployees() + 1);

            userRepo.save(user);

            return ResponseEntity.ok("Колдонуучу ийгиликтүү түзүлдү");
        } catch (RuntimeException e) {
            log.error("Колдонуучу түзүүдө ката: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Сервер катасы: " + e.getMessage());
        }
    }
}

