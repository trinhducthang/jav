package com.ducthang.ManagerUsers.mapper;

import com.ducthang.ManagerUsers.model.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.ducthang.ManagerUsers.dto.BankDTO;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    @Mapping(target = "usersId", source = "users.id")
    BankDTO bankToBankDTO(Bank bank);
    Bank bankDTOToBank(BankDTO bankDTO);
}
