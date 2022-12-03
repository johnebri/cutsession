package com.johnebri.cutsession.controller;

import com.johnebri.cutsession.dto.clients.GetClientRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantRequest;
import com.johnebri.cutsession.dto.register.RegisterMerchantResponse;
import com.johnebri.cutsession.dto.register.RegisterUserRequest;
import com.johnebri.cutsession.dto.register.RegisterUserResponse;
import com.johnebri.cutsession.dto.signin.SigninRequest;
import com.johnebri.cutsession.dto.signin.SigninResponse;
import com.johnebri.cutsession.model.UserTypeEnum;
import com.johnebri.cutsession.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author John on 11/29/22
 */

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/users")
    public ResponseEntity<RegisterUserResponse> registerUsers(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register/merchants")
    public ResponseEntity<RegisterMerchantResponse> registerMerchants(@RequestBody RegisterMerchantRequest request) {
        RegisterMerchantResponse response = userService.registerMerchant(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SigninResponse> signIn(@RequestBody SigninRequest request) throws Exception {
        SigninResponse response = userService.signin(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<Object> clients(@RequestParam(required = false) int limit,
                                          @RequestParam(required = false) int offset,
                                          @RequestParam(required = false) UserTypeEnum type,
                                          @RequestParam(required = false) String city,
                                          @RequestParam(required = false) String name) {
        GetClientRequest request = GetClientRequest.builder()
                .limit(limit)
                .offset(offset)
                .type(type)
                .city(city)
                .name(name)
                .build();

        Object list = userService.getClients(request);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
