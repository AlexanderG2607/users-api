package com.globalogic.bci.usersapi.mappers;

import com.globalogic.bci.usersapi.dto.PhoneDTO;
import com.globalogic.bci.usersapi.jpa.domains.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

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
