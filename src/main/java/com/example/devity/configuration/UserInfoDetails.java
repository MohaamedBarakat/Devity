package com.example.devity.configuration;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.devity.model.User;

public class UserInfoDetails implements UserDetails {

  private String name;
  private String password;
  private List<GrantedAuthority> authorities;

  private final static String BLOCKED_USER_NAME = "Darth Vader";

  public UserInfoDetails(User userInfo) {
    name = userInfo.getUsername();
    password = userInfo.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    if (Objects.equals(name, BLOCKED_USER_NAME)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
