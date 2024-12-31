package com.example.rolemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.rolemanagement.dto.RoleDTO;
import com.example.rolemanagement.model.Role;
import com.example.rolemanagement.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    // Create a new role
    public RoleDTO createRole(RoleDTO roleDTO) {
        LOGGER.info("Creating new role: {}", roleDTO);
        Role role = modelMapper.map(roleDTO, Role.class);  // Convert DTO to Entity
        Role savedRole = roleRepository.save(role);  // Save entity to DB
        LOGGER.info("Role created successfully: {}", savedRole);
        return modelMapper.map(savedRole, RoleDTO.class);  // Convert saved entity back to DTO
    }

    // Add a new role
    public RoleDTO addRole(RoleDTO roleDTO) {
        LOGGER.info("Adding role: {}", roleDTO);
        Role role = modelMapper.map(roleDTO, Role.class);  // Convert DTO to Entity
        Role savedRole = roleRepository.save(role);  // Save entity to DB
        LOGGER.info("Role added successfully: {}", savedRole);
        return modelMapper.map(savedRole, RoleDTO.class);  // Convert saved entity back to DTO
    }

    // Get all roles
    public List<RoleDTO> getAllRoles() {
        LOGGER.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        LOGGER.info("Fetched {} roles", roles.size());
        return roles.stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))  // Convert each entity to DTO
                .collect(Collectors.toList());
    }

    // Get role by ID
    public RoleDTO getRoleById(Long id) {
        LOGGER.info("Fetching role with ID: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
        LOGGER.info("Fetched role: {}", role);
        return modelMapper.map(role, RoleDTO.class);  // Convert entity to DTO
    }

    // Update role details
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        LOGGER.info("Updating role with ID: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        // Update entity fields from DTO
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        Role updatedRole = roleRepository.save(role);  // Save updated entity to DB
        LOGGER.info("Role updated successfully: {}", updatedRole);
        return modelMapper.map(updatedRole, RoleDTO.class);  // Convert updated entity back to DTO
    }

    // Delete a role by ID
    public void deleteRole(Long id) {
        LOGGER.info("Deleting role with ID: {}", id);
        try {
            roleRepository.deleteById(id);  // Delete role from DB
            LOGGER.info("Role deleted successfully");
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting role with ID: {}", id, e);
            throw new RuntimeException("Failed to delete role with ID: " + id, e);
        }
    }
}
