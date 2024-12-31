package com.example.rolemanagement.controller;

import static com.example.rolemanagement.utils.LoggingUtils.getLogger;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolemanagement.dto.RoleDTO;
import com.example.rolemanagement.service.RoleService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final Gson gson;
    private static final Logger LOGGER = getLogger(RoleController.class);

    public RoleController(RoleService roleService, Gson gson) {
        this.roleService = roleService;
        this.gson = gson;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody String roleJson) {
        LOGGER.info("Received request to create role: {}", roleJson);
        RoleDTO roleDTO = gson.fromJson(roleJson, RoleDTO.class);
        RoleDTO createdRole = roleService.addRole(roleDTO);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        LOGGER.info("Received request to fetch all roles");
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        LOGGER.info("Received request to fetch role with ID: {}", id);
        RoleDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody String roleJson) {
        LOGGER.info("Received request to update role with ID: {}", id);
        RoleDTO roleDTO = gson.fromJson(roleJson, RoleDTO.class);
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        LOGGER.info("Received request to delete role with ID: {}", id);
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
