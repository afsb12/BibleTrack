package com.example.BibleTrack.repository;


import com.example.BibleTrack.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    List<UserAccount> findByEmail(String email);
}
