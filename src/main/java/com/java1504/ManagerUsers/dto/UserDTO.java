package com.java1504.ManagerUsers.dto;
import com.java1504.ManagerUsers.enums.Role;
import com.java1504.ManagerUsers.ultil.Gender;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;

    private String name;


    private String dob;

    private Gender gender;

    private String phone;

    private String email;

    private String address;

    private Role role;
}
