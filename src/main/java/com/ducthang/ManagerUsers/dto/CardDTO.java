package com.ducthang.ManagerUsers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    private String cardNumber;
    private String cvv;
    private LocalDateTime createdAt;
    private LocalDateTime expiryDate;
    private Integer usersId;
}
