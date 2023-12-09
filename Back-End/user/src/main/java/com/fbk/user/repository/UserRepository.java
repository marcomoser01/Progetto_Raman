package com.fbk.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);

}
