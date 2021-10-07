package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.requests.UserRequest;
import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService = new UserService();

    @GetMapping
    public ResponseEntity<User> WhoAmI() {
        User tempUser = userService.createUser();

        return ResponseEntity.ok(tempUser);
    };

    @GetMapping(path = "/UserToAddress")
    public ResponseEntity<User> GetUserByAddress(@RequestBody Address requestAddress) {
        User tempUser = User.builder()
                .name("Shafiq")
                .balance(new BigDecimal(1500))
                .build();

        return ResponseEntity.ok(tempUser);
    };

    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(UserRequestToUser(userRequest)));
    }

    private User UserRequestToUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .balance(userRequest.getBalance())
                .build();
    }
}
