package com.example.rolemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rolemanagement.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName(String name);

    List<Role> findByDescription(String description);
    
}
