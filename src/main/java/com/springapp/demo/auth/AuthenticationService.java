package com.springapp.demo.auth;

import com.springapp.demo.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springapp.demo.users.User;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationRepository repository;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = new User(request.username(), passwordEncoder.encode(request.password()));

        this.repository.register(user);

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        Optional<User> user = this.repository.getUserByUsername(request.username());

        user.orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user.get());

        return new AuthenticationResponse(jwtToken);
    }
}
