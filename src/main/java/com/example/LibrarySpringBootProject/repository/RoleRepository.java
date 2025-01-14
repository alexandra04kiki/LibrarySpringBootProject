package com.example.LibrarySpringBootProject.repository;


import com.example.LibrarySpringBootProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}