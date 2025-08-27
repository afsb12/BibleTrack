package com.example.BibleTrack.repository;

import com.example.BibleTrack.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName(String Name);
}
