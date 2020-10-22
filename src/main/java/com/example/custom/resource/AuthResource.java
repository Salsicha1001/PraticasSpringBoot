package com.example.custom.resource;


import com.example.custom.dto.EmailDTO;
import com.example.custom.security.JWTUtil;
import com.example.custom.security.UserSS;
import com.example.custom.service.AuthService;
import com.example.custom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;
   @Autowired
    private AuthService service;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer "+ token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO response){
        service.sendNewPassword(response.getEmail());
        return ResponseEntity.noContent().build();
    }

}
