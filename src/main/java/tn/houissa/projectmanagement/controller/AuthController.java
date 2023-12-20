package tn.houissa.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.services.userservice.IUserService;
import tn.houissa.projectmanagement.services.userservice.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user) {
        User userExist = userService.getUserByEmail(user.getEmail());
        if (userExist != null) return new ResponseEntity<>("User already exists!", HttpStatusCode.valueOf(403));
        if (!userService.register(user)) return new ResponseEntity<>("Failed to create account!", HttpStatusCode.valueOf(403));
        return new ResponseEntity<>("User account created! Please verify your account.", HttpStatusCode.valueOf(201));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, Object> requestMap) {
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");

        User userExist = userService.getUserByEmail(email);
        if (userExist == null) return new ResponseEntity<>("Account not found!", HttpStatusCode.valueOf(403));
        if( !userExist.isEnabled()) return new ResponseEntity<>("Please verify your account!", HttpStatusCode.valueOf(403));
        return ResponseEntity.ok(userService.login(email,password));

    }

}
