package com.fbk.users.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbk.users.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
