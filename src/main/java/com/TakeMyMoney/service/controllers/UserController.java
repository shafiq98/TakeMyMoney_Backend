package com.TakeMyMoney.service.controllers;

import com.TakeMyMoney.service.controllers.requests.AddressRequest;
import com.TakeMyMoney.service.controllers.requests.UserRequest;
import com.TakeMyMoney.service.controllers.responses.UserResponse;
import com.TakeMyMoney.service.entities.Address;
import com.TakeMyMoney.service.entities.User;
import com.TakeMyMoney.service.services.AddressService;
import com.TakeMyMoney.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    // NOTE: Function is only here for debugging
    @GetMapping(path="/allUsers")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }


    // TODO change get mapping to post
    @PostMapping
    public ResponseEntity<User> getUser(@RequestBody AddressRequest addressRequest) {
        Address address = addressService.getAddress(addressRequest.getToken());
        return ResponseEntity.ok(userService.getUser(address.getId()));
    }

    // NOTE this only returns the name of the user
    @PostMapping(path="/name")
    public ResponseEntity<UserResponse> getUserName(@RequestBody AddressRequest addressRequest) {
        Address address = addressService.getAddress(addressRequest.getToken());
        User user = userService.getUser(address.getId());
        return ResponseEntity.ok(new UserResponse(user.getName()));
    }

    @PostMapping(path="/createUser")
    public ResponseEntity<User> CreateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(UserRequestToUser(userRequest)));
    }

    private User UserRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .balance(userRequest.getBalance())
                .build();
    }

//    private UserResponse UserToUserResponse(User user){
//        return UserResponse.builder
//    }
}
