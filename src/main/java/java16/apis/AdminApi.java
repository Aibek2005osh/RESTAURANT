package java16.apis;

import jakarta.validation.Valid;
import java16.dto.request.CreateAdminUserDTO;
import java16.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminApi {
private final AdminService adminService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUserByAdmin(@RequestBody @Valid   CreateAdminUserDTO createAdminUserDTO) {

        return adminService.createUserByAdmin(createAdminUserDTO);

    }

}
