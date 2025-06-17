package com.maville.Controller;

import com.maville.dto.LoginRequest;
import com.maville.dto.LoginResponse;
import com.maville.dto.SignUpRequest;
import com.maville.Model.User;
import com.maville.service.UserAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserAuthenticator userAuthenticator;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userAuthenticator.login(email, password);

        if (user != null) {
            // You can generate a JWT or dummy token here
            LoginResponse response = new LoginResponse(true, "Login successful", user.getEmail());
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse(false, "Invalid email or password", null);
            return ResponseEntity.status(401).body(response);
        }
    }

    // @Autowired
    // private AccountController accountController;

}