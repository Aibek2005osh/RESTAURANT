package java16.apis;

import java16.dto.response.SimpleResponse;
import java16.dto.response.UserResponcse;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping("/findAllUser")
    public List<UserResponcse>  getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/findByUserId/{userId}")
    public UserResponcse findByUserId(@PathVariable Long userId) {
        return userService.findByUserId(userId);

    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserResponcse userResponcse) {
        return userService.updateUser(userId, userResponcse);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public SimpleResponse deleteUser(@PathVariable Long userId){
       return userService.deleteUser(userId);
    }

    @GetMapping
    public String test() {
        return "hello";
    }
    g
    @GetMapping
    public String test2() {
        return "hello2";
    }
}
