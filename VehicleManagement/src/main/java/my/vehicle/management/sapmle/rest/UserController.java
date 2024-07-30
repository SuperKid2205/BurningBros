package my.vehicle.management.sapmle.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.vehicle.management.sapmle.entity.User;
import my.vehicle.management.sapmle.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User theUser = userService.getUserLogin(username);
        return ResponseEntity.ok("Logged in user: "+ theUser.getId() + " " + username);
    }
    
    @PostMapping("/users")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerNewUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
