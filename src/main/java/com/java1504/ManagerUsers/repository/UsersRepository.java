package com.java1504.ManagerUsers.repository;


import com.java1504.ManagerUsers.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.java1504.ManagerUsers.model.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByNameAndAddressAndDob(String name, String address, String dob);
    Optional<Users> findByUsername(String username);
}
