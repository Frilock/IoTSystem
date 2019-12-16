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
@RequestMapping("/api/users")
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

    @PostMapping("/signin")
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

    //TODO: заменить входной параметр с entity user на dto (userDto)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        log.info("User" + user.getPassword());
        if(userService.addUser(user)) {
            String token = tokenUtil.createToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponseDto(1337L, user.getEmail(), token));
        } else {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Метод автоматического входа после повторного захода
     * @param authentication
     * @return
     */
    @GetMapping("/users/user")
    public String getCurrentUser(Authentication authentication) {
        return authentication.getName();
    }
}
