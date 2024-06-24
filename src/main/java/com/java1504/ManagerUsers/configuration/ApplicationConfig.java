package com.java1504.ManagerUsers.configuration;

import com.java1504.ManagerUsers.enums.Role;
import com.java1504.ManagerUsers.model.Users;
import com.java1504.ManagerUsers.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UsersRepository usersRepository){
        return args -> {
            if(usersRepository.findByUsername("admin").isEmpty()){
                Role adminRole = Role.ADMIN;
                Users users = Users.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(adminRole)
                        .build();
                usersRepository.save(users);
                log.info("ADMIN saved successfully");
            }
        };
    }
}
