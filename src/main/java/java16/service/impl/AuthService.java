package java16.service.impl;

import jakarta.annotation.PostConstruct;
import java16.config.jwt.JwtService;
import java16.dto.request.UserLoginDTO;
import java16.dto.request.UserRegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.entitys.User;
import java16.enums.Role;
import java16.exceptions.NotFoundException;
import java16.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public SimpleResponse register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setDateOfBrith(userRegisterDTO.getDateOfBrith());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());

        try {
            userRepo.save(user);
            return SimpleResponse.builder()
                    .massage("Successfully registered  ❤️")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            return SimpleResponse
                    .builder().massage("server error " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO) {

        User user = userRepo.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("not found with email ->" + userLoginDTO.getEmail()));
        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(SimpleResponse.builder()
                    .massage("Successfully welcome")
                    .status(HttpStatus.OK)
                            .massage(token)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(SimpleResponse.builder()
                            .massage("Invalid email  password")
                            .status(HttpStatus.UNAUTHORIZED)
                            .build());
        }



    }
    @PostConstruct
    public void init() {
        String adminEmail = "admin@gmail.com";

        if (!userRepo.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setDateOfBrith(LocalDate.of(1990, 1, 1));
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("ADMIN123"));
            admin.setPhoneNumber("+996700000000");
            admin.setRole(Role.ADMIN);
            admin.setExpirense(20);

            userRepo.save(admin);
            log.info("Администратор аккаунту  түзүлдү  {}", adminEmail);
        } else {
            log.info("Администратор аккаунту мурунтан эле бар  {}",adminEmail);
        }
    }


}
