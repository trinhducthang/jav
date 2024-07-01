package com.java1504.ManagerUsers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private String bankName;
    private String bankNumber;
    private int usersId;
    private long balance;
}
