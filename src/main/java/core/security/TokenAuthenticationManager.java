package core.security;

import core.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return processAuthentication((UsernamePasswordAuthenticationToken) authentication);
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }
        } catch (Exception ex) {
            if (ex instanceof AuthenticationServiceException)
                throw ex;
        }
        return null;
    }

    private UsernamePasswordAuthenticationToken processAuthentication(UsernamePasswordAuthenticationToken userAuthentication) {
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(userAuthentication.getName());
        } catch (UsernameNotFoundException e) {
            log.error("Wrong login or password");
            throw new AuthenticationServiceException("Wrong login or password");
        }

        String plainPassword = (String) userAuthentication.getCredentials();
        String dbPassword = user.getPassword();
        if (!passwordEncoder.matches(plainPassword, dbPassword)) {
            log.error("Wrong login or password");
            throw new AuthenticationServiceException("Wrong login or password");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
    }
}
