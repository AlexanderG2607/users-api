package com.globalogic.bci.usersapi.mappers;

import com.globalogic.bci.usersapi.dto.CreateUserRequestDTO;
import com.globalogic.bci.usersapi.dto.CreateUserResponseDTO;
import com.globalogic.bci.usersapi.dto.PhoneDTO;
import com.globalogic.bci.usersapi.jpa.domains.Phone;
import com.globalogic.bci.usersapi.jpa.domains.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "created", target = "created"),
            @Mapping(source = "phones", target = "phones"),
            @Mapping(source = "isActive", target = "isActive")
    })
    CreateUserResponseDTO userToCCreateUserResponseDTO(User user);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "phones", target = "phones")
    })
    User createUserRequestDTOToUser(CreateUserRequestDTO createUserRequestDTO);


    @Mappings({
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "cityCode", target = "cityCode"),
            @Mapping(source = "countryCode", target = "countryCode"),
    })
    Phone phoneDTOToPhone(PhoneDTO phoneDTO);

    @Mappings({
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "cityCode", target = "cityCode"),
            @Mapping(source = "countryCode", target = "countryCode")
    })
    PhoneDTO phoneToPhoneDTO(Phone phone);
}
