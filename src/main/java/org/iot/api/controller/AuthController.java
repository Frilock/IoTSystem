package org.iot.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.iot.core.entity.User;
import org.iot.core.security.TokenUtil;
import org.iot.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenUtil.createToken(user.getEmail());
        log.info(user.toString());
        return ResponseEntity
                .ok()
                .header(TokenUtil.getTOKEN_HEADER(), TokenUtil.getTOKEN_PREFIX() + token)
                .body("Success");
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if(userService.addUser(user)) {
            String token = tokenUtil.createToken(user.getEmail());
            return ResponseEntity
                    .ok()
                    .header(TokenUtil.getTOKEN_HEADER(), TokenUtil.getTOKEN_PREFIX() + token)
                    .body("Success");
        } else {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
    }
}
