package com.java1504.ManagerUsers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.java1504.ManagerUsers.model.Users;
import org.springframework.stereotype.Repository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
