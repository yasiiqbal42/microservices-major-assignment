package com.userservice.repository;

import com.userservice.entity.Login;
import com.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUserName(String userName);
}
