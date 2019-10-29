package org.iot.core.service;

import org.iot.core.entity.Access;
import org.iot.core.entity.User;
import org.iot.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        //TODO: store roles in db



        Set<GrantedAuthority> roles = new HashSet<>();
        // TODO : thinking about it ....
        roles.add(new SimpleGrantedAuthority(new Access().getLevel().);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                roles);
    }

    public boolean addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }
}
