package com.java1504.ManagerUsers.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.java1504.ManagerUsers.InfoBase.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
