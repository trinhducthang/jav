package com.java1504.ManagerUsers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private int id;
    private String nameBank;
    private String numberBank;
    private int usersId;
}
