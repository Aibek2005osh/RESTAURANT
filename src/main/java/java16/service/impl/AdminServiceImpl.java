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

            if (userRepo.findByEmail(createAdminUserDTO.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Мындай email бар");
            }



            User user = new User();
            user.setFirstName(createAdminUserDTO.getFirstName());
            user.setLastName(createAdminUserDTO.getLastName());


            user.setDateOfBrith(createAdminUserDTO.getDateOfBirth());

            user.setEmail(createAdminUserDTO.getEmail());
            user.setPassword(passwordEncoder.encode(createAdminUserDTO.getPassword()));
            user.setPhoneNumber(createAdminUserDTO.getPhoneNumber());
            user.setRole(Role.valueOf(createAdminUserDTO.getRole()));
            user.setExpirense(createAdminUserDTO.getExperience());

            Restaurant restaurantRepoById = restaurantRepo.findByRestId(createAdminUserDTO.getRestaurantId());

            if (restaurantRepoById.getNumberOfEmployees() >=15){
                throw  new NoVacancyException("Вакансия жок! Бул ресторанда 15 кызматкер бар !✖️");
            }
            user.setRestaurant(restaurantRepoById);
            restaurantRepoById.setNumberOfEmployees(restaurantRepoById.getNumberOfEmployees() + 1);

            userRepo.save(user);

            return ResponseEntity.ok("Колдонуучу ийгиликтүү түзүлдү");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("error server"+e.getMessage());
        }
    }
}

