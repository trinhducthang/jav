package com.java1504.ManagerUsers.mapper;

import com.java1504.ManagerUsers.model.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.java1504.ManagerUsers.dto.BankDTO;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    @Mapping(target = "usersId", source = "users.id")
    BankDTO bankToBankDTO(Bank bank);
    Bank bankDTOToBank(BankDTO bankDTO);
}
