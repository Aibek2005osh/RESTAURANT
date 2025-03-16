package java16.service;


import java16.dto.response.SimpleResponse;
import java16.dto.response.UserResponcse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {


    List<UserResponcse> getAllUser();

    UserResponcse findByUserId(Long id);

    ResponseEntity<?> updateUser(Long userId, UserResponcse userResponcse);

    SimpleResponse deleteUser(Long userId);
}
