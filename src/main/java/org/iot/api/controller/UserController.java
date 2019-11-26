package org.iot.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.dto.AuthResponseDto;
import org.iot.core.entity.user.User;
import org.iot.core.security.TokenUtil;
import org.iot.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private TokenUtil tokenUtil;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, TokenUtil tokenUtil, AuthenticationManager manager){
        this.authenticationManager = manager;
        this.tokenUtil = tokenUtil;
        this.userService = userService;
    }

    @PostMapping("/users/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenUtil.createToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponseDto(1337L, user.getEmail(), token));
    }

    @PostMapping("/users/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.addUser(user)) {
            String token = tokenUtil.createToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponseDto(1337L, user.getEmail(), token));
        } else {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/user")
    public String getCurrentUser(Authentication authentication) {
        return authentication.getName();
    }
}
