package java16.service;


import java16.dto.request.CreateAdminUserDTO;
import java16.dto.request.UserRegisterDTO;
import java16.dto.response.SimpleResponse;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<?> createUserByAdmin(CreateAdminUserDTO createAdminUserDTO);


}
