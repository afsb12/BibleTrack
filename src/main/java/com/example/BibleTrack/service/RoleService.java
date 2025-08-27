package com.example.BibleTrack.service;

import com.example.BibleTrack.dto.Role.RoleRequest;
import com.example.BibleTrack.dto.Role.RoleResponse;
import com.example.BibleTrack.entity.Role;
import com.example.BibleTrack.repository.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, ObjectMapper objectMapper){
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
    }

    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());

        Role saved = roleRepository.save(role);

        return toResponse(saved);
    }

    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> users =  roleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return users;
    }

    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

        return toResponse(role);
    }

    public RoleResponse updateRole(Long id, RoleRequest roleRequest) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        Role saved = roleRepository.save(role);
        return toResponse(saved);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    private RoleResponse toResponse(Role role) {
        RoleResponse res = new RoleResponse();
        res.setId(role.getId());
        res.setName(role.getName());
        res.setDescription(role.getDescription());
        return res;
    }
}
