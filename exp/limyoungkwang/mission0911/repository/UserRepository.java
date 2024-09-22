package com.example.restapi_ex.repository;

import com.example.restapi_ex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
