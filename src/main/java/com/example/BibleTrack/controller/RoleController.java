package com.example.BibleTrack.controller;

import com.example.BibleTrack.dto.Role.RoleRequest;
import com.example.BibleTrack.dto.Role.RoleResponse;
import com.example.BibleTrack.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @Autowired
    public RoleController(RoleService roleService, ObjectMapper objectMapper) {
        this.roleService = roleService;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    public RoleResponse createUser(@RequestBody RoleRequest RoleRequest) {
        System.out.println(RoleRequest);
        return roleService.createRole(RoleRequest);
    }

    @GetMapping()
    public ResponseEntity<String> listUsers() {
        try{
            var roles =  roleService.getAllRoles();
            String json = objectMapper.writeValueAsString(roles);
            return ResponseEntity.ok(json);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(
    value = "/{id}")
    public RoleResponse getUser(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public RoleResponse updateUser(@PathVariable Long id,@RequestBody RoleRequest roleRequest) {
        return roleService.updateRole(id, roleRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
