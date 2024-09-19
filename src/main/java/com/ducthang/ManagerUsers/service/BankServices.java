package com.ducthang.ManagerUsers.service;

import com.ducthang.ManagerUsers.dto.BankTransferDto;
import com.ducthang.ManagerUsers.model.Bank;
import com.ducthang.ManagerUsers.dto.BankDTO;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankServices {

    @PostAuthorize("returnObject.users.username == authentication.name")
    public Bank addBank(Bank bank, int id);

    public List<BankDTO> getBanks();

    public Bank updateBank(BankDTO bankDTO, int id);

    public Bank bankTransaction(String source, String destination, long amount);

    @PostAuthorize("returnObject[0].users.username == authentication.name")
    public List<Bank> getBankByUser(Integer id);

    public boolean TransferOtherBank(BankTransferDto request);

    public Bank GetBank(String bankName, String bankNumber);
}
