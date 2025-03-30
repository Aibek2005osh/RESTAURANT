package java16.apis;

import java16.dto.request.UserLoginDTO;
import java16.dto.request.UserRegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

       @PostMapping("/register")
    public SimpleResponse register(@RequestBody UserRegisterDTO userRegisterDTO){

       return authService.register(userRegisterDTO);
    }

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.login(userLoginDTO);


    }
}
