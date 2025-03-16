package java16.service.impl;

import java16.dto.response.SimpleResponse;
import java16.dto.response.UserResponcse;
import java16.entitys.User;
import java16.repo.UserRepo;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;



    @Override
    public List<UserResponcse> getAllUser() {
        List<User> users = userRepo.findAll();
        List<UserResponcse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponcse userResponse = UserResponcse.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .dateOfBrith(user.getDateOfBrith())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phoneNumber(user.getPhoneNumber())
                    .expirense(user.getExpirense())
                    .build();

            userResponses.add(userResponse);
        }

        return userResponses;
    }

    @Override
    public UserResponcse findByUserId(Long id) {
        User byUserId = userRepo.findByUserId(id);
        UserResponcse userResponcse = new UserResponcse();
        userResponcse.setFirstName(byUserId.getFirstName());
        userResponcse.setLastName(byUserId.getLastName());
        userResponcse.setDateOfBrith(byUserId.getDateOfBrith());
        userResponcse.setEmail(byUserId.getEmail());
        userResponcse.setPassword(byUserId.getPassword());
        userResponcse.setPhoneNumber(byUserId.getPhoneNumber());
        userResponcse.setExpirense(byUserId.getExpirense());
        return userResponcse;
    }

    @Override
    public ResponseEntity<?> updateUser(Long userId, UserResponcse userResponcse) {
        User byUserId = userRepo.findByUserId(userId);
        byUserId.setFirstName(userResponcse.getFirstName());
        byUserId.setLastName(userResponcse.getLastName());
        byUserId.setDateOfBrith(userResponcse.getDateOfBrith());
        byUserId.setEmail(userResponcse.getEmail());
        byUserId.setPassword(passwordEncoder.encode(userResponcse.getPassword()));
        byUserId.setPhoneNumber(userResponcse.getPhoneNumber());
        byUserId.setExpirense(userResponcse.getExpirense());

        try {
            userRepo.save(byUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("success updated ❤️👌");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public SimpleResponse deleteUser(Long userId) {

        User byUserId = userRepo.findByUserId(userId);

        try {
            userRepo.delete(byUserId);
            return SimpleResponse.builder()
                    .massage("success delete user 😏")
                    .status(HttpStatus.OK)
                    .build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder().
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .massage(e.getMessage())
                    .build();
        }

    }

}
