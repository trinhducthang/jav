package com.java1504.ManagerUsers.dto;
import com.java1504.ManagerUsers.ultil.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String dob;
    private Gender gender;
    private String phone;
    private String email;
    private String address;

//    private String idbank;
//    private String nameofbank;


}
