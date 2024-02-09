package com.example.devity.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationContext {

  public static Authentication getAuthContext() {
    return SecurityContextHolder.getContext().getAuthentication();

  }
}
