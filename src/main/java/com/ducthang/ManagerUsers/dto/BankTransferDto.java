package com.ducthang.ManagerUsers.dto;

import lombok.Data;

@Data
public class BankTransferDto {
    public String nameSource;
    public String nameDestination;
    public int amount;
    public String bankNumberSource;
    public String bankNumberDestination;
}
