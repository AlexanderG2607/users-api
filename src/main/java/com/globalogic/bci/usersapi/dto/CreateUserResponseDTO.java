
package com.globalogic.bci.usersapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "email",
    "password",
    "phones"
})
public class CreateUserResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phones")
    private List<PhoneDTO> phones;
    @JsonProperty("created")
    private String created;
    @JsonProperty("lastLogin")
    private String lastLogin;
    @JsonProperty("token")
    private String token;
    @JsonProperty("isActive")
    private Boolean isActive;

}
