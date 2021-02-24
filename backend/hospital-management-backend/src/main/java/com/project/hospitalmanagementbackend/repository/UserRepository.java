package com.project.hospitalmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
