package com.example.devity.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.devity.configuration.UserInfoDetails;
import com.example.devity.model.User;
import com.example.devity.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  public List<User> find() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  public String addUser(User userInfo) {
    userInfo.setPassword(encoder.encode(userInfo.getPassword()));
    repository.save(userInfo);
    return "User Added Successfully";
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userDetail = repository.findByUsername(username);
    return userDetail.map(UserInfoDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
  }
}
