package java16.apis;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java16.dto.request.CreateAdminUserDTO;
import java16.dto.request.UserRegisterDTO;
import java16.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminApi {

private final AdminService adminService;


    @PostMapping("/create-user")
    public ResponseEntity<?> createUserByAdmin(@RequestBody CreateAdminUserDTO createAdminUserDTO) {
        return adminService.createUserByAdmin(createAdminUserDTO);
    }
}
