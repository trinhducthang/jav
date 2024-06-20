package com.java1504.ManagerUsers.dto;
import com.java1504.ManagerUsers.ultil.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;

    private String username;

    private String password;

    @NotBlank(message = "The name cannot leave empty")
    private String name;

    @NotNull
    private String dob;

    @NotNull
    private Gender gender;

    @NotNull
    private String phone;

    @Email(message = "Please config your email!")
    private String email;

    @NotNull
    private String address;

}
