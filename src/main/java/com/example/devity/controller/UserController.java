package com.example.devity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devity.model.User;
import com.example.devity.service.JwtService;
import com.example.devity.service.UserInfoService;

@RestController
@RequestMapping("auth")
public class UserController {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UserInfoService service;

  @Autowired
  private AuthenticationManager authenticationManager;

  @GetMapping("/user")
  public List<User> user() {
    return service.find();
  }

  @PostMapping("/addNewUser")
  public String addNewUser(@RequestBody User userInfo) {
    return service.addUser(userInfo);
  }

  @PostMapping(value = "/generateToken")
  public String authenticateAndGetToken(@RequestBody User authRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(authRequest.getUsername());
    } else {
      throw new UsernameNotFoundException("invalid user request !");
    }
  }

}
