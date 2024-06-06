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

    @NotBlank(message = "The name cannot leave empty")
    private String name;

    @NotNull
    private String dob;

    @NotNull
    private Gender gender;

    @NotNull
    private String phone;

    @Email(message = "Vui long nhap dung dinh dang email")
    private String email;

    @NotNull
    private String address;

}
