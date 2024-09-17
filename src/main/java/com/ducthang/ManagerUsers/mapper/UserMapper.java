package com.ducthang.ManagerUsers.mapper;


import com.ducthang.ManagerUsers.dto.UserDTO;
import com.ducthang.ManagerUsers.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Khong can tao instance nhu the nay
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(Users user);

    Users userDTOToUser(UserDTO userDTO);
}