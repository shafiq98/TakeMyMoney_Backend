package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.authentication.JwtTokenProvider;
import com.TakeMyMoney.service.controllers.authentication.UserContext;
import com.TakeMyMoney.service.controllers.responses.JwtResponse;
import com.TakeMyMoney.service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login/{username}")
    public ResponseEntity<JwtResponse> login(@PathVariable String username) {
        return ResponseEntity.ok(new JwtResponse("Bearer", jwtTokenProvider.generateToken(username)));
    }

    @GetMapping("/whoami")
    public ResponseEntity<User> whoami() {
        return ResponseEntity.ok(UserContext.getUser());
    }
}
