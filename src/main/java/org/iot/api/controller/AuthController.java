package org.iot.api.controller;

import org.iot.core.entity.User;
import org.iot.core.security.TokenUtil;
import org.iot.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.addUser(user)) {
            String token = tokenUtil.createToken(user.getUsername());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(TokenUtil.getTOKEN_HEADER(), TokenUtil.getTOKEN_PREFIX() + token);
            return ResponseEntity.ok().headers(responseHeaders).body("User registered successfully");
        } else {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
    }
}
