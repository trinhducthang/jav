package com.java1504.ManagerUsers.mapper;

import com.java1504.ManagerUsers.dto.UserDTO;
import com.java1504.ManagerUsers.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(Users user);
    Users userDTOToUser(UserDTO userDTO);
}

