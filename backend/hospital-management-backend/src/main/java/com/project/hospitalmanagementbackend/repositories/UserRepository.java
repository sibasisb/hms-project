package com.project.hospitalmanagementbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hospitalmanagementbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
