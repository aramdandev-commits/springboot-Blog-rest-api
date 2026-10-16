package com.springboot.blog.controller;

import com.springboot.blog.payload.JwtResponseDto;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    // Build Login REST API
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtResponseDto> login (@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JwtResponseDto response = new JwtResponseDto();
        response.setAccessToken(token);
        System.out.println(passwordEncoder.encode("abc"));
        System.out.println(passwordEncoder.encode("abc"));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register (@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
