package com.ducthang.ManagerUsers.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private String name;
    private String bankNumber;
    private int usersId;
    private long balance;
    private LocalDateTime createdAt;
}
