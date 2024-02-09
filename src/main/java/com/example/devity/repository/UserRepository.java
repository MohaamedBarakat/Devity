package com.example.devity.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.devity.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String name);
}
