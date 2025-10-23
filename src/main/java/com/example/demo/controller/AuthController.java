package com.example.demo.controller;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jstsUtils;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody EmployeeEntity employee){
        try{
            Authentication authprovide = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getUsername(),
                    employee.getPassword()));

            UserDetails userDetails = (UserDetails) authprovide.getPrincipal();

            String token = jstsUtils.getJwtTokenGeneration(userDetails);
            return ResponseEntity.ok(Map.of("token",token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","not matched Data"));
        }



    }

}
