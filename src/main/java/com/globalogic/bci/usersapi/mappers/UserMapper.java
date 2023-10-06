package com.globalogic.bci.usersapi.mappers;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.dto.PhoneDTO;
import com.globalogic.bci.usersapi.jpa.domains.Phone;
import com.globalogic.bci.usersapi.jpa.domains.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "phones", target = "phones")
    @Mapping(source = "lastLogin", target = "lastLogin")
    @Mapping(source = "isActive", target = "isActive", defaultValue = "true")
    CreateUserResponseDTO userToCreateUserResponseDTO(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phones", target = "phones")
    User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO);


    @Mapping(source = "number", target = "number")
    @Mapping(source = "cityCode", target = "cityCode")
    @Mapping(source = "countryCode", target = "countryCode")
    Phone phoneDTOToPhone(PhoneDTO phoneDTO);

    @Mapping(source = "number", target = "number")
    @Mapping(source = "cityCode", target = "cityCode")
    @Mapping(source = "countryCode", target = "countryCode")
    PhoneDTO phoneToPhoneDTO(Phone phone);
}
